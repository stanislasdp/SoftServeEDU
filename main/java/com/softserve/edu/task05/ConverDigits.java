/*
 * This application is designed to convert any integer digit representation
 * (from -2147483648 to 2147483647) to conventional russian string representation.
 */
package com.softserve.edu.task05;

/**
 * ConverDigits class is used to convert digit to its string representation
 * @author Stas kiryan
 * @version 1.0
 */
public class ConverDigits {
    private ConvertDigitParts convertDigits = new ConvertDigitParts();
    private  boolean isMinusShouldBeAppended = false;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int TEN = 10;
    private static final int DIGITS_DOZENS_LIMIT_UP = 19;
    private static final int DIGITS_DOZENS_LIMIT_LOW = 10;
    private static final String WHITE_SPACE = " ";


    /**
     * Convert any int digit to String representation
     *
     * @param digit - input digit.
     * @return the - String representation in Russian.
     */
    public String convertAnyIntToStringRepres(int digit) {

    	String digitStringRepresent = digit + "";
    	if (digitStringRepresent.startsWith("-")) {
            /*check whether digit starts with minus*/
    		isMinusShouldBeAppended = true;
    		digitStringRepresent = digitStringRepresent.substring(1);
    	}
        int digitLength = digitStringRepresent.length();

        switch (digitLength) {
            case TEN:   /*in case of billions*/
                convertDigits.convBillionsToString(digit);
                break;
            case NINE:  /*in case of hundred of millions*/
                convertDigits.convHundredMillionsToString(digit);
                break;
            case EIGHT: /*in case of dozens of millions*/
                convertDigits.convDozensMillionsToString(digit);
                break;
            case SEVEN: /*in case of millions*/
                convertDigits.convMillionsToString(digit);
                break;
            case SIX:   /*in case of hundred of thousands*/
                convertDigits.convHundredThousandToString(digit);
                break;
            case FIVE:  /*in case of dozens of thousands*/
                convertDigits.convDozensThousandToString(digit);
                break;
            case FOUR:  /*in case of thousands*/
                convertDigits.convThousandsToString(digit);
                break;
            case THREE: /*in case of hundreds*/
                convertDigits.convHundredsToString(digit);
                break;
            case TWO:   /*in case of dozens*/
                convertDigits.convTwoDigitsToString(digit);
                break;
            case ONE:   /*in case of single digit*/
                convertDigits.convDigitToString(digit);
                break;
            default:    /*if digit length more than for billions throw exception*/
            	throw new IllegalArgumentException("Not supported digit length");
        }
        return convertDigits.toString();
    }

    /**
     * ConvertDigitPart class is used to convert each part of digit to string representation
     * @author Stas Kiryan
     * @version 1.0
     */
    private class ConvertDigitParts {

        private StringBuilder stringBuilder = new StringBuilder();
        private boolean endAddToSb = false;

        private void convDigitToString(int fullDigit) {
        	if (!endAddToSb) {
                appendMinusIfPresent();
                StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
                helpSB.setLength(ONE);
                helpSB.reverse();
                int firstDigit = Integer.parseInt(helpSB.substring(ZERO, ONE));
                stringBuilder.append(Digits.UNITS.getValues()[firstDigit + ONE]);
                stringBuilder.append(WHITE_SPACE);
            }
        }

        private void convFirstDigitMoreThanTenToString(int fullDigit) {

            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
                helpSB.setLength(TWO);
                helpSB.reverse();
                int digitForDigit = Integer.parseInt(helpSB.toString().substring(ONE, TWO));
                stringBuilder.append(Digits.DOZEN.getValues()[digitForDigit]);
                stringBuilder.append(WHITE_SPACE);
            }
        }

