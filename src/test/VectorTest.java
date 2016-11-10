package test;

import org.junit.Test;
import task.vector.Vector;
import task.vector.VectorConverter;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class VectorTest {
    @Test
    public void testGetElement() {
        Vector vector = new Vector(2);
        vector.setElement(0, 5);
        assertEquals(vector.getElement(0), 5);
    }

    @Test
    public void testGetSize() {
        Vector vector = new Vector(2);
        assertEquals(vector.getSize(), 2);
    }

    @Test
    public void testMultiply() {
        VectorConverter converter = new VectorConverter();
        Vector first = converter.convertArrayToVector(new int[]{1, 2, 3});
        Vector second = converter.convertArrayToVector(new int[]{5, 3, 0});
        assertEquals(first.multiply(second), 11);
    }
}
