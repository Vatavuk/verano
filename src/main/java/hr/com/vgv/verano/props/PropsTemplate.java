package hr.com.vgv.verano.props;

import hr.com.vgv.verano.Props;

/**
 * <p>
 * <b>Title: PropsTemplate </b>
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
 * PA1 16.4.2018.
 * @since 16.4.2018.
 */

public abstract class PropsTemplate implements Props {

    private final Props origin;

    public PropsTemplate(final Props origin) {
        this.origin = origin;
    }

    @Override
    public String value(final String property) throws Exception {
        return this.origin.value(property);
    }

    @Override
    public String value(final String property, final String defaults)
        throws Exception{
        return this.origin.value(property, defaults);
    }

    @Override
    public Iterable<String> values(final String property) throws Exception {
        return this.origin.values(property);
    }

    @Override
    public boolean has(final String property) throws Exception {
        return this.origin.has(property);
    }
}
