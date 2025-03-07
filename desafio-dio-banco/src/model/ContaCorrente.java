package model;

import abstracts.Conta;

public class ContaCorrente extends Conta {

    public ContaCorrente(){

    }

    public ContaCorrente(double saldo, int numeroConta, Cliente cliente, Banco banco) {
        super(saldo, numeroConta, cliente, banco);
    }


    @Override
    public void saque( double valor, boolean transferencia) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            if (!transferencia) {
                System.out.println("Saque de R$" + valor + " realizado com sucesso.");
                imprimirExtrato();
            }
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public void deposito(double valor, boolean transferencia) {
        this.saldo += valor;
        if (!transferencia) {
            System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
            imprimirExtrato();
        }
    }

    @Override
    public void transferencia(Conta contaDestinatario, double valor) {
        if (contaDestinatario == null){
            System.out.println("Erro, essa conta não existe");
        }
        if (this.equals(contaDestinatario)){
            System.out.println("Erro: A conta rementte e a conta destinataria são iguais");
            return;
        }
        if(this.saldo < valor){
            System.out.println("Erro: Saldo insuficiente");
        }
        saque(valor, true);
        contaDestinatario.deposito(valor, true);
        System.out.println("Transferência de R$ " + String.format("%.2f", valor) + " realizada!");
        imprimirExtrato();
    }
}
