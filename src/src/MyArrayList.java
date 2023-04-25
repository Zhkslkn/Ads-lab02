public class MyArrayList<T> implements MyList<T>  {
    private T[] arr;
    private int size;

    public MyArrayList() {
        this.arr = (T[]) new Object[5];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(T value) {
        if(size == arr.length) {
            increaseLength();
        }
        arr[size++] = value;
    }

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

    @Override
    public void clear() {
        this.arr = (T[]) new Object[5];
        this.size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return arr[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == o) {
                return i;
            }
        }
        return -1;
    }

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

    private void checkIndex(int index) {
        if (index<0 || index>=size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
