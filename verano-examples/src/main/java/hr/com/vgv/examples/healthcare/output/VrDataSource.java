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
package hr.com.vgv.examples.healthcare.output;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.components.VrComponent;
import hr.com.vgv.verano.instances.VrInstance;
import hr.com.vgv.verano.props.TmpAppProps;
import hr.com.vgv.verano.wiring.ProfileWire;
import javax.sql.DataSource;

/**
 * <p>
 * <b>Title: VrDataSource </b>
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
 * PA1 23.5.2018.
 * @since 23.5.2018.
 */

public final class VrDataSource extends VrComponent<DataSource> {

    public VrDataSource(final AppContext ctx) {
        super(ctx,
            new VrInstance<>(
                new PostgresDataSource(new TmpAppProps(ctx)),
                new ProfileWire("dev")
            ),
            new VrInstance<>(
                new H2DataSource(),
                new ProfileWire("test")
            )
        );
    }
}
