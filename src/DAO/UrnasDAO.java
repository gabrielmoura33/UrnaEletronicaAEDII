package DAO;

import Classes.Candidatos;
import Classes.Eleitor;
import Classes.Partido;
import Classes.Urnas;
import EstruturasDeDados.Candidatos.ABBCandidatos;
import EstruturasDeDados.Eleitor.ABBEleitor;
import EstruturasDeDados.Partido.ABBPartido;
import EstruturasDeDados.Urnas.PilhaUrna;

import java.io.*;

public class UrnasDAO {
    private Urnas urnas[];
    private String filename;

    public UrnasDAO(String filename) {
        this.filename = filename;
    }

    public PilhaUrna getAll() {
        PilhaUrna pilhaUrna = readFromFile();
        return pilhaUrna;
    }

    private PilhaUrna readFromFile() {
        PilhaUrna aux = new PilhaUrna();
        try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(filename))) {
            String idSTR;
            int numeroDeLinhas = countLinesNew(filename);
            int i = 0;
            while ((idSTR = buffer_entrada.readLine()) != null) {
                try {
                    String[] arrayEntrada = idSTR.split(";");
                    if (arrayEntrada.length > 3 || arrayEntrada.length < 3) {
                        throw new Exception("Erro De leitura Arquivo de Urnas! Dados obrigatórios não foram preenchidos");
                    }
                    this.urnas = new Urnas[numeroDeLinhas + 1];

                    this.urnas[i] = new Urnas();
                    this.urnas[i].setNomeDoMunicípio(arrayEntrada[0]);
                    this.urnas[i].setZonaEleitoral(arrayEntrada[1]);
                    this.urnas[i].setSecaoEleitoral(arrayEntrada[2]);
                    aux.empilhar(urnas[i]);
                } catch (Exception e) {
                    System.out.println(e);
                }
                i++;
            }
            return aux;
        } catch (Exception e) {
            System.out.println("Erro na abertura do Arquivo! \n Verifique o nome do arquivo e tente novamente.");
        }
        return null;
    }

    public void cadastraEleitor(ABBEleitor eleitores, PilhaUrna urnas){
        Urnas[] urnasCadastradas = urnas.retornaUrnas();
        Eleitor[] arrayEleitor = eleitores.retornaEleitor();
        for (Urnas urna : urnasCadastradas) {
            String filename = urna.getNomeDoMunicípio().toLowerCase().replace(" ", "_") + "_" + urna.getSecaoEleitoral() +".txt";
            try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(filename))) {
                for (Eleitor el : arrayEleitor) {
                    if (el.getSecaoEleitoral().equalsIgnoreCase(urna.getSecaoEleitoral())){
                        buffer_saida.write(String.valueOf(el.getTituloEleitoral()));
                        buffer_saida.newLine();
                    }
                }
                buffer_saida.close();
            } catch (Exception e) {
                System.out.println("Erro na abertura do Arquivo de Escrita! \n Verifique o nome do arquivo e tente novamente.");
            }
        }
    }


    public void cadastraCandidatos(ABBCandidatos candidatos, PilhaUrna urnas){
        Urnas[] urnasCadastradas = urnas.retornaUrnas();
        Candidatos[] arrayCandidato = candidatos.retornaCandidato();

        for (Urnas urna : urnasCadastradas){
            String filename = urna.getNomeDoMunicípio().replace(" ", "_") + "_Candidatos.txt";
            try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(filename))){
                for(Candidatos cn : arrayCandidato){
                    buffer_saida.write(cn.getNome());
                    buffer_saida.write(";");
                    buffer_saida.write(String.valueOf(cn.getNumero()));
                    buffer_saida.write(";");
                    buffer_saida.write(cn.getCargo());
                    buffer_saida.newLine();
                }
            }
            catch (Exception e) {
                System.out.println("Erro na abertura do Arquivo de Escrita! \n Verifique o nome do arquivo e tente novamente.");
                System.exit(1);
            }
        }
    }


    public static int countLinesNew(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i = 0; i < 1024; ) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        } finally {
            is.close();
        }
    }
}
