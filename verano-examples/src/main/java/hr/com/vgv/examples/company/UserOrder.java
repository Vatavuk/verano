package hr.com.vgv.examples.company;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class UserOrder implements Order {

    private final Items items;

    public UserOrder(final Items items) {
        this.items = items;
    }

    @Override
    public void showItem(final String id) {
        this.items.printItem(id);
    }
}
