package task.matrix;


import task.vector.Vector;
import task.vector.VectorConverter;

public class Matrix {
    private int[][] matrix;

    public Matrix(int rowCount, int columnCount) {
        if (rowCount <= 0) {
            rowCount = 1;
        }
        if (columnCount < 0) {
            columnCount = 0;
        }
        this.matrix = new int[rowCount][columnCount];
    }

    public int getElement(int rowNumber, int columnNumber) {
        if (!checkElementNumber(rowNumber, columnNumber)) {
            throw new IllegalArgumentException("Неверныая позиция.");
        }
        return this.matrix[rowNumber][columnNumber];
    }

    public void setElement(int rowNumber, int columnNumber, int value) {
        if (!checkElementNumber(rowNumber, columnNumber)) {
            throw new IllegalArgumentException("Неверныая позиция.");
        }
        this.matrix[rowNumber][columnNumber] = value;
    }

    public Matrix add(Matrix second) {
        if (getRowCount() != second.getRowCount() || getColumnCount() != second.getColumnCount()) {
            throw new IllegalArgumentException("Матрицы разных размеров.");
        }
        Matrix result = new Matrix(getRowCount(), getColumnCount());
        for (int rowNumber = 0; rowNumber < getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < getColumnCount(); columnNumber++) {
                int firstElement = this.matrix[rowNumber][columnNumber];
                int secondElement = second.getElement(rowNumber, columnNumber);
                result.setElement(rowNumber, columnNumber, firstElement + secondElement);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix second) {
        if (getColumnCount() != second.getRowCount()) {
            throw new IllegalArgumentException("Умножение не определено.");
        }
        Matrix result = new Matrix(getRowCount(), second.getColumnCount());
        for (int rowNumber = 0; rowNumber < getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < second.getColumnCount(); columnNumber++) {
                result.setElement(rowNumber, columnNumber, multiplyRowOnColumn(rowNumber, columnNumber, second));
            }
        }
        return result;
    }

    public int findDeterminant() {
        if (getRowCount() != getColumnCount()) {
            throw new IllegalArgumentException("Матрица не квадратная.");
        }
        if (getRowCount() == 0) {
            return 0;
        }
        return findDeterminantMinor(this.matrix);
    }

    public int getRowCount() {
        return this.matrix.length;
    }

    public int getColumnCount() {
        return this.matrix[0].length;
    }

    private int findDeterminantMinor(int[][] minor) {
        int level = minor.length;
        int ROW_NUMBER_EXPANSION = 1;
        if (level == 1) {
            return minor[0][0];
        }
        int result = 0;
        for (int ColumnNumberExpansion = 0; ColumnNumberExpansion < level; ColumnNumberExpansion++) {
            int sign = (int)Math.pow(-1, ROW_NUMBER_EXPANSION + ColumnNumberExpansion);
            int determinantMinor = findDeterminantMinor(getMinor(minor, ROW_NUMBER_EXPANSION, ColumnNumberExpansion));
            result += sign * minor[ROW_NUMBER_EXPANSION][ColumnNumberExpansion] * determinantMinor;
        }
        return result;
    }

    private int[][] getMinor(int[][] array, int rowNumberSkip, int columnNumberSkip) {
        int level = array.length;
        int[][] result = new int[level - 1][level - 1];
        int rowNumberResult = 0;
        int columnNumberResult;
        for (int rowNumberSource = 0; rowNumberSource < level; rowNumberSource++) {
            if (rowNumberSource == rowNumberSkip) {
                continue;
            }
            columnNumberResult = 0;
            for (int columnNumberSource = 0; columnNumberSource < level; columnNumberSource++) {
                if (columnNumberSource != columnNumberSkip) {
                    result[rowNumberResult][columnNumberResult] = array[rowNumberSource][columnNumberSource];
                    columnNumberResult++;
                }
            }
            rowNumberResult++;
        }
        return result;
    }

    private boolean checkElementNumber (int rowNumber, int columnNumber) {
        return checkRowNumber(rowNumber) && checkColumnNumber(columnNumber);
    }
    private boolean checkRowNumber (int rowNumber) {
        return 0 <= rowNumber && rowNumber < getRowCount();
    }
    private boolean checkColumnNumber (int columnNumber) {
        return 0 <= columnNumber && columnNumber < getColumnCount();
    }

    private int[] getRow(int rowNumber) {
        return this.matrix[rowNumber];
    }

    private int[] getColumn(Matrix matrix, int columnNumber) {
        int[] result = new int[matrix.getRowCount()];
        for (int rowNumber = 0; rowNumber < matrix.getRowCount(); rowNumber++) {
            result[rowNumber] = matrix.getElement(rowNumber, columnNumber);
        }
        return result;
    }

    private int multiplyRowOnColumn(int rowNumber, int columnNumber, Matrix second) {
        VectorConverter converter = new VectorConverter();
        Vector rowMatrix = converter.convertArrayToVector(getRow(rowNumber));
        Vector columnMatrix = converter.convertArrayToVector(getColumn(second, columnNumber));
        return rowMatrix.multiply(columnMatrix);
    }
}
