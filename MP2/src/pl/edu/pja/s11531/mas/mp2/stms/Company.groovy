package pl.edu.pja.s11531.mas.mp2.stms

import pl.edu.pja.s11531.mas.mp2.AssociationViolationException
import pl.edu.pja.s11531.mas.mp2.LinkedObject

/**
 * Created by kris on 11/19/16.
 */
class Company extends LinkedObject {
    final String name
    private Set<SpaceShip> spaceShips = new HashSet<>()

    Company(String name) {
        this.name = name
    }

    void addSpaceShip(SpaceShip ship) {
        if ( ship.company != this ) {
            throw new AssociationViolationException('Composition violation - cannot add part to different whole', this, ship)
        }
        spaceShips << ship
    }
    Set<SpaceShip> getSpaceShips() {
        return Collections.unmodifiableSet(spaceShips)
    }

    Company leftShift(SpaceShip ship) {
        addSpaceShip(ship)
        return this
    }
}
