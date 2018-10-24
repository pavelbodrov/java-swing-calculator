package Operations;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal getResult(BigDecimal firstVal, BigDecimal secondVal) throws Exception;
}
