package dkit.oop;
/**                                     Feb 2022
 * Linked Lists are useful when we expect a lot of
 * inserts and removals of elements at various places.
 *
 * Here we demonstrate the use of the Java LinkedList
 * class for common operations.
 *
 * A ListIterator is used to move through the elements in a linked list,
 * and acts as a pointer between elements.
 *
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

public class MainApp
{
    public static void main(String[] args) {
        System.out.println("LinkedList sample App.");
        MainApp app = new MainApp();
        app.start();
    }

    private void start() {
        LinkedList<String> staffList = new LinkedList<>();  // create new linked list object

        staffList.addLast("Kyle");  // add at end/tail of the linked list
        staffList.addLast("Diana");
        staffList.addLast("Harry");

        System.out.println(staffList);

        // Access elements one-by-one using for-each loop
        for (String name : staffList) {
            System.out.println(name);
        }

        // Search for an element in the list - contains() returns true or false
        if (staffList.contains("Diana")) {
            System.out.println("The list  contains() the name \"Diana\".");
        } else {
            System.out.println("The list does not contain \"Diana\".");
        }

        staffList.addFirst("Zoe");     // Z KDH
        staffList.addLast("Ralph");    // ZKDH R
        staffList.addLast("Adam");     // ZKDHR A

        System.out.println("After additions: " + staffList);

        staffList.remove("Ralph"); // ZKDHA
        staffList.removeFirst();   //  KDHA
        staffList.removeLast();    //  KDH

        System.out.println("After removals: " + staffList);

        System.out.println("Iterators");

        // ListIterator
        // Iterator is used to iterate over the elements in a list.
        // Iterator can be moved forward and backwards.
        // '|' in the comments indicates the iterator position.
        ListIterator<String> iterator = staffList.listIterator(); // |KDH
        iterator.next(); // K|DH
        iterator.next(); // KD|H

        // Add more elements at iterator position
        iterator.add("Juliet"); // KDJ|H    - iterator was at H, so Julie added before Harry
        iterator.add("Nina");   // KDJN|H

        iterator.next(); // KDJNH|      // iterator now points at "end"- which is just beyond the last element

        // Remove last traversed element
        iterator.remove(); // KDJN|     // removes element that the iterator has just passed over

        System.out.println(staffList);

        staffList.addFirst("Nina");  // NKDJN  (now two Ninas)
        System.out.println(staffList);

        removeAllMatches(staffList, "Nina");

        System.out.println("After call to removeAllMatches(): " +staffList);

        boolean insertedFlag = insertValueBeforeKey(staffList, "James", "Diana");

        System.out.println("After insertBefore(): " + staffList);

        // How to Sort a LinkedList:
        // There is no convenient method available to sort a LinkedList.
        // Therefore, the best approach is to copy the LinkedList elements into
        // an array, sort the array using Arrays.sort(), and, if required, recreate
        // a new LinkedList from the sorted array.
        // Supply an empty array and a new array will be created
        // of the correct size to store all the values from the linked list
        // Note: if you find that a linked list needs to be sorted often, then
        // it may be best to use an alternative data structure.
        // The Idiom (way of doing something) is as follows:
        String[] staffArray = staffList.toArray(new String[0]);
        Arrays.sort(staffArray);
        // Convert an array, into a list, then into a new LinkedList
        LinkedList<String> sortedStaffList = new LinkedList<>(Arrays.asList(staffArray));

        System.out.println("After sort: " + sortedStaffList);
    }

    /**
     * Remove all occurrences of the key from the LinkedList
     *
     * @param list (LinkedList)
     * @param key
     */
    public void removeAllMatches( LinkedList<String> list, String key)
    {
        ListIterator<String> iterator = list.listIterator();  // initialize the iterator

        //Using an iterator to move over elements
        while (iterator.hasNext()) {
            if (iterator.next().equals(key)) {
                iterator.remove();  // remove the one that we passed over
            }
        }
    }

    /**
     * Insert a value into the list before a specified key.
     * If the key is not found, return false.
     *
     * @param list
     * @param key - the key value to be inserted
     * @param value - the value to insert
     */
    public boolean insertValueBeforeKey(LinkedList<String> list, String value, String key)
    {
        ListIterator iterator = list.listIterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(key)) {
                iterator.previous();  // back up
                iterator.add(value);
                return true;    // successful insertion
            }
        }
        return false; // not inserted, no key value founs
    }
}

