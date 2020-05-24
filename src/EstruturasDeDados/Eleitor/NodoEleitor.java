package EstruturasDeDados.Eleitor;

import Classes.Eleitor;

public class NodoEleitor {

	Eleitor item;           // contém os dados do aluno armazenado no nodo da árvore.
	NodoEleitor direita;    // referência ao nodo armazenado, na árvore, à direita do aluno em questão.
    NodoEleitor esquerda;   // referência ao nodo armazenado, na árvore, à esquerda do aluno em questão.

    public NodoEleitor(Eleitor registro) {
    	item = registro;
    	direita = null;
    	esquerda = null;
    }
}
