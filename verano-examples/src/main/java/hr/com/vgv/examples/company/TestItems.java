package hr.com.vgv.examples.company;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class TestItems implements Items {

    @Override
    public void printItem(final String id) {
        System.out.println(String.format("Test item %s", id));
    }
}
