package Contato;

import java.util.ArrayList;

public class Contato {
	private String nome;
	private ArrayList<Telefone> fones;
	
	public Contato(String nome) {
		this.nome = nome;
		this.fones = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Telefone> getTelefone() {
		return fones;
	}

	public void setTelefone(ArrayList<Telefone> telefone) {
		this.fones = telefone;
	}
	
	public String toString() {
		return "" + nome + "" + fones.toString();
	}
	
	//adcionar o contato
	
	public boolean addTelefone(Telefone telefone) {
		for(int i = 0; i < fones.size(); i++) {
			if(fones.get(i).getFoneId().equals(telefone.getFoneId())) {
				return false;
			}
		}
		fones.add(telefone);
		return true;
	}
	
	//remover contato
	
	public boolean rmFone(String idFone) {
		for(int i = 0; i < fones.size(); i ++) {
			if(fones.get(i).getFoneId().equals(idFone)) {
				fones.remove(fones.get(i));
				return true;
			}
		}
		return false;
	}
	
	//Mostrar
	public ArrayList<Telefone> showFone (ArrayList<Telefone> fones){
		return fones;
	}
	
	//Alterar contato
	public boolean atualizar(String nome, String idFone, int novoNumero) {
		for (int i = 0; i < fones.size(); i++) {
			if(this.nome.equals(nome) && fones.get(i).getFoneId().equals(idFone)) {
				fones.get(i).setNumero(novoNumero);
				return true;
			}
		}
		return false;
	}
	
}
	
