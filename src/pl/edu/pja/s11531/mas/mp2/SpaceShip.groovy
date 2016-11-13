package pl.edu.pja.s11531.mas.mp2

class SpaceShip extends BaseObject {
    public static final BigDecimal CREW_MASS = 0.1
    String name
    BigDecimal mass
    int crewCount
    String function

    SpaceShip(String name, BigDecimal mass, int crewCount, String function = null) {
        super()
        this.name = name
        this.mass = mass
        this.crewCount = crewCount
        this.function = function
    }

    public BigDecimal getMass() {
        return mass + crewCount * CREW_MASS
    }

    public static int getTotalFleetCrew() {
        return getExtent(SpaceShip.class).sum{it.crewCount}
    }

    @Override
    String toString() {
        return "SpaceShip name=$name, mass=$mass t, crew=$crewCount people" + (function ? ", function=$function" : "")
    }
}
