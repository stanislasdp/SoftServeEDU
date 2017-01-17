/*
 * This application is designed to convert any integer digit representation
 * (from -2147483648 to 2147483647) to conventional russian string representation.
 */
package com.softserve.edu.task05;

/**
 * ConvertDigit class is used to convert any integer digit to its string representation
 *
 * @author Stas Kiryan
 * @version 1.1
 */
public class ConvertDigit {
    private ConvertDigitParts convertDigits = new ConvertDigitParts();
    //ZERO is used in conditions to determine whether subsequent digits should be appended.
    private static final int ZERO = 0;
    //ONE is used to convert one char length digit to string and define first string digit element
    private static final int ONE = 1;
    //TWO is used to convert two char length digit to string and define second string digit element
    private static final int TWO = 2;
    //THREE is used to convert three chars length digit to string and define third string digit element
    private static final int THREE = 3;
    //FOUR is used to convert four chars length digit.
    private static final int FOUR = 4;
    //FIVE is used to convert five chars length digit.
    private static final int FIVE = 5;
    //SIX is used to convert six chars length digit.
    private static final int SIX = 6;
    //SEVEN is used to convert seven chars length digit.
    private static final int SEVEN = 7;
    //SEVEN is used to convert eight chars length digit.
    private static final int EIGHT = 8;
    //SEVEN is used to convert nine chars length digit.
    private static final int NINE = 9;
    //SEVEN is used to convert ten chars length digit.
    private static final int TEN = 10;
    //DIGITS_DOZENS_LIMIT_UP is used to define upper limit after which DOZENS digit values are used.
    private static final int DIGITS_DOZENS_LIMIT_UP = 19;
    //DIGITS_DOZENS_LIMIT_UP is used to define upper limit after which DOZEN digit values are used.
    private static final int DIGITS_DOZENS_LIMIT_LOW = 10;
    private static final String SPACE = " ";

    /**
     * Convert any int digit to String representation
     *
     * @param digit - input digit.
     * @return the - String representation in Russian.
     */
    public String convertAnyIntToStringRepres(int digit) {
        String digitStringRepresent = digit + "";
        String digWithoutZeroes = removeTrailingZeroes(digitStringRepresent);
        if (digWithoutZeroes.startsWith("-")) {
            convertDigits.addMinus();
            digWithoutZeroes = digWithoutZeroes.substring(1);
        }

        switch (digWithoutZeroes.length()) {
            case TEN:
                convertDigits.addBillions(digit);
                break;
            case NINE:
                convertDigits.addHundredsOfMillions(digit);
                break;
            case EIGHT:
                convertDigits.addDozensOfMillions(digit);
                break;
            case SEVEN:
                convertDigits.addMillions(digit);
                break;
            case SIX:
                convertDigits.addHundredsOfThousands(digit);
                break;
            case FIVE:
                convertDigits.addDozensOfThousands(digit);
                break;
            case FOUR:
                convertDigits.addThousands(digit);
                break;
            case THREE:
                convertDigits.addHundreds(digit);
                break;
            case TWO:
                convertDigits.addTwoDigits(digit);
                break;
            case ONE:
                convertDigits.addDigit(digit);
                break;
            default:
                throw new IllegalArgumentException("Not supported digit length");
        }
        return convertDigits.toString();
    }

    /**
     * ConvertDigitPart inner class is used to convert each part of digit to
     * string representation
     *
     * @author Stas Kiryan
     * @version 1.0
     */
    private class ConvertDigitParts {

        private StringBuilder stringBuilder = new StringBuilder(); //to store digit representation
        private boolean stopAddingToSB = false; //stop adding to String Builder

