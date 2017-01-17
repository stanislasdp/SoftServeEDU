/*
 * This application is designed to convert any integer digit representation
 * (from -2147483648 to 2147483647) to conventional russian string representation.
 */
package com.softserve.edu.task05;

/**
 * ConvertDigits class is used to convert any integer digit to its string representation
 *
 * @author Stas Kiryan
 * @version 1.1
 */
public class ConvertDigits {
    private ConvertDigitParts convertDigits = new ConvertDigitParts();
    private boolean isMinusShouldBeAppended = false;
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
        if (digitStringRepresent.startsWith("-")) {
            /* check whether digit starts with minus */
            isMinusShouldBeAppended = true;
            digitStringRepresent = digitStringRepresent.substring(1);
        }

        String digWithRemovTrailZeroes = convertDigits.removeTrailingZeroes(digitStringRepresent);
        switch (digWithRemovTrailZeroes.length()) {
            case TEN:
                convertDigits.convBillionsToString(digit);
                break;
            case NINE:
                convertDigits.convHundredMillionsToString(digit);
                break;
            case EIGHT:
                convertDigits.convDozensMillionsToString(digit);
                break;
            case SEVEN:
                convertDigits.convMillionsToString(digit);
                break;
            case SIX:
                convertDigits.convHundredThousandToString(digit);
                break;
            case FIVE:
                convertDigits.convDozensThousandToString(digit);
                break;
            case FOUR:
                convertDigits.convThousandsToString(digit);
                break;
            case THREE:
                convertDigits.convHundredsToString(digit);
                break;
            case TWO:
                convertDigits.convTwoDigitsToString(digit);
                break;
            case ONE:
                convertDigits.convDigitToString(digit);
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
        private void convDigitToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder helpSB = new StringBuilder().append(fullDigit)
                    .reverse();
            /* help stringBuilder that is used to get UNIT digit */
            helpSB.setLength(ONE);
            helpSB.reverse();
            int firstDigit = Integer.parseInt(helpSB.substring(0, 1));
            /* reverse stringBuilder and first digit from it */
            stringBuilder.append(Digits.UNITS.getValues()[firstDigit + 1]);
            /*
             * append corresponding UNITS digit representation to stringBulder
             */
            stringBuilder.append(SPACE);
        }

