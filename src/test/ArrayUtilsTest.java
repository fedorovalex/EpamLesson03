package test;

import org.junit.Test;
import task.ArrayUtils;

import java.util.Arrays;

import static task.ArrayUtils.*;

import static org.junit.Assert.*;


public class ArrayUtilsTest {

    @Test
    public void testTruncationOrExpansionOnNull() {
        assertTrue( Arrays.equals( truncationOrExpansion(null, 3), new int[3] ) );
    }
    @Test
    public void testTruncationOrExpansionOnNegativeSize() {
        int[] source = {1, 2, 3};
        assertTrue( Arrays.equals( truncationOrExpansion(source, -5), new int[0] ) );
    }
    @Test
    public void testTruncationOrExpansionOnTruncation() {
        int[] source = {1, 2, 3};
        int[] expectation = {1};
        assertTrue( Arrays.equals( truncationOrExpansion(source, 1), expectation ) );
    }
    @Test
    public void testTruncationOrExpansionOnExpansion() {
        int[] source = {1, 2, 3};
        int[] expectation = {1, 2, 3, 0, 0};
        assertTrue( Arrays.equals( truncationOrExpansion(source, 5), expectation ) );
    }

    @Test
    public void testEqualOnNullBothArrays() {
        assertEquals( ArrayUtils.equals(null, null), true );
    }
    @Test
    public void testEqualOnNullFirstArray() {
        assertEquals( ArrayUtils.equals(null, new int[3]), false );
    }
    @Test
    public void testEqualArraysDifferentSizes() {
        assertEquals( ArrayUtils.equals(new int[2], new int[3]), false );
    }
    @Test
    public void testEqualDifferentArraysSameSize() {
        int[] first = {1, 2, 3};
        int[] second = {4, 5, 6};
        assertEquals( ArrayUtils.equals(first, second), false );
    }
    @Test
    public void testEqualIdenticalArraysSameOrder() {
        int[] first = {1, 2, 3};
        int[] second = {1, 2, 3};
        assertEquals( ArrayUtils.equals(first, second), true );
    }
    @Test
    public void testEqualIdenticalArraysDifferentOrder() {
        int[] first = {1, 2, 3, 2};
        int[] second = {2, 3, 2, 1};
        assertEquals( ArrayUtils.equals(first, second), true );
    }

    @Test
    public void testShuffleOnPreservationElements() {
        int[] source = {13, 2, 5, 2, 4, 5, 7, 5};
        int[] mixed = Arrays.copyOf(source, source.length);
        shuffle(mixed);
        assertEquals( ArrayUtils.equals(source, mixed), true );
    }

    @Test
    public void testWriteInString() {
        int[] source = {13, 2, 5, 2, 4, 5, 7, 5};
        String expectation = "13; 2; 5; 2; 4; 5; 7; 5.";
        assertTrue(expectation.equals(writeInString(source)));
    }
}