package EstruturasDeDados.Partido;

import Classes.Partido;

public class ABBPartido {

	private NodoPartido raiz;

    public ABBPartido()
    {
        raiz = null;
    }

    public Boolean arvoreVazia()
    {
        if (this.raiz == null)
            return true;
        else
            return false;
    }


    private NodoPartido adicionar(NodoPartido raizArvore, Partido partido)
    {
        if (raizArvore == null)
            raizArvore = new NodoPartido(partido);
        else
        {
            if (raizArvore.item.getNome().length() > partido.getNome().length())
                raizArvore.esquerda = adicionar(raizArvore.esquerda, partido);
            else
            {
                if (raizArvore.item.getNome().length() < partido.getNome().length())
                    raizArvore.direita = adicionar(raizArvore.direita, partido);
                else
                    System.out.println("O Partido " + partido.getNome() + ", cujo Nome é :  " + partido.getNome() + ", já foi inserido anteriormente na árvore. \n");
            }
        }
        return raizArvore;
    }

    public void inserir(Partido partidoNovo)
    {
        this.raiz = adicionar(this.raiz, partidoNovo);
    }

    private NodoPartido antecessor(NodoPartido partidoRetirar, NodoPartido raizArvore)
    {
        if (raizArvore.direita != null)
        {
            raizArvore.direita = antecessor(partidoRetirar, raizArvore.direita);
            return raizArvore;
        }
        else
        {
            partidoRetirar.item.setNome(raizArvore.item.getNome());
            partidoRetirar.item.setSigla(raizArvore.item.getSigla());


            return raizArvore.esquerda;
        }
    }
    
    private NodoPartido retirar(NodoPartido raizArvore, String nomePartido)
    {
        if (raizArvore == null)
        {
        	 System.out.println("O partido, cujo Nome é " + nomePartido + ", não foi encontrado.");
        	 System.out.println("\n");

            return raizArvore;
        }
        else
        {
            if (raizArvore.item.getNome().length() == nomePartido.length())
            {
                if (raizArvore.direita == null)
                    return (raizArvore.esquerda);
                else
                    if (raizArvore.esquerda == null)
                        return (raizArvore.direita);
                    else
                    {
                        raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);
                        return (raizArvore);
                    }
            }
            else
            {
                if (raizArvore.item.getNome().length() > nomePartido.length())
                    raizArvore.esquerda = retirar(raizArvore.esquerda, nomePartido);
                else
                    raizArvore.direita = retirar(raizArvore.direita, nomePartido);
                return raizArvore;
            }
        }
    }

    public void remover(String nomeRemover)
    {
        this.raiz = retirar(this.raiz, nomeRemover);
    }

    private void imprimirMenorMaior(NodoPartido raizArvore){
        if (raizArvore != null) {
            imprimirMenorMaior(raizArvore.esquerda);
            System.out.println("Nome: " + raizArvore.item.getNome() + "         Nome do partido : " + raizArvore.item.getNome() + "     ");
            System.out.println("Sigla do partido: " + raizArvore.item.getSigla());
            System.out.println("\n");
            imprimirMenorMaior(raizArvore.direita);
        }
    }


    public void imprimirEmOrdem() {
        imprimirMenorMaior(this.raiz);
    }

    private int contarNumPartidos(NodoPartido raizArvore) {
        int soma = 1;
        if (raizArvore.esquerda != null){
            soma += contarNumPartidos(raizArvore.esquerda);
        }
        if (raizArvore.direita != null) {
            soma += contarNumPartidos(raizArvore.direita);
        }
        return  soma;
    }

    public int numPartidos(){
        return contarNumPartidos(this.raiz);
    }

}
