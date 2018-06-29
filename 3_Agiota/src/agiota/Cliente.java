package agiota;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
	public String id;
	public String nome;
	public String estado = "vivaco";
	public float saldo;

	public Cliente(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String toString() {
		return "" + nome + ":" + id + ":" + estado + "";
	}

	public String mostrarSaldoC() {
		return "" + nome + " tem : " + saldo + "";
	}

}

class Transacao {
	public String nome;
	public float valorDivida;
	public int id;
	public float valorTotal;

	public Transacao(String nome, float valorDivida, int id, float valorTotal) {
		this.nome = nome;
		this.valorDivida = valorDivida;
		this.id = id;
		this.valorTotal = valorTotal;
	}

	public String toString() {
		return " id:" + id + " nome:" + nome + " valor:" + valorDivida;

	}

}

class Sistema {
	public float saldo;
	public float dinheiro;
	public int idTransacao;
	public int id;
	ArrayList<Cliente> clientes;
	ArrayList<Transacao> transacoes;

	public Sistema(float saldo) {
		clientes = new ArrayList<Cliente>();
		transacoes = new ArrayList<Transacao>();
		this.saldo =  saldo;
	}

	public String toString() {
		return "Sistema iniciado com saldo: " + saldo + " e " + clientes.toString();
	}

	// Cadastrar
	public void cadastrarC(String nome, String clientId) {
		for (Cliente c : clientes)
			if (c.nome.equals(nome))
				System.out.println("Pessoa já existe");
		clientes.add(new Cliente(clientId, nome));

	}

	// cadastra a divida se tiver saldo
	public void cadastrarD(String nome, float valor) {
		if (valor <= saldo) {
			saldo = saldo + valor;
			this.transacoes.add(new Transacao(nome, valor, id, idTransacao));
			id++;
			return;
		} else {
			System.out.println("Você nao possui saldo");
		}
	}

	// Emprestar Dinheiro
	public void emprestarD(String nome, float valor) {
		if (this.saldo > valor) {
			for (Cliente c : clientes)
				if (c.nome.equals(nome)) {
					this.saldo -= valor;
					c.saldo -= valor;
					this.cadastrarD(nome, valor);
					return;
				} else {
					System.out.println("Pessoa ou saldo invalido");
				}
		}else {
			System.out.println("voce nao tem dinheiro pra emprestar");
		}
	}

	// mostrar dividas
	public String mostrarD() {
		return "" + transacoes.toString();
	}

	// mostrar clientes
	public String mostrarC() {
		String saida = "";
		for (int i = 0; i < clientes.size(); i++)
			saida += "" + this.clientes.get(i).mostrarSaldoC();
		return saida;
	}

	// Mostrar cliente especifco
	public void mostrarCE(String nome) {
		int i = 0;
		for (Cliente c : clientes) {
			if (c.nome.equals(nome)) {
				System.out.println(c.mostrarSaldoC());

				while (transacoes.get(i).nome.equals(nome)) {
					System.out.println(transacoes.get(i).toString());
					i++;
				}
			}
			return;
		}
		System.out.println("Cliente nao foi encontrado");
	}

	// Recebe dinheiro se o clitente tiver sido cadastrado
	public void receberDinheiro(String nome, float valor) {
		for (Cliente c : clientes)
			if (c.nome.equals(nome)) {
				if (c.saldo >= valor) {
					this.saldo += valor;
					c.saldo -= valor;
					dinheiro += valor;
					this.transacoes.add(new Transacao(nome, valor, id, idTransacao));
					id++;
					return;
				} else {
					System.out.println("saldo insuficiente");
				}
			} else {
				System.out.println("Cliente nao Existe");
			}
	}

	// apagar dividas
	public void apagarD(String nome) {
		for (int i = 0; i < transacoes.size(); i++) {
			if (transacoes.get(i).nome.equals(nome))
				this.transacoes.remove(transacoes.get(i));
			i--;
		}
	}

	// matar o cliente
	public void matarC(String nome) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).nome.equals(nome)) {
				this.clientes.remove(clientes.get(i));
				apagarD(nome);
				return;
			} else {
				System.out.println("Cliente nao existe");
			}
		}
	}
}

class Controller {
	Sistema sis;

	public String oracle(String line) {
		String[] ui = line.split(" ");

		if (ui[0].equals("help"))
			return "-> iniciar (valor)" + "\n" + "-> mostrar " + "\n" + "-> cadastrarCliente (nome, valorDivida)" + "\n"
					+ "-> Mostrar Dividas" + "\n" + "-> emprestarDinheiro (nome, valor a emprestar) " + "\n"
					+ "-> mostrarClienteEspecefico (nome)" + "\n" + "-> receberDinheiro (nome, valor a receber)" + "\n"
					+ "-> matarCliente (nome)";
		else if (ui[0].equals("iniciar"))
			sis = new Sistema(Float.parseFloat(ui[1]));
		else if (ui[0].equals("mostrar"))
			return " " + sis;
		else if (ui[0].equals("cadastrarCliente"))
			sis.cadastrarC(ui[1], ui[2]);
		else if (ui[0].equals("mostrarDividas"))
			return "" + sis.mostrarD();
		else if (ui[0].equals("emprestarDinheiro"))
			sis.emprestarD(ui[1], Float.parseFloat(ui[2]));
		else if (ui[0].equals("mostrarClientes"))
			return "" + sis.mostrarC();
		else if (ui[0].equals("mostrarClienteEspecifico"))
			sis.mostrarCE(ui[1]);
		else if (ui[0].equals("receberDinheiro"))
			sis.receberDinheiro(ui[1], Float.parseFloat(ui[2]));
		else if (ui[0].equals("matarCliente"))
			sis.matarC(ui[1]);
		else
			return " Comando invalido";
		return "done";
	}
}

class IO {
	// cria um objeto scan para ler strings do teclado
	static Scanner scan = new Scanner(System.in);

	// aplica um tab e retorna o texto tabulado com dois espaços
	static private String tab(String text) {
		return "  " + String.join("\n  ", text.split("\n"));
	}

	public static void main(String[] args) {
		Controller cont = new Controller();
		System.out.println("Digite um comando ou help:");
		while (true) {
			String line = scan.nextLine();
			try {
				// se não der problema, faz a pergunta e mostra a resposta
				System.out.println(tab(cont.oracle(line)));
			} catch (Exception e) {
				// se der problema, mostre o erro que deu
				System.out.println(tab(e.getMessage()));
			}
		}
	}
}
