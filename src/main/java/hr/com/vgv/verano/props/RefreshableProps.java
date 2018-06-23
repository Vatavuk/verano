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

import hr.com.vgv.verano.Instance;
import hr.com.vgv.verano.Props;
import hr.com.vgv.verano.instances.Container;
import hr.com.vgv.verano.wiring.Binary;
import java.io.File;
import java.util.Map;
import org.cactoos.Proc;
import org.cactoos.Scalar;
import org.cactoos.func.AsyncFunc;
import org.cactoos.iterable.Endless;
import org.cactoos.iterable.Joined;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.And;

/**
 * Properties that are refreshed if specific file is changed.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 * @checkstyle ParameterNumberCheck (500 lines)
 */
public final class RefreshableProps extends PropsEnvelope {

    /**
     * Ctor.
     * @param scalar Scalar
     * @param path File path
     */
    public RefreshableProps(final Scalar<Props> scalar, final String path) {
        this(scalar, path, 1000L);
    }

    /**
     * Ctor.
     * @param scalar Scalar
     * @param path File path
     * @param period Refresh period
     */
    public RefreshableProps(final Scalar<Props> scalar, final String path,
        final long period) {
        super(RefreshableProps.buildProps(scalar, path, period));
    }

    /**
     * Builds props.
     * @param scalar Scalar
     * @param path File path
     * @param period Refresh period
     * @return Scalar Props
     */
    private static Scalar<Props> buildProps(final Scalar<Props> scalar,
        final String path, final long period) {
        final RefreshableScalar<Props> origin = new RefreshableScalar<>(scalar);
        final Iterable<Instance<?>> instances = new Joined<>(
            new Mapped<>(
                Map.Entry::getValue,
                new Container().entrySet()
            )
        );
        final ObservedFile observed = new ObservedFile(new File(path));
        new AsyncFunc<Boolean, Boolean>(
            input -> {
                new And(
                    inp -> {
                        Thread.sleep(period);
                        new Binary(
                            observed.modified(),
                            RefreshableProps.refresh(instances, origin)
                        ).value();
                        return true;
                    },
                    new Endless<>(true)
                ).value();
            }
        ).apply(true);
        return origin;
    }

    /**
     * Refreshes instances and original props.
     * @param instances Instances
     * @param origin Origin
     * @return Proc Boolean proc
     */
    private static Proc<Boolean> refresh(
        final Iterable<Instance<?>> instances,
        final RefreshableScalar<Props> origin) {
        return input -> {
            origin.refresh();
            new And(
                (Instance<?> element) -> element.refresh(),
                instances
            ).value();
        };
    }
}
