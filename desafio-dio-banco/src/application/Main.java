package application;

import abstracts.Conta;
import model.Banco;
import model.Cliente;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("---- Banco ----");

        Scanner sc = new Scanner(System.in);

        List<Conta> contasBanco1 = new ArrayList<>();
        List<Conta> contasBanco2 = new ArrayList<>();
        

        Banco banco1 = new Banco("Banco 1", 1234);
        Banco banco2 = new Banco("Banco 2", 5678);

        do{

            System.out.println("Nome: Banco 1, Numero Agência: 1234");
            System.out.println("Nome: Banco 2, Numero Agência: 5678");
            System.out.print("Digite o numero da sua Agência: ");
            int banco = sc.nextInt();


                if (banco == 1234 || banco == 5678) {
                    List<Conta> contas = (banco == 1234) ? contasBanco1 : contasBanco2;
                    System.out.println(" Bem-vindo ao Banco " + ((banco == 1234) ? "1" : "2"));

                    Conta conta = null;
                    Cliente primerioCliente = null;
                    while (contas.size() < 2) {

                        System.out.print("Sua Conta é Corrente ou Conta Poupanca? (C/P) ");
                        char tipoConta = sc.next().charAt(0);

                        System.out.print("Insira o numero da conta: ");
                        int numeroConta = sc.nextInt();
                        System.out.print("Insira seu nome: ");
                        sc.nextLine();
                        String nomeCliente = sc.nextLine();
                        System.out.print("Insira seu saldo: ");
                        double saldo = sc.nextDouble();

                        if (tipoConta == 'C') {
                            conta = new ContaCorrente(saldo, numeroConta, new Cliente(nomeCliente), new Banco("Banco " + ((banco == 1234) ? "1" : "2"), banco));
                        } else if (tipoConta == 'P') {
                            conta = new ContaPoupanca(saldo, numeroConta, new Cliente(nomeCliente), new Banco("Banco " + ((banco == 1234) ? "1" : "2"), banco));
                        } else {
                            System.out.println("Erro: Tipo de conta invalida!");
                            continue;
                        }
                        contas.add(conta);
                        if(contas.size() == 1){
                            primerioCliente = conta.getCliente();
                        }
                        System.out.println("Conta criada");
                        if (contas.size() < 2) {
                            System.out.println("Crie mais uma conta para continuar");
                        }
                    }
                int opcao = 0;
                do{
                    if(primerioCliente != null){
                        System.out.println("Olá, " + primerioCliente.getNome());
                    }
                    System.out.println("-- Deposito -- (1)");
                    System.out.println("-- Saque -- (2)");
                    System.out.println("-- Transfrência -- (3)");
                    System.out.println(" -- Voltar -- (0)");
                    System.out.print("Digite a opção desejada: ");
                    opcao = sc.nextInt();

                    switch (opcao){
                        case 1 :
                            System.out.print("Digite o valor do deposito: ");
                            double deposito = sc.nextDouble();
                            contas.get(0).deposito(deposito, false);
                            break;
                        case 2 :
                            System.out.print("Digite o valor do saque: ");
                            double saque = sc.nextDouble();
                            contas.get(0).saque(saque, false);
                            break;
                        case 3 :
                            System.out.print("Digite o numero da conta que deseja transferir: ");
                            int contaDestino = sc.nextInt();
                            if (contaDestino == contas.get(0).getNumeroConta()){
                                System.out.println("Erro: Não é possivel tranferir para a mesma conta!");
                                break;
                            }
                            System.out.print("Digite o valor: ");
                            double valor = sc.nextDouble();
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
                        case 0:
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                } while (opcao != 0);
            } else {
                System.out.println("Erro: Esse banco não existe");
            }

            System.out.print("Você deseja sair? (0 para sair, 1 para continuar): ");
            int sair = sc.nextInt();
            if (sair == 0) {
                break;
            }
        } while (true);

        sc.close();
    }
}