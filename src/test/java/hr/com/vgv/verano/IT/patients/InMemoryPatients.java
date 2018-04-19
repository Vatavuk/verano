package hr.com.vgv.verano.IT.patients;

import hr.com.vgv.verano.AppContext;


class InMemoryPatients implements Patients {

    public InMemoryPatients(final AppContext context) {}

    @Override
    public void create() throws Exception {
        System.out.println("InMemorypatients value");
    }
}
