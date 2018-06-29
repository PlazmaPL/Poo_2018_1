package Contato;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		Contato contact = null;
		int sair = 1;

		while (sair != 0) {
			System.out.println("->");
			String line = read.nextLine();
			String cmd[] = line.split(" ");
			String options = cmd[0];

			switch (options) {
			case "sair":
				sair = 0;
				break;
			case "iniciar":
				contact = new Contato(cmd[1]);
				System.out.println("Feito " + cmd[1] + " iniciado");
				break;
			case "adicionar":
				if (contact.addTelefone(new Telefone(cmd[1], Integer.parseInt(cmd[2]))))
					System.out.println("Feito o " + cmd[1] + " " + cmd[2] + " foi adicionado");
				break;
			case "remover":
				if (contact.rmFone(cmd[1]))
					System.out.println("Feito o " + cmd[1] + " foi removido");
				else
					System.out.println("O " + cmd[1] + " nao foi encontrado");
				break;
			case "atualizar":
				if(contact.atualizar(cmd[1], cmd[2], Integer.parseInt(cmd[3])))
					System.out.println("Feito o contato: " + cmd[1] + cmd[2] +  " foi alterado para: " + cmd[3] + cmd[4]);
				else 
					System.out.println("O " + cmd[1] + " nao foi encontrado");
				break;
			case "mostrar":
				System.out.println(contact.toString());
				break;
			case "help":
				System.out.println("# iniciar nome");
				System.out.println("# adicionar nome numero");
				System.out.println("# remover nome");
				System.out.println("# update nome antigo numero antigo novo nome novo numero");
				System.out.println("# mostrar");
				break;
			default:
				System.out.println("Comando errado");
				break;
			}
		}
	}
}
