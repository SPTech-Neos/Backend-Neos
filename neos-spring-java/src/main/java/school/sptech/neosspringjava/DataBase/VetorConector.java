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

    public void executar(String operacao, User parametro){
        if (operacao == "INSERT"){
            vet.add(parametro);
        } else if (operacao == "UPDATE"){
            vet.update(parametro);
        } else if (operacao == "SELECT") {
            vet.getByObject(parametro);
        }
    };
    public void executar(String operacao, int parametro){
        if (operacao == "SELECT") {
            vet.getById(parametro);
        }else if (operacao == " DELETE"){
            vet.remove(parametro);
        }
    }

    @Override
    public void executar(String operacao, String nomeLog, String passwordLog) {
        if (operacao == "SELECT") {
            vet.getLogin(nomeLog,passwordLog);
        }
    }

    ;



}
