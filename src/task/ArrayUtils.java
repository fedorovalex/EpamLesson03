package task;

import java.util.Arrays;
import java.util.Random;

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
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
    }

    public static int[] shuffle(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            int indexReplacement = abs(random.nextInt()) % array.length;
            int valueReplacement = array[indexReplacement];
            array[indexReplacement] = array[i];
            array[i] = valueReplacement;
        }
        return array;
    }

    public static String getString(int[] array) {
        if (array == null) {
            return "Array is empty.";
        }
        if (array.length == 0) {
            return "The array contains no elements.";
        }
        String result = "";
        for (int i = 0; i < array.length - 1; i++) {
            result += array[i] + ";  ";
        }
        result += array[array.length - 1] + ".";
        return result;
    }

}
