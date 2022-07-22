package solanaj.rpc.types;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeightedEndpoint {

    private String url;
    private Integer weight;

}
