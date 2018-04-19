package hr.com.vgv.verano;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
public final class VrComponents implements Components {

    private final Iterable<Component<Object>> components;

    @SuppressWarnings({"unchecked", "varargs"})
    public <T> VrComponents(final Component<T>... cmps) {
        this(Arrays.asList(cmps));
    }

    @SuppressWarnings("unchecked")
    public <T> VrComponents(final Iterable<Component<T>> cmps) {
        this.components = StreamSupport.stream(cmps.spliterator(), false)
            .map(cmp -> (Component<Object>) cmp).collect(Collectors.toList());
    }

    @Override
    public Component<Object> get(final AppContext context) {
        return StreamSupport.stream(this.components.spliterator(), false)
            .filter(component -> component.isActive(context))
            .findFirst().get();
    }
}