        /**
         * Convert digit that refers to DOZEN (digit between 11 and 19) to their
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convFirstDigitMoreThanTenToString(int fullDigit) {

            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
            helpSB.setLength(TWO);
            helpSB.reverse();
            int secondDigit = Integer.parseInt(helpSB.toString().substring(1, 2));
            stringBuilder.append(Digits.DOZEN.getValues()[secondDigit]);
            /*
             * append corresponding DOZEN digit representation to stringBulder
             */
            stringBuilder.append(SPACE);
        }

        /**
         * Convert digit that refers to DOZENS (digit between 20 and 99) to
         * their string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convDozensToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder methodSb = new StringBuilder().append(fullDigit)
                    .reverse();
            methodSb.setLength(TWO);
            methodSb.reverse();
            int firstDigit = Integer.parseInt(methodSb.substring(0, 1));
            stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - 1]);
            stringBuilder.append(SPACE);
            if (fullDigit % Digits.DOZENS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            convDigitToString(fullDigit);
        }

        /**
         * Convert digit that refers to any digit between 11 and 99) to its
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convTwoDigitsToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(TWO);
            methodSB.reverse();
            int digitForDozens = Integer.parseInt((methodSB.toString()));
            int secondDigit = Integer.parseInt(methodSB.substring(1, 2));
            if (digitForDozens > DIGITS_DOZENS_LIMIT_UP) {
                convDozensToString(fullDigit);
            } else if (digitForDozens >= Digits.DOZENS.getDigitBasis()) {
                convFirstDigitMoreThanTenToString(fullDigit);
            } else {
                convDigitToString(secondDigit);
            }
        }

        /**
         * Convert digit that refers to hundreds position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convHundredsToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder methodSB = new StringBuilder().append(fullDigit)
                    .reverse();
            methodSB.setLength(THREE);
            methodSB.reverse();
            int firstDigit = Integer.parseInt((methodSB.substring(0, 1)));
            stringBuilder.append(Digits.HUNDREDS.getValues()[firstDigit]);
            stringBuilder.append(SPACE);
            if (fullDigit % Digits.HUNDREDS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            convTwoDigitsToString(fullDigit);
        }

        /**
         * Convert digit that refers to thousand position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convThousandsToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(FOUR);
            methodSB.reverse();
            if (fullDigit % Digits.THOUSANDS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            int firstDigit = Integer.parseInt(methodSB.substring(0, 1));
            stringBuilder.append(Digits.THOUSANDS.getValues()[firstDigit]);
            stringBuilder.append(SPACE);
            stringBuilder.append(Digits.THOUSANDS.getDigitSuffix(firstDigit));
            /* append thousands digit suffix representation depending on sex */
            stringBuilder.append(SPACE);
            convHundredsToString(fullDigit);
        }

        /**
         * Convert digit that refers to dozens of thousand position to its
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convDozensThousandToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(FIVE);
            methodSB.reverse();
            int firstTwoDigits = Integer.parseInt((methodSB.substring(0, 2)));
            if (firstTwoDigits > DIGITS_DOZENS_LIMIT_UP) {
                int firstDigit = Integer.parseInt(((methodSB.substring(0, 1))));
                /* firstDigit is used to parse first digit for thousands */
                stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - 1]);
                stringBuilder.append(SPACE);
                convThousandsToString(fullDigit);

            } else if (firstTwoDigits >= DIGITS_DOZENS_LIMIT_LOW) {
                int secondDigit = Integer.parseInt(((methodSB.substring(1, 2))));
                stringBuilder.append(Digits.DOZEN.getValues()[secondDigit]);
                stringBuilder.append(SPACE);
                stringBuilder.append(Digits.THOUSANDS.getDigitSuffix());
                /* append plural thousands digit representation */
                stringBuilder.append(SPACE);
                convHundredsToString(fullDigit);
            } else {
                convThousandsToString(fullDigit);
            }

        }

        /**
         * Convert digit that refers to hundred of thousand position to its
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convHundredThousandToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(SIX);
            methodSB.reverse();
            int firstThreeDigits = Integer.parseInt(methodSB.substring(0, 3));

            if (firstThreeDigits == 0) {
                convHundredsToString(fullDigit);
                return;
            }
            int firsTDigit = Integer.parseInt(methodSB.substring(0, 1));
            stringBuilder.append(Digits.HUNDREDS.getValues()[firsTDigit]);
            stringBuilder.append(SPACE);
            if (firstThreeDigits % Digits.HUNDREDS.getDigitBasis() == 0) {
                stringBuilder.append(Digits.THOUSANDS.getDigitSuffix());
                stringBuilder.append(SPACE);
                convHundredsToString(fullDigit);
            } else {
                convDozensThousandToString(fullDigit);
                // dozens of thousands are calculated otherwise
            }
        }

        /**
         * Convert digit that refers to million position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convMillionsToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(SEVEN);
            methodSB.reverse();
            int fullDigitToMill = Integer.parseInt(methodSB.toString());

            if (fullDigitToMill % Digits.MILLIONS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            int millionFirstDigit = Integer.parseInt(methodSB.substring(0, 1));
            stringBuilder.append(Digits.MILLIONS.getValues()[millionFirstDigit + 1]);
            stringBuilder.append(SPACE);
            stringBuilder.append(Digits.MILLIONS.getDigitSuffix(millionFirstDigit));
            stringBuilder.append(SPACE);
            convHundredThousandToString(fullDigit);
        }

        /**
         * Convert digit that refers to dozens of million position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convDozensMillionsToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            if (fullDigit % (TEN * Digits.MILLIONS.getDigitBasis()) == 0) {
                stopAddingToSB = true;
            }
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(EIGHT);
            methodSB.reverse();

            int firstTwoDigits = Integer.parseInt((methodSB.substring(0, 2)));
            if (firstTwoDigits > DIGITS_DOZENS_LIMIT_UP) {
                int firstDigit = Integer.parseInt(((methodSB.substring(0, 1))));
                stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - 1]);
                stringBuilder.append(SPACE);
                convMillionsToString(fullDigit);
            } else if (firstTwoDigits >= DIGITS_DOZENS_LIMIT_LOW) {
                int secondDigit = Integer.parseInt((methodSB.substring(1, 2)));
                stringBuilder.append(Digits.DOZEN.getValues()[secondDigit]);
                stringBuilder.append(SPACE);
                /* append plural millions digit suffix representation */
                stringBuilder.append(Digits.MILLIONS.getDigitSuffix());
                stringBuilder.append(SPACE);
                convHundredThousandToString(fullDigit);
            }
            else {
                convMillionsToString(fullDigit);
            }
        }

        /**
         * Convert digit that refers to hundred of million position to its
         * string representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convHundredMillionsToString(int fullDigit) {
            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            if (fullDigit % (TEN * TEN * Digits.MILLIONS.getDigitBasis()) == 0) {
                stopAddingToSB = true;
            }
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(NINE);
            methodSB.reverse();
            int firstThreeDigits = Integer.parseInt(methodSB.substring(0, 3));
            if (firstThreeDigits == 0) {
                convHundredThousandToString(fullDigit);
                return;
            }
            int firsTDigit = Integer.parseInt(methodSB.substring(0, 1));

            stringBuilder.append(Digits.HUNDREDS.getValues()[firsTDigit]);
            stringBuilder.append(SPACE);
            if (firstThreeDigits % Digits.HUNDREDS.getDigitBasis() == 0) {
                stringBuilder.append(Digits.MILLIONS.getDigitSuffix());
                stringBuilder.append(SPACE);
                convHundredThousandToString(fullDigit);
            } else {
                convDozensMillionsToString(fullDigit);
            }
        }

        /**
         * Convert digit that refers to billion position to its string
         * representation and appends it to stringBuilder
         *
         * @param fullDigit - input entire digit.
         */
        private void convBillionsToString(int fullDigit) {

            if (stopAddingToSB) {
                return;
            }
            appendMinusIfPresent();
            StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
            methodSB.setLength(TEN);
            methodSB.reverse();
            int fullDigitToMill = Integer.parseInt(methodSB.toString());
            if (fullDigitToMill % Digits.BILLIONS.getDigitBasis() == 0) {
                stopAddingToSB = true;
            }
            int billionFirstDigit = Integer.parseInt(methodSB.substring(0, 1));
            stringBuilder.append(Digits.UNITS.getValues()[billionFirstDigit + 1]);
            stringBuilder.append(SPACE);
            stringBuilder.append(Digits.BILLIONS.getDigitSuffix(billionFirstDigit));
            stringBuilder.append(SPACE);
            convHundredMillionsToString(fullDigit);
        }

        /**
         * add minus sign (if present) to stringBuilder
         */
        private void appendMinusIfPresent() {
            if (isMinusShouldBeAppended) {
                stringBuilder.append(Digits.MINUS_SIGN);
                stringBuilder.append(SPACE);
                isMinusShouldBeAppended = false;
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

        public StringBuilder revertDigitAndGetDigitPartDepOnLength(final String digit ,int partLength) {

        }

        @Override
        public String toString() {
            return stringBuilder.toString().replaceAll("\\s+", " ").trim();
        }
    }
}