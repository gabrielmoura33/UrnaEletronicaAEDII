package Classes;

public class Municipios {
	private String nome;
	private String estado;
	private double quantHabitantes;
	private double vagasVereador;
	
	public Municipios(String nome, String estado, double quantHabitantes, int vagasVereador) {
		this.nome = nome;
		this.estado = estado;
		this.quantHabitantes = quantHabitantes;
		this.vagasVereador = vagasVereador;
	}
	
	public Municipios() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getQuantHabitantes() {
		return quantHabitantes;
	}

	public void setQuantHabitantes(double quantHabitantes) {
		this.quantHabitantes = quantHabitantes;
	}

	public double getVagasVereador() {
		return vagasVereador;
	}

	public void setVagasVereador(double vagasVereador) {
		this.vagasVereador = vagasVereador;
	}

	
	
}
