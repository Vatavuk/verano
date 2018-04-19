package hr.com.vgv.verano;

import java.util.stream.StreamSupport;
import org.cactoos.iterable.IterableOf;

/**
 * <p>
 * <b>Title: ComponentOf </b>
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
 * PA1 28.3.2018.
 * @since 28.3.2018.
 */

public final class VrComponent<T> implements Component<T>{

    private final Instance<T> instance;

    private final Iterable<Condition> conditions;

    public VrComponent(final Instance<T> instance, final Condition... conditions) {
        this(instance, new IterableOf<>(conditions));
    }

    public VrComponent(final Instance<T> instance, final Iterable<Condition> conditions) {
        this.instance = instance;
        this.conditions = conditions;
    }

    @Override
    public boolean isActive(final AppContext context) {
        return StreamSupport.stream(this.conditions.spliterator(), false)
            .anyMatch(condition -> condition.check(context));
    }

    @Override
    public T instance() {
        return this.instance.value();
    }
}
