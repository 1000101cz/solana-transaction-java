package solanaj.rpc.types.config;

import com.squareup.moshi.Json;

public class RpcSendTransactionConfig {

    public enum Encoding {
        base64("base64");

        private String enc;

        Encoding(String enc) {
            this.enc = enc;
        }

        public String getEncoding() {
            return enc;
        }

    }

    @Json(name = "encoding")
    private Encoding encoding = Encoding.base64;

    @Json(name ="skipPreflight")
    private boolean skipPreFlight = true;

}
