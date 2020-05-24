package EstruturasDeDados.Partido;


import Classes.Partido;

public class NodoPartido {

	Partido item;           // contém os dados do partido armazenado no nodo da árvore.
	NodoPartido direita;    // referência ao nodo armazenado, na árvore, à direita do partido em questão.
    NodoPartido esquerda;   // referência ao nodo armazenado, na árvore, à esquerda do partido em questão.

    public NodoPartido(Partido registro) {
    	item = registro;
    	direita = null;
    	esquerda = null;
    }
}
