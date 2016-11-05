package pl.edu.pja.s11531.mas.mp1

/**
 * Created by kris on 05.11.16.
 */
abstract class BaseObject implements Serializable {
    private static Map<Class<? extends BaseObject>, List<BaseObject>> extent = [:].withDefault {[]};

    BaseObject() {
        addObject this
    }

    protected static addObject(BaseObject object) {
        extent[object.class].add object
    }

    public static <C extends BaseObject> List<C> getExtent(Class<C> cls) {
        return (List<C>) extent[cls];
    }

    public static saveAll(File store) {
        def stream = store.newObjectOutputStream()
        extent.each {
            it.getValue().each stream.&leftShift
        }
        stream.close()
    }

    public static loadAll(File store) {
        def stream = store.newObjectInputStream()
        stream.eachObject BaseObject.&addObject
        stream.close()
    }
}
