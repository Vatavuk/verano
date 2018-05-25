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

import hr.com.vgv.verano.Instance;
import hr.com.vgv.verano.VrAppContext;
import hr.com.vgv.verano.fakes.FkWire;
import hr.com.vgv.verano.wiring.ProfileWire;
import java.util.concurrent.atomic.AtomicInteger;
import org.cactoos.iterable.IterableOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Test case for {@link VrInstance}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class VrInstanceTest {

    @Test
    public void instanceIsApplicable() throws Exception {
        MatcherAssert.assertThat(
            new VrInstance<>(
                () -> true,
                new FkWire(true)
            ).applicable(new VrAppContext()),
            new IsEqual<>(true)
        );
    }

    @Test
    public void instanceNotApplicable() throws Exception {
        MatcherAssert.assertThat(
            new VrInstance<>(
                () -> true,
                new FkWire(false)
            ).applicable(new VrAppContext()),
            new IsEqual<>(false)
        );
    }

    @Test
    public void retrievesInstanceValue() throws Exception {
        MatcherAssert.assertThat(
            new VrInstance<>(
                () -> true
            ).value(),
            new IsEqual<>(true)
        );
    }

    @Test
    public void instanceApplicableDependingOnWireConditions() throws Exception {
        MatcherAssert.assertThat(
            new VrInstance<>(
                () -> true,
                new ProfileWire("test")
            ).applicable(new IterableOf<>(new ProfileWire("test"))),
            new IsEqual<>(true)
        );
    }

    @Test
    public void instanceWithoutWiresIsNotApplicable() throws Exception {
        MatcherAssert.assertThat(
            new VrInstance<>(
                () -> true
            ).applicable(new VrAppContext()),
            new IsEqual<>(false)
        );
    }

    @Test
    public void refreshesInstance() throws Exception {
        final AtomicInteger value = new AtomicInteger();
        final Instance<Integer> instance = new VrInstance<>(
            value::getAndIncrement
        );
        instance.value();
        instance.value();
        instance.refresh();
        instance.value();
        MatcherAssert.assertThat(
            value.intValue(),
            new IsEqual<>(2)
        );
    }
}
