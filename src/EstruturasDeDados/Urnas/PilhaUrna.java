package EstruturasDeDados.Urnas;

import Classes.Urnas;

public class PilhaUrna {

	private CelulaUrna fundo; // referência à célula que está no fundo da pilha. Essa célula é utilizada apenas para controle.
    private CelulaUrna topo;  // referência à célula que está no topo da pilha.

    // Construtor que cria uma pilha "vazia" inicializando a célula do fundo da pilha e os atributos de controle da pilha (fundo e topo).
    public PilhaUrna() {
    	
        CelulaUrna aux = new CelulaUrna();

        fundo = aux;
        topo = aux;
    }

    // Insere o item, passado como parâmetro para esse método, no topo da pilha.
    public void empilhar(Urnas l)
    {
    	// inserção da nova célula no topo da pilha.
        CelulaUrna aux = new CelulaUrna();
    	aux.proximo = topo;
    	aux.item = l;
    	
        // atualização do atributo de controle topo.
        topo = aux;
    }

    // Retira o item que está no topo da pilha. Se a pilha estiver vazia, esse método deve retornar null; caso contrário, esse método deve retornar o item retirado da pilha.
    public Urnas desempilhar()
    {
        // se a pilha estiver vazia, esse método deve retornar null.
        Urnas aux = null;

        if (!pilhaVazia())
        {
            // aux aponta para o item da pilha que será retornado/desempilhado, ou seja, o item do topo.
            aux = topo.item;

            // atualização do atributo de controle topo.
            topo = topo.proximo;

        }
        return (aux);
    }

    // Verifica se a pilha está vazia. Em caso afirmativo, retorna true e em caso negativo retorna false.
    public Boolean pilhaVazia()
    {
        // se a pilha apresentar apenas a célula sentinela, ela está vazia.
        if (fundo == topo)
            return (true);
        else
            return (false);
    }

    public void Imprimir() {
        CelulaUrna aux = this.topo;
        while (aux.proximo != null) {
            System.out.println(aux.item.getNomeDoMunicípio());
            aux = aux.proximo;
        }
    }

    public int tamanhoPilha(){
        CelulaUrna aux = this.topo;
        int i = 0;
        while (aux.proximo != null) {
            i ++;
            aux = aux.proximo;
        }
        return i;
    }

    public Urnas[] retornaUrnas() {
        int tamanhoPilha = this.tamanhoPilha();
        int i = 0;
        Urnas[] arrayUrnas = new Urnas[tamanhoPilha];
        CelulaUrna aux = this.topo;
        while (aux.proximo != null) {
            arrayUrnas[i] = aux.item;
            aux = aux.proximo;
            i++;
        }
        return arrayUrnas;
    }
}