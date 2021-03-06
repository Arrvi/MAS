package pl.edu.pja.s11531.util

class AssertionUtil {
    static <E extends Exception> void assertException(Class<E> exceptionClass, String message = null, Closure operation) throws AssertionError {
        try {
            operation()
            throw new AssertionError(message ?: "No exception")
        } catch (E ignored) {
        }
    }
}
