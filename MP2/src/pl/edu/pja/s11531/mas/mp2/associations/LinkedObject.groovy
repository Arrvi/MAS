package pl.edu.pja.s11531.mas.mp2.associations

import java.lang.reflect.Field

/**
 * Created by kris on 11/13/16.
 */
abstract class LinkedObject extends BaseObject implements AssociationProcessor {
    private static int lastId = 0;
    final int id
    Map<Field, Integer> links

    LinkedObject() {
        super()
        id = ++lastId
        validateAssociationDefinitions()
    }


    @Override
    public String toString() {
        return "LinkedObject{id=$id}"
    }
}
