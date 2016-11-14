package task.utils;

import java.util.Arrays;
import java.util.Random;
import task.utils.predicate.Predicate;

import static java.lang.Math.abs;

public class ArrayUtils {

    public static int[] truncationOrExpansion(int[] array, int newSize) {
        if (newSize < 0) {
            newSize = 0;
        }
        if (array == null) {
            return new int[newSize];
        }
        return Arrays.copyOf(array, newSize);
    }

    public static boolean equals(int[] first, int[] second) {
        if (first == null && second == null) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        if (first.length != second.length) {
            return false;
        }
        int[] copyFirst = Arrays.copyOf(first, first.length);
        int[] copySecond = Arrays.copyOf(second, second.length);
        Arrays.sort(copyFirst);
        Arrays.sort(copySecond);
        return Arrays.equals(copyFirst, copySecond);
    }

    public static void shuffle(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int indexReplacement = abs(random.nextInt(array.length));
            swap(array, indexReplacement, i);
        }
    }

    public static String writeInString(int[] array) {
        if (array == null) {
            return "Array is empty.";
        }
        if (array.length == 0) {
            return "The array contains no elements.";
        }
        String result = "";
        for (int i = 0; i < array.length - 1; i++) {
            result += array[i] + "; ";
        }
        result += array[array.length - 1] + ".";
        return result;
    }

    public static int[] filter(int[] array, Predicate predicate) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        int[] result = new int[array.length];
        int resultNumber = 0;
        for (int arrayValue: array) {
            if (predicate.checkCondition(arrayValue)) {
                result[resultNumber] = arrayValue;
                resultNumber++;
            }
        }
        return Arrays.copyOf(result, resultNumber);
    }

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int replacement = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = replacement;
    }

}
