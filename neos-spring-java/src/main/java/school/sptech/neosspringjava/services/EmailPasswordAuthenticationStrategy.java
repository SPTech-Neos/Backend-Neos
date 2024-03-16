package school.sptech.neosspringjava.services;

import school.sptech.neosspringjava.modal.User;


/*
 * Classe: EmailPasswordAuthenticationStrategy
 ^====================================================================================================^
 * Autor: Gabriel Yukio - @GabrielYukioMC
 * Ultima modificação feita por: Gabriel Yukio
 * Data de criação: 09/03/2024
 * Versão: 1.0
 ^====================================================================================================^
 * Descrição: Estratégia de autenticação por email e senha
 ^====================================================================================================^
 * Método: authenticate
 * Descrição: Autentica o usuário com base no email e senha fornecidos
 * 
 * @Param user usuário a ser autenticado
 * @Param email email do usuário
 * @Param password senha do usuário
 * 
 * @return true se o usuário for autenticado com sucesso, false caso contrário
 */
public class EmailPasswordAuthenticationStrategy implements UserAuthenticationStrategy {
    @Override
    public boolean authenticate(User user, String email, String password) {
        return user.getEmail().equals(email) && user.getPassword().equals(password);
    }
}