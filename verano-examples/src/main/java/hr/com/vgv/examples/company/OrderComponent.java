package hr.com.vgv.examples.company;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.components.VrComponent;
import hr.com.vgv.verano.instances.VrInstance;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class OrderComponent extends VrComponent<Order> {

    public OrderComponent(final AppContext ctx) {
        super(ctx,
            new VrInstance<>(
                () -> new UserOrder(new ItemsComponent(ctx).instance())
            )
        );
    }
}
