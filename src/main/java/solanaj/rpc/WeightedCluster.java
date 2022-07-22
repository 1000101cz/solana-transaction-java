package solanaj.rpc;

import lombok.*;
import solanaj.rpc.types.WeightedEndpoint;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeightedCluster {

    List<WeightedEndpoint> endpoints;

}
