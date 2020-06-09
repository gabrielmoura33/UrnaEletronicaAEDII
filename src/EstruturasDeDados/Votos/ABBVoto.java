package EstruturasDeDados.Votos;

import Classes.Voto;

public class ABBVoto {

	private NodoVoto raiz;
    private Voto[] arrayVotos;

    public ABBVoto()
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


    private NodoVoto adicionar(NodoVoto raizArvore, Voto voto)
    {
        if (raizArvore == null)
            raizArvore = new NodoVoto(voto);
        else
        {
            if (raizArvore.item.getTituloEleitoral() > voto.getTituloEleitoral())
                raizArvore.esquerda = adicionar(raizArvore.esquerda, voto);
            else
            {
                if (raizArvore.item.getTituloEleitoral() < voto.getTituloEleitoral())
                    raizArvore.direita = adicionar(raizArvore.direita, voto);
                else
                    System.out.println("Este Eleitor já votou ou justificou ausencia!");
            }
        }
        return raizArvore;
    }

    public void inserir(Voto eleitorNovo)
    {
        this.raiz = adicionar(this.raiz, eleitorNovo);
    }




    private int contarNumVotos(NodoVoto raizArvore) {
        int soma = 1;
        if (raizArvore.esquerda != null){
            soma += contarNumVotos(raizArvore.esquerda);
        }
        if (raizArvore.direita != null) {
            soma += contarNumVotos(raizArvore.direita);
        }
        return  soma;
    }

    public int numVotos(){
        return contarNumVotos(this.raiz);
    }


    private Voto[] retornaVotosMaiorMenor(NodoVoto raizArvore, int posicao ){
        if (raizArvore != null) {
            retornaVotosMaiorMenor(raizArvore.esquerda, posicao + 1);
                arrayVotos[posicao] = raizArvore.item;
            retornaVotosMaiorMenor(raizArvore.direita, posicao + 1);
        }
        return arrayVotos;
    }

    public Voto[] retornaVotos() {
        arrayVotos = new Voto[numVotos()];
        return retornaVotosMaiorMenor(this.raiz, 0);
    }

    public Voto[] retornaVotosPorZonaEleitoral (String zonaEleitoral) {
        Voto[] eleitoresTotais = retornaVotos();
        Voto[] eleitoresPorZona = new Voto[eleitoresTotais.length];
        int i = 0;
        for (Voto el : eleitoresTotais){
            if (el.getZonaEleitoral().equals(zonaEleitoral)){
                eleitoresPorZona[i] = el;
                i++;
            }
        }
        return eleitoresPorZona;
    }

    public int retornaQuantidadeVotos(Voto[] zonaEleitoral, double numeroCandidato){
        int quantidade = 0;
        for (Voto voto : zonaEleitoral){
            if (voto.getNumeroCanditatoPrefeito() == numeroCandidato || voto.getNumeroCandidatoVereador() == numeroCandidato){
                quantidade ++;
            }
        }
        return quantidade;
    }



}

