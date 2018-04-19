package hr.com.vgv.verano.props;

import hr.com.vgv.verano.AppContext;


public final class Dependencies extends PropsTemplate {

    public Dependencies(final AppContext context) {
        super(context.get("dependencies"));
    }
}
