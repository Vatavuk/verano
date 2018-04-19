package hr.com.vgv.verano;

import java.util.HashMap;
import java.util.Map;
import org.cactoos.map.MapEnvelope;

/**
 * <p>
 * <b>Title: ComponentContainer </b>
 * </p>
 * <p>
 * <b> Description:
 * </b>
 * </p>
 * <p>
 * <b>Copyright:(</b> Copyright (c) ETK 2017
 * </p>
 * <p>
 * <b>Company:(</b> Ericsson Nikola Tesla d.d.
 * </p>
 * @author evedvat
 * @version PA1
 * <p>
 * <b>Version History:(</b>
 * </p>
 * <br>
 * PA1 29.3.2018.
 * @since 29.3.2018.
 */

public class VrContainer extends MapEnvelope<String, Components> {

    private static final Map<String, Components> MAP =
        new HashMap<>(0);

    public VrContainer() {
        super(() -> VrContainer.MAP);
    }

    public VrContainer(final String namespace, final Components cmps) {
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
