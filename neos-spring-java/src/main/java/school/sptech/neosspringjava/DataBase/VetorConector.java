package school.sptech.neosspringjava.DataBase;
import school.sptech.neosspringjava.services.User;

import java.sql.Connection;

public class VetorConector implements DatabaseConnector {

     ArrayMock vet;

    @Override
    public Connection connect() {

        vet = new ArrayMock();
        return null;
    }

    @Override
    public void disconnect() {
        
    }

    @Override
    public String verificarTipo() {
    return "VETOR";
    }

    public User executar(String operacao, User parametro){
        if (operacao == "INSERT"){
            return vet.add(parametro);
        } else if (operacao == "UPDATE"){
            return vet.update(parametro);
        } else if (operacao == "SELECT") {
           return vet.getByObject(parametro);
        }else {return null;}
    };
    public User executar(String operacao, int parametro){
        if (operacao == "SELECT") {
            return vet.getById(parametro);
        }else if (operacao == " DELETE"){
            return vet.remove(parametro);
        }else {return null;}
    }

    @Override
    public User executar(String operacao, String nomeLog, String passwordLog) {
        if (operacao == "SELECT") {
            return vet.getLogin(nomeLog,passwordLog);
        }else {return null;}
    }

    ;

}
