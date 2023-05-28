import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedSetTest<T> {

    @Test
    public static void containsTest1() {

        LinkedSet testSet = new LinkedSet();
        testSet.add(1);
        testSet.add(2);
        testSet.add(3);
        testSet.add(4);
        testSet.add(5);
        testSet.add(6);

        boolean expected = true;
        boolean actual = testSet.contains(3);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public static void containsTest2() {

        LinkedSet testSet = new LinkedSet();
        testSet.add(1);
        testSet.add(3);
        testSet.add(5);
        testSet.add(4);
        testSet.add(2);
        testSet.add(6);
        testSet.add(12);
        testSet.add(0);
        testSet.add(0);
        testSet.add(13);

        boolean expected = true;
        boolean actual = testSet.contains(3);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public static void containsTest3() {

        LinkedSet testSet = new LinkedSet();
        testSet.add(4);
        testSet.add(1);
        testSet.add(5);
        testSet.add(3);
        testSet.add(1);
        testSet.add(2);

        boolean expected = true;
        boolean actual = testSet.contains(3);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public static void removeTest1() {

        LinkedSet testSet = new LinkedSet();
        testSet.add(1);
        testSet.add(2);
        testSet.add(3);
        boolean expected1 = true;
        int expectedSize = 2;
        boolean actual1 = testSet.remove(2);
        int actualSize = testSet.size();
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expectedSize, actualSize);


    }

    @Test
    public static void unionSetTest1() {

        LinkedSet testSet = new LinkedSet();
        testSet.add(1);
        testSet.add(2);
        testSet.add(3);
        LinkedSet testSet1 = new LinkedSet();
        testSet.add(1);
        testSet.add(2);
        testSet.add(3);
        testSet.union(testSet1);

    }

    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        LinkedSet.Node next;
        /** a reference to the node before this node. */
        LinkedSet.Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}