package Contato;

public class Telefone {
	private String foneId;
	private int numero;
	
	
	public Telefone(String foneId, int numero) {
		this.foneId = foneId;
		this.numero = numero;
	}


	public void setFoneId(String foneId) {
		this.foneId = foneId;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getFoneId() {
		return foneId;
	}	
	
	public String toString() {
		return "(" + foneId + ":" + numero + ")";
	}
}
