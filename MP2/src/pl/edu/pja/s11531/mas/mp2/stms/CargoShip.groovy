package pl.edu.pja.s11531.mas.mp2.stms

import pl.edu.pja.s11531.mas.mp2.AssociationViolationException

class CargoShip extends SpaceShip {
    final Map<String, Cargo> storage = [:]

    CargoShip(Company company, String name, BigDecimal mass) {
        super(company, name, mass)
    }

    @Override
    public BigDecimal getMass() {
        return super.mass + (cargo.values().sum { it.mass } as BigDecimal)
    }

    void addCargo(String bay, Cargo cargo) {
        if (storage[bay]) {
            throw new AssociationViolationException("Cargo bay [$bay] is full", this, cargo)
        }
        storage[bay] = cargo
    }

    Cargo takeCargo(String bay) {
        storage.remove(bay)
    }

    @Override
    String toString() {
        return super.toString() + ", cargo=$cargo"
    }
}
