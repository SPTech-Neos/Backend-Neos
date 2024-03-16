package school.sptech.neosspringjava.database;

import school.sptech.neosspringjava.services.User;

public class ArrayMock {

    private User[] array;
    private int size;

    public ArrayMock() {
        array = new User[10];
        size = 0;
    }

    public User add(User obj) {
        if (size >= array.length) {
            aumentarArray();
        }

        array[size] = obj;
        size++;

        return obj;
    }

    public User update(User obj) {
        for (int i = 0; i < size; i++) {
            if (array[i].getId() == obj.getId()) {
                array[i] = obj;
                return obj;
            }
        }
        return null;
    }

    public User remove(int obj) {
        for (int i = 0; i < size; i++) {
            if (array[i].getId() == obj) {
                array[i] = null;
                // Mover os elementos para preencher o espaço vazio
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[size - 1] = null;
                size--;
                return null;
            }
        }
        return null;
    }

    public User getById(int Id) {
        for (int i = 0; i < size; i++) {
            if (array[i].getId() == Id) {
                return array[i];
            }
        }
        return null;
    }

    public User getByObject(User obj) {
        for (int i = 0; i < size; i++) {
            if (array[i] == obj) {
                return array[i];
            }
        }
        return null;
    }

    public User getLogin(String nomeLog, String passwordLog) {
        for (int i = 0; i < size; i++) {
            if (array[i].getName().equals(nomeLog) && array[i].getPassword().equals(passwordLog)) {
                return array[i];
            }
        }
        return null;
    }

    private void aumentarArray() {
        User[] arraySub = array;
        array = new User[array.length * 2];
        for (int i = 0; i < size; i++) {
            array[i] = arraySub[i];
        }
    }
}