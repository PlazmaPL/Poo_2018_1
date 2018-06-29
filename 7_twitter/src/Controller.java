import java.util.Scanner;

public class Controller {
	Repositorio<Usuario> usuarios;
	Scanner sca;
	GerenciadorTweet gerenciarTweets;
	int numTweet = 1;
	int cont = 0;

	public Controller() {
		usuarios =  new Repositorio<Usuario>("usuarios");
		sca = new Scanner(System.in);
		gerenciarTweets = new GerenciadorTweet(null);
	}

	public String query(String line) {
		String[] ui = line.split(" ");

		if (ui[0].equals("help")) {
			throw new RuntimeException(
					"" + "adduser _usuario, showuser\n" + "seguir _idseguindo _iduseguidor\n" + "twittar ");
		} else if (ui[0].equals("adduser"))
			usuarios.add(ui[1], new Usuario(ui[1]));
		else if (ui[0].equals("showuser")) {
			String saida = "";
			for (Usuario u : usuarios.getAll()) {
				saida += u.toString() + "\n   Seguidores [" + u.mostrarFollowers() + "]\n   Seguindo ["
						+ u.mostrarFollows() + "] \n";
			}
			return saida;
		} else if (ui[0].equals("seguir"))
			usuarios.get(ui[2]).follow(usuarios.get(ui[1]));
		else if (ui[0].equals("twittar")) {
			String texto = "";
			for (int i = 3; i < ui.length; i++) {
				texto += ui[i] + " ";
			}
			tweets aux = new tweets(numTweet, usuarios.get(ui[1]), ui[2], texto);
			for (Usuario u : usuarios.get(ui[1]).getFollowers().getAll())
				u.addTimeline(aux);

			usuarios.get(ui[1]).criarTweet(aux);
			gerenciarTweets.gerenciarTweet(aux);
			numTweet++;
		} else if (ui[0].equals("darlike")) {
			usuarios.get(ui[1]).darLike(Integer.parseInt(ui[2]));
			gerenciarTweets.getTweeters().get(ui[2]).darLike(usuarios.get(ui[1]).getUserId());
		} else if (ui[0].equals("mytweets"))
			System.out.println(usuarios.get(ui[1]).mostrarMyTweets());
		else if (ui[0].equals("timeline"))
			System.out.println(usuarios.get(ui[1]).mostrarTimeline());
		else if (ui[0].equals("showlikes"))
			System.out.println(" " + gerenciarTweets.getTweeters().get(ui[1]).showLikes());
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