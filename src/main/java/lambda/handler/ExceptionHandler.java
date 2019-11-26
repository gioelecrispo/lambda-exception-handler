package lambda.handler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

@Getter
@AllArgsConstructor
public class ExceptionHandler<E extends Exception> {

    private E exception;
    private Consumer<E> handler;

}
