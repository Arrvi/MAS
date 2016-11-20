package pl.edu.pja.s11531.mas.mp2

abstract class BaseObject implements Serializable {
    /**
     * Map of object lists by their class. It defaults to empty list to avoid boilerplate checks while adding.
     * Note that each object is stored under its class key and all of its super classes. In other words class entry
     * contains all of its objects and objects of its subclass.
     */
    private static Map<Class<? extends BaseObject>, List<BaseObject>> extent = [:].withDefault {[]};

    /**
     * Base constructor. Adds object to extent.
     */
    BaseObject() {
        addObject this
    }

    /**
     * Adds an object to its class extent and all of its super classes' extents.
     */
    protected static <C extends BaseObject> void addObject(C object) {
        Class cls = object.class
        while (cls && cls != BaseObject.class) {
            extent[cls].add object
            cls = cls.superclass
        }
    }

    /**
     * @return extent of given class
     */
    public static <C extends BaseObject> List<C> getExtent(Class<C> cls) {
        return (List<C>) extent[cls];
    }

    /**
     * Clears extents of all classes
     */
    public static void clearExtent() {
        extent.clear()
    }

    /**
     * Clears extent of given class
     */
    public static void clearExtent(Class<? extends BaseObject> cls) {
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

    /**
     * Stores all extents to a file using Groovy serialization
     */
    public static void saveAll(File store) {
        def stream = store.newObjectOutputStream()
        def objects = new HashSet<Class<? extends BaseObject>>();
        extent.each {
            it.value.each objects.&add
        }
        objects.each stream.&leftShift
        stream.close()
    }

    /**
     * Loads all extents from a file using Groovy deserialization
     */
    public static void loadAll(File store) {
        def stream = store.newObjectInputStream()
        stream.eachObject BaseObject.&addObject
        stream.close()
    }
}
