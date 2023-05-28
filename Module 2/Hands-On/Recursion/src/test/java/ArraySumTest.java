import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

public class ArraySumTest {

    @Test
    public void sumTest1() {

        int a[] = {1, 2, 3, 4, 5};
        int left = 1;
        int right = 3;
        int expected = 9;
        int actual = ArraySum.sum(a, left, right);
        assertEquals(expected, actual);
        System.out.println("Expected: " + expected + " Actual: " + actual);

    }

    @Test
    public void sumTest2() {

        int a[] = {1, 1, 1, 1, 1};
        int left = 1;
        int right = 4;
        int expected = 4;
        int actual = ArraySum.sum(a, left, right);
        assertEquals(expected, actual);
        System.out.println("Expected: " + expected + " Actual: " + actual);

    }

    @Test
    public void sumTest3() {

        int a[] = {0, 0, 0, 0, 0};
        int left = 0;
        int right = 4;
        int expected = 0;
        int actual = ArraySum.sum(a, left, right);
        assertEquals(expected, actual);
        System.out.println("Expected: " + expected + " Actual: " + actual);

    }

    @Test
    public void sumTest4() {

        int a[] = {1, 3, 5, 7, 9};
        int left = 0;
        int right = 4;
        int expected = 25;
        int actual = ArraySum.sum(a, left, right);
        assertEquals(expected, actual);
        System.out.println("Expected: " + expected + " Actual: " + actual);

    }


}