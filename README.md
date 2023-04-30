# ⭐ Assignment 2 Zhaksylyk Nurbol

## MyArrayList class
### increaseLength
> **Description**: It creates a new array with twice the length of the current array, copies the elements of the old array to the new array, and replaces the old array with the new array.

```java
private void increaseLength() {
        T[] newArr = (T[]) new Object[arr.length*2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }
```
### size
> **Description**: This method returns the number of elements in the array.

```java
@Override
    public int size() {
        return size;
    }
```
### contains
> **Description**: This method checks and returns true if the array contains the specified object, false otherwise.

```java
@Override
public boolean contains(Object o) {
        boolean bool = false;
        for (int i = 0; i < size; i++) {
            if (arr[i] == o) {
                bool = true;
            }
        }
        return bool;
    }
```
### add
> **Description**: This method adds an element to the end of the array. If the array is full, it calls the increaseBufferIfNeeded() method to increase the size of the array.

```java
@Override
    public void add(Object item) {
        if(size == arr.length){
            increaseLength();
        }
        arr[size++] = (T) item;
    }
```
### add item to index
> **Description**: This method adds an element at a specified index in the array. If the array is full, it calls the increaseLength() method to increase the size of the array. It shifts all the elements from the specified index to the end of the array one position to the right before inserting the new element.

```java
@Override
    public void add(Object item, int index) {
        if (size == arr.length) {
            increaseLength(); //
        }
        T[] newArr = (T[]) new Object[arr.length];
        for (int i = 0; i < index; i++) {
            newArr[i] = arr[i];
        }
        for (int i = index; i < size; i++) {
            newArr[i+1] = arr[i];
        }
        newArr[index] = (T) item;
        arr = newArr;
        size++;
    }
```
### remove
> **Description**: This method removes the element at the specified index from the array. It shifts all the elements from the specified index to the end of the array one position to the left after removing the element.

```java
@Override
    public boolean remove(Object item) {
        boolean removed = false;
        for (int i = 0; i < size; i++) {
            if (arr[i] == item) { // checking if given item contains in array and remove it after
                remove(i);
                removed = true;
            }
        }
        return removed;
    }
```
### remove item in index
> **Description**: This method removes the element at the specified index from the array. It shifts all the elements from the specified index to the end of the array one position to the left after removing the element.

```java
@Override
    public Object remove(int index) {
        checkIndex(index);
        T removed = arr[index];
        for(int i = index + 1; i < size; i++) {
            arr[i-1] = arr[i];
        }
        size--;
        return removed;
    }
```
### clear
> **Description**: This method clears the array by setting its size to 0 and creating a new array of length 5.

```java
@Override
    public void clear() {
        this.arr = (T[]) new Object[5];
        this.size = 5;
    }
```
### get
> **Description**: This method returns the element at the specified index in the array.
```java
@Override
    public T get(int index) {
        checkIndex(index);
        return arr[index];
    }
```
### indexOf
> **Description**: This method returns the index of the first occurrence of the specified object in the array. If the object is not found, it returns -1.
```java
@Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == o) {
                return i;
            }
        }
        return -1;
    }
```
### lastIndexOf
> **Description**: This method returns the index of the last occurrence of the specified object in the array. If the object is not found, it returns -1.
```java
@Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == o) {
                return i;
            }
        }
        return -1;
    }
```

### sort
> **Description**: This method sorts the elements of the array in ascending order using the bubble sort algorithm. It only works for arrays of Integer and Double type.
```java
@Override
    public void sort() {
        if (findTypeOf().equals("int")) {
            int n = size;
            // bubble sort
            for (int i = 0; i < n-1; i++)
                for (int j = 0; j < n-i-1; j++)
                    if ((int) arr[j] > (int) arr[j+1])
                    {
                        // swap temp and arr[i]
                        T temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
        }
        if (findTypeOf().equals("double")) {
            int n = size;
            // bubble sort
            for (int i = 0; i < n-1; i++)
                for (int j = 0; j < n-i-1; j++)
                    if ((Double) arr[j] > (Double) arr[j+1])
                    {
                        // swap temp and arr[i]
                        T temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
        }
    }
```
### findTypeOf
> **Description**: This method checking is array can be sorted, return true if yes, false otherwise.
```java
public String findTypeOf() {
        int intSize = 0;
        int doubleSize = 0;
        for (int i = 0; i < size; i++) {
            try {
                int value = (Integer) arr[i];
                intSize++;
            } catch (ClassCastException e) {
            }
            try {
                double tempD = (Double) arr[i];
                doubleSize++;
            } catch (ClassCastException e) {
            }
        }
        if (intSize == size) {
            return "int";
        }
        if (doubleSize == size) {
            return "double";
        }
        return "0";
    }
```
### checkIndex
> **Description**: This is a helper method that checks if the specified index is valid for the array. If the index is less than 0 or greater than or equal to the size of the array, it throws an IndexOutOfBoundsException.
```java
public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
```

## MyLinkedList class

### size
> **Description**: This method returns the number of elements in the list.

```java
@Override
    public int size() {
        return size;
    }
```
### isEmpty
> **Description**: This method checking is linked list empty or not.

