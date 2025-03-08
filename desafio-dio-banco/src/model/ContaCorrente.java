package model;

import abstracts.Conta;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContaCorrente extends Conta {

    public ContaCorrente(double saldo, int numeroConta, Cliente cliente, Banco banco) {
        super(saldo, numeroConta, cliente, banco);
    }


    @Override
    public boolean saque(double valor, boolean transferencia) {
        System.out.println("Tentando sacar R$" + valor + " da conta corrente " + this.numeroConta);
        if (this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Novo saldo após saque: R$" + this.saldo);
            return true;
        } else {
            System.out.println("Saldo insuficiente.");
            return false;
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
        if (contaDestinatario == null) {
            System.out.println("Erro, essa conta não existe");
            return;
        }
        if (this.equals(contaDestinatario)) {
            System.out.println("Erro: A conta remetente e a conta destinataria são iguais");
            return;
        }
        if (this.saldo < valor) {
            System.out.println("Erro: Saldo insuficiente");
            return;
        }
        if (this.saque(valor, true)) {

            contaDestinatario.deposito(valor, true);
            System.out.println("Transferência de R$" + String.format("%.2f", valor) + " realizada com sucesso!");

            System.out.println("Extrato da conta remetente após a transferência:");
            this.imprimirExtrato();
        } else {
            System.out.println("Erro na transferência: saldo insuficiente.");
        }
    }
}
