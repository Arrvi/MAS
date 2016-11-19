package pl.edu.pja.s11531.mas.mp2.associations

import static com.google.common.base.Preconditions.checkNotNull

/**
 * Created by kris on 11/19/16.
 */
abstract class Component<T extends Composite> extends LinkedObject {
    final T owner

    Component(T owner) {
        checkNotNull(owner)
        this.owner = owner
        owner.addComponent(this)
    }
}
