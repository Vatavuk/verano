package hr.com.vgv.verano;


public interface Factory<T> {

    T instance() throws Exception;
}
