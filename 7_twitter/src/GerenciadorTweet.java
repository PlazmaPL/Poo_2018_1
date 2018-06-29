
public class GerenciadorTweet {
	private Repositorio<tweets> tweeters;

	public GerenciadorTweet(Repositorio<tweets> tweeters) {
		this.tweeters = new Repositorio<tweets>("Tweeters");
	}

	public Repositorio<tweets> getTweeters() {
		return tweeters;
	}

	public void setTweeters(Repositorio<tweets> tweeters) {
		this.tweeters = tweeters;
	}
	
	public String showTweets() {
		String saida = "";
		for(tweets tw : tweeters.getAll())
			saida += tw.toString() + "";
		return saida;
	}
	public void gerenciarTweet(tweets tw) {
		tweeters.add("" + tw.getIdTweets(), tw);
	}
	
}
