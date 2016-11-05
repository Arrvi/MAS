package pl.edu.pja.s11531.mas.mp1

/**
 * Created by kris on 05.11.16.
 */
abstract class BaseObject implements Serializable {
    private static Map<Class<? extends BaseObject>, List<BaseObject>> extent = [:].withDefault {[]};

    BaseObject() {
        addObject this
    }

    protected static <C extends BaseObject> void addObject(C object) {
        Class cls = object.class
        while (cls && cls != BaseObject.class) {
            extent[cls].add object
            cls = cls.superclass
        }
    }

    public static <C extends BaseObject> List<C> getExtent(Class<C> cls) {
        return (List<C>) extent[cls];
    }

    public static clearExtent() {
        extent.clear()
    }

    public static clearExtent(Class<? extends BaseObject> cls) {
        extent.each {
            if (cls.isAssignableFrom(it.key) && it.key != cls) {
                it.value.clear()
            }
        }
        def classExtent = extent[cls]
        Class currentClass = cls.superclass
        while (currentClass && currentClass != BaseObject.class) {
            extent[currentClass].removeAll classExtent
            currentClass = currentClass.superclass
        }
        extent[cls].clear()
    }

    public static saveAll(File store) {
        def stream = store.newObjectOutputStream()
        def objects = new HashSet<Class<? extends BaseObject>>();
        extent.each {
            it.value.each objects.&add
        }
        objects.each stream.&leftShift
        stream.close()
    }

    public static loadAll(File store) {
        def stream = store.newObjectInputStream()
        stream.eachObject BaseObject.&addObject
        stream.close()
    }
}
