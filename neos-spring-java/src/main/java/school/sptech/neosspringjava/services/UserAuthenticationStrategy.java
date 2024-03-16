package school.sptech.neosspringjava.services;

import school.sptech.neosspringjava.modal.User;

/*
 * Interface: UserAuthenticationStrategy
 ^====================================================================================================^
 * Autor: Gabriel Yukio - @GabrielYukioMC
 * Ultima modificação feita por: Gabriel Yukio
 * Data de criação: 09/03/2024
 * Versão: 1.0
 ^====================================================================================================^
 * Descrição: Interface para as estratégias de autenticação de usuário
 ^====================================================================================================^
 * Método: authenticate
 * Descrição: Método utilizado para autenticar um usuário
 * 
 * @Param user usuário a ser autenticado
 * @Param email email do usuário
 * @Param password senha do usuário
 * 
 * @return true se o usuário for autenticado com sucesso, false caso contrário
 */

 public interface UserAuthenticationStrategy {
    boolean authenticate(User user, String email, String password);
}