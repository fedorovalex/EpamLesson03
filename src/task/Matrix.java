package task;


public class Matrix {
    private int rowCount;
    private int columnCount;
    private int[][] matrix;

    public Matrix(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        if (!checkSize(rowCount)) {
            this.rowCount = 0;
        }
        if (!checkSize(columnCount)) {
            this.columnCount = 0;
        }
        this.matrix = new int[rowCount][columnCount];
    }

    public int getElement(int rowNumber, int columnNumber) {
        if (!checkRowNumber(rowNumber)) {
            throw new IllegalArgumentException("Неверный номер строки.");
        }
        if (!checkColumnNumber(columnNumber)) {
            throw new IllegalArgumentException("Неверный номер колонки.");
        }
        return this.matrix[rowNumber][columnNumber];
    }

    public void setElement(int rowNumber, int columnNumber, int value) {
        if (!checkRowNumber(rowNumber)) {
            throw new IllegalArgumentException("Неверный номер строки.");
        }
        if (!checkColumnNumber(columnNumber)) {
            throw new IllegalArgumentException("Неверный номер колонки.");
        }
        this.matrix[rowNumber][columnNumber] = value;
    }

    public void setMatrix(int[][] array) {
        if (!checkArray(array)) {
            throw new IllegalArgumentException("Неподходящая матрица.");
        }
        if (!checkRowNumber(array.length)) {
            throw new IllegalArgumentException("Неверное количество строк.");
        }
        if (!checkColumnNumber(array[0].length)) {
            throw new IllegalArgumentException("Неверное количество колонок.");
        }
        for (int rowNumber = 0; rowNumber < rowCount; rowNumber++) {
            for (int columnNumber = 0; columnNumber < columnCount; columnNumber++) {
                this.matrix[rowNumber][columnNumber] = array[rowNumber][columnNumber];
            }
        }
    }

    public Matrix toFold(Matrix second) {
        if (this.rowCount != second.getRowCount() || this.columnCount != second.getColumnCount()) {
            throw new IllegalArgumentException("Матрицы разных размеров.");
        }
        Matrix result = new Matrix(this.rowCount, this.columnCount);
        for (int rowNumber = 0; rowNumber < this.rowCount; rowNumber++) {
            for (int columnNumber = 0; columnNumber < this.columnCount; columnNumber++) {
                int firstElement = this.matrix[rowNumber][columnNumber];
                int secondElement = second.getElement(rowNumber, columnNumber);
                result.setElement(rowNumber, columnNumber, firstElement + secondElement);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix second) {
        if (this.columnCount != second.getRowCount()) {
            throw new IllegalArgumentException("Умножение не определено.");
        }
        Matrix result = new Matrix(this.rowCount, second.getColumnCount());
        for (int rowNumber = 0; rowNumber < this.rowCount; rowNumber++) {
            for (int columnNumber = 0; columnNumber < second.getColumnCount(); columnNumber++) {
                int value = multiplyVector(this.getRow(rowNumber), getColumn(second, columnNumber));
                result.setElement(rowNumber, columnNumber, value);
            }
        }
        return result;
    }

    public int getDeterminant() {
        if (this.rowCount != this.columnCount) {
            throw new IllegalArgumentException("Матрица не квадратная.");
        }
        if (this.rowCount == 0) {
            return 0;
        }
        return getDeterminantMinor(this.matrix);
    }

    private int getDeterminantMinor(int[][] minor) {
        int level = minor.length;
        int ROW_NUMBER_EXPANSION = 1;
        if (level == 1) {
            return minor[0][0];
        }
        int result = 0;
        for (int ColumnNumberExpansionsN = 0; ColumnNumberExpansionsN < level; ColumnNumberExpansionsN++) {
            int sign = (int)Math.pow(-1, ROW_NUMBER_EXPANSION + ColumnNumberExpansionsN);
            int determinantMinor = getDeterminantMinor(getMinor(minor, ROW_NUMBER_EXPANSION, ColumnNumberExpansionsN));
            result += sign * minor[ROW_NUMBER_EXPANSION][ColumnNumberExpansionsN] * determinantMinor;
        }
        return result;
    }

    private int[][] getMinor(int[][] array, int rowNumberSkip, int columnNumberSkip) {
        int level = array.length;
        int[][] result = new int[level - 1][level - 1];
        int rowNumberResult = 0;
        int columnNumberResult;
        for (int rowNumberSource = 0; rowNumberSource < level; rowNumberSource++) {
            if (rowNumberSource != rowNumberSkip) {
                columnNumberResult = 0;
                for (int columnNumberSource = 0; columnNumberSource < level; columnNumberSource++) {
                    if (columnNumberSource != columnNumberSkip) {
                        result[rowNumberResult][columnNumberResult] = array[rowNumberSource][columnNumberSource];
                        columnNumberResult++;
                    }
                }
                rowNumberResult++;
            }
        }
        return result;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    private boolean checkSize(int num) {
        return num > 0;
    }
    private boolean checkRowNumber (int rowNumber) {
        return 0 <= rowNumber && rowNumber < this.rowCount;
    }
    private boolean checkColumnNumber (int columnNumber) {
        return 0 <= columnNumber && columnNumber < this.columnCount;
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

    private int multiplyVector(int[] left, int[] right) {
        int result = 0;
        for (int i = 0; i < left.length; i++) {
            result += left[i] * right[i];
        }
        return result;
    }
}
