package lambda.handler.exceptions;

import lombok.Getter;

@Getter
public class IntegerSmallerThan10Exception extends Exception {

    private Integer value;

    public IntegerSmallerThan10Exception(String message) {
        super(message);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}