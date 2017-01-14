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
    private ConvertDigitPart convertDigits = new ConvertDigitPart();
    private  boolean isMinusShouldBeAppended = false;
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
            default:    /*if digit length more than for billions thow exception*/
            	throw new IllegalArgumentException("Not supported digit length");
        }
        return convertDigits.toString();
    }

    /**
     * ConvertDigitPart class is used to convert each part of digit to string representation
     * @author Stas Kiryan
     * @version 1.0
     */
    private  class ConvertDigitPart {

        private StringBuilder stringBuilder = new StringBuilder();
        private boolean endAddToSb = false;

        private void convDigitToString(int fullDigit) {
        	if (!endAddToSb) {
                appendMinusIfPresent();
                StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
                helpSB.setLength(1);
                helpSB.reverse();
                int firstDigit = Integer.parseInt(helpSB.substring(0, 1));
                stringBuilder.append(Digits.UNITS.getValues()[firstDigit + 1]);
                stringBuilder.append(" ");
            }
        }

        private void convFirstDigitMoreThanTenToString(int fullDigit) {

            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
                helpSB.setLength(TWO);
                helpSB.reverse();
                int digitForDigit = Integer.parseInt(helpSB.toString().substring(1, 2));
                stringBuilder.append(Digits.DOZEN.getValues()[digitForDigit]);
                stringBuilder.append(" ");
            }
        }

        private void convDozensToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
                helpSB.setLength(TWO);
                helpSB.reverse();
                int firstDigit = Integer.parseInt(helpSB.substring(0, 1));
                stringBuilder.append(Digits.DOZENS.getValues()[firstDigit - 1]);
                stringBuilder.append(" ");
                if (fullDigit % 10 == 0) {
                    endAddToSb = true;
                }
                convDigitToString(fullDigit);
            }
        }

        private void convTwoDigitsToString(int fullDigit) {
            if (!endAddToSb) {
                StringBuilder helpSB = new StringBuilder().
                        append(fullDigit).reverse();
                helpSB.setLength(TWO);
                helpSB.reverse();
                int digitForDozens = Integer.parseInt((helpSB.toString()));
                int digitForDigit = Integer.parseInt(helpSB.substring(1, 2));
                if (digitForDozens > 19) {
                    convDozensToString(fullDigit);
                } else if (digitForDozens >= 10) {
                    convFirstDigitMoreThanTenToString(fullDigit);
                } else {
                    convDigitToString(digitForDigit);
                }
            }
        }

        private void convHundredsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
                helpSB.setLength(THREE);
                helpSB.reverse();
                int firstDigit = Integer.parseInt((helpSB.substring(0, 1)));
                stringBuilder.append(Digits.HUNDREDS.getValues()[firstDigit]);
                stringBuilder.append(" ");
                if (fullDigit % 100 == 0)  {
                    endAddToSb = true;
                }
                convTwoDigitsToString(fullDigit);
            }
        }

        private void convThousandsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
                helpSB.setLength(FOUR);
                helpSB.reverse();
                if (fullDigit % 1000 == 0)  {
                    endAddToSb = true;
                }
                int firstDigit = Integer.parseInt(helpSB.substring(0, 1));
                stringBuilder.append(Digits.THOUSANDS.getValues()[firstDigit]);
                stringBuilder.append(" ");
                if (firstDigit == 1) {
                    stringBuilder.append("тысяча");
                } else if (firstDigit > 1 && firstDigit < 5) {
                    stringBuilder.append("тысячи");
                } else {
                        stringBuilder.append("тысяч");
                }
                stringBuilder.append(" ");
                convHundredsToString(fullDigit);
            }
        }

        private void convDozensThousandToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder helpSB = new StringBuilder().append(fullDigit).reverse();
                helpSB.setLength(FIVE);
                helpSB.reverse();
                int intForChmethod = Integer.parseInt((helpSB.substring(0, 2)));
                if (intForChmethod > 19) {
                    int temp = Integer.parseInt(((helpSB.substring(0, 1))));
                    stringBuilder.append(Digits.DOZENS.getValues()[temp - 1]);
                    stringBuilder.append(" ");
                    convThousandsToString(fullDigit);

                } else if (intForChmethod >= 10) {
                    int temp = Integer.parseInt(((helpSB.substring(1, 2))));
                    stringBuilder.append(Digits.DOZEN.getValues()[temp]);
                    stringBuilder.append(" ");
                    stringBuilder.append("тысяч");
                    stringBuilder.append(" ");
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
                StringBuilder tempSBToRev  =  new StringBuilder().append(fullDigit).reverse();
                tempSBToRev.setLength(SIX);
                tempSBToRev.reverse();
                int firstThreeDigits = Integer.parseInt(tempSBToRev.substring(0, 3));

                if (firstThreeDigits == 0) {
                    convHundredsToString(fullDigit);
                    return;
                    /*when first three digits are zeroes, then go directly to hundreds*/
                }

                int firsTDigit = Integer.parseInt(tempSBToRev.substring(0, 1));
                stringBuilder.append(Digits.HUNDREDS.getValues()[firsTDigit]);
                stringBuilder.append(" ");
                if (firstThreeDigits % 100 == 0) {
                    stringBuilder.append("тысяч");
                    stringBuilder.append(" ");
                    convHundredsToString(fullDigit);
                } else {
                    convDozensThousandToString(fullDigit);
                }
            }

        }
        private void convMillionsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                StringBuilder tempSBToRev  =  new StringBuilder().append(fullDigit).reverse();
                tempSBToRev.setLength(SEVEN);
                tempSBToRev.reverse();
                int fullDigitToMill = Integer.parseInt(tempSBToRev.toString());

                if (fullDigitToMill % 1_000_000 == 0) {
                    endAddToSb = true;
                }
                int millionFirstDigit = Integer.parseInt(tempSBToRev.substring(0, 1));
                stringBuilder.append(Digits.UNITS.getValues()[millionFirstDigit + 1]);
                stringBuilder.append(" ");

                if (millionFirstDigit == 1) {
                    stringBuilder.append("миллион");
                } else if (millionFirstDigit  > 1 && millionFirstDigit < 5) {
                    stringBuilder.append("миллиона");
                } else {
                    stringBuilder.append("миллионов");
                }
                stringBuilder.append(" ");
                convHundredThousandToString(fullDigit);
            }
        }

        private void convDozensMillionsToString(int fullDigit) {
            if (!endAddToSb) {
            	appendMinusIfPresent();
                if (fullDigit % 10_000_000 == 0) {
                    endAddToSb =true;
                }
                StringBuilder tempSBToRev = new StringBuilder().append(fullDigit).reverse();
                tempSBToRev.setLength(EIGHT);
                tempSBToRev.reverse();

                int intForChmethod = Integer.parseInt((tempSBToRev.substring(0, 2)));
                if (intForChmethod > 19) {
                    int temp = Integer.parseInt(((tempSBToRev.substring(0, 1))));
                    stringBuilder.append(Digits.DOZENS.getValues()[temp - 1]);
                    stringBuilder.append(" ");
                    convMillionsToString(fullDigit);
                } else if (intForChmethod >= 10) {
                    int temp = Integer.parseInt((tempSBToRev.substring(1, 2)));
                    stringBuilder.append(Digits.DOZEN.getValues()[temp]);
                    stringBuilder.append(" ");
                    stringBuilder.append("миллионов");
                    stringBuilder.append(" ");
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
                if (fullDigit % 100_000_000 == 0) {
                    endAddToSb = true;
                }
                StringBuilder tempSBToRev  =  new StringBuilder().append(fullDigit).reverse();
                tempSBToRev.setLength(NINE);
                tempSBToRev.reverse();
                int firstThreeDigits = Integer.parseInt(tempSBToRev.substring(0, 3));

                if (firstThreeDigits == 0) {
                    convHundredThousandToString(fullDigit);
                    return;
                    /*when first three digits are zeroes, then go directly to hundreds*/
                }

                int firsTDigit = Integer.parseInt(tempSBToRev.substring(0, 1));
                stringBuilder.append(Digits.HUNDREDS.getValues()[firsTDigit]);
                stringBuilder.append(" ");
                if (firstThreeDigits % 100 == 0) {
                    stringBuilder.append("миллионов");
                    stringBuilder.append(" ");
                    convHundredThousandToString(fullDigit);
                } else {
                    convDozensMillionsToString(fullDigit);
                }
            }
        }

        private void convBillionsToString(int fullDigit) {

            if (!endAddToSb) {
                appendMinusIfPresent();
                StringBuilder tempSBToRev  =  new StringBuilder().append(fullDigit).reverse();
                tempSBToRev.setLength(TEN);
                tempSBToRev.reverse();
                int fullDigitToMill = Integer.parseInt(tempSBToRev.toString());

                if (fullDigitToMill % 1_000_000_000 == 0) {
                    endAddToSb = true;
                }
                int billionFirstDigit = Integer.parseInt(tempSBToRev.substring(0, 1));
                stringBuilder.append(Digits.UNITS.getValues()[billionFirstDigit + 1]);
                stringBuilder.append(" ");

                if (billionFirstDigit == 1) {
                    stringBuilder.append("миллиард");
                } else if (billionFirstDigit > 1 && billionFirstDigit < 4) {
                    stringBuilder.append("миллиарда");
                } else {
                    stringBuilder.append("миллиардов");
                }
                stringBuilder.append(" ");
                convHundredMillionsToString(fullDigit);
            }

        }

        private void appendMinusIfPresent() {
        	if (isMinusShouldBeAppended) {
        		stringBuilder.append("минус");
        		stringBuilder.append(" ");
        		isMinusShouldBeAppended = false;
        	}
        }

        @Override
        public String toString() {
            return stringBuilder.toString().replaceAll("\\s+", " ").trim();
        }
    }
}
