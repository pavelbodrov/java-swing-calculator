package Operations;

import Exceptions.EmptyValueException;

import java.math.BigDecimal;

public class Substraction implements Operation{
    @Override
    public BigDecimal getResult(BigDecimal firstVal, BigDecimal secondVal) throws EmptyValueException {
        if (firstVal == null | secondVal == null) {
            throw new EmptyValueException();
        }
        else {
            return firstVal.subtract(secondVal);
        }
    }
}
