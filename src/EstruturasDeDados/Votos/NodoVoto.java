package EstruturasDeDados.Votos;

import Classes.Voto;

public class NodoVoto {

	Voto item;           // contém os dados do aluno armazenado no nodo da árvore.
	NodoVoto direita;    // referência ao nodo armazenado, na árvore, à direita do aluno em questão.
    NodoVoto esquerda;   // referência ao nodo armazenado, na árvore, à esquerda do aluno em questão.

    public NodoVoto(Voto registro) {
    	item = registro;
    	direita = null;
    	esquerda = null;
    }
}
