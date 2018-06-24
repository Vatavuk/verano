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
package hr.com.vgv.verano.props;

import hr.com.vgv.verano.wiring.Binary;
import java.io.File;
import java.io.IOException;
import org.cactoos.scalar.Or;

/**
 * File that can be observed for modifications.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ObservedFile {

    /**
     * File to be observed.
     */
    private final File origin;

    /**
     * Last modified date.
     */
    private long timestamp;

    /**
     * Ctor.
     * @param file File
     */
    public ObservedFile(final File file) {
        this(file, file.lastModified());
    }

    /**
     * Ctor.
     * @param file File
     * @param timestmp Last modified date
     */
    public ObservedFile(final File file, final long timestmp) {
        this.origin = file;
        this.timestamp = timestmp;
    }

    /**
     * Check if the file is modified.
     * @return Boolean Boolean
     * @throws Exception If file does not exist
     */
    public boolean modified() throws Exception {
        final long modified = this.origin.lastModified();
        return new Or(
            new Binary(
                modified == 0L,
                () -> {
                    throw new IOException(
                        String.format(
                            "File %s does not exist.",
                            this.origin.getAbsolutePath()
                        )
                    );
                }
            ),
            new Binary(
                modified != this.timestamp,
                () -> this.timestamp = modified
            )
        ).value();
    }
}
