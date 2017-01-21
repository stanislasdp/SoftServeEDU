/*
 * This application is designed to convert any integer digit representation
 * (from -2147483648 to 2147483647) to conventional russian string representation
 */
package com.softserve.edu.task05;

/**
 * Digits enum is used to store digit representation for each particular digit
 * depending to which category it belongs.
 * it to the console
 *
 * @author Stas Kiryan
 * @version 1.0
 */
public enum Digits {
    /**
     * Units - used for single digit.
     */
    UNITS {
        @Override
        String[] getValues() {
            return new String[]{"", "ноль", "один", "два", "три", "четыре",
                    "пять", "шесть", "семь", "восемь", "девять"};
        }

        int getDigitBasis() {
            return 1;
        }

        @Override
        String getDigitSuffix(int count) {
            throw new IllegalArgumentException("operation is not supported");
        }

        @Override
        String getDigitSuffix() {
            throw new IllegalArgumentException("operation is not supported");
        }
    },

    /**
     * Dozen - used for dozens, dozens of thousands (if lower than 20)".
     */
    DOZEN {
        @Override
        String[] getValues() {
            return new String[]{"десять", "одиннадцать", "двенадцать", "тринадцать",
                    "четырнадцать", "пятнадцать", "шестнадцать", "семьнадцать",
                    "восемнадцать", "девятнадцать"};
        }

        int getDigitBasis() {
            return 10;
        }

        @Override
        String getDigitSuffix(int count) {
            throw new IllegalArgumentException("operation is not supported");
        }

        @Override
        String getDigitSuffix() {
            throw new IllegalArgumentException("operation is not supported");
        }

    },


    /**
     * Dozens - used for dozens, dozens of thousands (if higher than 19)"
     */
    DOZENS {
        @Override
        String[] getValues() {
            return new String[]{"", "двадцать", "тридцать", "сорок", "пятьдесят",
                    "шестдесят", "семьдесят", "восемьдесят", "девяносто"};
        }

        int getDigitBasis() {
            return 10;
        }

        @Override
        String getDigitSuffix(int count) {
            throw new IllegalArgumentException("operation is not supported");
        }

        @Override
        String getDigitSuffix() {
            throw new IllegalArgumentException("operation is not supported");
        }
    },

    /**
     * Hundreds - used for hundreds.
     */
    HUNDREDS {
        @Override
        String[] getValues() {
            return new String[]{"", "сто", "двести", "триста", "четыреста", "пятьсот",
                    "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        }

        int getDigitBasis() {
            return 100;
        }

        @Override
        String getDigitSuffix(int count) {
            throw new IllegalArgumentException("operation is not supported");
        }

        @Override
        String getDigitSuffix() {
            throw new IllegalArgumentException("operation is not supported");
        }
    },

    /**
     * Thousands - used for thousands
     */
    THOUSANDS {
        @Override
        String[] getValues() {
            return new String[]{"", "одна", "две", "три", "четыре",
                    "пять", "шесть", "семь", "восемь", "девять"};
        }

        int getDigitBasis() {
            return 1000;
        }

        @Override
        String getDigitSuffix(int count) {
            if (count == 1) {
                return "тысяча";
            } else if (count >= 1 && count <= 4) {
                return "тысячи";
            } else if ((count > 4 && count <= 9) || count == 0) {
                return getDigitSuffix();
            }
            throw new IllegalArgumentException();
        }

        @Override
        String getDigitSuffix() {
            return "тысяч";
        }
    },

    /**
     * MILLIONS  - used for millions.
     */
    MILLIONS {
        @Override
        String[] getValues() {
            return UNITS.getValues();
        }

        @Override
        int getDigitBasis() {
            return 100_000_0;
        }

        @Override
        String getDigitSuffix(int count) {
            if (count == 1) {
                return "миллион";
            } else if (count >= 1 && count <= 4) {
                return "миллиона";
            } else if (count > 4 && count <= 9) {
                return getDigitSuffix();
            }
            throw new IllegalArgumentException("digit not supported");
        }

        @Override
        String getDigitSuffix() {
            return "миллионов";
        }
    },

    /**
     * BILLIONS - used for billions.
     */
    BILLIONS {
        @Override
        String[] getValues() {
            return UNITS.getValues();
        }

        @Override
        int getDigitBasis() {
            return 1_000_000_000;
        }

        @Override
        String getDigitSuffix(int count) {

            if (count == 1) {
                return "миллиард";
            } else if (count > 1 && count <= 4) {
                return "миллиарда";
            } else if (count > 4) {
                return getDigitSuffix();
            }
            throw new IllegalArgumentException("digit not supported");

        }

        @Override
        String getDigitSuffix() {
            return "миллиардов";
        }
    };


    public static final String MINUS_SIGN = "минус";


    /**
     * return array of representations corresponding to each enum value.
     *
     * @return the string [ ]
     */
    abstract String[] getValues();

    /**
     * return array of representations corresponding to each enum value.
     *
     * @return digit position to calculate specific digit for current enum value[ ]
     */
    abstract int getDigitBasis();

    /**
     * overloaded version of getDigitSuffix()
     * return always digit suffix depend on digit "sex" or plural
     * @return  - String representation
     */
    abstract String getDigitSuffix(int count);

    /**
     * return return digit suffix plural representation
     * (Enum's implementation may throw IllegalArgumentException if this operation
     * is not applicable for used enum)
     * @throws   - implementation should throw IllegalArgumentException
     * if passed digit not supported
     * @return  - String representation
     */
    abstract String getDigitSuffix();

}
