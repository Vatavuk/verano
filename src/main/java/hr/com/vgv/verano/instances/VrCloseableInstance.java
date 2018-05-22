package hr.com.vgv.verano.instances;

import hr.com.vgv.verano.Wire;
import hr.com.vgv.verano.props.RefreshableScalar;
import java.io.Closeable;
import org.cactoos.Scalar;
import org.cactoos.iterable.IterableOf;

/**
 * <p>
 * <b>Title: VrCloseableInstance </b>
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
 * PA1 21.5.2018.
 * @since 21.5.2018.
 */

public final class VrCloseableInstance<T extends Closeable> extends
    InstanceEnvelope<T> {

    public VrCloseableInstance(final Scalar<T> scalar,
        final Wire... wires) {
        this(scalar, new IterableOf<>(wires));
    }

    public VrCloseableInstance(final Scalar<T> scalar,
        final Iterable<Wire> wires) {
        super(() -> new VrInstance<>(
            new RefreshableScalar<>(scalar, Closeable::close), wires)
        );
    }
}
