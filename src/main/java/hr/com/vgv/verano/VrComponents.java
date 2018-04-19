package hr.com.vgv.verano;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * <p>
 * <b>Title: Components </b>
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
 * PA1 20.2.2018.
 * @since 20.2.2018.
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
