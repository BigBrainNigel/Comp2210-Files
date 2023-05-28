import org.junit.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SelectorTest {


    static Comparator<Integer> ascendingInteger =
            new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i1.compareTo(i2);
                }
            };

    @Test
    public void kMinTest1() {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        inputList.add(2);
        inputList.add(8);
        inputList.add(7);
        inputList.add(3);
        inputList.add(4);
        int k = 1;
        int expected = 2;
        int actual = Selector.kmin(inputList, k, ascendingInteger);
        assertEquals(expected, actual);

    }

    @Test
    public void kMinTest2() {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        inputList.add(5);
        inputList.add(9);
        inputList.add(1);
        inputList.add(7);
        inputList.add(3);
        int k = 3;
        int expected = 5;
        int actual = Selector.kmin(inputList, k, ascendingInteger);
        assertEquals(expected, actual);

    }

    @Test
    public void kMinTest3() {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        inputList.add(8);
        inputList.add(2);
        inputList.add(8);
        inputList.add(7);
        inputList.add(3);
        inputList.add(3);
        inputList.add(4);
        int k = 3;
        int expected = 4;
        int actual = Selector.kmin(inputList, k, ascendingInteger);
        assertEquals(expected, actual);

    }

    @Test
    public void kMinTest4() {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        inputList.add(8);
        inputList.add(8);
        int k = 1;
        int expected = 8;
        int actual = Selector.kmin(inputList, k, ascendingInteger);
        assertEquals(expected, actual);

    }

    @Test
    public void kMaxTest1() {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        inputList.add(2);
        inputList.add(8);
        inputList.add(7);
        inputList.add(3);
        inputList.add(4);
        int k = 1;
        int expected = 8;
        int actual = Selector.kmax(inputList, k, ascendingInteger);
        assertEquals(expected, actual);

    }

    @Test
    public void kMaxTest2() {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        inputList.add(5);
        inputList.add(9);
        inputList.add(1);
        inputList.add(7);
        inputList.add(3);
        int k = 3;
        int expected = 5;
        int actual = Selector.kmax(inputList, k, ascendingInteger);
        assertEquals(expected, actual);

    }

    @Test
    public void kMaxTest3() {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        inputList.add(8);
        inputList.add(2);
        inputList.add(8);
        inputList.add(7);
        inputList.add(3);
        inputList.add(3);
        inputList.add(4);
        int k = 3;
        int expected = 4;
        int actual = Selector.kmax(inputList, k, ascendingInteger);
        assertEquals(expected, actual);

    }

    @Test
    public void kMaxTest4() {

        ArrayList<Integer> inputList = new ArrayList<Integer>();
        inputList.add(8);
        inputList.add(8);
        int k = 1;
        int expected = 8;
        int actual = Selector.kmax(inputList, k, ascendingInteger);
        assertEquals(expected, actual);

    }
}