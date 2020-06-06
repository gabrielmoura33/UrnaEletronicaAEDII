package Aplicacao;

import Classes.*;
import DAO.*;
import EstruturasDeDados.Candidatos.ABBCandidatos;
import EstruturasDeDados.Eleitor.ABBEleitor;
import EstruturasDeDados.Municipios.ListaMunicipios;
import EstruturasDeDados.Partido.ABBPartido;
import EstruturasDeDados.Urnas.PilhaUrna;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        ModuloTRE moduloTRE = null;
        boolean urnaConfigurada = false;

        try {
            while (input != 3) {
                System.out.println("URNA Eletronica \n");
                System.out.println("Menu Options:");
                System.out.println("1. Modulo TRE ");
                System.out.println("2. Modulo Urna Eletronica");
                System.out.println("3. Sair do Programa");
                System.out.println("");
                System.out.print("Please select an option from 1-3\r\n");
                input = Integer.parseInt(br.readLine());

                if(input < 0 || input > 3) {
                    System.out.println("Opção Selecionada inválida! Tente Novamente\r\n");
                } else if(input == 3) {
                    System.out.println("Você saiu do programa\r\n");
                    System.exit(1);
                } else if (input == 1){
                    moduloTRE = new ModuloTRE();
                    urnaConfigurada = true;
                } else if(input == 2) {
                    if (!urnaConfigurada) {
                        System.out.println("Atenção, primeiro é necessário configurar a urna Eletronica com as informações de candidatos, eleitores e urnas!");
                        moduloTRE = new ModuloTRE();
                    }
                    moduloUrna(moduloTRE);
                }
            }
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your input!\r\n");
            System.exit(1);
        }


    }

    private static void moduloUrna(ModuloTRE moduloTRE) {

    }
}

class ModuloTRE {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

     // DAO -> Objetos de acesso ao arquivo
    UrnasDAO urnasDAO;
    CandidatosDAO candidatosDAO;
    EleitorDAO eleitorDAO;
    MunicipioDAO municipioDAO;
    PartidoDAO partidoDAO;

    // Estruturas de dados iniciais
    ABBCandidatos abbCandidatos;
    ABBEleitor abbEleitor;
    ABBPartido abbPartido;
    ListaMunicipios listaMunicipios;
    PilhaUrna pilhaUrna;

    ModuloTRE() throws IOException {
        try {
            System.out.println("1. Digite o nome do arquivo de Eleitores");
            eleitorDAO = new EleitorDAO(br.readLine());
            abbEleitor = eleitorDAO.getAll();

            System.out.println("2. Digite o nome do arquivo de Candidatos");
            candidatosDAO = new CandidatosDAO(br.readLine());
            abbCandidatos = candidatosDAO.getall();

            System.out.println("3. Digite o nome do arquivo de Municipios");
            municipioDAO = new MunicipioDAO(br.readLine());
            listaMunicipios = municipioDAO.getAll();

            System.out.println("4. Digite o nome do arquivo de Partidos");
            partidoDAO = new PartidoDAO(br.readLine());
            abbPartido = partidoDAO.getAll();

            System.out.println("5. Digite o nome do arquivo de Urnas");
            urnasDAO = new UrnasDAO(br.readLine());
            pilhaUrna = urnasDAO.getAll();

            urnasDAO.cadastraEleitor(abbEleitor, pilhaUrna);
            urnasDAO.cadastraCandidatos(abbCandidatos, pilhaUrna);

        }  catch (IOException e) {
            return;
        }
    }

    void ImportarDados() {

    }
    void ListarPrefeitosEleitos() {

    }
    void ListarVereadoresEleitos() {

    }
}
