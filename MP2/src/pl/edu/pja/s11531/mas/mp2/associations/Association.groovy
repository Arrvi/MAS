package pl.edu.pja.s11531.mas.mp2.associations

/**
 * Created by kris on 11/19/16.
 */

@interface Association {
    String name() default null
    AssociationType[] type() default []
    Class attribute() default null
    Class qualifier() default null
    int minAmount() default -1
    int maxAmount() default -1
}