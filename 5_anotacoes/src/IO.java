import java.util.Scanner;
	
class Nota{
	private String titulo;
	private String texto;
	
	public Nota(String titulo, String texto) {
		this.titulo = titulo;
		this.texto = texto;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}

class Usuarios implements Comparable<Usuarios>{
	
	private String nomeUsuario;
	private String senha;
	public Repositorio<Nota> notas;
	
	public Usuarios(String user, String senha) {
		this.nomeUsuario = user;
		this.senha = senha;
		notas = new Repositorio<Nota>(user);
	}
	
	public boolean senhaCerta(String password) {
		return this.senha.equals(password);
	}

	public String getUser() {
		return nomeUsuario;
	}

	public void setUser(String user) {
		this.nomeUsuario = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Repositorio<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Repositorio<Nota> notas) {
		this.notas = notas;
	}
	
	public String toString() {
		return nomeUsuario + ":" + senha; 
	}
	public int compareTo(Usuarios other) {
		return this.nomeUsuario.compareTo(other.nomeUsuario);
	}
}

class GerenciarLogin{
	private Repositorio<Usuarios> users;
	private Usuarios user;
	
	public GerenciarLogin(Repositorio<Usuarios> users) {
		this.users = users;
		user = null;
	}
	
	public void Logar(String usuario, String senha) {
		if(user != null)
			throw new RuntimeException("Já está logado");
		if(!users.get(usuario).senhaCerta(senha))
			throw new RuntimeException("Senha errada '-'");
		this.user = users.get(usuario);
	}
	
	public void deslogar() {
		if(user == null)
			throw new RuntimeException("Nao tem niguem on");
		this.user = null;
	}
	
	public Usuarios getUser() {
		if(user == null)
			throw new RuntimeException("Nao tem niguem on");
		return user;
	}
	
}
class Controller{
	Repositorio<Usuarios> users;
	Repositorio<Nota> notas;
	Scanner sca;
	GerenciarLogin ger;
		
	public Controller() {
		
		sca = new Scanner(System.in);
		users = new Repositorio<Usuarios>("username");
		ger = new GerenciarLogin(users);
		notas = new Repositorio<Nota>("notas");
		users.add("admin", new Usuarios("admin", "admin"));
		users.get("admin").notas.add("Lembrar",new Nota("Lembrar", "i see dead people"));
	}

	    //nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
	public String oracle(String line){
		String ui[] = line.split(" ");
		
		
		 if (ui[0].equals("help"))
			return "-> adicionarUsuario (username) (senha)" + "\n" + "-> mostrar " + "\n" + "-> mostrarUsuario " + "\n"
				+ "-> addNota (texto)" + "\n" + "-> rmNota (nome da nota) " + "\n"
				+ "-> logar (login) (senha)" + "\n" + "-> deslogar" + "\n"
				+ "-> mudarSenha (senha antiga) (senha nova)";
		 else if (ui[0].equals("adicionarUsuario"))
			users.add(ui[1], new Usuarios(ui[1], ui[2]));
	    else if (ui[0].equals("mostrarUsuario")) {
			String saida = "";
			for(Usuarios us : users.getAll()) {
				saida += us.getUser() + "\n";
			return saida;
			}
		}
	    else if(ui[0].equals("logar"))
	    	ger.Logar(ui[1], ui[2]);
	    else if(ui[0].equals("deslogar"))
	    	ger.deslogar();
	    else if (ui[0].equals("mudarSenha")) {
			if (ger.getUser().senhaCerta(ui[1]))
					ger.getUser().setSenha(ui[2]);
		}
		else if(ui[0].equals("addNota")) {
		    String texto = "";
		    for(int i = 2 ; i<ui.length; i++)
		    	  texto += ui[i] + "";
		    ger.getUser().notas.add(ui[1],new Nota(ui[1], texto));
		}
		else if(ui[0].equals("rmNota"))
			ger.getUser().notas.remove(ui[1]);
		else if(ui[0].equals("mostrar")){
			String saida ="";
		for(Usuarios u : users.getAll())
			saida += u.getNotas() + "\n";
		return saida;
		}
	    else
	    	return " comando invalido";
	    return "done";
	    }
	}

public class IO {
	 //cria um objeto scan para ler strings do teclado
    static Scanner scan = new Scanner(System.in);
    
    //aplica um tab e retorna o texto tabulado com dois espaços
    static private String tab(String text){
        return "  " + String.join("\n  ", text.split("\n"));
    }
    
    public static void main(String[] args) {
        Controller cont = new Controller();
        System.out.println("Digite um comando:");
        while(true){
            String line = scan.nextLine();
            try {
                //se não der problema, faz a pergunta e mostra a resposta
                System.out.println(tab(cont.oracle(line)));
            }catch(Exception e) {
                //se der problema, mostre o erro que deu
                System.out.println(tab(e.getMessage()));
            	}
        	}
    	}
}