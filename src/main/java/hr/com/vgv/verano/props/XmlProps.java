package hr.com.vgv.verano.props;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import hr.com.vgv.verano.Props;
import org.cactoos.func.SolidFunc;
import org.cactoos.func.UncheckedFunc;

/**
 * <p>
 * <b>Title: XmlProps </b>
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
 * PA1 6.2.2018.
 * @since 6.2.2018.
 */

public final class XmlProps implements Props {

    private static final UncheckedFunc<String, XML> SINGLETON =
        new UncheckedFunc<>(
            new SolidFunc<>(
                xpath -> new XMLDocument("<profile>dev</profile>")
            )
        );

    private final String path;

    public XmlProps(final String xpath) {
        this.path = xpath;
    }

    @Override
    public String value(final String xpath) {
        return XmlProps.SINGLETON.apply(this.path).xpath(
            String.format(
                "%s/text()", xpath
            )
        ).get(0);
    }

    @Override
    public String value(final String property, final String defaults) throws Exception {
        throw new UnsupportedOperationException("#value()");
    }

    @Override
    public Iterable<String> values(final String property) throws Exception {
        throw new UnsupportedOperationException("#values()");
    }

    @Override
    public boolean has(final String property) {
       return !XmlProps.SINGLETON.apply(this.path).xpath(
            String.format(
                "%s/text()", property
            )
        ).isEmpty();
    }
}
