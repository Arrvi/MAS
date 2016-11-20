package pl.edu.pja.s11531.mas.mp2.stms

import pl.edu.pja.s11531.mas.mp2.LinkedObject

class Cargo extends LinkedObject {
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
