import java.util.ArrayList;

public class tweets {
	private int idTweets;
	Usuario user;
	private String texto;
	private String title;
	private boolean lido;
	private boolean like;
	private ArrayList<String> qtdLikes;
	
	public tweets(int idTweets, Usuario user, String title, boolean lido, boolean like, ArrayList<String> qtdLikes) {
		this.idTweets = idTweets;
		this.user = user;
		this.title = title;
		this.lido = lido;
		this.like = like;
		this.texto = texto;
		this.qtdLikes = qtdLikes;
	}
	

	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public String toString() {
		return idTweets + ":" + user + ":" + title + ":" + texto;
	}
	
	public String showLikes() {
		String saida = "";
		for(int i = 0; i < qtdLikes.size(); i++)
			saida += "" + qtdLikes.get(i);
		
		return saida;
	}

	public int getIdTweets() {
		return idTweets;
	}

	public void setIdTweets(int idTweets) {
		this.idTweets = idTweets;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isLido() {
		return lido;
	}

	public void setLido(boolean lido) {
		this.lido = lido;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public ArrayList<String> getQtdLikes() {
		return qtdLikes;
	}

	public void setQtdLikes(ArrayList<String> qtdLikes) {
		this.qtdLikes = qtdLikes;
	}
	
	
}
