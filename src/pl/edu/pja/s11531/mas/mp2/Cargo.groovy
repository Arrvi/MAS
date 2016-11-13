package pl.edu.pja.s11531.mas.mp2

class Cargo implements Serializable {
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
