package pl.edu.pja.s11531.mas.mp1

class LightFighter extends SpaceShip {
    final static CREW_COUNT = 2

    LightFighter(String name, BigDecimal mass) {
        super(name, mass, CREW_COUNT)
    }

    @Override
    public void setCrewCount(int crewCount) {
        throw new UnsupportedOperationException("Fighters only take 2 pilots")
    }
}
