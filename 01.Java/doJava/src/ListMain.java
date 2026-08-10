import java.lang.reflect.Field;
import java.util.ArrayList;

public class ListMain {
    public static void main(String[] args) throws Exception {
        var myList = new ArrayList<>();
        System.out.println("Capacity: " + getCapacity(myList) + " Size: " + myList.size()); // 0 (최신 자바 버전 기준)

        myList.add(0);
        System.out.println("Capacity: " + getCapacity(myList) + " Size: " + myList.size()); // 10

        for (int i = 1; i < 10; i++) {
            myList.add(i);
        }
        System.out.println("Capacity: " + getCapacity(myList) + " Size: " + myList.size()); // 10

        myList.add(10);
        System.out.println("Capacity: " + getCapacity(myList) + " Size: " + myList.size()); // 10

        myList.get(2);
    }

    public static int getCapacity(ArrayList<?> list) throws Exception {
        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] elementData = (Object[]) field.get(list);
        return elementData.length;
    }
}