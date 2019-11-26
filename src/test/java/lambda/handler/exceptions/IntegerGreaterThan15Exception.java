package lambda.handler.exceptions;

import lombok.Getter;

@Getter
public class IntegerGreaterThan15Exception extends Exception {

    private Integer value;

    public IntegerGreaterThan15Exception(String message) {
        super(message);
        this.value = value;
    }
}