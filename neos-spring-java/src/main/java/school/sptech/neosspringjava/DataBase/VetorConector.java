package school.sptech.neosspringjava.DataBase;


import school.sptech.neosspringjava.services.User;

import java.sql.Connection;

public class VetorConector implements DatabaseConnector {

    @Override
    public Connection connect() {

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

        } else if (operacao == "UPDATE"){

        } else if (operacao == "SELECT") {

        }
    };
    public void executar(String operacao, int parametro){
        if (operacao == "INSERT"){

        } else if (operacao == "UPDATE"){

        } else if (operacao == "SELECT") {

        }
    }

    @Override
    public void executar(String operacao, String nomeLog, String passwordLog) {
        if (operacao == "INSERT"){

        } else if (operacao == "UPDATE"){

        } else if (operacao == "SELECT") {

        }
    }

    ;



}
