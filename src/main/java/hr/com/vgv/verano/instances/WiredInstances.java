package hr.com.vgv.verano.instances;

import hr.com.vgv.verano.Instance;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.cactoos.map.MapEnvelope;

/**
 * Container of wired instances.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class WiredInstances extends MapEnvelope<String, Instance<?>> {

    /**
     * Map of wired instances.
     */
    private static final Map<String, Instance<?>> MAP =
        new ConcurrentHashMap<>(0);

    /**
     * Ctor.
     * @param namespace Namespace
     * @param instances Instances
     * @param <T> Input type
     */
    public <T> WiredInstances(final String namespace,
        final Iterable<Instance<T>> instances) {
        super(() -> {
            WiredInstances.MAP.put(namespace, new WiredInstance<>(instances));
            return WiredInstances.MAP;
        });
    }
}
