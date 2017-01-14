/*
 * This application is designed to convert any integer digit representation
 * (from -2147483648 to 2147483647) to conventional russian string representation
 */
package com.softserve.edu.task05;

/**
 * Digits enum is used to store digit representation for each particular digit
 * depending to which category it belongs.
 * it to the console
 * @author Stas kiryan
 * @version 1.0
 */
public enum Digits {
    /**
     * Units - used for single digit.
     */
    UNITS {
                @Override
                String[] getValues() {
                    return new String[] {"","ноль", "один", "два", "три", "четыре", "пять",
                            "шесть", "семь", "восемь", "девять"};
                }
            },

    /**
     * Dozen - used for dozens, dozens of thousands (if lower than 20), dozens of millions.
     */
    DOZEN {

                @Override
                String[] getValues() {
                    return new String[] {"десять", "одиннадцать", "двенадцать", "тринадцать",
                            "четырнадцать", "пятнадцать", "шестнадцать", "семьнадцать",
                            "восемнадцать", "девятнадцать"};
                }
            },

    /**
     * Dozens - used for dozens, dozens of thousands (if higher than 19), dozens of millions
     */
    DOZENS {
                @Override
                String[] getValues() {
                    return new String[] {"", "двадцать", "тридцать", "сорок",
                            "пятьдесят", "шестдесят", "семьдесят", "восемьдесят", "девяносто"};
                }
            },

    /**
     * Hundreds - used for hundreds, hundreds of thousands, hundsred of millions.
     */
    HUNDREDS {
                @Override
                String[] getValues() {
                    return new String[] {"", "сто", "двести", "триста", "четыреста",
                            "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
                }
            },

    /**
     * Thousands - used for thousands
     */
    THOUSANDS {
                @Override
                String[] getValues() {
                    return new String[] {"", "одна", "две", "три", "четыре",
                            "пять", "шесть", "семь", "восемь", "девять"};
                }
            };

    /**
     * return array of representations corresponing to each enum value.
     *
     * @return the string [ ]
     */
    abstract String[] getValues();
}
