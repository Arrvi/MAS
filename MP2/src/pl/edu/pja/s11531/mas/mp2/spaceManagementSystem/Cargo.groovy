package pl.edu.pja.s11531.mas.mp2.spaceManagementSystem

import pl.edu.pja.s11531.mas.mp2.associations.LinkedObject
import pl.edu.pja.s11531.mas.mp2.associations.QualifiedPart

class Cargo extends LinkedObject implements QualifiedPart<CargoShip, String> {
    String name
    BigDecimal mass

    Cargo(String name, BigDecimal mass) {
        this.name = name
        this.mass = mass
    }

    @Override
    String toString() {
        return "$name ($mass t)"
    }
}
