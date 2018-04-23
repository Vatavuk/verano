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

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import hr.com.vgv.verano.Props;
import org.cactoos.func.SolidFunc;
import org.cactoos.func.UncheckedFunc;

/**
 * Xml properties.
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XmlProps implements Props {

    /**
     * XML file as singleton.
     */
    private static final UncheckedFunc<String, XML> SINGLETON =
        new UncheckedFunc<>(
            new SolidFunc<>(
                xpath -> new XMLDocument("<profile>dev</profile>")
            )
        );

    /**
     * Path.
     */
    private final String path;

    /**
     * Ctor.
     * @param xpath Xml path
     */
    public XmlProps(final String xpath) {
        this.path = xpath;
    }

    @Override
    public String value(final String xpath) {
        return XmlProps.SINGLETON.apply(this.path).xpath(
            XmlProps.text(xpath)
        ).get(0);
    }

    @Override
    public String value(final String property, final String defaults)
        throws Exception {
        throw new UnsupportedOperationException("#value()");
    }

    @Override
    public Iterable<String> values(final String property) throws Exception {
        throw new UnsupportedOperationException("#values()");
    }

    @Override
    public boolean has(final String property) {
        return !XmlProps.SINGLETON.apply(this.path).xpath(
            XmlProps.text(property)
        ).isEmpty();
    }

    /**
     * Text from path.
     * @param xpath Xpath
     * @return String Text
     */
    private static String text(final String xpath) {
        return String.format(
            "%s/text()", xpath
        );
    }
}
