package hr.com.vgv.verano;


public interface Component<T> {

    boolean isActive(AppContext context);

    T instance();
}
