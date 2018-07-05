
 class STA extends Funcionario {
	 private int nivel;

	public STA(String nome, int max_diarias, int nivel) {
		super(nome, max_diarias);
		this.nivel = nivel;
		this.setMax_diarias(1);
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	 
	 public double calc_salario(){
		 double salario;
		 salario = 3000 + 300 * nivel;
		 return salario;
	 }
	 
}