        /**
         * Convert digit that refers to UNIT (last digit) from whole passed
         * digit to to its string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addDigit(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, ONE);
            int firstDigit = Integer.parseInt(digitPart.substring(0, 1));
            stringBuilder.append(Digits.UNITS.getValues()[firstDigit + 1]);
            stringBuilder.append(SPACE);
        }

        /**
         * Convert digit that refers to DOZEN (digit between 11 and 19) to their
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addTwoDigitsLessThanTwenty(int fullDigit) {

            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, TWO);
            int secondDigit = Integer.parseInt(digitPart.substring(1, 2));
            stringBuilder.append(Digits.DOZEN.getValues()[secondDigit]);
            stringBuilder.append(SPACE);
        }

        /**
         * Convert digit that refers to DOZENS (digit between 20 and 99) to
         * their string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addTwoDigitsMoreThanTwenty(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, TWO);
            int firstDigit = Integer.parseInt(digitPart.substring(0, 1));
            stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - 1]);
            stringBuilder.append(SPACE);
            if (fullDigit % Digits.DOZENS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            addDigit(fullDigit);
        }

        /**
         * Convert digit that refers to any digit between 11 and 99) to its
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addTwoDigits(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, TWO);
            int digitForDozens = Integer.parseInt(digitPart);
            int secondDigit = Integer.parseInt(digitPart.substring(1, 2));
            if (digitForDozens > DIGITS_DOZENS_LIMIT_UP) {
                addTwoDigitsMoreThanTwenty(fullDigit);
            } else if (digitForDozens >= Digits.DOZENS.getDigitBasis()) {
                addTwoDigitsLessThanTwenty(fullDigit);
            } else {
                addDigit(secondDigit);
            }
        }

        /**
         * Convert digit that refers to hundreds position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addHundreds(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, THREE);
            int firstDigit = Integer.parseInt((digitPart.substring(0, 1)));
            stringBuilder.append(Digits.HUNDREDS.getValues()[firstDigit]);
            stringBuilder.append(SPACE);
            if (fullDigit % Digits.HUNDREDS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            addTwoDigits(fullDigit);
        }

        /**
         * Convert digit that refers to thousand position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addThousands(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, FOUR);
            if (fullDigit % Digits.THOUSANDS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            int firstDigit = Integer.parseInt(digitPart.substring(0, 1));
            stringBuilder.append(Digits.THOUSANDS.getValues()[firstDigit]);
            stringBuilder.append(SPACE);
            stringBuilder.append(Digits.THOUSANDS.getDigitSuffix(firstDigit));
            stringBuilder.append(SPACE);
            addHundreds(fullDigit);
        }

        /**
         * Convert digit that refers to dozens of thousand position to its
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addDozensOfThousands(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, FIVE);
            int firstTwoDigits = Integer.parseInt((digitPart.substring(0, 2)));
            if (firstTwoDigits > DIGITS_DOZENS_LIMIT_UP) {
                int firstDigit = Integer.parseInt(((digitPart.substring(0, 1))));
                stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - 1]);
                stringBuilder.append(SPACE);
                addThousands(fullDigit);

            } else if (firstTwoDigits >= DIGITS_DOZENS_LIMIT_LOW) {
                int secondDigit = Integer.parseInt(((digitPart.substring(1, 2))));
                stringBuilder.append(Digits.DOZEN.getValues()[secondDigit]);
                stringBuilder.append(SPACE);
                stringBuilder.append(Digits.THOUSANDS.getDigitSuffix());
                stringBuilder.append(SPACE);
                addHundreds(fullDigit);
            } else {
                addThousands(fullDigit);
            }

        }

        /**
         * Convert digit that refers to hundred of thousand position to its
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addHundredsOfThousands(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, SIX);
            int firstThreeDigits = Integer.parseInt(digitPart.substring(0, 3));
            if (firstThreeDigits == 0) {
                addHundreds(fullDigit);
                return;
            }
            int firsTDigit = Integer.parseInt(digitPart.substring(0, 1));
            stringBuilder.append(Digits.HUNDREDS.getValues()[firsTDigit]);
            stringBuilder.append(SPACE);
            if (firstThreeDigits % Digits.HUNDREDS.getDigitBasis() == 0) {
                stringBuilder.append(Digits.THOUSANDS.getDigitSuffix());
                stringBuilder.append(SPACE);
                addHundreds(fullDigit);
            } else {
                addDozensOfThousands(fullDigit);
            }
        }

        /**
         * Convert digit that refers to million position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addMillions(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, SEVEN);
            int fullDigitToMill = Integer.parseInt(digitPart);
            if (fullDigitToMill % Digits.MILLIONS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            int millionFirstDigit = Integer.parseInt(digitPart.substring(0, 1));
            stringBuilder.append(Digits.MILLIONS.getValues()[millionFirstDigit + 1]);
            stringBuilder.append(SPACE);
            stringBuilder.append(Digits.MILLIONS.getDigitSuffix(millionFirstDigit));
            stringBuilder.append(SPACE);
            addHundredsOfThousands(fullDigit);
        }

        /**
         * Convert digit that refers to dozens of million position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addDozensOfMillions(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            if (fullDigit % (TEN * Digits.MILLIONS.getDigitBasis()) == 0) {
                stopAddingToSB = true;
            }
            String digitPart = getDigPart(fullDigit, EIGHT);
            int firstTwoDigits = Integer.parseInt((digitPart.substring(0, 2)));
            if (firstTwoDigits > DIGITS_DOZENS_LIMIT_UP) {
                int firstDigit = Integer.parseInt(((digitPart.substring(0, 1))));
                stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - 1]);
                stringBuilder.append(SPACE);
                addMillions(fullDigit);
            } else if (firstTwoDigits >= DIGITS_DOZENS_LIMIT_LOW) {
                int secondDigit = Integer.parseInt((digitPart.substring(1, 2)));
                stringBuilder.append(Digits.DOZEN.getValues()[secondDigit]);
                stringBuilder.append(SPACE);
                stringBuilder.append(Digits.MILLIONS.getDigitSuffix());
                stringBuilder.append(SPACE);
                addHundredsOfThousands(fullDigit);
            }
            else {
                addMillions(fullDigit);
            }
        }

        /**
         * Convert digit that refers to hundred of million position to its
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addHundredsOfMillions(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            if (fullDigit % (TEN * TEN * Digits.MILLIONS.getDigitBasis()) == 0) {
                stopAddingToSB = true;
            }
            String digitPart = getDigPart(fullDigit, NINE);
            int firstThreeDigits = Integer.parseInt(digitPart.substring(0, 3));
            if (firstThreeDigits == 0) {
                addHundredsOfThousands(fullDigit);
                return;
            }
            int firsTDigit = Integer.parseInt(digitPart.substring(0, 1));
            stringBuilder.append(Digits.HUNDREDS.getValues()[firsTDigit]);
            stringBuilder.append(SPACE);
            if (firstThreeDigits % Digits.HUNDREDS.getDigitBasis() == 0) {
                stringBuilder.append(Digits.MILLIONS.getDigitSuffix());
                stringBuilder.append(SPACE);
                addHundredsOfThousands(fullDigit);
            } else {
                addDozensOfMillions(fullDigit);
            }
        }

        /**
         * Convert digit that refers to billion position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void addBillions(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            String digitPart = getDigPart(fullDigit, TEN);
            int digitToMill = Integer.parseInt(digitPart);
            if (digitToMill % Digits.BILLIONS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            int billionFirstDigit = Integer.parseInt(digitPart.substring(0, 1));
            stringBuilder.append(Digits.UNITS.getValues()[billionFirstDigit + 1]);
            stringBuilder.append(SPACE);
            stringBuilder.append(Digits.BILLIONS.getDigitSuffix(billionFirstDigit));
            stringBuilder.append(SPACE);
            addHundredsOfMillions(fullDigit);
        }

        /**
         * add minus sign (if present) to stringBuilder
         */
        private void addMinus() {
            stringBuilder.append(Digits.MINUS_SIGN);
            stringBuilder.append(SPACE);
        }

        /**
         * get digit part fro the end from passed full digit
         *
         * @param fullDigit - input entire digit.
         * @param partLengthFromEnd - digit part length from end
         * @return - digit part
         */
        private String getDigPart(int fullDigit, int partLengthFromEnd) {
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(partLengthFromEnd);
            methodSB.reverse();
            return methodSB.toString();
        }

        @Override
        public String toString() {
            return stringBuilder.toString().replaceAll("\\s+", " ").trim();
        }
    }

    /**
     * remove trailing zeroes from passed digit if in only zero in the
     * string nothing is removed representation and appends it to
     * stringBuilder
     *
     * @param digit - input entire digit.
     */
    private String removeTrailingZeroes(final String digit) {
        return digit.replaceFirst("^0+(?!$)", "");
    }
}