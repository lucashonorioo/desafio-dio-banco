package application;

import abstracts.Conta;
import exceptions.ExcecoesInput;
import model.Banco;
import model.Cliente;
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

            List<Conta> contas = (banco == 1234) ? contasBanco1 : contasBanco2;
            System.out.println("Bem-vindo ao Banco " + ((banco == 1234) ? "1" : "2"));

            Conta conta = null;
            while (contas.size() < 2) {
                System.out.print("Sua Conta é Corrente ou Conta Poupança? (C/P): ");
                char tipoConta = sc.next().charAt(0);

                int numeroConta = lerInteiro("Insira o número da conta: ", sc);
                sc.nextLine();
                System.out.print("Insira seu nome: ");
                String nomeCliente = sc.nextLine();
                double saldo = lerDouble("Insira seu saldo: ", sc);

                if (tipoConta == 'C') {
                    conta = new ContaCorrente(saldo, numeroConta, new Cliente(nomeCliente), new Banco("Banco " + ((banco == 1234) ? "1" : "2"), banco));
                } else if (tipoConta == 'P') {
                    conta = new ContaPoupanca(saldo, numeroConta, new Cliente(nomeCliente), new Banco("Banco " + ((banco == 1234) ? "1" : "2"), banco));
                } else {
                    System.out.println("Erro: Tipo de conta inválida!");
                    continue;
                }

                contas.add(conta);
                if (contas.size() == 1) {
                    primeiroCliente = conta.getCliente();
                    contaAtiva = conta;
                }
                System.out.println("Conta criada");
                if (contas.size() < 2) {
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
                            double deposito = lerDouble("Digite o valor do depósito: ", sc);
                            contas.get(0).deposito(deposito, false);
                            break;
                        case 2:
                            double saque = lerDouble("Digite o valor do saque: ", sc);
                            contas.get(0).saque(saque, false);
                            break;
                        case 3:
                            int contaDestino = lerInteiro("Digite o número da conta que deseja transferir: ", sc);
                            if (contaDestino == contas.get(0).getNumeroConta()) {
                                System.out.println("Erro: Não é possível transferir para a mesma conta!");
                                break;
                            }
                            double valor = lerDouble("Digite o valor: ", sc);
                            Conta destino = null;
                            for (Conta c : contas) {
                                if (c.getNumeroConta() == contaDestino) {
                                    destino = c;
                                    break;
                                }
                            }
                            if (destino != null) {
                                contas.get(0).transferencia(destino, valor);
                            } else {
                                System.out.println("Conta de destino não encontrada.");
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
                            for (Conta c : contas) {
                                System.out.println(" - " + c.getCliente().getNome() + ", Número da conta: " + c.getNumeroConta());
                            }
                            break;
                        case 2:
                            System.out.println("Contas existentes:");
                            for (Conta c : contas) {
                                System.out.println("- Número da conta: " + c.getNumeroConta() + ", Cliente: " + c.getCliente().getNome());
                            }
                            int numeroConta = lerInteiro("Digite o número da conta que deseja acessar: ", sc);
                            Conta contaEscolhida = null;
                            for (Conta c : contas) {
                                if (c.getNumeroConta() == numeroConta) {
                                    contaEscolhida = c;
                                    break;
                                }
                            }
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