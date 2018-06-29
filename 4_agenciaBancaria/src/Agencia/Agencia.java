package Agencia;

public class Agencia {
	private Repositorio<Cliente> clientes;
	private Cliente user;
	
	public Agencia(){
		clientes = new Repositorio<Cliente>("clientes");
		user = null;
	}
	
	public Conta getContaCli(int numero) {
		for (Cliente cli : clientes.getAll()) {
			for (Conta conta : cli.getContas().getAll()) {
				if(conta.getNumero() == numero) {
					return conta;
				}
			}
		}
		throw new RuntimeException("Fail: conta " + numero + " não existe.");
	}
	
	public boolean addCliente(String idCliente) {
		for (Cliente c : clientes.getAll()) {
			if (c.getIdCliente().equals(idCliente)) {
				throw new RuntimeException("Fail: " + idCliente + " já cadastrado.");
			}
		}
		this.clientes.add(new Cliente(idCliente));
		return true;
	}
	
	public Conta getConta(int numero) {
		for(Conta conta : getUser().getContas().getAll()) {
			if(conta.getNumero() == numero) {
				return conta;
			}
		}
		throw new RuntimeException("Fail: conta " + numero + " não existe.");
	}
	
	public void encerrar(String idCli, int numero) {
		for (Cliente cli : clientes.getAll()) {
			if(cli.getIdCliente().equals(idCli)) {
				for (Conta conta : cli.getContas().getAll()) {
					if(conta.getNumero() == numero) {
						cli.encerrarConta(numero);
					}
				}
			}
		}
	}
	
	public Cliente getCliente(String idCliente) {
		for (Cliente cli : clientes.getAll()) {
			if (cli.getIdCliente().equals(idCliente)) {
				return cli;
			}
		}
		throw new RuntimeException("Fail: " + idCliente + " não existe.");
	}

	public Repositorio<Cliente> getClientes() {
		return clientes;
	}
	

	public Cliente getClientes(String id) {
		for (Cliente cli : clientes.getAll()) {
			if (cli.getIdCliente().equals(id)) {
				return cli;
			}
		}
		throw new RuntimeException("Fail: " + id + " nãos existe.");
	}
	
	public String toString() {
		String saida = "";
		for (Cliente cli : clientes.getAll()) {
			saida += cli.getIdCliente() + "\n";
		}
		return saida;
	}
	public boolean abrirNovaConta(String id){
		for(Cliente c: clientes.getAll()){
			if(c.getIdCliente().equals(id)){
				c.addConta(new Conta(Conta.idConta++));
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean Login(String usuario) {
		
		if(user != null)
			throw new RuntimeException("fail: Ja existe alguem logado");
		if(usuario.equals(null))
			throw new RuntimeException("fail: é nulo");
		
		
		for (Cliente cli : clientes.getAll()) {
			if(cli.getIdCliente().equals(usuario)) {
				this.user = cli;
				return true;
			}
			else {
				
				throw new RuntimeException("fail: Usuario não existe");
			}
		}
		return false;
	}

	public void Logout() {
		
		if(user == null)
		    throw new RuntimeException("fail: ninguem logado");
		
		this.user = null;
	}
	
	public Cliente getUser() {
		if(user == null)
			throw new RuntimeException("fail: ninguem logado");
		return user;
	}
}