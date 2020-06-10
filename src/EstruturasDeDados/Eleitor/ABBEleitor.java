package EstruturasDeDados.Eleitor;

import Classes.Eleitor;

public class ABBEleitor {

	private NodoEleitor raiz;
	private int root = 0;
    private Eleitor[] arrayEleitores;

    public ABBEleitor()
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


// Método recursivo do procedimento de inserir novos eleitores

    private NodoEleitor adicionar(NodoEleitor raizArvore, Eleitor eleitor)
    {
        if (raizArvore == null)
            raizArvore = new NodoEleitor(eleitor);
        else
        {
            if (raizArvore.item.getTituloEleitoral() > eleitor.getTituloEleitoral())
                raizArvore.esquerda = adicionar(raizArvore.esquerda, eleitor);
            else
            {
                if (raizArvore.item.getTituloEleitoral() < eleitor.getTituloEleitoral())
                    raizArvore.direita = adicionar(raizArvore.direita, eleitor);
                else
                    System.out.println("O Eleitor " + eleitor.getNome() + ", cujo titulo Eleitoral é " + eleitor.getTituloEleitoral() + ", já foi inserido anteriormente. \n");
            }
        }
        return raizArvore;
    }

// Metodo para inserir novos eleitores a arvore
    public void inserir(Eleitor eleitorNovo)
    {
        this.raiz = adicionar(this.raiz, eleitorNovo);
    }

    private NodoEleitor antecessor(NodoEleitor eleitorRetirar, NodoEleitor raizArvore)
    {
        if (raizArvore.direita != null)
        {
            raizArvore.direita = antecessor(eleitorRetirar, raizArvore.direita);
            return raizArvore;
        }
        else
        {
            eleitorRetirar.item.setNome(raizArvore.item.getNome());
            eleitorRetirar.item.setTituloEleitoral(raizArvore.item.getTituloEleitoral());
            eleitorRetirar.item.setSecaoEleitoral(raizArvore.item.getSecaoEleitoral());
            eleitorRetirar.item.setZonaEleitoral(raizArvore.item.getZonaEleitoral());
            eleitorRetirar.item.setMunicipioEleitoral(raizArvore.item.getMunicipioEleitoral());


            return raizArvore.esquerda;
        }
    }

    private NodoEleitor retirar(NodoEleitor raizArvore, int tituloEleitor)
    {
        if (raizArvore == null)
        {
        	 System.out.println("O Eleitor, cujo Titulo de Eleitor é " + tituloEleitor + ", não foi encontrado.");
        	 System.out.println("\n");

            return raizArvore;
        }
        else
        {
            if (raizArvore.item.getTituloEleitoral() == tituloEleitor)
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
                if (raizArvore.item.getTituloEleitoral() > tituloEleitor)
                    raizArvore.esquerda = retirar(raizArvore.esquerda, tituloEleitor);
                else
                    raizArvore.direita = retirar(raizArvore.direita, tituloEleitor);
                return raizArvore;
            }
        }
    }

    public void remover(int matriculaRemover)
    {
        this.raiz = retirar(this.raiz, matriculaRemover);
    }

    private void imprimirMenorMaior(NodoEleitor raizArvore){
        if (raizArvore != null) {
            imprimirMenorMaior(raizArvore.esquerda);
            System.out.println("Nome: " + raizArvore.item.getNome() + "         Titulo Eleitoral: " + raizArvore.item.getTituloEleitoral() + "     ");
            System.out.println("Municipio Eleitoral: " + raizArvore.item.getMunicipioEleitoral() + "         Zona Eleitoral:  " + raizArvore.item.getZonaEleitoral() + "      ");
            System.out.println("Seção Eleitoral:  " + raizArvore.item.getSecaoEleitoral());
            System.out.println("\n");
            imprimirMenorMaior(raizArvore.direita);
        }
    }

//Método que usa imprimirMenorMaior de forma recursiva
    public void imprimirEmOrdem() {
        imprimirMenorMaior(this.raiz);
    }

    private int contarNumEleitores(NodoEleitor raizArvore) {
        int soma = 1;
        if (raizArvore.esquerda != null){
            soma += contarNumEleitores(raizArvore.esquerda);
        }
        if (raizArvore.direita != null) {
            soma += contarNumEleitores(raizArvore.direita);
        }
        return  soma;
    }

    public int numEleitores(){
        return contarNumEleitores(this.raiz);
    }

    // Método utilizado para retornar as informações da árvore em um array
    private Eleitor[] retornaEleitorMenorMaior(NodoEleitor raizArvore){
        if (raizArvore != null) {
            retornaEleitorMenorMaior(raizArvore.esquerda);
                arrayEleitores[root] = raizArvore.item;
                root ++;
            retornaEleitorMenorMaior(raizArvore.direita);
        }
        return arrayEleitores;
    }

    public Eleitor[] retornaEleitor() {
        arrayEleitores = new Eleitor[numEleitores()];

        return retornaEleitorMenorMaior(this.raiz);
    }

    public Eleitor[] retornaEleitorPorZonaEleitoral (String zonaEleitoral) {
        this.root = 0;
        Eleitor[] eleitoresTotais = retornaEleitor();
        int i = 0;
        int j  = 0;
        for (Eleitor el : eleitoresTotais){
            if (el.getZonaEleitoral().equalsIgnoreCase(zonaEleitoral)){
                i++;
            }
        }
        Eleitor[] eleitoresPorZona = new Eleitor[i];
        for (Eleitor el : eleitoresTotais){
            if (el.getZonaEleitoral().equalsIgnoreCase(zonaEleitoral)){
                eleitoresPorZona[j] = el;
                j++;
            }
        }
        return eleitoresPorZona;
    }



    public Eleitor buscar(double tituloEleitoral){
        Eleitor[] eleitores = retornaEleitor();

        for (Eleitor el: eleitores) {
            if (el.getTituloEleitoral() == tituloEleitoral)
                return el;
        }

        return null;
    }



}
