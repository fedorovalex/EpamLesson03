package task.vector;

public class VectorConverter {

    public Vector convertArrayToVector(int[] array) {
        if (!checkArray(array)) {
            throw new IllegalArgumentException("Пустой массив.");
        }
        Vector ret = new Vector(getSize(array));
        for (int i = 0; i < getSize(array); i++) {
            ret.setElement(i, array[i]);
        }
        return ret;
    }

    private static int getSize(int[] array) {
        return array.length;
    }

    private boolean checkArray(int[] array) {
        return array != null && array.length > 0;
    }
}
