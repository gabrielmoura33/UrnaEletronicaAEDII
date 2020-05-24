package Testes;

import DAO.EleitorDAO;
import EstruturasDeDados.Eleitor.ABBEleitor;

import java.io.IOException;

public class TestaDAO {
    public static void main(String[] args) throws IOException {
        try {
            var DAO = new EleitorDAO("testes.txt");
            ABBEleitor listaEleitor = DAO.getAll();

            listaEleitor.imprimirEmOrdem();

            System.out.println("Quantidade de Votantes");
            System.out.println(listaEleitor.numAlunos());
        } catch (Exception e) {
            return;
        }




//        listaEleitor.imprimir();
//        Eleitor[] eleitor = new Eleitor[5];;
//
//        eleitor[0] = new Eleitor();
//        eleitor[0].setNome("arrayEntrada[0]");
//        eleitor[0].setTituloEleitoral(Double.parseDouble("13"));
//        eleitor[0].setMunicipioEleitoral("arrayEntrada[2]");
//        eleitor[0].setZonaEleitoral("arrayEntrada[3]");
//        eleitor[0].setSecaoEleitoral("arrayEntrada[4]");
//
//        System.out.println(eleitor[0].getNome());
//        System.out.println(eleitor[0].getTituloEleitoral());
//        System.out.println(eleitor[0].getMunicipioEleitoral());
//        System.out.println( eleitor[0].getZonaEleitoral());
//        System.out.println(eleitor[0].getSecaoEleitoral());

    }
}
