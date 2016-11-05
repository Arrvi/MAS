package pl.edu.pja.s11531.mas.mp1

/**
 * Created by kris on 05.11.16.
 */
class SpaceShip extends BaseObject {
    String name
    BigDecimal mass
    int crewCount

    SpaceShip(String name, BigDecimal mass, int crewCount) {
        super()
        this.name = name
        this.mass = mass
        this.crewCount = crewCount
    }

    public BigDecimal getMass() {
        return mass + crewCount * 0.1
    }

    public static int getMaximumFleetCrew() {
        return getExtent(SpaceShip.class).sum{it.crewCount}
    }
}
