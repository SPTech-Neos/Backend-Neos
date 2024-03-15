package school.sptech.neosspringjava.database;

public class ArrayMock {

    Object[] array = new Object[10];

    public void add(Object obj) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = obj;
                break;
            }
        }

    }

    public void remove(Object obj) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == obj) {
                array[i] = null;
                break;
            }
        }
    }


    public void aumentarArray(){
        Object[] arraySub = array;
        array = new Object[array.length * 2];
        array = arraySub;

    }
}
