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

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import hr.com.vgv.verano.Props;
import org.cactoos.Scalar;
import org.cactoos.list.ListOf;

/**
 * Development mongo client.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
final class DevMongo implements Scalar<MongoClient> {

    private final Props props;

    public DevMongo(final Props properties) {
        this.props = properties;
    }

    @Override
    public MongoClient value() throws Exception {
        return new MongoClient(
            new ServerAddress(
                this.props.value("mongo.host"),
                Integer.parseInt(this.props.value("mongo.port"))
            ),
            new ListOf<>(
                MongoCredential.createCredential(
                    this.props.value("mongo.user"),
                    this.props.value("mongo.dbname"),
                    this.props.value("mongo.password").toCharArray()
                )
            )
        );
    }
}
