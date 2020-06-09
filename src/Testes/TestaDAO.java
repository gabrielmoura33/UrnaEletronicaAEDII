package Testes;

import Classes.Candidatos;
import Classes.Eleitor;
import Classes.Urnas;
import Classes.Voto;
import DAO.*;
import EstruturasDeDados.Candidatos.ABBCandidatos;
import EstruturasDeDados.Eleitor.ABBEleitor;
import EstruturasDeDados.Municipios.ListaMunicipios;
import EstruturasDeDados.Partido.ABBPartido;
import EstruturasDeDados.Urnas.PilhaUrna;
import EstruturasDeDados.Votos.ABBVoto;

import java.io.IOException;

public class TestaDAO {
    public static void main(String[] args) throws IOException {
        try {
//            var eleitorDAO = new EleitorDAO("eleitores.txt");
//            ABBEleitor abbEleitor = eleitorDAO.getAll();
//            Eleitor[] eleuts = abbEleitor.retornaEleitorPorZonaEleitoral("1795");
//            ABBEleitor aux = new ABBEleitor();
//
//            for (int i = 0; i < eleuts.length; i++) {
//                aux.inserir(eleuts[i]);
//            }
//
//           Eleitor auxDois = aux.buscar(Double.parseDouble("22222"));
//
//            if (auxDois == null){
//                System.out.println("NuLo CARALHO");
//            }
//            System.out.println(auxDois.getNome());


//
//            System.out.println(abbEleitor.buscar(Double.parseDouble("23131")).getNome());
//            for (Eleitor el : eleitors) {
//                System.out.println(el.getNome());
//            }
//            var candidatosDAO = new CandidatosDAO("candidatos.txt");
//            ABBCandidatos auxiliar = candidatosDAO.getall();
//
//            Candidatos[] candidatos = auxiliar.retornaCandidatoPorMunicipioECargo("BeloHorizonte", 'V');
//
//            for (Candidatos cand : candidatos){
//                System.out.println(cand.getNome());
//            }


            var candidatosDAO = new CandidatosDAO("candidatos.txt");
            ABBCandidatos arvoreCandidato = candidatosDAO.getall();
            VotoDAO votoDAO = new VotoDAO("Votos.txt");
            ABBVoto abbVoto = votoDAO.getAll();

            Voto[] arrVoto = abbVoto.retornaVotosPorZonaEleitoral("1795");
            votoDAO.armazenaVencedor(abbVoto, arvoreCandidato);

//            var urnasDAO = new UrnasDAO("urnas.txt");
//
//            urnasDAO.cadastraCandidatos(candidatosDAO.getall(), urnasDAO.getAll());


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
