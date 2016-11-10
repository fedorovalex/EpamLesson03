package task.matrix;

public class MatrixConverter {

    public Matrix convertArrayToMatrix(int[][] array) {
        if (!checkArray(array)) {
            throw new IllegalArgumentException("Неподходящая матрица.");
        }
        Matrix ret = new Matrix(getRowCount(array), getColumnCount(array));
        for (int row = 0; row < getRowCount(array); row++) {
            for (int column = 0; column < getColumnCount(array); column++) {
                ret.setElement(row, column, array[row][column]);
            }
        }
        return ret;
    }

    private static int getRowCount(int[][] elements) {
        return elements.length;
    }

    private static int getColumnCount(int[][] elements) {
        return elements[0].length;
    }

    private boolean checkArray(int[][] array) {

        boolean isValid = true;
        int columnCount = 0;

        if (array == null || array.length == 0 || array[0] == null) {
            isValid = false;
        } else {
            columnCount = array[0].length;
        }

        for (int i = 1; isValid && i < array.length; i++) {
            if (array[i] == null || array[i].length != columnCount) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }
}
