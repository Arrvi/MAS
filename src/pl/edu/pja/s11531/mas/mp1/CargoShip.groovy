package pl.edu.pja.s11531.mas.mp1

/**
 * Created by kris on 05.11.16.
 */
class CargoShip extends SpaceShip {
    List<Cargo> cargo = []

    CargoShip(String name, BigDecimal mass, int crewCount) {
        super(name, mass, crewCount)
    }

    @Override
    public BigDecimal getMass() {
        return super.mass + cargo.sum {it.mass}
    }
}
