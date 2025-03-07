package abstracts;

import model.Banco;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Conta {

    protected double saldo;
    protected int numeroConta;

    protected Banco banco;
    protected Cliente cliente;

    public Conta(){
        super();

    }

    public Conta(double saldo, int numeroConta, Cliente cliente, Banco banco) {
        this.saldo = saldo;
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.banco = banco;
    }

    public int getNumeroConta() {
        return this.numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }
    // Criei os metodos de forma abstratas para se caso no futuro a implementação de conta corrente ou poupança
    // serem diferente fica mais facil para a alteração individual!
    public abstract void saque(double valor, boolean transferencia);
    public abstract void deposito(double valor, boolean transferencia);
    public abstract void transferencia(Conta contaDestinatario, double valor);

    public void imprimirExtrato(){
        System.out.println("==== Extrato Bancario ====");
        System.out.println(" Agencia: " + banco.getNumeroAgencia());
        System.out.println(" Numero da Conta: " + this.numeroConta);
        System.out.println(" Saldo: "+ String.format("%.2f", this.saldo));
        System.out.println("===========================");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Conta conta = (Conta) o;
        return numeroConta == conta.numeroConta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroConta);
    }



}
