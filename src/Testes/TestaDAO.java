package Testes;

import Classes.Eleitor;
import Classes.Urnas;
import DAO.*;
import EstruturasDeDados.Candidatos.ABBCandidatos;
import EstruturasDeDados.Eleitor.ABBEleitor;
import EstruturasDeDados.Municipios.ListaMunicipios;
import EstruturasDeDados.Partido.ABBPartido;
import EstruturasDeDados.Urnas.PilhaUrna;

import java.io.IOException;

public class TestaDAO {
    public static void main(String[] args) throws IOException {
        try {
            var eleitorDAO = new EleitorDAO("eleitores.txt");
            var candidatosDAO = new CandidatosDAO("candidatos.txt");
            var urnasDAO = new UrnasDAO("urnas.txt");

            urnasDAO.cadastraCandidatos(candidatosDAO.getall(), urnasDAO.getAll());


//            var candidatosDAO = new CandidatosDAO("candidatos.txt");
//            ABBCandidatos arvoreCandidato = candidatosDAO.getall();
//            arvoreCandidato.imprimirEmOrdem();

//             var municipioDAO = new MunicipioDAO("municipios.txt");
//             ListaMunicipios listaMunicipios = municipioDAO.getAll();
//             listaMunicipios.imprimir();

//            var partidosDAO = new PartidoDAO("partido.txt");
//            ABBPartido arvorePartido = partidosDAO.getAll();
//            arvorePartido.imprimirEmOrdem();

//



        } catch (Exception e) {
            return;
        }

    }
}