        private void convDozensToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder methodSb = new StringBuilder().append(fullDigit).reverse();
                methodSb.setLength(TWO);
                methodSb.reverse();
                int firstDigit = Integer.parseInt(methodSb.substring(ZERO, ONE));
                stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - ONE]);
                stringBuilder.append(WHITE_SPACE);
                if (fullDigit % Digits.DOZENS.getDigitPos() == ZERO) {
                    endAddToSb = true;
                }
                convDigitToString(fullDigit);
            }
        }

        private void convTwoDigitsToString(int fullDigit) {
            if (!endAddToSb) {
                StringBuilder methodSB = new StringBuilder().
                        append(fullDigit).reverse();
                methodSB.setLength(TWO);
                methodSB.reverse();
                int digitForDozens = Integer.parseInt((methodSB.toString()));
                int digitForDigit = Integer.parseInt(methodSB.substring(ONE, TWO));
                if (digitForDozens > DIGITS_DOZENS_LIMIT_UP) {
                    convDozensToString(fullDigit);
                } else if (digitForDozens >= Digits.DOZENS.getDigitPos()) {
                    convFirstDigitMoreThanTenToString(fullDigit);
                } else {
                    convDigitToString(digitForDigit);
                }
            }
        }

        private void convHundredsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
                methodSB.setLength(THREE);
                methodSB.reverse();
                int firstDigit = Integer.parseInt((methodSB.substring(ZERO, ONE)));
                stringBuilder.append(Digits.HUNDREDS.getValues()[firstDigit]);
                stringBuilder.append(WHITE_SPACE);
                if (fullDigit % Digits.HUNDREDS.getDigitPos() == ZERO)  {
                    endAddToSb = true;
                }
                convTwoDigitsToString(fullDigit);
            }
        }

        private void convThousandsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder methodSB= new StringBuilder().append(fullDigit).reverse();
                methodSB.setLength(FOUR);
                methodSB.reverse();
                if (fullDigit % Digits.THOUSANDS.getDigitPos() == ZERO)  {
                    endAddToSb = true;
                }
                int firstDigit = Integer.parseInt(methodSB.substring(ZERO, ONE));
                stringBuilder.append(Digits.THOUSANDS.getValues()[firstDigit]);
                stringBuilder.append(WHITE_SPACE);
                stringBuilder.append(Digits.THOUSANDS.getDigitRepresent(firstDigit));
                stringBuilder.append(WHITE_SPACE);
                convHundredsToString(fullDigit);
            }
        }

        private void convDozensThousandToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
                methodSB.setLength(FIVE);
                methodSB.reverse();
                int intForChmethod = Integer.parseInt((methodSB.substring(ZERO, TWO)));
                if (intForChmethod > DIGITS_DOZENS_LIMIT_UP) {
                    int temp = Integer.parseInt(((methodSB.substring(ZERO, ONE))));
                    stringBuilder.append(Digits.DOZENS.getValues()[temp - ONE]);
                    stringBuilder.append(WHITE_SPACE);
                    convThousandsToString(fullDigit);

                } else if (intForChmethod >= DIGITS_DOZENS_LIMIT_LOW) {
                    int temp = Integer.parseInt(((methodSB.substring(ONE, TWO))));
                    stringBuilder.append(Digits.DOZEN.getValues()[temp]);
                    stringBuilder.append(WHITE_SPACE);
                    stringBuilder.append(Digits.THOUSANDS.getDigitRepresent());
                    stringBuilder.append(WHITE_SPACE);
                    if (!endAddToSb) {
                        convHundredsToString(fullDigit);
                    }
                } else {
                    convThousandsToString(fullDigit);
                }
            }
        }

        private void convHundredThousandToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
                methodSB.setLength(SIX);
                methodSB.reverse();
                int firstThreeDigits = Integer.parseInt(methodSB.substring(ZERO, THREE));

                if (firstThreeDigits == ZERO) {
                    convHundredsToString(fullDigit);
                    return;
                    /*when first three digits are zeroes, then go directly to hundreds*/
                }

                int firsTDigit = Integer.parseInt(methodSB.substring(ZERO, ONE));
                stringBuilder.append(Digits.HUNDREDS.getValues()[firsTDigit]);
                stringBuilder.append(WHITE_SPACE);
                if (firstThreeDigits % Digits.HUNDREDS.getDigitPos() == ZERO) {
                    stringBuilder.append(Digits.THOUSANDS.getDigitRepresent());
                    stringBuilder.append(WHITE_SPACE);
                    convHundredsToString(fullDigit);
                } else {
                    convDozensThousandToString(fullDigit);
                }
            }
        }

        private void convMillionsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder methodSB =  new StringBuilder().append(fullDigit).reverse();
                methodSB .setLength(SEVEN);
                methodSB .reverse();
                int fullDigitToMill = Integer.parseInt(methodSB .toString());

                if (fullDigitToMill % Digits.MILLIONS.getDigitPos() == ZERO) {
                    endAddToSb = true;
                }
                int millionFirstDigit = Integer.parseInt(methodSB.substring(ZERO, ONE));
                stringBuilder.append(Digits.MILLIONS.getValues()[millionFirstDigit + ONE]);
                stringBuilder.append(WHITE_SPACE);
                stringBuilder.append(Digits.MILLIONS.getDigitRepresent(millionFirstDigit));
                stringBuilder.append(WHITE_SPACE);
                convHundredThousandToString(fullDigit);
            }
        }

        private void convDozensMillionsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                if (fullDigit % (TEN * Digits.MILLIONS.getDigitPos()) == ZERO) {
                    endAddToSb =true;
                }
                StringBuilder methodSB  = new StringBuilder().append(fullDigit).reverse();
                methodSB .setLength(EIGHT);
                methodSB .reverse();

                int intForChmethod = Integer.parseInt((methodSB.substring(ZERO, TWO)));
                if (intForChmethod > DIGITS_DOZENS_LIMIT_UP) {
                    int firstDigit = Integer.parseInt(((methodSB.substring(ZERO, ONE))));
                    stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - ONE]);
                    stringBuilder.append(WHITE_SPACE);
                    convMillionsToString(fullDigit);
                } else if (intForChmethod >= Digits.DOZEN.getDigitPos()) {
                    int temp = Integer.parseInt((methodSB.substring(ONE, TWO)));
                    stringBuilder.append(Digits.DOZEN.getValues()[temp]);
                    stringBuilder.append(WHITE_SPACE);
                    stringBuilder.append(Digits.MILLIONS.getDigitRepresent());
                    stringBuilder.append(WHITE_SPACE);
                    if (!endAddToSb) {
                        convHundredThousandToString(fullDigit);
                    }
                } else {
                    convMillionsToString(fullDigit);
                }
            }
        }

        private void convHundredMillionsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                if (fullDigit % (TEN * TEN * Digits.MILLIONS.getDigitPos()) == ZERO) {
                    endAddToSb = true;
                }
                StringBuilder methodSB = new StringBuilder().append(fullDigit).reverse();
                methodSB.setLength(NINE);
                methodSB.reverse();
                int firstThreeDigits = Integer.parseInt(methodSB.substring(ZERO, THREE));

                if (firstThreeDigits == ZERO) {
                    convHundredThousandToString(fullDigit);
                    return;
                    /*when first three digits are zeroes, then go directly to hundreds*/
                }

                int firsTDigit = Integer.parseInt(methodSB .substring(ZERO, ONE));
                stringBuilder.append(Digits.HUNDREDS.getValues()[firsTDigit]);
                stringBuilder.append(WHITE_SPACE);
                if (firstThreeDigits % Digits.HUNDREDS.getDigitPos() == ZERO) {
                    stringBuilder.append(Digits.MILLIONS.getDigitRepresent());
                    stringBuilder.append(WHITE_SPACE);
                    convHundredThousandToString(fullDigit);
                } else {
                    convDozensMillionsToString(fullDigit);
                }
            }
        }

        private void convBillionsToString(int fullDigit) {

            if (!endAddToSb) {
                appendMinusIfPresent();
                StringBuilder methodSB  = new StringBuilder().append(fullDigit).reverse();
                methodSB .setLength(TEN);
                methodSB.reverse();
                int fullDigitToMill = Integer.parseInt(methodSB.toString());

                if (fullDigitToMill % Digits.BILLIONS.getDigitPos() == ZERO) {
                    endAddToSb = true;
                }
                int billionFirstDigit = Integer.parseInt(methodSB.substring(ZERO, ONE));
                stringBuilder.append(Digits.UNITS.getValues()[billionFirstDigit + ONE]);
                stringBuilder.append(WHITE_SPACE);
                stringBuilder.append(Digits.BILLIONS.getDigitRepresent(billionFirstDigit));
                stringBuilder.append(WHITE_SPACE);
                convHundredMillionsToString(fullDigit);
            }

        }

        private void appendMinusIfPresent() {
        	if (isMinusShouldBeAppended) {
        		stringBuilder.append(Digits.MINUS_SIGN);
        		stringBuilder.append(WHITE_SPACE);
        		isMinusShouldBeAppended = false;
        	}
        }

        @Override
        public String toString() {
            return stringBuilder.toString().replaceAll("\\s+", " ").trim();
        }
    }
}
