package hr.com.vgv.examples.company;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.VrAppContext;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final AppContext context = new VrAppContext(args);
        final Order order = new OrderComponent(context).instance();
        order.showItem("123");
    }
}
