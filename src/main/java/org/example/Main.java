package org.example;

import java.io.*;

import solanaj.core.Account;
import solanaj.core.PublicKey;
import solanaj.core.Transaction;
import solanaj.programs.SystemProgram;
import solanaj.rpc.Cluster;
import solanaj.rpc.RpcClient;

public class Main {

    public static void main(String[] args) {
        DoTransaction("/path/to/keypair.json", "ATKcvtbtgXExHd3xZqb97M3Ab1jg2NZ8ztL8fCRJU2N1", 1000000);
    }

    /**
     * prepare and send transaction
     * @param TargetAddress target solana wallet (base58 string)
     * @param lamports amount of lamports to send
     */
    public static void DoTransaction(String PathToKeypair, String TargetAddress, int lamports) {

        // get Account from keypair file
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(PathToKeypair);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String data0 = resultStringBuilder.toString();
        String data = data0.substring(1,data0.length()-2);
        String[] dataX = data.split(",");
        byte[] whole_key = new byte[64];
        for (int i = 0; i < 64; i++) {
            whole_key[i] = (byte)Integer.parseInt(dataX[i]);
        }
        Account signer = new Account(whole_key);


        PublicKey toPublicKey = new PublicKey(TargetAddress);

        // select cluster
        RpcClient client = new RpcClient(Cluster.MAINNET);

        // prepare transaction
        Transaction transaction = new Transaction();
        transaction.addInstruction(SystemProgram.transfer(signer.getPublicKey(), toPublicKey, lamports));

        String signature = client.getApi().sendTransaction(transaction, signer);
        System.out.println("tx signature: " + signature);
    }
}