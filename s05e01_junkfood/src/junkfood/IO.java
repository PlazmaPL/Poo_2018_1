package junkfood;

import java.util.ArrayList;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

class Espiral {
	public String nome;
	public int qtd;
	public float preço;

	public Espiral() {
		this.nome = "-";
		this.qtd = 0;
		this.preço = 0;
	}

	public String toString() {
		return "[" + this.nome + " : " + this.qtd + " U : " + this.preço + " RS]";
	}
}

class Maquina {
	ArrayList<Espiral> espirais;
	float saldoC;
	float lucro;

	public Maquina(int qtdEspirais, int maxProdutos) {
		this.saldoC = 0;
		this.lucro = 0;
		this.espirais = new ArrayList<Espiral>();
		for (int i = 0; i < qtdEspirais; i++)
			this.espirais.add(new Espiral());
	}

	public boolean inserirDinheiro(float value) {
		if (value > 0) {
			saldoC = saldoC + value;
			return true;
		} else
			throw new RuntimeException("Valor invalido");
	}

	public boolean limpar(int indice) {
		if (espirais.get(indice) == null)
			throw new RuntimeException("esse produto nem existe men");
		espirais.get(indice).nome = "-";
		espirais.get(indice).preço = 0;
		espirais.get(indice).qtd = 0;

		return true;
	}

	public float pedirTroco() {
		float troco;
		troco = saldoC;
		saldoC = 0;
		return troco;
	}

	public boolean Comprar(int indice) {
		if (espirais.get(indice) == null)
			throw new RuntimeException("esse produto nem existe men");
		if (espirais.get(indice).qtd <= 0)
			throw new RuntimeException("Acabou o " + espirais.get(indice).nome + " :(");
		if (espirais.get(indice).preço >= this.saldoC)
			throw new RuntimeException("tu ta pobre parcero");
		espirais.get(indice).qtd--;
		this.saldoC -= espirais.get(indice).preço;
		this.lucro += espirais.get(indice).preço;
		return true;

	}

	public void setEspiral(int indice, String nome, int qtd, float preço) {
		espirais.get(indice).nome = nome;
		espirais.get(indice).qtd = qtd;
		espirais.get(indice).preço = preço;
	}

	public float getSaldo() {
		return saldoC;
	}

	public String toString() {
		String saida = "";
		for (int i = 0; i < espirais.size(); i++)
			saida += i + " " + espirais.get(i) + "\n";
		return saida;
	}
}

class Controller {
	Maquina maq;
	static final int DEFAULT_ESPIRAIS = 3;
	static final int DEFAULT_MAX = 5;

	public Controller() {
		maq = new Maquina(DEFAULT_ESPIRAIS, DEFAULT_MAX);
	}

	// recebe uma string e tenta converter em float
	private float toFloat(String s) {
		return Float.parseFloat(s);
	}

	// nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
	public String oracle(String line) {
		String ui[] = line.split(" ");

		if (ui[0].equals("help")) {
			return "show, init _espirais _maximo";
		} else if (ui[0].equals("iniciar")) {
			maq = new Maquina(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
		} else if (ui[0].equals("show")) {
			return "" + maq + "" + maq.saldoC;
		} else if (ui[0].equals("saldo")) {
			System.out.println(maq.saldoC);
		} else if (ui[0].equals("set")) {
			maq.setEspiral(Integer.parseInt(ui[1]), ui[2], Integer.parseInt(ui[3]), Float.parseFloat(ui[4]));
		} else if (ui[0].equals("dinheiro")) {
			maq.inserirDinheiro(Float.parseFloat(ui[1]));
		} else if (ui[0].equals("troco")) {
			System.out.println("Voce recebeu:" + maq.pedirTroco());
		} else if (ui[0].equals("comprar")) {
			if (maq.Comprar(Integer.parseInt(ui[1])))
				System.out.println(
						"Comprou um " + maq.espirais.get(Integer.parseInt(ui[1])).nome + ". Saldo: " + maq.saldoC);
		} else if (ui[0].equals("limpar")) {
			if (maq.limpar(Integer.parseInt(ui[1])))
				System.out.println("limpou");
		} else {
			return "comando invalido";
		}
		return "feito";
	}
}

public class IO {
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

// iniciar 3 5
// dinheiro 20
