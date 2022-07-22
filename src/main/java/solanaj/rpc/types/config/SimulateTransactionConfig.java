package solanaj.rpc.types.config;

import java.util.Map;

import lombok.Setter;
import solanaj.rpc.types.config.RpcSendTransactionConfig.Encoding;

import com.squareup.moshi.Json;

@Setter
public class SimulateTransactionConfig {

    @Json(name = "encoding")
    private Encoding encoding = Encoding.base64;

    @Json(name = "accounts")
    private Map accounts = null;

    @Json(name = "commitment")
    private String commitment = "finalized";

    @Json(name = "sigVerify")
    private Boolean sigVerify = false;

    @Json(name = "replaceRecentBlockhash")
    private Boolean replaceRecentBlockhash = false;
}