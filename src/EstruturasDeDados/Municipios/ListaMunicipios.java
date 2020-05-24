package EstruturasDeDados.Municipios;

import Classes.Municipios;

public class ListaMunicipios {

	private CelulaMunicipios primeiro;
	private CelulaMunicipios ultimo;

	public ListaMunicipios() {
		primeiro = new CelulaMunicipios();

		ultimo = primeiro;
	}

	public void inserirFinal(Municipios cm) {
		CelulaMunicipios aux = new CelulaMunicipios();

		ultimo.proximo = aux;

		aux.item = cm;

		ultimo = ultimo.proximo;
	}

	public Municipios retirar(String nome) {
		CelulaMunicipios aux, anterior;

		anterior = primeiro;
		aux = primeiro.proximo;

		while (aux != null) {
			if (aux.item.getNome() == nome) {
				anterior.proximo = aux.proximo;

				if (aux == ultimo) {
					ultimo = anterior;
				}
				return aux.item;
			} else {
				anterior = aux;
				aux = aux.proximo;
			}
		}
		return null;
	}

	public Municipios localizar(String nome) {
		CelulaMunicipios aux;

		aux = primeiro.proximo;

		while (aux != null) {
			if (aux.item.getNome() == nome) {
				return aux.item;
			} else {
				aux = aux.proximo;
			}
		}
		return null;
	}

	public Boolean listaVazia() {
		if (primeiro == ultimo) {
			return true;
		} else {
			return false;
		}
	}

	public void imprimir() {
		CelulaMunicipios aux;

		aux = primeiro.proximo;

		if (aux == null) {
			System.out.println("A lista de municipios est� vazia.");
		} else {
			while (aux != null) {
				System.out.println("Municipio " + aux.item.getNome() + ", Estado: " + aux.item.getEstado()
						+ ", N�mero de habitantes: " + aux.item.getQuantHabitantes() + ", Vagas de vereador: "
						+ aux.item.getVagasVereador());

				aux = aux.proximo;
			}
		}
	}

	public char[] nome() {
		// TODO Auto-generated method stub
		return null;
	}
}
