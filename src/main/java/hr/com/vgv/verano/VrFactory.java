package hr.com.vgv.verano;

import java.util.Map;

/**
 * <p>
 * <b>Title: CmFactory </b>
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
 * PA1 10.4.2018.
 * @since 10.4.2018.
 */

public abstract class VrFactory<T> implements Factory<T> {

    private final String namespace;

    private final AppContext context;

    private final Map<String, Components> container;

    public VrFactory(final AppContext ctx, final Component<T>... components) {
        this(ctx, new VrComponents(components));
    }

    private VrFactory(final AppContext ctx, final Components components) {
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
