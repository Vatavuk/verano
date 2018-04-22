package hr.com.vgv.verano;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface InstancePool<T> {

    T get(AppContext context);
}
