package pl.edu.pja.s11531.mas.mp4

/**
 * Created by kris on 12/18/16.
 */
class ConstraintViolationException extends IllegalArgumentException {
    ConstraintViolationException(String s) {
        super(s)
    }

    ConstraintViolationException(String message, Throwable cause) {
        super(message, cause)
    }
}
