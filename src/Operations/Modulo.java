package Operations;

import Exceptions.DivisionByZeroException;
import Exceptions.EmptyValueException;

import java.math.BigDecimal;

public class Modulo implements Operation {
    @Override
    public BigDecimal getResult(BigDecimal firstVal, BigDecimal secondVal) throws Exception {
        if (firstVal == null | secondVal == null) {
            throw new EmptyValueException();
        }
        else if (secondVal.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException();
        }
        else {
            return firstVal.remainder(secondVal);
        }
    }
}
