package hr.com.vgv.verano;

import java.util.Map;

/**
 *
 * @param <T>
 */
public abstract class VrFactory<T> implements Factory<T> {

    private final String namespace;

    private final AppContext context;

    private final Map<String, Components<?>> container;

    public VrFactory(final AppContext ctx, final Component<T>... components) {
        this(ctx, new VrComponents<>(components));
    }

    private VrFactory(final AppContext ctx, final Components<T> components) {
        this.context = ctx;
        this.namespace = this.getClass().getName();
        this.container = new VrContainer(this.namespace, components);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final T instance() {
        return (T) this.container.get(this.namespace)
            .get(this.context).instance();
    }
}
