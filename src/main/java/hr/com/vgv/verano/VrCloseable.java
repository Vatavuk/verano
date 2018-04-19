package hr.com.vgv.verano;

import java.io.Closeable;
import java.io.IOException;

/**
 * <p>
 * <b>Title: Closeable </b>
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
 * PA1 19.4.2018.
 * @since 19.4.2018.
 */

public final class VrCloseable<T extends Closeable>
    implements Instance<T>, Closeable {

    private final Instance<T> origin;

    public VrCloseable(final Instance<T> instance) {
        this.origin = instance;
    }

    @Override
    public T value() {
        return this.origin.value();
    }

    @Override
    public void close() throws IOException {
        this.origin.value().close();
    }
}
