package hr.com.vgv.verano;


public interface Props {

    String value(String property) throws Exception;

    String value(String property, String defaults) throws Exception;

    Iterable<String> values(String property) throws Exception;

    boolean has(String property) throws Exception;
}
