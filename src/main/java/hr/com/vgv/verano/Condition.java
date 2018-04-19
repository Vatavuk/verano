package hr.com.vgv.verano;


public interface Condition {

    Boolean check(AppContext context);

    Boolean check(Condition condition);
}
