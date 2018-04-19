package hr.com.vgv.verano;

import java.io.Closeable;
import java.io.IOException;


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
