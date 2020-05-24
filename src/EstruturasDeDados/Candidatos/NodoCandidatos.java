package EstruturasDeDados.Candidatos;

import Classes.Candidatos;

public class NodoCandidatos {
	Candidatos item;
	NodoCandidatos esquerda;
	NodoCandidatos direita;
	
	public NodoCandidatos(Candidatos registro) {
		item = registro;
		esquerda = null;
		direita = null;
	}
}
