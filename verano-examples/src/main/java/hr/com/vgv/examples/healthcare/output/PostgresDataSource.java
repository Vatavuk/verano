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

import hr.com.vgv.verano.Props;
import javax.sql.DataSource;
import org.cactoos.Scalar;
import org.postgresql.ds.PGPoolingDataSource;

/**
 * <p>
 * <b>Title: DevDataSource </b>
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

class PostgresDataSource implements Scalar<DataSource> {

    private final Props props;

    public PostgresDataSource(final Props props) {
        this.props = props;
    }

    @Override
    public DataSource value() throws Exception {
        final PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDatabaseName(this.props.value("postgres.dbname"));
        source.setServerName(this.props.value("postgres.host"));
        source.setUser(this.props.value("postgres.user"));
        source.setPassword(this.props.value("postgres.password"));
        source.setMaxConnections(10);
        return source;
    }
}
