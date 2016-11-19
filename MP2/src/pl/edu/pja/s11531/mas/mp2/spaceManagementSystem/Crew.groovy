package pl.edu.pja.s11531.mas.mp2.spaceManagementSystem

import pl.edu.pja.s11531.mas.mp2.associations.Association
import pl.edu.pja.s11531.mas.mp2.associations.LinkedObject

/**
 * Created by kris on 11/19/16.
 */
class Crew extends LinkedObject implements Association<SpaceShip> {
    @Override
    def getMaxMultiplicity(Class<SpaceShip> cls) {
        return null
    }
}
