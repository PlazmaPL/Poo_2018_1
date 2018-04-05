package appagenda;

public class Contato {
	private String nome;
	private String email;
	private String numero;
	
	public static void main (String [] args) {
		Contato c1 = new Contato();
		
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static Object size() {
		return null;
	}
	
}
