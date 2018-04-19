package hr.com.vgv.verano;

/**
 * <p>
 * <b>Title: Condition </b>
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
 * PA1 7.2.2018.
 * @since 7.2.2018.
 */

public interface Condition {

    Boolean check(AppContext context);

    Boolean check(Condition condition);
}
