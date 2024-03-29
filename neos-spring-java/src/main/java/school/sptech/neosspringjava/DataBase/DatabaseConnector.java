package school.sptech.neosspringjava.database;


import java.sql.Connection;

import school.sptech.neosspringjava.modal.User;

public interface DatabaseConnector {
    Connection connect();
    void disconnect();
    String verificarTipo();

    User executar(String operacao, User user);
    User executar(String operacao, int parametro);
    User executar(String operacao,String nomeLog,String passwordLog);
}
