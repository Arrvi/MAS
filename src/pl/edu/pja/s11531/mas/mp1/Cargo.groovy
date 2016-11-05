package pl.edu.pja.s11531.mas.mp1

/**
 * Created by kris on 05.11.16.
 */
class Cargo implements Serializable {
    String name
    BigDecimal mass

    Cargo(String name, BigDecimal mass) {
        this.name = name
        this.mass = mass
    }
}
