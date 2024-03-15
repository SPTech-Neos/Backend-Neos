package school.sptech.neosspringjava.DataBase;


import school.sptech.neosspringjava.services.User;

import java.sql.Connection;

public interface DatabaseConnector {
    Connection connect();
    void disconnect();
    String verificarTipo();

    void executar(String operacao, User user);
    void executar(String operacao, int parametro);
    void executar(String operacao,String nomeLog,String passwordLog);
}
