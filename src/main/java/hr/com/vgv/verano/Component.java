package hr.com.vgv.verano;

/**
 * <p>
 * <b>Title: Component </b>
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
 * PA1 20.2.2018.
 * @since 20.2.2018.
 */

public interface Component<T> {

    boolean isActive(AppContext context);

    T instance();
}
