package EstruturasDeDados.Votos;

import Classes.Voto;

public class ABBVoto {

	private NodoVoto raiz;
    private Voto[] arrayVotos;
    private int root = 0;

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


    private Voto[] retornaVotosMaiorMenor(NodoVoto raizArvore ){
        if (raizArvore != null) {
            retornaVotosMaiorMenor(raizArvore.esquerda);
                arrayVotos[root] = raizArvore.item;
                root ++;
            retornaVotosMaiorMenor(raizArvore.direita);
        }
        return arrayVotos;
    }

    public Voto[] retornaVotos() {
        arrayVotos = new Voto[numVotos()];
        this.root = 0;
        return retornaVotosMaiorMenor(this.raiz);
    }

    public Voto[] retornaVotosPorZonaEleitoral (String zonaEleitoral) {
        Voto[] eleitoresTotais = retornaVotos();
        int i = 0;
        int j = 0;
        for (Voto el : eleitoresTotais){
            if (el.getZonaEleitoral().equals(zonaEleitoral)){
                i++;
            }
        }
        Voto[] eleitoresPorZona = new Voto[i];
        for (Voto ul : eleitoresTotais){
            if (ul.getZonaEleitoral().equals(zonaEleitoral)){
                eleitoresPorZona[j] = ul;
                j++;
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

