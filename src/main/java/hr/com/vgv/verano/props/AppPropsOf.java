package hr.com.vgv.verano.props;

import hr.com.vgv.verano.AppContext;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class AppPropsOf extends PropsEnvelope {

    /**
     * Ctor.
     * @param context Application Context
     */
    public AppPropsOf(final AppContext context) {
        super(() -> context.props("app"));
    }
}
