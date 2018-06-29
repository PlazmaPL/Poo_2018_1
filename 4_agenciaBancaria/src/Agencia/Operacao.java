package Agencia;

public class Operacao {
	private String descricao;
	private float valor;
	private float saldoParcial;
	
	public Operacao(String descricao, float valor, float saldoParcial) {
		this.descricao = descricao;
		this.valor = valor;
		this.saldoParcial = saldoParcial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getSaldoParcial() {
		return saldoParcial;
	}

	public void setSaldoParcial(float saldoParcial) {
		this.saldoParcial = saldoParcial;
	}
	public String toString(){
		return descricao + " valor:" + valor + " saldo:" + saldoParcial;
	}
	
}
