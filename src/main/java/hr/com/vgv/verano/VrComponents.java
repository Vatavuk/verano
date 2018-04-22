package hr.com.vgv.verano;

import java.util.Arrays;
import java.util.stream.StreamSupport;

/**
 *
 */
public final class VrComponents<T> implements Components<T> {

    private final Iterable<Component<T>> components;

    @SuppressWarnings({"unchecked", "varargs"})
    public VrComponents(final Component<T>... cmps) {
        this(Arrays.asList(cmps));
    }

    @SuppressWarnings("unchecked")
    public VrComponents(final Iterable<Component<T>> cmps) {
        this.components = cmps;
    }

    @Override
    public Component<T> get(final AppContext context) {
        return StreamSupport.stream(this.components.spliterator(), false)
            .filter(component -> component.isActive(context))
            .findFirst().get();
    }
}
