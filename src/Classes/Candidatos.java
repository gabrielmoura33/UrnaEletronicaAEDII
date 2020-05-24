package Classes;

public class Candidatos{
	
	private String nome;
	private double numero;
	private String municipio;
	private String partidoPolitico;
	private char cargo;
	
	public Candidatos(String nome, double numero, String municipio, String partidoPolitico, char cargo) {
		this.nome = nome;
		this.numero = numero;
		this.municipio = municipio;
		this.partidoPolitico = partidoPolitico;
		this.cargo = cargo;
	}
	
	public Candidatos() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNumero() {
		return numero;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getPartidoPolitico() {
		return partidoPolitico;
	}

	public void setPartidoPolitico(String partidoPolitico) {
		this.partidoPolitico = partidoPolitico;
	}

	public char getCargo() {
		return cargo;
	}

	public void setCargo(char cargo) {
		this.cargo = cargo;
	}
}
