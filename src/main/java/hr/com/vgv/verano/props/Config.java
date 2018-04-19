package hr.com.vgv.verano.props;

import hr.com.vgv.verano.AppContext;

/**
 * <p>
 * <b>Title: Config </b>
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

public final class Config extends PropsTemplate {

    public Config(final AppContext context) {
        super(context.get("config"));
    }
}
