package lambda.handler;
import java.util.Map;
import java.util.function.Consumer;


@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;

    static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(
            ThrowingConsumer<T, E> throwingConsumer, Map<Class, Consumer<Exception>> exceptionHandlersMap) {

        return i -> {
            try {
                throwingConsumer.accept(i);
            } catch (Exception ex) {
                ExceptionHandler<Exception> exceptionHandler = BaseThrowingInterface.retrieveExceptionHandler(ex, exceptionHandlersMap);
                if (exceptionHandler != null) {
                    // If exception is present in the ExceptionsToHandle list, you can handle it here as you prefer
                    exceptionHandler.getHandler().accept(exceptionHandler.getException());
                } else {
                    // Else throw a new RuntimeException that encloses the Exception
                    throw new RuntimeException(ex);
                }
            }
        };
    }


}
