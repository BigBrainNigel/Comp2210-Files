import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * LinearSearch1Test.java
 * Illustrates JUnit tests for the LinearSearch1 class.
 */
public class LinearSearch1Test {

    /** Test case for the search method. */
    @Test
    public void testSearch1() {
        int[] a = {2, 4, 6, 8, 10};
        int target = 6;
        int expected = 2;
        int actual = LinearSearch1.search(a, target);
        assertEquals(expected, actual);
    }

    /** Test case for the search method. */
    @Test
    public void testSearch2() {
        int[] a = {2, 4, 6, 8, 10};
        int target = 2;
        int expected = 0;
        int actual = LinearSearch1.search(a, target);
        assertEquals(expected, actual);
    }

    /** Test case for the search method. */
    @Test
    public void testSearch3() {
        int[] a = {2, 4, 6, 8, 10};
        int target = 10;
        int expected = 4;
        int actual = LinearSearch1.search(a, target);
        assertEquals(expected, actual);
    }
    @Test
    public void testSearch4() {

        int[] a = {2, 4, 6, 8, 10};
        int target = 15;
        int expected = 5;
        int actual = LinearSearch1.search(a, target);
        assertEquals(expected, actual);

    }

}

