package solanaj.rpc;

import java.io.IOException;
import java.util.*;
import solanaj.core.Account;
import solanaj.core.Transaction;

import solanaj.rpc.types.*;
import solanaj.rpc.types.config.RpcSendTransactionConfig;
import solanaj.rpc.types.config.Commitment;

public class RpcApi {
    private RpcClient client;

    public RpcApi(RpcClient client) {
        this.client = client;
    }

    public String getRecentBlockhash() {
        return getRecentBlockhash(null);
    }

    public String getRecentBlockhash(Commitment commitment) {
        List<Object> params = new ArrayList<>();

        if (null != commitment) {
            params.add(Map.of("commitment", commitment.getValue()));
        }

        try {
            return client.call("getRecentBlockhash", params, RecentBlockhash.class).getValue().getBlockhash();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String sendTransaction(Transaction transaction, Account signer) {
        return sendTransaction(transaction, Collections.singletonList(signer), null);
    }

    public String sendTransaction(Transaction transaction, List<Account> signers, String recentBlockHash) {
        if (recentBlockHash == null) {
            recentBlockHash = getRecentBlockhash();
        }
        transaction.setRecentBlockHash(recentBlockHash);
        transaction.sign(signers);
        byte[] serializedTransaction = transaction.serialize();

        String base64Trx = Base64.getEncoder().encodeToString(serializedTransaction);

        List<Object> params = new ArrayList<Object>();

        params.add(base64Trx);
        params.add(new RpcSendTransactionConfig());

        try {
            return client.call("sendTransaction", params, String.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    /**
     * Returns a list of confirmed blocks between two slots
     * DEPRECATED: use getBlocks instead
     */
    @Deprecated
    public List<Double> getConfirmedBlocks(Integer start, Integer end) {
        List<Object> params;
        params = (end == null ? Arrays.asList(start) : Arrays.asList(start, end));
        try {
            return this.client.call("getConfirmedBlocks", params, List.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Returns a list of confirmed blocks between two slots
     * DEPRECATED: use getBlocks instead
     */
    @Deprecated
    public List<Double> getConfirmedBlocks(Integer start) {
        return this.getConfirmedBlocks(start, null);
    }

}
