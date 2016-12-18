package pl.edu.pja.s11531.mas.mp4.stms

import pl.edu.pja.s11531.mas.mp4.AssociationViolationException
import pl.edu.pja.s11531.mas.mp4.ConstraintViolationException

class CargoShip extends SpaceShip {
    final Map<String, Cargo> storage = [:]
    final int maxCargo

    CargoShip(Company company, String name, BigDecimal mass, int maxCargo) {
        super(company, name, mass)
        this.maxCargo = maxCargo
    }

    @Override
    public BigDecimal getMass() {
        return super.mass + (storage.values().sum { it.mass } as BigDecimal)
    }

    void addCargo(String bay, Cargo cargo) {
        if (storage.size() >= maxCargo) {
            throw new ConstraintViolationException("Storage is full")
        }
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
