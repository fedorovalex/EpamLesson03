package test;

import org.junit.Test;
import task.Matrix;

import static org.junit.Assert.*;
import static task.ArrayUtils.*;

public class MatrixTest {

    @Test
    public void getElementTest() {
        Matrix matrix = new Matrix(3, 2);
        matrix.setMatrix(new int[][]{{1, 2},
                                     {3, 4},
                                     {5, 6}});
        assertEquals(matrix.getElement(2, 0), 5);
    }
    @Test
    public void getRowCountTest() {
        Matrix matrix = new Matrix(3, 2);
        assertEquals(matrix.getRowCount(), 3);
    }
    @Test
    public void getColumnCountTest() {
        Matrix matrix = new Matrix(3, 2);
        assertEquals(matrix.getColumnCount(), 2);
    }
    @Test
    public void toFoldTest() {
        Matrix first = new Matrix(2, 2);
        first.setMatrix(new int[][]{{1, 2},
                                    {3, 4}});
        Matrix second = new Matrix(2, 2);
        second.setMatrix(new int[][]{{1, 1},
                                     {2, 2}});
        Matrix testMatrix = first.toFold(second);
        int[][] expectation = {{2, 3},
                               {5, 6}};
        for (int rowNumber = 0; rowNumber < testMatrix.getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < testMatrix.getColumnCount(); columnNumber++) {
                assertEquals(testMatrix.getElement(rowNumber, columnNumber), expectation[rowNumber][columnNumber]);
            }
        }
    }
    @Test
    public void multiplyTest() {
        Matrix first = new Matrix(2, 2);
        first.setMatrix(new int[][]{{1, 2},
                                    {3, 4}});
        Matrix second = new Matrix(2, 2);
        second.setMatrix(new int[][]{{1, 1},
                                     {2, 2}});
        Matrix testMatrix = first.multiply(second);
        int[][] expectation = {{5, 5},
                               {11, 11}};
        for (int rowNumber = 0; rowNumber < testMatrix.getRowCount(); rowNumber++) {
            for (int columnNumber = 0; columnNumber < testMatrix.getColumnCount(); columnNumber++) {
                assertEquals(testMatrix.getElement(rowNumber, columnNumber), expectation[rowNumber][columnNumber]);
            }
        }
    }
    @Test
    public void getDeterminant() {
        Matrix matrix = new Matrix(3, 3);
        matrix.setMatrix(new int[][]{{5, 0, 3},
                                     {2, 1, 0},
                                     {1, 0, 1}});
        assertEquals(matrix.getDeterminant(), 2);
    }

}