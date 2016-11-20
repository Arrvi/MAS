package pl.edu.pja.s11531.mas.mp2.stms

import pl.edu.pja.s11531.mas.mp2.LinkedObject

import java.time.LocalDate

class SpaceShip extends LinkedObject {
    public static final BigDecimal CREW_MASS = 0.1

    final Company company
    final List<CrewService> crewList = []
    Fleet fleet

    String name
    BigDecimal mass
    String function

    SpaceShip(Company company, String name, BigDecimal mass, String function = null) {
        super()
        this.name = name
        this.mass = mass
        this.function = function
        this.company = company
        company.addSpaceShip(this)
    }

    public BigDecimal getMass() {
        return mass + crewCount * CREW_MASS
    }

    static int getTotalFleetCrew() {
        return getExtent(SpaceShip.class).sum { it.crewCount } as int
    }

    int getCrewCount() {
        return crewList.size()
    }

    List<Crew> getCrew() {
        crewList.findAll { it.ended == null }*.crew
    }

    void addCrew(Crew crew, LocalDate serviceStarted, LocalDate serviceEnded = null) {
        CrewService.create(crew, this, serviceStarted, serviceEnded)
    }

    SpaceShip leftShift(Crew crew) {
        addCrew(crew, LocalDate.now())
        return this
    }

    SpaceShip rightShift(Crew crew) {
        crewList.remove(crew)
        return this
    }

    void setFleet(Fleet fleet) {
        if (this.fleet == fleet)
            return
        if (this.fleet != null && fleet == null)
            this.fleet >> this
        this.fleet = fleet
        if (fleet != null)
            fleet << this
    }


    @Override
    String toString() {
        return "SpaceShip name=$name, mass=$mass t, crew=$crewCount people" + (function ? ", function=$function" : "")
    }
}
