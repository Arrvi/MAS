package pl.edu.pja.s11531.mas.mp1

class CargoShip extends SpaceShip {
    List<Cargo> cargo = []

    CargoShip(String name, BigDecimal mass, int crewCount) {
        super(name, mass, crewCount)
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
