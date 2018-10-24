import Exceptions.UnsupportedOprationException;
import Operations.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ValueRange;
import java.util.HashMap;

public class CalcLogic {

    public BigDecimal firstVal = null;
    public BigDecimal secondVal = null;
    public boolean firstValPos = true;
    public boolean secondValPos = true;

    private BigDecimal result;
    private char operation;

    public boolean isSeparatorSet = false;
    private boolean isOperationSet = false;

    private HashMap<Character, Operation> OPERATIONS = new HashMap<Character, Operation>();

    CalcLogic() {
        this.OPERATIONS.put('+', new Addition());
        this.OPERATIONS.put('-', new Substraction());
        this.OPERATIONS.put('*', new Multiplication());
        this.OPERATIONS.put('/', new Division());
        this.OPERATIONS.put('%', new Modulo());
    }

    private boolean checkSize(BigDecimal val, String type) {
        int intPart = val.toPlainString().split("\\.")[0].length();
        int fractPart = val.toPlainString().split("\\.")[1].length();
        if (type.equals("int") & intPart > 9) {
            return false;
        }
        else return !(type.equals("fract") & fractPart > 9);
    }

    public void setValue(char operation) throws Exception {
        if (!this.isOperationSet & this.firstVal == null) {
            if (operation == '-') {
                this.firstValPos = false;
            }
            else if (operation == '+') {
                this.firstValPos = true;
            }
        }
        else if (this.isOperationSet & this.secondVal == null) {
            if (operation == '-') {
                this.secondValPos = false;
            }
            else if (operation == '+') {
                this.secondValPos = true;
            }
        }
        else if (!this.isOperationSet & this.secondVal == null) {
            this.operation = operation;
            this.isOperationSet = true;
        }
        else {
            this.getResult();
            this.operation = operation;
            this.isOperationSet = true;
        }
    }

    public void setValue(BigDecimal value) {
        // случай, когда хотим первое число сделать отрицательным
        if (this.firstVal == null) {
            if (this.firstValPos) {
                this.firstVal = value;
            }
            else {
                this.firstVal = value.negate();
            }
        }
        // работа с первым числом
        else if (!this.isOperationSet) {
            // с целой частью
            if (!this.isSeparatorSet) {
                // проверка на максимальный размер числа
                if (! this.checkSize(this.firstVal, "int")) return;
                // если первое число хотим сделать отрицательным (-33)
                if (this.firstVal.compareTo(BigDecimal.ZERO) < 0) {
                    this.firstVal = this.firstVal.multiply(BigDecimal.valueOf(10.0)).subtract(value).setScale(1, RoundingMode.HALF_UP);
                }
                else {
                    this.firstVal = this.firstVal.multiply(BigDecimal.valueOf(10.0)).add(value).setScale(1, RoundingMode.HALF_UP);
                }
            }
            // с дробной частью
            else {
                // проверка на максимальный размер числа
                if (! this.checkSize(this.firstVal, "fract")) return;
                // реализация добавления доп. нулей после запятой
                if (value.compareTo(BigDecimal.ZERO) == 0) {
                    this.firstVal = new BigDecimal(this.firstVal.toPlainString()+"0");
                }
                String fraction = this.firstVal.abs().toPlainString().split("\\.")[1];

                int tmpPower = (Float.parseFloat(fraction) == 0) ? (fraction.length() ): (fraction.length() + 1);
                if (this.firstVal.compareTo(BigDecimal.ZERO) < 0) {
                    this.firstVal = this.firstVal.subtract(value.divide(BigDecimal.valueOf(10).pow(tmpPower)));
                }
                else {
                    this.firstVal = this.firstVal.add(value.divide(BigDecimal.valueOf(10).pow(tmpPower)));
                    if (!this.firstValPos) this.firstVal = this.firstVal.negate();
                }
            }
        }
        // работа со вторым числом
        else {
            // во избежании null pointer exception
            if (this.secondVal == null) {
                if (this.secondValPos) {
                    this.secondVal = value;
                }
                else {
                    this.secondVal = value.negate();
                }
            }
            // с целой частью
            else if (!this.isSeparatorSet) {
                if (!this.secondValPos) this.secondVal = this.secondVal.negate();
                // проверка на максимальный размер числа
                if (! this.checkSize(this.secondVal, "int")) return;
                // если второе число хотим сделать отрицательным (-33)
                if (this.secondVal.compareTo(BigDecimal.ZERO) < 0) {
                    this.secondVal = this.secondVal.multiply(BigDecimal.valueOf(10.0)).subtract(value).setScale(1, RoundingMode.HALF_UP);
                }
                else {
                    this.secondVal = this.secondVal.multiply(BigDecimal.valueOf(10.0)).add(value).setScale(1, RoundingMode.HALF_UP);
                }
            }
            // с дробной частью
            else {
                // проверка на максимальный размер числа
                if (! this.checkSize(this.secondVal, "fract")) return;
                // реализация добавления доп. нулей после запятой
                if (value.compareTo(BigDecimal.ZERO) == 0) {
                    this.secondVal = new BigDecimal(this.secondVal.toPlainString()+"0");
                }
                String fraction = this.secondVal.abs().toPlainString().split("\\.")[1];
                int tmpPower = (Float.parseFloat(fraction) == 0) ? (fraction.length() ): (fraction.length() + 1);
                if (this.secondVal.compareTo(BigDecimal.ZERO) < 0) {
                    this.secondVal = this.secondVal.subtract(value.divide(BigDecimal.valueOf(10).pow(tmpPower)));
                }
                else {
                    this.secondVal = this.secondVal.add(value.divide(BigDecimal.valueOf(10).pow(tmpPower)));
                    if (!this.secondValPos) this.secondVal = this.secondVal.negate();
                }
            }
        }
    }

