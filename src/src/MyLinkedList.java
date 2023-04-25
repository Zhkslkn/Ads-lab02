public class MyLinkedList<T> implements MyList {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;
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

    @Override
    public int size() {
        return size;
    }

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

    public void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(Object item) {
        return false;
    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public void sort() {

    }
}
