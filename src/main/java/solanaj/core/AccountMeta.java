package solanaj.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountMeta {

    private PublicKey publicKey;

    private boolean isSigner;

    private boolean isWritable;
}