
public class Usuario {
	private String userId;
	private Repositorio<Usuario> followers;
	private Repositorio<Usuario> follow;
	private Repositorio<tweets> timeline;
	private Repositorio<tweets> meuTweet;

	tweets mensager;

	int count_tweeters = 0;

	public Usuario(String userId) {
		this.userId = userId;
		this.followers = new Repositorio<Usuario>("followers");
		this.follow = new Repositorio<Usuario>("follow");
		this.timeline = new Repositorio<tweets>("timeline");
		this.meuTweet = new Repositorio<tweets>("meuTweet");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Repositorio<Usuario> getFollowers() {
		return followers;
	}

	public void setFollowers(Repositorio<Usuario> followers) {
		this.followers = followers;
	}

	public Repositorio<Usuario> getFollow() {
		return follow;
	}

	public void setFollow(Repositorio<Usuario> follow) {
		this.follow = follow;
	}

	public Repositorio<tweets> getTimeline() {
		return timeline;
	}

	public void setTimeline(Repositorio<tweets> timeline) {
		this.timeline = timeline;
	}

	public Repositorio<tweets> getMeuTweet() {
		return meuTweet;
	}

	public void setMeuTweet(Repositorio<tweets> meuTweet) {
		this.meuTweet = meuTweet;
	}

	public void follow(Usuario user) {
		user.follow.add(getUserId(), new Usuario(getUserId()));
		followers.add(user.getUserId(), user);
	}
	
	public void criarTweet(tweets t) {
		meuTweet.add("" + t.getIdTweets(), t);
	}

	public void addTimeline(tweets t) {
		this.timeline.add("" + t.getIdTweets(), t);
	}

	public String mostrarTimeline() {
		String saida = "";
		for (tweets tw : timeline.getAll()) {
			if (!tw.isLido()) {
				saida += "" + tw.getIdTweets() + "" + tw.getUser() + ":" + tw.getTitle() + "" + tw.getTexto();
				tw.setLido(true);
				count_tweeters++;
			}
		}
		System.out.println("Voce possui:" + count_tweeters + " tweets");
		count_tweeters = 0;
		return saida;
	}
	public String mostrarFollows() {
		String mostrar = "";
		for(Usuario flw : follow.getAll())
			mostrar += flw + " ";
		return mostrar;
	}


	public String mostrarFollowers() {
		String mostrar = "";
		for (Usuario flw : followers.getAll())
			mostrar += flw + "";
		return mostrar;
	}

	public String mostrarMyTweets() {
		String mostrar = "";
		for (tweets t : meuTweet.getAll()) {
			mostrar += " " + t.getIdTweets() + " " + t.getUser() + " " + t.getTitle() + " " + t.getTexto();
		}
		return mostrar;

	}
	
	public void darLike(int idTweet) {
		for(tweets t : timeline.getAll()) {
			if(t.getIdTweets() == idTweet) {
				if(!t.isLike()) {
					   t.setLike(true);
				 	   return;
				}
			}
		}
		throw new RuntimeException("Você não tem esse Tweet");
	}
	
	
}
