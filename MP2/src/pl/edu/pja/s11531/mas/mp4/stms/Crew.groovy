package pl.edu.pja.s11531.mas.mp4.stms

import pl.edu.pja.s11531.mas.mp4.LinkedObject

import java.time.LocalDate

class Crew extends LinkedObject {
    final String name
    final List<CrewService> service = []

    Crew(String name) {
        this.name = name
    }

    CrewService getCurrentService() {
        service.find { it.ended == null }
    }

    SpaceShip getSpaceShip() {
        currentService?.spaceShip
    }

    void addService(CrewService service) {
        this.service.findAll { it.ended == null }*.ended = LocalDate.now()
        this.service << service
    }

    @Override
    String toString() {
        "Crew $name" + (spaceShip ? " serving on $spaceShip" : "")
    }
}
