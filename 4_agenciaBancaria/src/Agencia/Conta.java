package Agencia;

public class Conta {
	public static int idConta = 0;
	private float saldo;
	private int numero;
	private Repositorio<Operacao> extrato;
	private boolean ativa;

	public Conta(int numero) {
		this.saldo = 0;
		this.numero = numero;
		this.extrato = new Repositorio<Operacao>("Extrato");
		this.ativa = true;
	}

	public boolean isAtiva() {
		return ativa;
	}
	
	public boolean depositar(float valor) {
		if (ativa) {
			if (valor <= 0)
				throw new RuntimeException("Valor negativo ou abaixo de 0");
		} else {
			throw new RuntimeException("Conta desativa");
		}
		this.saldo += valor;
		this.extrato.add(new Operacao("Depositar", valor, this.saldo));
		return true;
	}

	public boolean sacar(float valor) {
		if (ativa) {
			if (valor <= 0)
				throw new RuntimeException("Valor negativo ou abaixo de 0");
			if (valor > saldo)
				throw new RuntimeException("Saldo insuficiente");
		}
		this.saldo -= valor;
		this.extrato.add(new Operacao("Sacar", valor, this.saldo));
		return true;

	}
	
	public boolean transferir(Conta other, float valor) {
		if(!other.isAtiva())
			throw new RuntimeException("A conta não está ativa.");
		if(this.sacar(valor)) {
			other.depositar(valor);
			this.extrato.add(new Operacao("Transferir", valor, this.saldo));
			return true;
		}
		return false;
	}
	
	public void encerrar() {
		this.ativa = false;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getExtrato() {
		String saida = " ";
		for(Operacao opera: extrato.getAll()) {
			saida += opera.toString();
		}
		return saida;
	}
	public String toString() {
		return "Conta:" + numero +  " Saldo:" + saldo + " Status:" + ativa;
	}

}
