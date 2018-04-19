package hr.com.vgv.verano;

import org.cactoos.Scalar;
import org.cactoos.func.SolidFunc;
import org.cactoos.func.UncheckedFunc;

/**
 * <p>
 * <b>Title: Singleton </b>
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
 * PA1 7.2.2018.
 * @since 7.2.2018.
 */

public final class VrCached<T> implements Instance<T> {

    private final UncheckedFunc<Scalar<T>, T>  cached =
        new UncheckedFunc<>(
            new SolidFunc<Scalar<T>, T>(
                Scalar::value
            )
        );

    private final Scalar<T> scalar;

    public VrCached(final Scalar<T> instance) {
        this.scalar = instance;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T value() {
        return this.cached.apply(this.scalar);
    }
}
