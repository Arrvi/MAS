package pl.edu.pja.s11531.mas.mp2.associations

/**
 * Created by kris on 11/19/16.
 */
trait QualifiedPart<T extends QualifiedOwner<? extends QualifiedPart, Q>, Q> {
    T qualifiedOwner

    void setQualifiedOwner(T owner, Q qualifier) {
        qualifiedOwner=owner
        owner.addQualifiedPart(qualifier, this)
    }
}