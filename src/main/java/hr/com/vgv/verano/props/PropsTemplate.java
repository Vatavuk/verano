package hr.com.vgv.verano.props;

import hr.com.vgv.verano.Props;


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
