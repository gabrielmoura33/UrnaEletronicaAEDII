package EstruturasDeDados.Votos;

import Classes.Voto;

public class ListaVotos {

	private CelulaVotos primeiro;
	private CelulaVotos ultimo;

	public ListaVotos() {
		primeiro = new CelulaVotos();

		ultimo = primeiro;
	}

	public void inserirFinal(Voto cm) {
		CelulaVotos aux = new CelulaVotos();

		ultimo.proximo = aux;

		aux.item = cm;

		ultimo = ultimo.proximo;
	}


	public Boolean listaVazia() {
		if (primeiro == ultimo) {
			return true;
		} else {
			return false;
		}
	}

	public void imprimir() {
		CelulaVotos aux;

		aux = primeiro.proximo;

		if (aux == null) {
			System.out.println("A lista de votos está vazia.");
		} else {
			while (aux != null) {
				if (aux.item.JustificouAusencia()){
					System.out.println("Ausencia Justificada pelo eleitor de título Eleitoral: " + aux.item.getTituloEleitoral());
				}
				else {
					System.out.println("Voto para o Candidato a vereador: " + aux.item.getNumeroCandidatoVereador());
					System.out.println("\n");
					System.out.println("Voto para o Candidato a Prefeito: " + aux.item.getNumeroCanditatoPrefeito());
				}
				aux = aux.proximo;
			}
		}
	}

}
