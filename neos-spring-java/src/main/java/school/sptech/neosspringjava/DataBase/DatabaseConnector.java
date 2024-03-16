package school.sptech.neosspringjava.database;


import school.sptech.neosspringjava.services.User;

import java.sql.Connection;

public interface DatabaseConnector {
    Connection connect();
    void disconnect();
    String verificarTipo();

    User executar(String operacao, User user);
    User executar(String operacao, int parametro);
    User executar(String operacao,String nomeLog,String passwordLog);
}
