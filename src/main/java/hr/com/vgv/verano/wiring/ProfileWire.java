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
package hr.com.vgv.verano.wiring;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.Props;
import hr.com.vgv.verano.Wire;
import hr.com.vgv.verano.props.CliPropsOf;

/**
 * Profile wire.
 * Wire is active if the right profile is set via command line interface.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ProfileWire implements Wire {

    /**
     * Profile value.
     */
    private final String value;

    /**
     * Ctor.
     * @param profile Profile
     */
    public ProfileWire(final String profile) {
        this.value = profile;
    }

    @Override
    public Boolean isActive(final AppContext context, final String component)
        throws Exception {
        final String profile = "profile";
        final Props props = new CliPropsOf(context);
        return props.has(profile)
            && props.value(profile).equals(this.value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
