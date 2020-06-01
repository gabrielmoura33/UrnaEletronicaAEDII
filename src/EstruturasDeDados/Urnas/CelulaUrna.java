package EstruturasDeDados.Urnas;

import Classes.Urnas;

public class CelulaUrna {
	
	Urnas item;          // corresponde ao item armazenado na célula da pilha.
    CelulaUrna proximo; // referência à próxima célula de uma pilha.

    CelulaUrna() {
    	item = new Urnas();
    	proximo = null;
    }
    
    // Construtor que inicializa o item com o valor passado através do parâmetro l desse método; e o atributo que indica a próxima célula da pilha com null.
    CelulaUrna(char l) {
    	item = new Urnas();
        proximo = null;
    }
}