```java
public boolean isEmpty() {
        return head == null;
    }
```
### contains
> **Description**: This function returns true if the given object is present in the linked list, otherwise false.
```java
@Override
    public boolean contains(Object o) {
        if (head.val == o) {
            return true;
        }
        Node<T> ptr = head.next;
        while (ptr != null) {
            if (ptr.val == o) {
                return true;
            }
            ptr = ptr.next;
        }
        return false;
    }
```
### add
> **Description**: This function adds a new element to the end of the linked list.
```java
@Override
    public void add(Object item) {
        Node<T> newNode = new Node<T>((T) item, null, null);
        if (head == null) {
            head = newNode;
            tail = head;
        }
        else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
```
### add item to index
**Description**: This function adds a new element at the specified index of the linked list.
```java
@Override
    public void add(Object item, int index) {
        checkIndex(index);
        Node<T> newNode = new Node<T>((T) item, null, null);
        if (index == 0) {
            add(item);
            return;
        }
        Node<T> ptr = head;
        for (int i = 1; i <= size; i++) {
            if (i == index) {
                Node<T> temp = ptr.next;
                ptr.next = newNode;
                newNode.prev = ptr;
                newNode.next = temp;
                temp.prev = newNode;
            }
            ptr = ptr.next;
        }
        size++;
    }
```
### remove
**Description**: This function removes the given object from the linked list, if it exists.
```java
@Override
    public boolean remove(Object item) {
        Node<T> newNode = new Node<T>((T) item, null, null);
        if (head.val == newNode.val) {
            head = head.next;
            head.prev = null;
            size--;
            return true;
        }
        Node<T> ptr = head.next;
        while (ptr != null) {
            if (ptr.val == newNode.val) {
                Node<T> temp = ptr.prev;
                temp.next = ptr.next;
                Node<T> temp2 = ptr.next;
                temp2.prev = temp;
                size--;
                return true;
            }
            ptr = ptr.next;
        }
        return false;
    }
```
### remove item in index
**Description**:  This function removes the element at the given index from the linked list.
```java
@Override
    public Object remove(int index) {
        checkIndex(index);
        // checking if given index acceptable
        if (index == 0) {
            Object removed = head.val;
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
            Object removed = tail.val;
            tail = tail.prev;
            tail.next = null;
            size--;
            return removed;
        }
        Node<T> ptr = head.next;
        for (int i = 1; i <= size; i++) {
            if (index == i) {
                Node<T> p = ptr.prev;
                Node<T> n = ptr.next;
                Object removed = ptr.val;

                p.next = n;
                n.prev = p;
                size--;
                return removed;
            }
            ptr = ptr.next;
        }
        return null;
    }
```
### clear
**Description**: This function removes all elements from the linked list.
```java
@Override
    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
    }
```
### get
**Description**: This function returns the element at the given index in the linked list.
```java
@Override
    public Object get(int index) {
        checkIndex(index); // checking if given index acceptable
        if (index == 0) {
            return head.val;
        }
        Node<T> newNode = head.next;
        for (int i = 1; i <= size; i++) {
            if (i == index) {
                return newNode.val;
            }
            newNode = newNode.next;
        }
        return null;
    }
```
### indexOf
**Description**: This function returns the index of the first occurrence of the given object in the linked list, or -1 if not found.
```java
@Override
    public int indexOf(Object o) {
        Node<T> newNode = new Node<T>((T) o, null, null);
        if (head.val == newNode.val) {
            return 0;
        }
        Node<T> ptr = head.next;
        for (int i = 1; i <= size; i++) {
            if (ptr.val == newNode.val) { // checking if element contains in linked list
                return i;
            }
            ptr = ptr.next;
        }
        return -1;
    }
```
### lastIndexOf
**Description**: This function returns the index of the first occurrence of the given object in the linked list, or -1 if not found.
```java
@Override
    public int lastIndexOf(Object o) {
        Node<T> newNode = new Node<T>((T) o, null, null);
        if (tail.val == newNode.val) { // checking if element equal to tail of linked list
            return size-1;
        }
        Node<T> ptr = tail.prev;
        for (int i = size-1; i >= 1; i--) {
            if (ptr.val == newNode.val) { // checking if element contains in linked list
                return i;
            }
            ptr = ptr.prev;
        }
        return -1;
    }
```
### sort
**Description**: This function sorts the elements of the linked list in ascending order using insertion sort). Note: this function will only work if all the elements in the linked list are integers or doubles.
```java
@Override
    public void sort() {
        if (isSortable()) {
            Node<T> front = head;
            Node<T> back = null;
            while (front != null) {
                back = front.next;
                while (back != null && back.prev != null && (Integer) back.val < (Integer) back.prev.val) {
                    swapValue(back, back.prev);
                    back = back.prev;
                }
                front = front.next;
            }
        }

    }
```

### swapValue
**Description**: This function helps to swap two Nodes, used in sort.
```java
public void swapValue(Node<T> first, Node<T> second) {
        Object value = first.val;
        first.val = second.val;
        second.val = (T) value;
    }
```
### isSortable
**Description**: This function checking if linked list contains all integers or doubles.
```java
public boolean isSortable() {
        Node<T> ptr = head;
        int intSize = 0;
        int doubleSize = 0;
        for (int i = 1; i <= size; i++) {
            try {
                int value = (Integer) ptr.val ;
                intSize++;
            } catch (ClassCastException e) {
            }
            try {
                double tempD = (Double) ptr.val;
                doubleSize++;
            } catch (ClassCastException e) {
            }
            ptr = ptr.next;
        }
        System.out.println(intSize + " " + doubleSize);
        if (intSize == size || doubleSize == size || doubleSize + intSize == size) {
            return true;
        }
        return false;
    }
```
### checkIndex
**Description**: This is a helper method that checks if the specified index is valid for the array. If the index is less than 0 or greater than or equal to the size of the array, it throws an IndexOutOfBoundsException.
```java
public void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }
```
