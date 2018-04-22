package hr.com.vgv.verano;


public interface Components<T> {

    Component<T> get(AppContext context);
}
