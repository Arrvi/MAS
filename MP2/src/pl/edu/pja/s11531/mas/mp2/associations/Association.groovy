package pl.edu.pja.s11531.mas.mp2.associations

/**
 * Created by kris on 11/19/16.
 */
trait Association<T extends Association> {
    abstract getMaxMultiplicity(Class<T> cls)

}