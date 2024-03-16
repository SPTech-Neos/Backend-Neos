
package school.sptech.neosspringjava.DataBase;

import school.sptech.neosspringjava.services.User;

public class ArrayMock {

    User[] array = new User[10];

    public void add(User obj) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = obj;
                break;
            }
        }

    }

    public void update(User obj){
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if (array[j+1].getId()==obj.getId()){
                    array[j+1] = obj;
                }
                if (array[j].getId() > array[j+1].getId()) {
                    User auxiliar = array[j+1];
                    array[j+1] = array[j];
                    array[j] = auxiliar;
                }
            }
        }
    }

    public void remove(int obj) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getId() == obj) {
                array[i] = null;
                break;
            }
        }
    }

    public void getById(int Id){
        for (int i = 0; i < array.length; i++) {
            if (array[i].getId() == Id){
               // return array[i];
            }
            break;
        }

    };
    public void getByObject(User obj){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == obj){
                // return array[i];
            }
            break;
        }

    };

    public void getLogin(String nomeLog, String passwordLog){
        for (int i = 0; i < array.length; i++) {
            if (array[i].getName() == nomeLog && array[i].getPassword() == passwordLog){
                // return array[i];
            }
            break;
        }

    };




    public void aumentarArray(){
        User[] arraySub = array;
        array = new User[array.length * 2];
        array = arraySub;

    }
}

