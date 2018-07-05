
 class Tercerizado extends Funcionario {
	 private int horasTrab;
	 private boolean insalubre;
	
	 public Tercerizado(String nome, int max_diarias, int horasTrab, boolean insalubre) {
		super(nome, max_diarias);
		this.horasTrab = horasTrab;
		this.insalubre = insalubre;
		this.setMax_diarias(0);
	}
	public int getHorasTrab() {
		return horasTrab;
	}
	public void setHorasTrab(int horasTrab) {
		this.horasTrab = horasTrab;
	}
	public boolean isInsalubre() {
		return insalubre;
	}
	public void setInsalubre(boolean insalubre) {
		this.insalubre = insalubre;
	}
	
	public double calc_salario(){
		 double salario;
		 if(insalubre == true) {
		 salario = 4 * horasTrab + 500;
		 return salario;
		 }else
			 salario = 4 * horasTrab;
		 return salario;
	 }
	 
}
