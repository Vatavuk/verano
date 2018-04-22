package hr.com.vgv.verano;

import java.util.Map;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class VrInstancePool<T> implements InstancePool<T> {

    private final Map<String, Components<?>> map;
    

    @Override
    public T get(final AppContext context) {
        throw new UnsupportedOperationException("#get()");
    }
}
