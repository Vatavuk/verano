package hr.com.vgv.verano;

import java.util.HashMap;
import java.util.Map;
import org.cactoos.map.MapEnvelope;

/**
 *
 */
public class VrContainer extends MapEnvelope<String, Components<?>> {

    private static final Map<String, Components<?>> MAP =
        new HashMap<>(0);

    public VrContainer() {
        super(() -> VrContainer.MAP);
    }

    public VrContainer(final String namespace, final Components<?> cmps) {
        super(
            () -> {
                if (!VrContainer.MAP.containsKey(namespace)) {
                    VrContainer.MAP.put(namespace, cmps);
                }
                return VrContainer.MAP;
            }
        );
    }
}
