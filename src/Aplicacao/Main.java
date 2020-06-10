package Aplicacao;

import Classes.*;
import DAO.*;
import EstruturasDeDados.Candidatos.ABBCandidatos;
import EstruturasDeDados.Eleitor.ABBEleitor;
import EstruturasDeDados.Municipios.ListaMunicipios;
import EstruturasDeDados.Partido.ABBPartido;
import EstruturasDeDados.Urnas.PilhaUrna;
import EstruturasDeDados.Votos.ABBVoto;

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
                        urnaConfigurada = true;
                    }
                    moduloUrna(moduloTRE);

                }
            }
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your input!\r\n");
            System.exit(1);
        }


    }

    private static void moduloUrna(ModuloTRE moduloTRE) throws IOException {
        Urnas[] urnasCadastradas = moduloTRE.pilhaUrna.retornaUrnas();
        Candidatos[] vereadoresPorUrna, prefeitosPorUrna;
        VotoDAO daoVotos = new VotoDAO("Votos.txt");
        Voto voto;

        int i = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        while (input != urnasCadastradas.length + 1) {
            System.out.println("Para selecionar uma urna eletronica digite seu codigo de zona eleitoral ou " + ( urnasCadastradas.length + 1 ) + " para SAIR! \n");
            for (Urnas urna : urnasCadastradas) {
                System.out.println(urna.getZonaEleitoral() + ". "+ urna.getNomeDoMunicípio() + " Zona Eleitoral: "+ urna.getZonaEleitoral());
                i++;
            }
            System.out.println(urnasCadastradas.length + 1 + ". SAIR!");
            input = Integer.parseInt(br.readLine());
            Eleitor[] eleitoresVotantes = moduloTRE.abbEleitor.retornaEleitorPorZonaEleitoral(String.valueOf(input));

            if (eleitoresVotantes.length <= 0){
                System.out.println("Não existem eleitores para essa zona eleitoral");
                break;
            }

            ABBEleitor arvoreEleitoresVotantes = new ABBEleitor();
            for (Eleitor el : eleitoresVotantes) {
                arvoreEleitoresVotantes.inserir(el);
            }

            System.out.println("Selecione Uma Opçao");
            System.out.println("1. Votar");
            System.out.println("2. Justificar Ausência");
            int inputSubMenu = Integer.parseInt(br.readLine());
            switch (inputSubMenu){
                case 1:
                    voto = new Voto();
                    System.out.println("Digite seu título de Eleitor");
                    double inputSubSubMenu = Double.parseDouble(br.readLine());
                    Eleitor votante = arvoreEleitoresVotantes.buscar(inputSubSubMenu);


                    if (votante == null){
                        System.out.println("Você nao está configurado para votar nesta Urna!");
                    } else{
                        vereadoresPorUrna = moduloTRE.abbCandidatos.retornaCandidatoPorMunicipioECargo(votante.getMunicipioEleitoral());
                        if (vereadoresPorUrna == null){
                            System.out.println("Sem candidatos para esse municipio!");
                        } else {
                            i = 1;
                            for (Candidatos vereador : vereadoresPorUrna) {
                                System.out.println(vereador.getNumero() + ". " + vereador.getNome() + " Cargo: " + vereador.getCargo());
                                i++;
                            }
                            System.out.println("Selecione Um Candidato a Vereador digitando seu número");
                            double inputVoto = Double.parseDouble(br.readLine());
                            voto.setNumeroCandidatoVereador(inputVoto);
                            System.out.println("Selecione Um Candidato a Prefeito digitando seu número");
                            inputVoto = Double.parseDouble(br.readLine());
                            voto.setNumeroCanditatoPrefeito(inputVoto);
                            voto.setJustificouAusencia(false);
                            voto.setZonaEleitoral(String.valueOf(input));
                            voto.setTituloEleitoral(votante.getTituloEleitoral());
                        }

                        daoVotos.armazenaVoto(voto);
                    }
                    break;
                case 2:
                    System.out.println("Justificou voto!");
                    break;
                default:
                    System.out.println("Opçao inválida!");
                    break;
            }



            if(input < 0 || input > urnasCadastradas.length + 2) {
                System.out.println("Opção Selecionada inválida! Tente Novamente\r\n");
            } else if(input == urnasCadastradas.length + 1) {
                System.out.println("Você saiu do programa\r\n");
            }
            i = 1;
        }

        moduloTRE.ImportarDados(daoVotos);


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
//            eleitorDAO = new EleitorDAO(br.readLine());
            eleitorDAO = new EleitorDAO("eleitores.txt");
            abbEleitor = eleitorDAO.getAll();

            System.out.println("2. Digite o nome do arquivo de Candidatos");
//            candidatosDAO = new CandidatosDAO(br.readLine());
            candidatosDAO = new CandidatosDAO("candidatos.txt");
            abbCandidatos = candidatosDAO.getall();

            System.out.println("3. Digite o nome do arquivo de Municipios");
//            municipioDAO = new MunicipioDAO(br.readLine());
            municipioDAO = new MunicipioDAO("municipios.txt");
            listaMunicipios = municipioDAO.getAll();

            System.out.println("4. Digite o nome do arquivo de Partidos");
//            partidoDAO = new PartidoDAO(br.readLine());
            partidoDAO = new PartidoDAO("partidos.txt");
            abbPartido = partidoDAO.getAll();

            System.out.println("5. Digite o nome do arquivo de Urnas");
//            urnasDAO = new UrnasDAO(br.readLine());
            urnasDAO = new UrnasDAO("urnas.txt");
            pilhaUrna = urnasDAO.getAll();

            urnasDAO.cadastraEleitor(abbEleitor, pilhaUrna);
            urnasDAO.cadastraCandidatos(abbCandidatos, pilhaUrna);

        }  catch (Exception e) {
            return;
        }
    }

    void ImportarDados(VotoDAO votos) {
        ABBVoto votosTotais = votos.getAll();
        votos.armazenaVencedor(votosTotais, candidatosDAO.getall());
    }
    void ListarPrefeitosEleitos() {

    }
    void ListarVereadoresEleitos() {

    }
}
