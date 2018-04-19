package hr.com.vgv.verano;

import java.util.stream.StreamSupport;
import org.cactoos.iterable.IterableOf;


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
