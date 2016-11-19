package pl.edu.pja.s11531.mas.mp2.associations

/**
 * Created by kris on 11/19/16.
 */
trait Composite<T extends Component> {
    List<T> components

    void addComponent(T component) {
        if ( component.owner != this ){
            throw new AssociationViolationException("Cannot add component to different composite", this, component);
        }
        components << component
    }
}