    public BigDecimal getResult() throws Exception {
        System.out.println(this.isOperationSet);
        System.out.println(this.operation);
        if (this.isOperationSet) {
            Operation requestedOperation = OPERATIONS.get(this.operation);
            if (requestedOperation != null) {
                this.result = requestedOperation.getResult(this.firstVal, this.secondVal);
                this.firstVal = this.result;
                this.secondVal = null;
                this.secondValPos = true;
                this.isOperationSet = false;
                return result;
            }
            else throw new UnsupportedOprationException();

        }
        else {
            return this.firstVal;
        }
    }

    public String getStringReprOfOperation() {
        // создаем переменные для вывода на экран (пустые строки по умолчанию)
        String firstValStr = "";
        String secondValStr = "";
        // если первое значение непустое
        if (this.firstVal != null) {
            // работа с дробной частью
            if (this.isSeparatorSet & this.secondVal == null) {
                String fraction = this.firstVal.abs().toPlainString().split("\\.")[1];
                // если дробная часть равно 0, последний 0 не выводим (экстетика)
                if (Float.parseFloat(fraction) == 0) {
                    firstValStr = this.firstVal.toPlainString().substring(0,this.firstVal.toPlainString().length()-1);
                }
                else {
                    firstValStr = this.firstVal.toPlainString();
                }
            }
            else {
                firstValStr = this.firstVal.stripTrailingZeros().toPlainString();
            }
        }
        if (this.secondVal != null) {
            if (this.isSeparatorSet) {
                String fraction = this.secondVal.abs().toPlainString().split("\\.")[1];
                if (Float.parseFloat(fraction) == 0) {
                    secondValStr = this.secondVal.toPlainString().substring(0,this.secondVal.toPlainString().length()-1);
                }
                else {
                    secondValStr = this.secondVal.toPlainString();
                }
            }
            else {
                secondValStr = this.secondVal.stripTrailingZeros().toPlainString();
            }
        }

        if (!this.isOperationSet & this.firstVal == null & !this.firstValPos) {
            return "-";
        }
        else if (this.isOperationSet & this.secondVal == null & !this.secondValPos)
        {
            return firstValStr + operation + "-";
        }
        else if (!this.isOperationSet & this.firstVal == null) {
            return firstValStr;
        }
        else {
            return firstValStr + operation + secondValStr;
        }
    }

}
