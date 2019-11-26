package lambda.handler;


import java.util.Map;
import java.util.function.Consumer;

public interface BaseThrowingInterface {

    static <R, E extends Exception> ExceptionHandler<Exception> retrieveExceptionHandler(Exception ex, Map<Class, Consumer<Exception>> exceptionHandlersMap) {
        for (Class exceptionClassToHandle : exceptionHandlersMap.keySet()) {
            try {
                E exception = (E) exceptionClassToHandle.cast(ex);
                return new ExceptionHandler<>(exception, exceptionHandlersMap.get(exceptionClassToHandle));
            } catch (ClassCastException ignored) { }
        }
        return null;
    }

}
