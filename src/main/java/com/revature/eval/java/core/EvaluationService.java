package com.revature.eval.java.core;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluationService
{

    /**
     * 1. Without using the StringBuilder or StringBuffer class, write a method that
     * reverses a String. Example: reverse("example"); -> "elpmaxe"
     *
     * @param string
     * @return
     */
    public String reverse(String string)
    {
        char[] reversed = new char[string.length()];
        for (int i = reversed.length - 1, j = 0; i >= 0; i--, j++)
        {
            reversed[j] = string.charAt(i);
        }
        return new String(reversed);
    }

    /**
     * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
     * Acronyms)! Help generate some jargon by writing a program that converts a
     * long name like Portable Network Graphics to its acronym (PNG).
     *
     * @param phrase
     * @return
     */
    public String acronym(String phrase)
    {
        StringTokenizer tokenizer = new StringTokenizer(phrase, " -");
        StringBuilder stringBuilder = new StringBuilder();

        while (tokenizer.hasMoreTokens())
        {
            stringBuilder.append(tokenizer.nextToken().charAt(0));
        }

        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 4. Given a word, compute the scrabble score for that word.
     * <p>
     * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
     * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
     * "cabbage" should be scored as worth 14 points:
     * <p>
     * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
     * point for E And to total:
     * <p>
     * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
     *
     * @param string
     * @return
     */
    public int getScrabbleScore(String string)
    {
        HashMap<String, Integer> letterValues = new HashMap<>();

        // Map letters to their value
        letterValues.put("A", 1);
        letterValues.put("E", 1);
        letterValues.put("I", 1);
        letterValues.put("O", 1);
        letterValues.put("U", 1);
        letterValues.put("L", 1);
        letterValues.put("N", 1);
        letterValues.put("R", 1);
        letterValues.put("S", 1);
        letterValues.put("T", 1);

        letterValues.put("D", 2);
        letterValues.put("G", 2);

        letterValues.put("B", 3);
        letterValues.put("C", 3);
        letterValues.put("M", 3);
        letterValues.put("P", 3);

        letterValues.put("F", 4);
        letterValues.put("H", 4);
        letterValues.put("V", 4);
        letterValues.put("W", 4);
        letterValues.put("Y", 4);

        letterValues.put("K", 5);

        letterValues.put("J", 8);
        letterValues.put("X", 8);

        letterValues.put("Q", 10);
        letterValues.put("Z", 10);

        int score = 0;
        for (String letter : string.split(""))
        {
            score += letterValues.get(letter.toUpperCase());
        }


        return score;
    }

    /**
     * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
     * <p>
     * The North American Numbering Plan (NANP) is a telephone numbering system used
     * by many countries in North America like the United States, Canada or Bermuda.
     * All NANP-countries share the same international country code: 1.
     * <p>
     * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
     * Area code, commonly known as area code, followed by a seven-digit local
     * number. The first three digits of the local number represent the exchange
     * code, followed by the unique four-digit number which is the subscriber
     * number.
     * <p>
     * The format is usually represented as
     * <p>
     * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
     * from 0 through 9.
     * <p>
     * Your task is to clean up differently formatted telephone numbers by removing
     * punctuation and the country code (1) if present.
     * <p>
     * For example, the inputs
     * <p>
     * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
     * the output
     * <p>
     * 6139950253
     * <p>
     * Note: As this exercise only deals with telephone numbers used in
     * NANP-countries, only 1 is considered a valid country code.
     */
    public String cleanPhoneNumber(String string)
    {
        String number = string.replaceAll("[^0-9]", "");
        if (number.length() > 11 || number.length() < 10)
        {
            throw new IllegalArgumentException();
        }
        if (number.length() == 11)
        {
            number = number.substring(1);
        }
        return number;
    }

    /**
     * 6. Given a phrase, count the occurrences of each word in that phrase.
     * <p>
     * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
     * free: 1
     *
     * @param string
     * @return
     */
    public Map<String, Integer> wordCount(String string)
    {
        HashMap<String, Integer> wordFrequency = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(string, " ,\n");
        while (tokenizer.hasMoreTokens())
        {
            String word = tokenizer.nextToken();
            if (wordFrequency.containsKey(word))
            {
                wordFrequency.put(word, wordFrequency.get(word) + 1);
            } else
            {
                wordFrequency.put(word, 1);
            }
        }
        return wordFrequency;
    }

    /**
     * 8. Implement a program that translates from English to Pig Latin.
     * <p>
     * Pig Latin is a made-up children's language that's intended to be confusing.
     * It obeys a few simple rules (below), but when it's spoken quickly it's really
     * difficult for non-children (and non-native speakers) to understand.
     * <p>
     * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
     * the word. Rule 2: If a word begins with a consonant sound, move it to the end
     * of the word, and then add an "ay" sound to the end of the word. There are a
     * few more rules for edge cases, and there are regional variants too.
     * <p>
     * See http://en.wikipedia.org/wiki/Pig_latin for more details.
     *
     * @param string
     * @return
     */
    public String toPigLatin(String string)
    {
        Set<String> vowels = new HashSet<>(Arrays.asList("a", "e", "i", "o", "u"));
        Set<String> edgeCases = new HashSet<>(Arrays.asList("qu"));

        StringBuilder pigLatinPhrase = new StringBuilder();
        for (String word : string.split("[^A-Za-z]"))
        {
            StringBuilder pigLatinWord = new StringBuilder();

            StringBuilder beginning = new StringBuilder();
            int i;
            if (edgeCases.contains(word.substring(0, 2))) // Check for special edge cases
            {
                beginning.append(word.substring(0, 2));
                i = 2;
            } else  // Grab the constants from the front to append at the end
            {
                for (i = 0; !vowels.contains(Character.toString(string.charAt(i))); i++)
                {
                    beginning.append(word.charAt(i));
                }
            }

            pigLatinWord.append(word.substring(i, word.length()));
            pigLatinWord.append(beginning).append("ay");

            pigLatinPhrase.append(pigLatinWord).append(" ");
        }
        return pigLatinPhrase.toString().trim();
    }

    /**
     * 9. An Armstrong number is a number that is the sum of its own digits each
     * raised to the power of the number of digits.
     * <p>
     * For example:
     * <p>
     * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
     * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
     * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
     * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
     * a number is an Armstrong number.
     *
     * @param input
     * @return
     */
    public boolean isArmstrongNumber(int input)
    {
        String[] digits = Integer.toString(input).split("");
        int sum = 0;
        for (String digit : digits)
        {
            sum += Math.pow(Integer.parseInt(digit), digits.length);
        }

        return sum == input;
    }

    /**
     * 10. Compute the prime factors of a given natural number.
     * <p>
     * A prime number is only evenly divisible by itself and 1.
     * <p>
     * Note that 1 is not a prime number.
     *
     * @param l
     * @return
     */
    public List<Long> calculatePrimeFactorsOf(long l)
    {
        List<Long> primeFactors = new ArrayList<>();
        while (l % 2 == 0)
        {
            primeFactors.add(2L);
            l /= 2;
        }

        for (long i = 3; i <= Math.sqrt(l); i += 2)
        {
            while (l % i == 0)
            {
                primeFactors.add(i);
                l /= i;
            }
        }

        if (l > 2L)
        {
            primeFactors.add(l);
        }
        return primeFactors;
    }

    /**
     * 12. Given a number n, determine what the nth prime is.
     * <p>
     * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
     * that the 6th prime is 13.
     * <p>
     * If your language provides methods in the standard library to deal with prime
     * numbers, pretend they don't exist and implement them yourself.
     *
     * @param i
     * @return
     */
    public int calculateNthPrime(int i)
    {
        int primeCounter = 0;
        int currentNumber = 1;
        if (i <= 0)
        {
            throw new IllegalArgumentException();
        }
        while (primeCounter < i)
        {
            currentNumber++;
            if (calculatePrimeFactorsOf(currentNumber).size() == 1)
            {  // If the number of prime factors of the number is 1, that means that the number is prime since the factor is itself
                primeCounter++;
            }
        }
        return currentNumber;
    }

    /**
     * 15. The ISBN-10 verification process is used to validate book identification
     * numbers. These normally contain dashes and look like: 3-598-21508-8
     * <p>
     * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
     * a digit or an X only). In the case the check character is an X, this
     * represents the value '10'. These may be communicated with or without hyphens,
     * and can be checked for their validity by the following formula:
     * <p>
     * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
     * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
     * otherwise it is invalid.
     * <p>
     * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
     * and get:
     * <p>
     * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
     * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
     *
     * @param string
     * @return
     */
    public boolean isValidIsbn(String string)
    {
        String isbn = string.replaceAll("[^0-9X]", "");

        // Check if isbn has invalid length
        if (isbn.length() != 10)
        {
            return false;
        }

        // Check if X exists and if it does, it is the last character
        if (isbn.contains("X"))
        {
            if (isbn.indexOf("X") != 9)
            {
                return false;
            }
        }

        int sumCheck = 0;

        for (int i = 0; i < isbn.length(); i++)
        {
            if (isbn.charAt(i) == 'X')
            {
                sumCheck += 10 * (isbn.length() - i);
            } else
            {
                sumCheck += Integer.parseInt(Character.toString(isbn.charAt(i))) * (isbn.length() - i);
            }
        }


        return sumCheck % 11 == 0;
    }

    /**
     * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
     * gramma, "every letter") is a sentence using every letter of the alphabet at
     * least once. The best known English pangram is:
     * <p>
     * The quick brown fox jumps over the lazy dog.
     * <p>
     * The alphabet used consists of ASCII letters a to z, inclusive, and is case
     * insensitive. Input will not contain non-ASCII symbols.
     *
     * @param string
     * @return
     */
    public boolean isPangram(String string)
    {
        Set<Character> alphabet = new HashSet<>();

        for (char letter : string.toLowerCase().replaceAll("[^a-z]", "").toCharArray())
        {
            alphabet.add(letter);
        }

        return alphabet.size() == 26;
    }

    /**
     * 17. Calculate the moment when someone has lived for 10^9 seconds.
     * <p>
     * A gigasecond is 109 (1,000,000,000) seconds.
     *
     * @param given
     * @return
     */
    public Temporal getGigasecondDate(Temporal given)
    {
        // TODO Write an implementation for this method declaration
        LocalDateTime start;

        if (given instanceof LocalDate) {
            start = ((LocalDate) given).atStartOfDay(); // Converts LocalDate to LocalDateTime so we can add seconds to it
        } else  {
            start = (LocalDateTime)given;
        }

        return start.plusSeconds(1000000000L);
    }

    /**
     * 18. Given a number, find the sum of all the unique multiples of particular
     * numbers up to but not including that number.
     * <p>
     * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
     * get 3, 5, 6, 9, 10, 12, 15, and 18.
     * <p>
     * The sum of these multiples is 78.
     *
     * @param i   Max integer value for multiples (excluded)
     * @param set A set of integers to determine the multiples of
     * @return
     */
    public int getSumOfMultiples(int i, int[] set)
    {
        Set<Integer> multiples = new HashSet<>();
        int multiplesSum = 0;

        for (int number : set)
        {
            for (int j = 1; number * j < i; j++)
            {
                multiples.add(number * j);
            }
        }

        for (int multiple : multiples)
        {
            multiplesSum += multiple;
        }

        return multiplesSum;
    }

    /**
     * 19. Given a number determine whether or not it is valid per the Luhn formula.
     * <p>
     * The Luhn algorithm is a simple checksum formula used to validate a variety of
     * identification numbers, such as credit card numbers and Canadian Social
     * Insurance Numbers.
     * <p>
     * The task is to check if a given string is valid.
     * <p>
     * Validating a Number Strings of length 1 or less are not valid. Spaces are
     * allowed in the input, but they should be stripped before checking. All other
     * non-digit characters are disallowed.
     * <p>
     * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
     * the Luhn algorithm is to double every second digit, starting from the right.
     * We will be doubling
     * <p>
     * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
     * then subtract 9 from the product. The results of our doubling:
     * <p>
     * 8569 2478 0383 3437 Then sum all of the digits:
     * <p>
     * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
     * then the number is valid. This number is valid!
     * <p>
     * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
     * digits, starting from the right
     * <p>
     * 7253 2262 5312 0539 Sum the digits
     * <p>
     * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
     * this number is not valid.
     *
     * @param string
     * @return
     */
    public boolean isLuhnValid(String string)
    {
        if (string.length() <= 1)
        {
            return false;
        }

        Pattern pattern = Pattern.compile("[^0-9 ]");      // Pattern to find alphabet characters
        Matcher matcher = pattern.matcher(string);
        if (matcher.find())
        {
            return false;
        }

        String[] digits = string.replaceAll("[^0-9]", "").split("");
        int sum = 0;

        for (int i = 0; i < digits.length; i++)
        {
            if (i % 2 == digits.length % 2)                 // Targets every second digit starting from the right
            {
                int num = Integer.parseInt(digits[i]) * 2;  // Doubles the digit value
                if (num > 9)
                {                                           // Subtracts doubled value by 9 if greater than 9
                    num -= 9;
                }
                sum += num;                                 // Add processed number to sum

            } else
            {
                sum += Integer.parseInt(digits[i]);
            }
        }

        return sum % 10 == 0;
    }

    /**
     * 20. Parse and evaluate simple math word problems returning the answer as an
     * integer.
     * <p>
     * Add two numbers together.
     * <p>
     * What is 5 plus 13?
     * <p>
     * 18
     * <p>
     * Now, perform the other three operations.
     * <p>
     * What is 7 minus 5?
     * <p>
     * 2
     * <p>
     * What is 6 multiplied by 4?
     * <p>
     * 24
     * <p>
     * What is 25 divided by 5?
     * <p>
     * 5
     *
     * @param string
     * @return
     */
    public int solveWordProblem(String string)
    {
        String[] equation = string.split(" ");
        int result = 0;

        // Doesn't work if string is not exactly formatted as in the JavaDoc examples
        if (string.contains("plus")){
            result = Integer.parseInt(equation[2]) + Integer.parseInt(equation[4].replaceAll("\\?",""));

        } else if (string.contains("minus")) {
            result = Integer.parseInt(equation[2]) - Integer.parseInt(equation[4].replaceAll("\\?",""));

        } else if (string.contains("multiplied by")) {
            result = Integer.parseInt(equation[2]) * Integer.parseInt(equation[5].replaceAll("\\?",""));

        } else if (string.contains("divided by")) {
            result = Integer.parseInt(equation[2]) / Integer.parseInt(equation[5].replaceAll("\\?",""));

        }

        return result;
    }

    /**
     * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
     * equilateral triangle has all three sides the same length. An isosceles
     * triangle has at least two sides the same length. (It is sometimes specified
     * as having exactly two sides the same length, but for the purposes of this
     * exercise we'll say at least two.) A scalene triangle has all sides of
     * different lengths.
     */
    static class Triangle
    {
        private double sideOne;
        private double sideTwo;
        private double sideThree;

        public Triangle()
        {
            super();
        }

        public Triangle(double sideOne, double sideTwo, double sideThree)
        {
            this();
            this.sideOne = sideOne;
            this.sideTwo = sideTwo;
            this.sideThree = sideThree;
        }

        public double getSideOne()
        {
            return sideOne;
        }

        public void setSideOne(double sideOne)
        {
            this.sideOne = sideOne;
        }

        public double getSideTwo()
        {
            return sideTwo;
        }

        public void setSideTwo(double sideTwo)
        {
            this.sideTwo = sideTwo;
        }

        public double getSideThree()
        {
            return sideThree;
        }

        public void setSideThree(double sideThree)
        {
            this.sideThree = sideThree;
        }

        public boolean isEquilateral()
        {
            return sideOne == sideTwo && sideTwo == sideThree;
        }

        public boolean isIsosceles()
        {
            return sideOne == sideTwo || sideTwo == sideThree || sideOne == sideThree;
        }

        public boolean isScalene()
        {
            return !isEquilateral() && !isIsosceles();
        }

    }

    /**
     * 7. Implement a binary search algorithm.
     * <p>
     * Searching a sorted collection is a common task. A dictionary is a sorted list
     * of word definitions. Given a word, one can find its definition. A telephone
     * book is a sorted list of people's names, addresses, and telephone numbers.
     * Knowing someone's name allows one to quickly find their telephone number and
     * address.
     * <p>
     * If the list to be searched contains more than a few items (a dozen, say) a
     * binary search will require far fewer comparisons than a linear search, but it
     * imposes the requirement that the list be sorted.
     * <p>
     * In computer science, a binary search or half-interval search algorithm finds
     * the position of a specified input value (the search "key") within an array
     * sorted by key value.
     * <p>
     * In each step, the algorithm compares the search key value with the key value
     * of the middle element of the array.
     * <p>
     * If the keys match, then a matching element has been found and its index, or
     * position, is returned.
     * <p>
     * Otherwise, if the search key is less than the middle element's key, then the
     * algorithm repeats its action on the sub-array to the left of the middle
     * element or, if the search key is greater, on the sub-array to the right.
     * <p>
     * If the remaining array to be searched is empty, then the key cannot be found
     * in the array and a special "not found" indication is returned.
     * <p>
     * A binary search halves the number of items to check with each iteration, so
     * locating an item (or determining its absence) takes logarithmic time. A
     * binary search is a dichotomic divide and conquer search algorithm.
     */
    static class BinarySearch<T extends Comparable<T>>
    {
        private List<T> sortedList;

        public BinarySearch(List<T> sortedList)
        {
            super();
            this.sortedList = sortedList;
        }

        public int indexOf(T t)
        {
            int minIndex = 0;
            int maxIndex = sortedList.size() - 1;
            int middleIndex;
            T middleIndexValue;

            while (true)
            {
                middleIndex = (minIndex + maxIndex) / 2;
                middleIndexValue = sortedList.get(middleIndex);
                if (middleIndexValue.compareTo(t) == 0)
                { // Returns the index if value is found
                    return middleIndex;
                } else if (minIndex == maxIndex)
                { // We've exhausted our search
                    return -1;
                } else if (middleIndexValue.compareTo(t) > 0)
                { // Value must exist before middle index
                    maxIndex = middleIndex - 1;
                } else if (middleIndexValue.compareTo(t) < 0)
                { // Value must exist after middle index
                    minIndex = middleIndex + 1;
                }
            }
        }

        public List<T> getSortedList()
        {
            return sortedList;
        }

        public void setSortedList(List<T> sortedList)
        {
            this.sortedList = sortedList;
        }

    }

    /**
     * 11. Create an implementation of the rotational cipher, also sometimes called
     * the Caesar cipher.
     * <p>
     * The Caesar cipher is a simple shift cipher that relies on transposing all the
     * letters in the alphabet using an integer key between 0 and 26. Using a key of
     * 0 or 26 will always yield the same output due to modular arithmetic. The
     * letter is shifted for as many values as the value of the key.
     * <p>
     * The general notation for rotational ciphers is ROT + <key>. The most commonly
     * used rotational cipher is ROT13.
     * <p>
     * A ROT13 on the Latin alphabet would be as follows:
     * <p>
     * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
     * stronger than the Atbash cipher because it has 27 possible keys, and 25
     * usable keys.
     * <p>
     * Ciphertext is written out in the same formatting as the input including
     * spaces and punctuation.
     * <p>
     * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
     * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
     * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
     * quick brown fox jumps over the lazy dog.
     */
    static class RotationalCipher
    {
        private int key;

        public RotationalCipher(int key)
        {
            super();
            this.key = key;
        }

        public String rotate(String string)
        {
            if ( key == 0){
                return string;
            }
            ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));

            StringBuilder cypherText = new StringBuilder();

            for(int i = 0; i < string.length(); i++) {
                Character character = Character.toLowerCase(string.charAt(i)); //

                if (alphabet.contains(character) ){
                    if (Character.isUpperCase(string.charAt(i))){
                        cypherText.append(Character.toUpperCase(alphabet.get((alphabet.indexOf(character) + key) % 26)));
                    } else {
                        cypherText.append(alphabet.get((alphabet.indexOf(character) + key) % 26));
                    }
                } else {
                    cypherText.append(string.charAt(i));
                }
            }

            return cypherText.toString();
        }

    }

    /**
     * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
     * system created in the Middle East.
     * <p>
     * The Atbash cipher is a simple substitution cipher that relies on transposing
     * all the letters in the alphabet such that the resulting alphabet is
     * backwards. The first letter is replaced with the last letter, the second with
     * the second-last, and so on.
     * <p>
     * An Atbash cipher for the Latin alphabet would be as follows:
     * <p>
     * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
     * very weak cipher because it only has one possible key, and it is a simple
     * monoalphabetic substitution cipher. However, this may not have been an issue
     * in the cipher's time.
     * <p>
     * Ciphertext is written out in groups of fixed length, the traditional group
     * size being 5 letters, and punctuation is excluded. This is to make it harder
     * to guess things based on word boundaries.
     * <p>
     * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
     * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
     */
    static class AtbashCipher
    {
        private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
        private static String encodingAlphabet = "zyxwvutsrqponmlkjihgfedcba";

        /**
         * Question 13
         *
         * @param string
         * @return
         */
        public static String encode(String string)
        {
            StringBuilder encodedText = new StringBuilder();
            string = string.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); // Removes special characters and converts to lowercase


            for (int i = 0; i < string.length(); i += 5){
                for (int j = i; j < string.length() && j < i + 5; j++)
                {
                    String character = Character.toString(string.charAt(j));
                    if(alphabet.contains(character) ){
                        encodedText.append(encodingAlphabet.charAt(alphabet.indexOf(character)));
                    } else {
                        encodedText.append(string.charAt(j));
                    }
                }
                encodedText.append(" ");
            }
            return encodedText.toString().trim();
        }

        /**
         * Question 14
         *
         * @param string
         * @return
         */
        public static String decode(String string)
        {
            StringBuilder decodedText = new StringBuilder();
            string = string.replaceAll(" ", "");

            for(Character c : string.toCharArray()){
                if(alphabet.contains(Character.toString(c))) {
                    decodedText.append(alphabet.charAt(encodingAlphabet.indexOf(c)));
                } else {
                    decodedText.append(c);
                }
            }

            return decodedText.toString();
        }
    }

}
