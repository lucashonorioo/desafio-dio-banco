package application;

import abstracts.Conta;
import model.Cliente;
import model.Banco;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        System.out.println("---- Banco ----");

        Scanner sc = new Scanner(System.in);

        List<Conta> contasBanco1 = new ArrayList<>();
        List<Conta> contasBanco2 = new ArrayList<>();

        Cliente primeiroCliente = null;
        Conta contaAtiva = null;

        Banco banco1 = new Banco("Banco 1", 1234);
        Banco banco2 = new Banco("Banco 2", 5678);

        do {
            int banco;
            while (true) {
                System.out.println("Nome: Banco 1, Numero Agência: 1234");
                System.out.println("Nome: Banco 2, Numero Agência: 5678");
                banco = lerInteiro("Digite o número da sua Agência: ", sc);
                if (banco == 1234 || banco == 5678) {
                    break;
                } else {
                    System.out.println("Erro: Você digitou o número da agência errado. Tente novamente.");
                }
            }

            Banco bancoAtivo = (banco == 1234) ? banco1 : banco2;
            System.out.println("Bem-vindo ao Banco " + ((banco == 1234) ? "1" : "2"));

            Conta conta = null;
            while (bancoAtivo.encontrarConta(1) == null || bancoAtivo.encontrarConta(2) == null) {
                System.out.print("Sua Conta é Corrente ou Conta Poupança? (C/P): ");
                char tipoConta = sc.next().charAt(0);

                int numeroConta = lerInteiro("Insira o número da conta: ", sc);
                sc.nextLine();
                System.out.print("Insira seu nome: ");
                String nomeCliente = sc.nextLine();
                double saldo = lerDouble("Insira seu saldo: ", sc);

                if (tipoConta == 'C') {
                    conta = new ContaCorrente(saldo, numeroConta, new Cliente(nomeCliente), bancoAtivo);
                } else if (tipoConta == 'P') {
                    conta = new ContaPoupanca(saldo, numeroConta, new Cliente(nomeCliente), bancoAtivo);
                } else {
                    System.out.println("Erro: Tipo de conta inválida!");
                    continue;
                }

                bancoAtivo.adicionarConta(conta);
                if (bancoAtivo.encontrarConta(1) == null) {
                    primeiroCliente = conta.getCliente();
                    contaAtiva = conta;
                }
                System.out.println("Conta criada");
                if (bancoAtivo.encontrarConta(1) == null || bancoAtivo.encontrarConta(2) == null) {
                    System.out.println("Crie mais uma conta para continuar");
                }
            }

            boolean voltarMenuPrincipal = true;
            while (voltarMenuPrincipal) {
                int opcao;
                do {
                    if (primeiroCliente != null) {
                        System.out.println("Olá, " + primeiroCliente.getNome());
                    }
                    System.out.println("-- Depósito -- (1)");
                    System.out.println("-- Saque -- (2)");
                    System.out.println("-- Transferência -- (3)");
                    System.out.println("-- Extrato Bancário -- (4)");
                    System.out.println("-- Voltar / Lista de Clientes / Trocar de Cliente -- (0)");
                    opcao = lerInteiro("Digite a opção desejada: ", sc);

                    switch (opcao) {
                        case 1:
                            int numeroContaDeposito = lerInteiro("Digite o número da conta: ", sc);
                            Conta contaDeposito = bancoAtivo.encontrarConta(numeroContaDeposito);
                            if (contaDeposito != null) {
                                double deposito = lerDouble("Digite o valor do depósito: ", sc);
                                contaDeposito.deposito(deposito, false);
                            } else {
                                System.out.println("Conta não encontrada.");
                            }
                            break;
                        case 2:
                            int numeroContaSaque = lerInteiro("Digite o número da conta: ", sc);
                            Conta contaSaque = bancoAtivo.encontrarConta(numeroContaSaque);
                            if (contaSaque != null) {
                                double saque = lerDouble("Digite o valor do saque: ", sc);
                                contaSaque.saque(saque, false);
                            } else {
                                System.out.println("Conta não encontrada.");
                            }
                            break;
                        case 3:
                            int numeroContaOrigem = lerInteiro("Digite o número da sua conta: ",sc);
                            Conta contaOrigem = bancoAtivo.encontrarConta(numeroContaOrigem);
                            int contaDestino = lerInteiro("Digite o número da conta que deseja transferir: ", sc);
                            Conta destino = bancoAtivo.encontrarConta(contaDestino);

                            if (contaOrigem != null && destino != null) {
                                double valor = lerDouble("Digite o valor: ", sc);
                                contaOrigem.transferencia(destino, valor);
                            } else {
                                System.out.println("Conta de origem ou destino não encontrada.");
                            }
                            break;
                        case 4:
                            if (contaAtiva != null) {
                                contaAtiva.imprimirExtrato();
                            } else {
                                System.out.println("Nenhuma conta ativa.");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                } while (opcao != 0);

                do {
                    System.out.println("-- Lista de Clientes -- (1)");
                    System.out.println("-- Trocar de Usuário -- (2)");
                    System.out.println("-- Voltar -- (0)");
                    opcao = lerInteiro("Digite a opção desejada: ", sc);

                    switch (opcao) {
                        case 1:
                            System.out.println("Clientes cadastrados: ");
                            for (Conta c : bancoAtivo.getContas()) {
                                System.out.println(" - " + c.getCliente().getNome() + ", Número da conta: " + c.getNumeroConta());
                            }
                            break;
                        case 2:
                            System.out.println("Contas existentes:");
                            for (Conta c : bancoAtivo.getContas()) {
                                System.out.println("- Número da conta: " + c.getNumeroConta() + ", Cliente: " + c.getCliente().getNome());
                            }
                            int numeroConta = lerInteiro("Digite o número da conta que deseja acessar: ", sc);
                            Conta contaEscolhida = bancoAtivo.encontrarConta(numeroConta);
                            if (contaEscolhida != null) {
                                primeiroCliente = contaEscolhida.getCliente();
                                contaAtiva = contaEscolhida;
                                System.out.println("Conta acessada com sucesso.");
                                opcao = 0;
                                voltarMenuPrincipal = true;
                            } else {
                                System.out.println("Conta não encontrada.");
                            }
                            break;
                        case 0:
                            voltarMenuPrincipal = false;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }

                } while (opcao != 0);
            }

            int sair = lerInteiro("Você deseja sair? (0 para sair, 1 para continuar): ", sc);
            if (sair == 0) {
                break;
            }
        } while (true);

        sc.close();
    }

    public static int lerInteiro(String mensagem, Scanner sc) {
        while (true) {
            try {
                System.out.print(mensagem);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
                sc.nextLine();
            }
        }
    }

    public static double lerDouble(String mensagem, Scanner sc) {
        while (true) {
            try {
                System.out.print(mensagem);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número decimal válido.");
                sc.nextLine();
            }
        }
    }
}