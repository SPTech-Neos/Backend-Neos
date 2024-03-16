package school.sptech.neosspringjava;

import school.sptech.neosspringjava.Modal.UserModal;
import school.sptech.neosspringjava.services.User;

import java.util.Random;
import java.util.Scanner;

public class Exemplo {
    public static void main(String[] args) {
        UserModal UserMod = new UserModal();

        Scanner option = new Scanner(System.in);
        Integer op;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastro");
            System.out.println("2 - Login");
            System.out.println("3 - Atualização de usuário");
            System.out.println("4 - Opções avançadas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            op = Integer.parseInt(option.nextLine());

            if (op == 1) {
                System.out.println("Opção 1 selecionada: Cadastro de novo usuário.");
                Scanner scanner = new Scanner(System.in);

                System.out.println("Por favor, preencha o cadastro:");

                Random random = new Random();
                Integer id = random.nextInt(1000);

                System.out.print("Nome: ");
                String name = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Senha: ");
                String password = scanner.nextLine();

                System.out.print("Permissão: ");
                Integer permission = Integer.parseInt(scanner.nextLine());

                System.out.print("Telefone: ");
                String telephone = scanner.nextLine();

                System.out.print("CPF: ");
                String cpf = scanner.nextLine();

                User usuario = new User(id, name, email, password, permission, telephone, cpf);

                UserMod.createUser(usuario);

            } else if (op == 2) {
                System.out.println("Opção 2 selecionada: Login de usuário.");
                Scanner scanner2 = new Scanner(System.in);

                System.out.print("Email: ");
                String email = scanner2.nextLine();

                System.out.print("Senha: ");
                String password = scanner2.nextLine();

                UserMod.login(email, password);

            } else if (op == 3) {
                System.out.println("Opção 3 selecionada: Atualização de usuário.");
                Scanner scanner3 = new Scanner(System.in);

                System.out.print("Email: ");
                String email = scanner3.nextLine();

                System.out.print("Senha: ");
                String password = scanner3.nextLine();

                User us = UserMod.login(email, password);

                System.out.print("ID: ");
                Integer id = us.getId();

                System.out.print("Nome novo: ");
                String nameNew = scanner3.nextLine();

                System.out.print("Email novo: ");
                String emailNew = scanner3.nextLine();

                System.out.print("Senha novo: ");
                String passwordNew = scanner3.nextLine();

                System.out.print("Permissão novo: ");
                Integer permissionNew = Integer.parseInt(scanner3.nextLine());

                System.out.print("Telefone novo: ");
                String telephoneNew = scanner3.nextLine();

                System.out.print("CPF novo: ");
                String cpfNew = scanner3.nextLine();

                User usuario = new User(id, nameNew, emailNew, passwordNew, permissionNew, telephoneNew, cpfNew);

                UserMod.updateUser(usuario);

            } else if (op == 4) {
                System.out.println("Opção 4 selecionada: Opções avançadas.");
                Scanner AdvancedOptions = new Scanner(System.in);
                Integer AdvOp;
                do {
                    System.out.println("Selecione uma opção:");
                    System.out.println("1 - Obter informações de usuário por ID");
                    System.out.println("2 - Excluir usuário por ID");
                    System.out.println("0 - Voltar ao menu principal");
                    System.out.print("Opção: ");
                    AdvOp = Integer.parseInt(AdvancedOptions.nextLine());

                    if (AdvOp == 1) {
                        System.out.println("Opção 1 selecionada: Obter informações de usuário por ID.");
                        System.out.print("ID: ");
                        Integer id = Integer.parseInt(AdvancedOptions.nextLine());

                        UserMod.getUser(id);
                    } else if (AdvOp == 2) {
                        System.out.println("Opção 2 selecionada: Excluir usuário por ID.");
                        System.out.print("ID: ");
                        Integer id = Integer.parseInt(AdvancedOptions.nextLine());

                        UserMod.deleteUser(id);
                    }
                } while (AdvOp != 0);
            }
        } while (op != 0);

    }
}
