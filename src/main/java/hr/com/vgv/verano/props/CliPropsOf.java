package hr.com.vgv.verano.props;

import hr.com.vgv.verano.AppContext;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CliPropsOf extends PropsEnvelope {

    /**
     * Ctor.
     * @param context Context
     */
    public CliPropsOf(final AppContext context) {
        super(() -> context.props("cli"));
    }
}
