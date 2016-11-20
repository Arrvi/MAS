package pl.edu.pja.s11531.mas.mp2.spaceManagementSystem

import pl.edu.pja.s11531.mas.mp2.associations.Association
import pl.edu.pja.s11531.mas.mp2.associations.AssociationType

class CargoShip extends SpaceShip {
    @Association(type = AssociationType.QUALIFIED, qualifier = String.class)
    List<Cargo> cargo = []

    CargoShip(Company company, String name, BigDecimal mass, int crewCount) {
        super(company, name, mass, crewCount)
    }

    @Override
    public BigDecimal getMass() {
        return super.mass + cargo.sum {it.mass}
    }

    @Override
    String toString() {
        return super.toString() + ", cargo=$cargo"
    }
}