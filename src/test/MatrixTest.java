package test;

import org.junit.Test;
import task.matrix.Matrix;
import task.matrix.MatrixConverter;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void getElementTest() {
        MatrixConverter converter = new MatrixConverter();
        Matrix matrix = converter.convertArrayToMatrix(new int[][]{
                                                                    {1, 2},
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
    public void addTest() {
        MatrixConverter converter = new MatrixConverter();
        Matrix first = converter.convertArrayToMatrix(new int[][]{
                                                                {1, 2},
                                                                {3, 4}});

        Matrix second = converter.convertArrayToMatrix(new int[][]{
                                                                {1, 1},
                                                                {2, 2}});
        Matrix testMatrix = first.add(second);
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
        MatrixConverter converter = new MatrixConverter();
        Matrix first = converter.convertArrayToMatrix(new int[][]{
                                                                {1, 2},
                                                                {3, 4}});
        Matrix second = converter.convertArrayToMatrix(new int[][]{
                                                                {1, 1},
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
    public void findDeterminant() {
        MatrixConverter converter = new MatrixConverter();
        Matrix matrix = converter.convertArrayToMatrix(new int[][]{
                                                                {5, 0, 3},
                                                                {2, 1, 0},
                                                                {1, 0, 1}});
        assertEquals(matrix.findDeterminant(), 2);
    }

}