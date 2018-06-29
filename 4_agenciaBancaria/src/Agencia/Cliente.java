package Agencia;

public class Cliente {
	private String idCli;
	private Repositorio<Conta> contas;
	int qtd = 0;

	
	public Cliente(String idCliente) {
		this.idCli = idCliente;
		this.contas = new Repositorio<Conta>("Contas");
		this.contas.add(new Conta(Conta.idConta+=1));
		Conta.idConta++;
	}

	public boolean addConta(Conta conta) {
		if (conta == null) {
			throw new RuntimeException("Fail: " + conta +" nula!");
		}
		for (Conta c : contas.getAll()) {
			if (c.isAtiva()) {
				qtd+=1;
			}
		}
		if (qtd >= 2) {
			throw new RuntimeException("Fail: limite de contas atingido!");
		}
		this.contas.add(conta);
		return true;
	}

	public boolean encerrarConta(int numero) {
		for (Conta c : contas.getAll()) {
			if (c.getNumero() == numero) {
				if (c.getSaldo() == 0) {
					c.encerrar();
					return true;
				}
			}
		}
		throw new RuntimeException("Fail: conta com saldo não nulo!");
	}

	public String getIdCliente() {
		return idCli;
	}

	public Repositorio<Conta> getContas() {
		return contas;
	}
	
	public boolean equals(Cliente cli) {
		return cli.getIdCliente().equals(this.getIdCliente());
	}
}