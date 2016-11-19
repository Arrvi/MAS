package pl.edu.pja.s11531.mas.mp2.associations

/**
 * Created by kris on 11/19/16.
 */
trait QualifiedOwner<T extends QualifiedPart<? extends QualifiedOwner, Q>, Q> {
    Map<Q, T> links = new HashMap<>()

    void addQualifiedPart(Q qualifier, T object) {
        links[qualifier] = object
    }

    T getQualifiedPart(Q qualifier) {
        return links[qualifier]
    }

    T removeQualifiedPart(Q qualifier) {
        return links.remove(qualifier)
    }
}