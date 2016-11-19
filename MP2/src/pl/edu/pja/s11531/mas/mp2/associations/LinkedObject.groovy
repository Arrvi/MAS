package pl.edu.pja.s11531.mas.mp2.associations

import java.lang.reflect.Field

/**
 * Created by kris on 11/13/16.
 */
abstract class LinkedObject extends BaseObject {
    int id
    Map<Field, Integer> links
}
