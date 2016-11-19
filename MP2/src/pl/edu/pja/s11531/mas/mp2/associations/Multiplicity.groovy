package pl.edu.pja.s11531.mas.mp2.associations

/**
 * Created by kris on 11/19/16.
 */
enum Multiplicity {
    ZERO_OR_MORE(null, null),
    ONE_OR_MORE(null, 1),
    ZERO_OR_ONE(1, null),
    ONE(1, 1)

    final Integer maxMultiplicity, minMultiplicity

    Multiplicity(Integer maxMultiplicity = null, Integer minMultiplicity = null) {
        this.maxMultiplicity = maxMultiplicity
        this.minMultiplicity = minMultiplicity
    }

    boolean isValid(int number) {
        if ( maxMultiplicity != null ) {
            return number <= maxMultiplicity
        }
        if ( minMultiplicity != null ) {
            return number >= minMultiplicity
        }
        return true
    }
}
