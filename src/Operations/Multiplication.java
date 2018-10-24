package Operations;

import Exceptions.EmptyValueException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class Multiplication implements Operation {
    @Override
    public BigDecimal getResult(BigDecimal firstVal, BigDecimal secondVal) throws EmptyValueException {
        if (firstVal == null | secondVal == null) {
            throw new EmptyValueException();
        }
        else {
            ArrayList<Integer> fractPart = new ArrayList<>();

//            fractPart.add(firstVal.toString().split("\\.")[1].length());
//            fractPart.add(secondVal.toString().split("\\.")[1].length());

            return firstVal.multiply(secondVal);
        }
    }
}
