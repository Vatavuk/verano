package hr.com.vgv.verano;

import org.cactoos.Scalar;
import org.cactoos.func.SolidFunc;
import org.cactoos.func.UncheckedFunc;


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
