package lambda.handler;/*package com.inpeco.das.workflow.service.utils;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {
    R apply(T t) throws E;

    static <T, R, E extends Exception> Function<T, R> handlingConsumerFunction(ThrowingFunction<T, R, E> function,
                                                                               Map<Class, Function<R, Exception>> exceptionHandlersMap) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception ex) {
                ExceptionHandler exceptionHandler = BaseThrowingInterface.retrieveExceptionHandler(ex, exceptionHandlersMap);

                if (exceptionHandler != null) {
                    // If exception is present in the ExceptionsToHandle list, you can handle it here as you prefer
                    return
                } else {
                    // Else throw a new InpecoException (Runtime)
                    throw new InpecoException(ex);
                }
            }
        };
    }
}*/