
class Professor extends Funcionario {
	private char classe;

	public Professor(String nome, int max_diarias, char classe) {
		super(nome, max_diarias);
		this.classe = classe;
		this.setMax_diarias(2);
	}

	public char getClasse() {
		return classe;
	}

	public void setClasse(char classe) {
		this.classe = classe;
	}

	public double calc_salario(){
		double salario;
		if(classe == 'a') {
		salario = 3000;
		}else if (classe == 'b') {
			salario = 5000;
		}else if(classe == 'c') {
			salario = 7000;
		}else if(classe == 'd') {
			salario = 9000;
		}else if(classe == 'f') {
			salario = 11000;	
		}
		salario =  classe;
		return salario + (getQtd_diarias() * 100) + getBonus();
		}

}
