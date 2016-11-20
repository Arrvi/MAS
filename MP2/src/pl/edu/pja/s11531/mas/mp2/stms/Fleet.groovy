package pl.edu.pja.s11531.mas.mp2.stms

import pl.edu.pja.s11531.mas.mp2.LinkedObject

class Fleet extends LinkedObject {
    final String name
    Set<SpaceShip> spaceShips = new HashSet<>()

    Fleet(String name) {
        this.name = name
    }

    Fleet leftShift(SpaceShip spaceShip) {
        if (spaceShip.fleet == this && spaceShips.contains(spaceShip)) {
            return this
        }
        spaceShip.fleet?.spaceShips?.remove(spaceShip)
        spaceShips << spaceShip
        spaceShip.fleet = this
        return this
    }

    Fleet rightShift(SpaceShip spaceShip) {
        spaceShip.fleet = null
        spaceShips.remove(spaceShip)
        return this
    }
}
