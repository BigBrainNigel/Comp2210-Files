import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectorTest {

        @Test
        public void kMinTest1() {
            int[] a = {7};
            int k = 1;
            int expected = 7;
            int actual = Selector.kmin(a, k);
            assertEquals(expected, actual);
        }

    @Test
    public void kMinTest2() {
        int[] a = {5, 7};
        int k = 1;
        int expected = 5;
        int actual = Selector.kmin(a, k);
        assertEquals(expected, actual);
    }

    @Test
    public void kMinTest3() {
        int[] a = {1, 3, 5, 7, 9};
        int k = 1;
        int expected = 1;
        int actual = Selector.kmin(a, k);
        assertEquals(expected, actual);
    }

    @Test
    public void kMinTest4() {
        int[] a = {4, 4};
        int k = 1;
        int expected = 4;
        int actual = Selector.kmin(a, k);
        assertEquals(expected, actual);
    }

    @Test
    public void kMinTest5() {
        int[] a = {1, 3, 5, 7, 9};
        int k = 5;
        int expected = 9;
        int actual = Selector.kmin(a, k);
        assertEquals(expected, actual);
    }

    @Test
    public void kMinTest6() {
        int[] a = {-4, -4};
        int k = 2;
        int expected = -4;
        int actual = Selector.kmin(a, k);
        assertEquals(expected, actual);
    }

}