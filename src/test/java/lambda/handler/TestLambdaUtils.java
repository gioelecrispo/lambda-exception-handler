package lambda.handler;


import lambda.handler.exceptions.IntegerGreaterThan15Exception;
import lambda.handler.exceptions.IntegerSmallerThan10Exception;
import lambda.handler.exceptions.MineException;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestLambdaUtils {

    private static void elaborateNumber(Integer integer) throws IntegerSmallerThan10Exception, IntegerGreaterThan15Exception, MineException {
        if (integer > 15) {
            throw new IntegerGreaterThan15Exception("Integer " + integer + " is greater then 15");
        } else if (integer < 10) {
            throw new IntegerSmallerThan10Exception("Integer " + integer + " is smaller then 10");
        } else {
            throw new MineException("Integer " + integer + " is neither greater then 15 nor smaller then 10");
        }
    }

    @Test(expected = RuntimeException.class)
    public void testLambdaHandlerThrowingMineException() {
        // Given
        List<Integer> integers = Arrays.asList(3, 20, 7, 12, 9);
        Map<Class, Consumer<Exception>> exceptionHandlers = new HashMap<>();
        exceptionHandlers.put(IntegerGreaterThan15Exception.class, exception -> System.out.println("IntegerGreaterThan15Exception - " + exception.getMessage()) );
        exceptionHandlers.put(IntegerSmallerThan10Exception.class, exception -> System.out.println("IntegerSmallerThan10Exception - " + exception.getMessage()) );

        // When
        integers.forEach(ThrowingConsumer.handlingConsumerWrapper(TestLambdaUtils::elaborateNumber, exceptionHandlers));

        // Then
        // Check for test
    }

    @Test
    public void testLambdaHandlerMineException() {
        // Given
        List<String> integerGreaterThan15 = new ArrayList<>();
        List<String> integerSmallerThan10 = new ArrayList<>();
        List<Integer> integers = Arrays.asList(3, 20, 7, 9);
        Map<Class, Consumer<Exception>> exceptionHandlers = new HashMap<>();
        exceptionHandlers.put(IntegerGreaterThan15Exception.class, exception -> integerGreaterThan15.add(exception.getMessage()) );
        exceptionHandlers.put(IntegerSmallerThan10Exception.class, exception -> integerSmallerThan10.add(exception.getMessage()) );

        // When
        integers.forEach(ThrowingConsumer.handlingConsumerWrapper(TestLambdaUtils::elaborateNumber, exceptionHandlers));

        // Then
        List<String> expectedIntegerGreaterThan15 = new ArrayList<String>() {{
            add("Integer 20 is greater then 15");
        }};
        assertArrayEquals(expectedIntegerGreaterThan15.toArray(), integerGreaterThan15.toArray());
        List<String> expectedIntegerSmallerThan10 = new ArrayList<String>() {{
            add("Integer 3 is smaller then 10");
            add("Integer 7 is smaller then 10");
            add("Integer 9 is smaller then 10");
        }};
        assertArrayEquals(expectedIntegerSmallerThan10.toArray(), integerSmallerThan10.toArray());
    }

}
