import java.util.Scanner;

abstract class Funcionario {
	private String nome;
	private int max_diarias;
	private int qtd_diarias;
	private double bonus;

	public Funcionario(String nome, int max_diarias) {
		this.nome = nome;
		this.max_diarias = max_diarias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMax_diarias() {
		return max_diarias;
	}

	public void setMax_diarias(int max_diarias) {
		this.max_diarias = max_diarias;
	}

	public int getQtd_diarias() {
		return qtd_diarias;
	}

	public void setQtd_diarias(int qtd_diarias) {
		this.qtd_diarias = qtd_diarias;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public void addDiaria() {
		if (qtd_diarias == max_diarias) {
			System.out.println("Atingiu o maximo de diarias permitida");
		} else {
			qtd_diarias++;
		}
	}

	public double _calcSalario() {
		return bonus + qtd_diarias * 100;
	}

}

class Controller {
	Scanner sca;
	Funcionario func1;
	Repositorio<Funcionario> func;
	Repositorio<Professor> prof;
	Repositorio<STA> sta;
	Repositorio<Tercerizado> ter;

	public Controller() {
		func = new Repositorio<Funcionario>("nome");
		sca = new Scanner(System.in);
		prof = new Repositorio<Professor>("nome e classe");
		sta = new Repositorio<STA>("nome e nivel");
		ter = new Repositorio<Tercerizado>("nome horas e se é iunsalubre");

	}

	// nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
	public String oracle(String line) {
		String ui[] = line.split(" ");

		if (ui[0].equals("help"))
			return "-> adicionarUsuario (username) (senha)" + "\n" + "-> mostrar " + "\n" + "-> mostrarUsuario " + "\n"
					+ "-> addNota (texto)" + "\n" + "-> rmNota (nome da nota) " + "\n" + "-> logar (login) (senha)"
					+ "\n" + "-> deslogar" + "\n" + "-> mudarSenha (senha antiga) (senha nova)";
		else if (ui[0].equals("addSTA")) 
			sta.add(ui[1], new STA(ui[1],Integer.parseInt(ui[2]) , Integer.parseInt(ui[3])));
		else if(ui[0].equals("addTer"))
			ter.add(ui[1], new Tercerizado(ui[1], Integer.parseInt(ui[2]), Integer.parseInt(ui[3]), ui[4]));
		else if (ui[0].equals("addProf")) 
	        prof.add(ui[1], new Professor(ui[1],Integer.parseInt(ui[2]), ui[3])); 
		else if(ui[0].equals("addDiaria"))
			func.add(ui[1], func1.addDiaria());
		else if(ui[0].equals("addBonus"))
			func1.setBonus(Double.parseDouble(ui[1]));
		else if(ui[0].equals("show"))
			System.out.println(func1.getNome());
		else	
			return " comando invalido";
		return "done";
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
		System.out.println("Digite um comando:");
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
