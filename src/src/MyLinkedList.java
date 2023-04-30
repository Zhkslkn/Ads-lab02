public class MyLinkedList<T> implements MyList {
    /*
     value of element, reference to next and previous elements
     */
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        /**
         * @function Node - create Node with given item
         * @param value - node value
         * @param next - next Node
         * @param prev - previous Node
         */
        Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    /*
     * @function size - returning size of linked list
     * @return int size
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * @function contains - checking linked list contains given item
     * @param o - item to check
     * @return boolean value
     */
    @Override
    public boolean contains(Object o) {
        if (head.value == o) {
            return true;
        }
        Node<T> ptr = head.next;
        while (ptr != null) {
            if (ptr.value == o) {
                return true;
            }
            ptr = ptr.next;
        }
        return false;
    }

    /*
     * @function add - adding element to linked list at tail
     * @param item - element
     * @return void
     */
    @Override
    public void add(Object item) {
        Node<T> myNode = new Node<T>((T) item, null, null);
        if (head == null) {
            head = myNode;
            tail = head;
        }
        else {
            myNode.prev = tail;
            tail.next = myNode;
            tail = myNode;
        }
        size++;
    }

    /*
     * @function add - adding element to linked list to particular index
     * @param item - element to add
     * @param index - index of element to add
     * @return void
     */
    @Override
    public void add(Object item, int index) {
        checkIndex(index);
        Node<T> myNode = new Node<T>((T) item, null, null);
        if (index == 0) {
            add(item);
            return;
        }
        Node<T> nextNode = head;
        for (int i = 1; i < size; i++) {
            if (i == index) {
                Node<T> temp = nextNode.next;
                nextNode.next = myNode;
                myNode.prev = nextNode;
                myNode.next = temp;
                temp.prev = myNode;
            }
            nextNode = nextNode.next;
        }
        size++;
    }

    /*
     * @function checkIndex - checking given index
     * @param index - index to check
     * @return void
     */
    public void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /*
     * @function remove - removing element in linked list
     * @param item - element to delete
     * @return boolean
     */

    @Override
    public boolean remove(Object item) {
        Node<T> myNode = new Node<T>((T) item, null, null);
        if (head.value == myNode.value) {
            head = head.next;
            head.prev = null;
            size--;
            return true;
        }
        Node<T> nextNode = head.next;
        while (nextNode != null) {
            if (nextNode.value == myNode.value) {
                Node<T> temp = nextNode.prev;
                temp.next = nextNode.next;
                Node<T> temp2 = nextNode.next;
                temp2.prev = temp;
                size--;
                return true;
            }
            nextNode = nextNode.next;
        }
        return false;
    }

    /*
     * @function remove - deleting element at specific index
     * @param index - index of element to delete
     * @return Object
     */
    @Override
    public Object remove(int index) {
        checkIndex(index);
        if (index == 0) {
            Object removed = head.value;
            if (size == 1) {
                head = null;
                tail = null;
                size = 0;
                return removed;
            }
            head = head.next;
            head.prev = null;
            size--;
            return removed;
        }
        if (index == size-1) {
            Object removed = tail.value;
            tail = tail.prev;
            tail.next = null;
            size--;
            return removed;
        }
        Node<T> nextNode = head.next;
        for (int i = 1; i <= size; i++) {
            if (index == i) {
                Node<T> p = nextNode.prev;
                Node<T> n = nextNode.next;
                Object removed = nextNode.value;

                p.next = n;
                n.prev = p;
                size--;
                return removed;
            }
            nextNode = nextNode.next;
        }
        return null;
    }

    /*
     * @function clear - clearing all linked list by creating new
     * @noparam
     * @return void
     */
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    /*
     * @function get - returning element in linked list by specific index
     * @param index - index of element to get
     * @return Object
     */
    @Override
    public Object get(int index) {
        checkIndex(index);
        if (index == 0) {
            return head.value;
        }
        Node<T> nextNode = head.next;
        for (int i = 1; i < size; i++) {
            if (i == index) {
                return nextNode.value;
            }
            nextNode = nextNode.next;
        }
        return null;
    }

    /*
     * @function indexOf - returning index of given element in linked list
     * @param o - element to find
     * @return int
     */
    @Override
    public int indexOf(Object o) {
        Node<T> newNode = new Node<T>((T) o, null, null);
        if (head.value == newNode.value) {
            return 0;
        }
        Node<T> nextNode = head.next;
        for (int i = 1; i <= size; i++) {
            if (nextNode.value == newNode.value) {
                return i;
            }
            nextNode = nextNode.next;
        }
        return -1;
    }

    /*
     * @function lastIndexOf - returning last index of given element in linked list
     * @param o - element to find
     * @return - int
     */
    @Override
    public int lastIndexOf(Object o) {
        Node<T> nextNode = new Node<T>((T) o, null, null);
        if (tail.value == nextNode.value) { // checking if element equal to tail of linked list
            return size-1;
        }
        Node<T> nextNode2 = tail.prev;
        for (int i = size-2; i >= 0; i--) {
            if (nextNode2.value == nextNode.value) { // checking if element contains in linked list
                return i;
            }
            nextNode2 = nextNode2.prev;
        }
        return -1;
    }

    /*
     * @function sort - sorting elements by insertion sort
     * @noparam
     * @return void
     */
    @Override
    public void sort() {
        if (isSortable()) {
            Node<T> front = head;
            Node<T> back = null;
            while (front != null) {
                back = front.next;
                while (back != null && back.prev != null && (Integer) back.value < (Integer) back.prev.value) {
                    swapValue(back, back.prev);
                    back = back.prev;
                }
                front = front.next;
            }
        }
    }

    /*
     * @function swapValue - function to swap two Nodes, used in sort
     * @param first - first node
     * @param second - second node
     * @return void
     */
    public void swapValue(Node<T> first, Node<T> second) {
        Object value = first.value;
        first.value = second.value;
        second.value = (T) value;
    }

    /*
     * @function isSortable - checking if linked list contains all integers or doubles
     * @noparam
     * @return boolean
     */
    public boolean isSortable() {
        Node<T> nextNode = head;
        int intSize = 0;
        int doubleSize = 0;
        for (int i = 1; i <= size; i++) {
            try {
                int value = (Integer) nextNode.value;
                intSize++;
            } catch (ClassCastException e) {
            }
            try {
                double tempD = (Double) nextNode.value;
                doubleSize++;
            } catch (ClassCastException e) {
            }
            nextNode = nextNode.next;
        }
        if (intSize == size || doubleSize == size || doubleSize + intSize == size) {
            return true;
        }
        return false;
    }
}
