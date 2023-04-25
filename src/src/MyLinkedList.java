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

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
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
