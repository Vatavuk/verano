package hr.com.vgv.verano.props;

import hr.com.vgv.verano.AppContext;


public final class Config extends PropsTemplate {

    public Config(final AppContext context) {
        super(context.get("config"));
    }
}
