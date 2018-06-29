package Agencia;

import java.util.Scanner;

public class Controller {
	Scanner sca;
	Agencia agencia;
	Repositorio<Cliente> clientes;

	public Controller() {
		sca = new Scanner(System.in);
		agencia = new Agencia();
	}

	public String query(String line) {
		String[] ui = line.split(" ");

		if (ui[0].equals("addcliente"))
			agencia.addCliente(ui[1]);
		else if (ui[0].equals("showcli"))
			System.out.println(" " + agencia.toString());
		else if (ui[0].equals("addconta"))
			agencia.abrirNovaConta(ui[1]);
		else if (ui[0].equals("encerrar"))
			agencia.encerrar(ui[1], Integer.parseInt(ui[2]));
		else if (ui[0].equals("login"))
			agencia.Login(ui[1]);
		else if (ui[0].equals("logout"))
			agencia.Logout();
		else if (ui[0].equals("showcontas")) {
			System.out.println(agencia.getUser().getIdCliente());
			System.out.println(agencia.getUser().getContas().toString());
		} else if (ui[0].equals("saldo"))
			System.out.println("R$: " + agencia.getConta(Integer.parseInt(ui[1])).getSaldo());
		else if (ui[0].equals("deposito"))
			agencia.getConta(Integer.parseInt(ui[1])).depositar(Float.parseFloat(ui[2]));
		else if (ui[0].equals("saque"))
			agencia.getConta(Integer.parseInt(ui[1])).sacar(Float.parseFloat(ui[2]));
		else if (ui[0].equals("extrato"))
			System.out.println(agencia.getConta(Integer.parseInt(ui[1])).getExtrato());
		else if (ui[0].equals("transferir"))
			agencia.getConta(Integer.parseInt(ui[1])).transferir(agencia.getContaCli(Integer.parseInt(ui[2])),
					Float.parseFloat(ui[3]));
		else
			return " Comando invalido";
		return "done";
	}

	public void shell() {
		while (true) {
			String line = sca.nextLine();
			try {
				System.out.println(query(line));
			} catch (RuntimeException re) {
				System.out.println(re.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		Controller c = new Controller();
		c.shell();
	}
}
	
	/*showcli
	 
	done
	addcliente Plazma
	done
	showcli
	 Plazma

	done
	login Plazma
	done
	show cli
	 Comando invalido
	showcli
	 Plazma

	done
	showcontas
	Plazma
	Conta:1 Saldo:0.0 Status:true

	done
	logout
	done
	showcontas
	fail: ninguem logado
	login Plazma
	done
	addconta Plazma
	done
	showcontas
	Plazma
	Conta:1 Saldo:0.0 Status:true
	Conta:2 Saldo:0.0 Status:true

	done
	desposito Plazma 500
	 Comando invalido
	deposito 1 500
	done
	showcontas
	Plazma
	Conta:1 Saldo:500.0 Status:true
	Conta:2 Saldo:0.0 Status:true

	done
	saque 1 200
	done
	showcontas
	Plazma
	Conta:1 Saldo:300.0 Status:true
	Conta:2 Saldo:0.0 Status:true

	done
	transferir 1 150 2
	Fail: conta 150 não existe.
	transfer Plazma 150 2
	 Comando invalido
	addconta flavio
	done
	mostrar
	 Comando invalido
	showcontas
	Plazma
	Conta:1 Saldo:300.0 Status:true
	Conta:2 Saldo:0.0 Status:true

	done
	logout
	done
	login flavio
	fail: Usuario não existe
	addCliente flavio
	 Comando invalido
	addcliente flavio
	done
	login flavio
	fail: Usuario não existe
	login Plazma
	done
	show cli
	 Comando invalido
	showcli
	 Plazma
	flavio

	done
	mostrar
	 Comando invalido
	showconta
	 Comando invalido
	shwocontas
	 Comando invalido
	showcontas
	Plazma
	Conta:1 Saldo:300.0 Status:true
	Conta:2 Saldo:0.0 Status:true

	done
	transferir 1 2 150
	done
	showcontas
	Plazma
	Conta:1 Saldo:150.0 Status:true
	Conta:2 Saldo:150.0 Status:true

	done
	extrato 1
	 Depositar valor:500.0 saldo:500.0Sacar valor:200.0 saldo:300.0Sacar valor:150.0 saldo:150.0Transferir valor:150.0 saldo:150.0
	done
	saldo 1
	R$: 150.0
	done
	saque 1 25
	done
	showcontas
	Plazma
	Conta:1 Saldo:125.0 Status:true
	Conta:2 Saldo:150.0 Status:true

	done

}*/
