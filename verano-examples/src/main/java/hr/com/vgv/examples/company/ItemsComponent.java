package hr.com.vgv.examples.company;

import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.components.VrComponent;
import hr.com.vgv.verano.instances.VrInstance;
import hr.com.vgv.verano.wiring.ProfileWire;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class ItemsComponent extends VrComponent<Items> {

    public ItemsComponent(final AppContext ctx) {
        super(ctx,
            new VrInstance<>(
                () -> new RealItems(),
                new ProfileWire("prod")
            ),
            new VrInstance<>(
                () -> new TestItems(),
                new ProfileWire("test")
            )
        );
    }
}
