package pl.edu.pja.s11531.mas.mp2.associations
import java.lang.reflect.Field

/**
 * Created by kris on 11/19/16.
 */
trait AssociationProcessor {
    private static Set<Class> validationCache = new HashSet<>()

    void overrideSetters() {

    }

    void validateAssociationDefinitions() {
        if ( validationCache.contains(this.class) ) {
            return
        }

        Class cls = this.class
        cls.fields.findAll({it.isAnnotationPresent(Association.class)}).every { field ->
            Association assoc = field.getAnnotation(Association.class)
            String endDescription = "Association end $cls.simpleName->$field.type.simpleName"
            Set<AssociationType> types = new HashSet<>(Arrays.asList(assoc.type()))
            assert !types.containsAll([AssociationType.COMPOSITE, AssociationType.COMPONENT]) :
                    "$endDescription cannot be both composite and component"
            assert !(types.contains(AssociationType.WITH_ATTRIBUTE) && assoc.attribute() == null) :
                    "$endDescription has no attribute class"
            assert !(types.contains(AssociationType.QUALIFIED) && assoc.qualifier() == null) :
                    "$endDescription nas no qualifier class"
            assert hasOtherEnd(cls, field, assoc) :
                    "$endDescription has no other end"
            assert !types.contains(AssociationType.COMPONENT) || hasComponentConstructor(cls, field) :
                    "$endDescription has no required constructor param (at least one constructor does not include proper field)"
            assert assoc.maxAmount() <= 1 || isCollectionType(field.type) :
                    "$endDescription has multiplicity greater than 1, but field is not a collection"
            assert !types.contains(AssociationType.COMPONENT) || assoc.maxAmount() <= 1 :
                    "$endDescription is a component, but has multiplicity greater than 1"
            return true
        }
        validationCache << cls;
    }

    private static Class getFieldType(Class field) {
        if (Collection.class.isAssignableFrom(field)){
            return field.typeParameters[0].genericDeclaration
        }
        if (Map.class.isAssignableFrom(field)) {
            return field.typeParameters[1].genericDeclaration
        }
        return field
    }

    private static boolean hasOtherEnd(Class cls, Field field, Association association) {
        return field.type.fields.any {
            getFieldType(it.class) == getFieldType(cls) &&
                    it.getAnnotation(Association) &&
                    it.getAnnotation(Association).name() == association.name()
        }
    }

    private static boolean hasComponentConstructor(Class cls, Field field) {
        def type = getFieldType field.type
        return cls.constructors.every {
            it.parameterTypes.contains(type)
        }
    }

    private static boolean isCollectionType(Class cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls)
    }
}