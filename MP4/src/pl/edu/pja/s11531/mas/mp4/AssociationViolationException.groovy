package pl.edu.pja.s11531.mas.mp4

class AssociationViolationException extends IllegalArgumentException {
    Object baseObject
    Object linkedObject

    AssociationViolationException(String message, Object baseObject, Object linkedObject) {
        super("$message, association=$baseObject->$linkedObject")
        this.baseObject = baseObject
        this.linkedObject = linkedObject
    }


}
