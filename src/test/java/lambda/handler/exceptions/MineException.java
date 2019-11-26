package lambda.handler.exceptions;

import lombok.Getter;

@Getter
public class MineException extends Exception {

    public MineException(String message) {
        super(message);
    }
}