package EstruturasDeDados.Candidatos;

import Classes.Candidatos;
import Classes.Eleitor;
import EstruturasDeDados.Eleitor.NodoEleitor;

public class ABBCandidatos {

	private NodoCandidatos raiz;
	private int root = 0;
	private Candidatos[] arrayCandidatos;

	public ABBCandidatos() {
		raiz = null;
	}

	public Boolean arvoreVazia() {
		if(this.raiz == null) {
			return true;
		}else {
			return false;
		}
	}

	private NodoCandidatos adicionar(NodoCandidatos raizArvore, Candidatos candidatos)
	{
		if (raizArvore == null)
			raizArvore = new NodoCandidatos(candidatos);
		else
		{
			if (raizArvore.item.getNumero() > candidatos.getNumero())
				raizArvore.esquerda = adicionar(raizArvore.esquerda, candidatos);
			else
			{
				if (raizArvore.item.getNumero() < candidatos.getNumero())
					raizArvore.direita = adicionar(raizArvore.direita, candidatos);
				else
					System.out.println("O Candidato " + candidatos.getNome() + ", cujo número é " + candidatos.getNumero() + ", já foi inserido anteriormente. \n");
			}
		}
		return raizArvore;
	}


	public void inserir(Candidatos candidatoNovo) {
		this.raiz = adicionar(this.raiz, candidatoNovo);
	}

	private NodoCandidatos antecessor(NodoCandidatos candidatosRetirar, NodoCandidatos raizArvore) {
		if(raizArvore.direita != null) {
			raizArvore.direita = antecessor(candidatosRetirar, raizArvore.direita);
			return raizArvore;
		}else {
			candidatosRetirar.item.setNome(raizArvore.item.getNome());
			candidatosRetirar.item.setNumero(raizArvore.item.getNumero());
			candidatosRetirar.item.setMunicipio(raizArvore.item.getMunicipio());
			candidatosRetirar.item.setPartidoPolitico(raizArvore.item.getPartidoPolitico());
			candidatosRetirar.item.setCargo(raizArvore.item.getCargo());

			return raizArvore.esquerda;
		}
	}

	private NodoCandidatos retirar(NodoCandidatos raizArvore, double numero) {
		if(raizArvore == null) {
			System.out.println("O Candidato, cujo o N�mero " + numero + ", n�o foi encontrado");
			System.out.println("\n");

			return raizArvore;
		}else {
			if(raizArvore.item.getNumero() == numero) {
				if(raizArvore.direita == null) {
					return (raizArvore.direita);
				}else if(raizArvore.direita == null) {
					return (raizArvore.direita);
				}else {
					raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);
					return (raizArvore);
				}
			}else {
				if(raizArvore.item.getNumero() > numero) {
					raizArvore.esquerda = retirar(raizArvore.esquerda, numero);
				}else {
					raizArvore.direita = retirar(raizArvore.direita, numero);
				}
				return raizArvore;
			}
		}

	}

	public void remover(int matriculaRemover) {
		this.raiz = retirar(this.raiz, matriculaRemover);
	}

	public void imprimirMenorMaior(NodoCandidatos raizArvore) {
		if(raizArvore != null) {
			imprimirMenorMaior(raizArvore.esquerda);
			System.out.println("Nome: " + raizArvore.item.getNome() + "             N�mero: " + raizArvore.item.getNumero() + "    ");
			System.out.println("Municipio: " + raizArvore.item.getMunicipio() + "               Partido Politico: " + raizArvore.item.getPartidoPolitico() + "      ");
			System.out.println("Cargo: " + raizArvore.item.getCargo());
			System.out.println("\n");
			imprimirMenorMaior(raizArvore.direita);
		}
	}

	public void imprimirEmOrdem() {
		imprimirMenorMaior(this.raiz);
	}

	public int contarNumCandidatos(NodoCandidatos raizArvore) {
		int soma = 1;

		if(raizArvore.esquerda != null) {
			soma += contarNumCandidatos(raizArvore.esquerda);
		}
		if(raizArvore.direita != null) {
			soma += contarNumCandidatos(raizArvore.direita);
		}
		return soma;
	}

	public int numCandidatos() {
		return contarNumCandidatos(this.raiz);
	}

	private Candidatos[] retornaCandidatoMenorMaior(NodoCandidatos raizArvore){
		if (raizArvore != null) {
			retornaCandidatoMenorMaior(raizArvore.esquerda);
			arrayCandidatos[root] = raizArvore.item;
			root ++;
			retornaCandidatoMenorMaior(raizArvore.direita);
		}
		return arrayCandidatos;
	}

	public Candidatos[] retornaCandidato() {
		arrayCandidatos = new Candidatos[numCandidatos()];
		this.root = 0;
		return retornaCandidatoMenorMaior(this.raiz);
	}

	public Candidatos[] retornaCandidatoPorMunicipioECargo (String nomeMunicipio) {
		Candidatos[] candidatosTotais = retornaCandidato();
		int i = 0;
		int j = 0;

		for (Candidatos CD : candidatosTotais){
			if (CD.getMunicipio().trim().replace(" ", "").equalsIgnoreCase(nomeMunicipio)){
				i++;
			}
		}
		Candidatos[] candidatosPorMunicipio = new Candidatos[i];
		for (Candidatos candidato : candidatosTotais){
			if (candidato.getMunicipio().trim().replace(" ", "").equalsIgnoreCase(nomeMunicipio.replace(" ", ""))){
				candidatosPorMunicipio[j] = candidato;
				j++;
			}
		}

		return candidatosPorMunicipio;
	}


	public boolean candidatoPorCargo (Candidatos[] candidatos, char cargo){
		for (Candidatos cand : candidatos){
			if (cand.getCargo() == cargo){
				return true;
			}
		}
		return false;
	}
}
