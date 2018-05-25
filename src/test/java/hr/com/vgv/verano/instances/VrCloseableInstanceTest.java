/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hr.com.vgv.verano.instances;

import hr.com.vgv.verano.fakes.FkWire;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test case for {@link VrCloseableInstance}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class VrCloseableInstanceTest {

    @Test
    public void closesInstance() throws Exception {
        final AtomicBoolean value = new AtomicBoolean();
        new VrCloseableInstance<>(
            () -> new VrCloseableInstanceTest.CustomCloseable(value),
            new FkWire(true)
        ).refresh();
        MatcherAssert.assertThat(
            value.get(),
            new IsEqual<>(true)
        );
    }

    /**
     * Custom closeable class.
     */
    private static class CustomCloseable implements Closeable {

        /**
         * Atomic value.
         */
        private final AtomicBoolean value;

        /**
         * Ctor.
         * @param val Value
         */
        public CustomCloseable(final AtomicBoolean val) {
            this.value = val;
        }

        @Override
        public void close() throws IOException {
            this.value.set(true);
        }
    }
}
