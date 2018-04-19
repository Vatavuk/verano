package hr.com.vgv.verano.IT.patients;

import hr.com.vgv.verano.VrCached;
import hr.com.vgv.verano.VrFactory;
import hr.com.vgv.verano.VrComponent;
import hr.com.vgv.verano.AppContext;
import hr.com.vgv.verano.conditions.HasProfile;


public class VrPatients extends VrFactory<Patients> {

    public VrPatients(final AppContext ctx) {
        super(
            ctx,
            new VrComponent<>(
                new VrCached<>(() -> new DefaultPatients(ctx)),
                new HasProfile("prod")
            ),
            new VrComponent<>(
                new VrCached<>(() -> new InMemoryPatients(ctx)),
                new HasProfile("test")
            )
        );
    }
}
