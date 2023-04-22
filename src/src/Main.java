public class Main {
    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList(){};
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        System.out.println(myArrayList.size());
        System.out.println(myArrayList.contains(5));
        myArrayList.add(5, 2);
        System.out.println(myArrayList.remove(2));
        System.out.println(myArrayList.get(1));
        System.out.println(myArrayList.indexOf(3));
        myArrayList.sort();
    }
}