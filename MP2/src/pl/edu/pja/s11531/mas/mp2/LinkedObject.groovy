package pl.edu.pja.s11531.mas.mp2
import java.lang.reflect.Field

abstract class LinkedObject extends BaseObject {
    private static int lastId = 0;
    final int id
    Map<Field, Integer> links

    LinkedObject() {
        super()
        id = ++lastId
    }




    @Override
    public String toString() {
        return "LinkedObject{id=$id}"
    }
}
