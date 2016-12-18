package pl.edu.pja.s11531.mas.mp4.stms

import java.time.LocalDate

class CrewService {
    final Crew crew
    final SpaceShip spaceShip
    final LocalDate started
    LocalDate ended

    private CrewService(Crew crew, SpaceShip spaceShip, LocalDate started, LocalDate ended = null) {
        this.crew = crew
        this.spaceShip = spaceShip
        this.started = started
        this.ended = ended
    }

    static CrewService create(Crew crew, SpaceShip spaceShip, LocalDate started, LocalDate ended = null) {
        CrewService cs = new CrewService(crew, spaceShip, started, ended)
        crew.addService cs
        spaceShip.crewList << cs
        return cs
    }

    @Override
    String toString() {
        "$crew.name serving on $spaceShip.name from $started" + (ended ? " to $ended" : "")
    }
}
