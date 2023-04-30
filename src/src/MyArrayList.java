public class MyArrayList<T> implements MyList<T>  {
    private T[] arr;
    private int size;

    /*
     * @function MyArrayList - constructor
     * @return void
     */
    public MyArrayList() {
        this.arr = (T[]) new Object[5];
        this.size = 0;
    }

    /*
     * @function size - returning size of array as integer
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * @function contains - returning true if array contains given object, false otherwise
     * @param o - object to check
     * @return boolean
     */

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == o) {
                return true;
            }
        }
        return false;
    }

    /*
     * @function add - adding a new element to array
     * @param item - object to add
     * @return void
     */
    @Override
    public void add(T value) {
        if(size == arr.length) {
            increaseLength();
        }
        arr[size++] = value;
    }

    /*
     * @function add - adding a new element to specific index in array
     * @param item - object to add
     * @param index - where to add item
     * @return void
     */
    @Override
    public void add(T item, int index) {
        if (size == arr.length) { // checking is array have enough space to add new value
            increaseLength(); // increase space of array if not
        }
        T[] newArr = (T[]) new Object[arr.length]; // creating new array to swap elements to add new between them
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

    /*
     * @function remove - removing item from array and return true if item has removed, false otherwise
     * @param item - item to remove
     * @return boolean
     */
    @Override
    public boolean remove(T item) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == item) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /*
     * @function remove - removing item from array in specific index and return item which was removed
     * @param index - index of the element to remove
     * @return Object
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = arr[index];
        for(int i = index+1; i<arr.length; i++) {
            arr[i-1] = arr[i];
        }
        size--;
        return removed;
    }

    /*
     * @function clear - clearing array by creating new empty array
     * @noparam
     * @return void
     */
    @Override
    public void clear() {
        this.arr = (T[]) new Object[5];
        this.size = 0;
    }

    /*
     * @function get - returning element from array in specific index
     * @param index - index of element to get
     * @return Object in generic type
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return arr[index];
    }

    /*
     * @function indexOf - returning index of given element
     * @param o - item to find index
     * @return int
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == o) {
                return i;
            }
        }
        return -1;
    }

    /*
     * @function lastIndexOf - returning last index of given element, -1 if element doesn't exist in array
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == o) {
                return i;
            }
        }
        return -1;
    }

    /*
     * @function sort - sorting array with bubble sort
     * @noparam
     * @return void
     */
    @Override
    public void sort() {
        String type = FindTypeOf();
        if (type.equals("int")) {
            int n = size;
            // bubble sort
            for (int i = 0; i < n-1; i++)
                for (int j = 0; j < n-i-1; j++)
                    if ((int) arr[j] > (int) arr[j+1])
                    {
                        T temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
        }
        if (type.equals("double")) {
            int n = size;
            for (int i = 0; i < n-1; i++)
                for (int j = 0; j < n-i-1; j++)
                    if ((Double) arr[j] > (Double) arr[j+1])
                    {
                        T temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
        }
    }

    /*
     * @function FindTypeOf - checking is array can be sorted, return true if yes, false otherwise
     * @return bool
     */
    public String FindTypeOf() {
        int intSize = 0;
        int doubleSize = 0;
        for (int i = 0; i < size; i++) {
            try {
                int value = (Integer) arr[i];
                intSize++;
            } catch (ClassCastException ignored) {
            }
            try {
                double tempD = (Double) arr[i];
                doubleSize++;
            } catch (ClassCastException ignored) {
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

    private void increaseLength() {
        T[] newArr = (T[]) new Object[arr.length*2];
        for(int i=0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /*
     * @function checkIndex - checking if given index correct, throwing error if not
     * @param index - index to check
     * @return void
     */
    private void checkIndex(int index) {
        if (index<0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
