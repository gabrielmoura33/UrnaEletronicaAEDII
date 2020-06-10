package DAO;

import Classes.Candidatos;
import Classes.Urnas;
import Classes.Voto;
import EstruturasDeDados.Candidatos.ABBCandidatos;
import EstruturasDeDados.Urnas.PilhaUrna;
import EstruturasDeDados.Votos.ABBVoto;

import java.io.*;
import java.math.BigDecimal;

public class VotoDAO {
    private Voto votos[];
    private String filename;

    public VotoDAO(String filename) {
        this.filename = filename;
    }

    public ABBVoto getAll() {
        ABBVoto abbVoto = readFromFile();
        return abbVoto;
    }
    private ABBVoto readFromFile() {
        ABBVoto aux = new ABBVoto();
        try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(filename))) {
            String idSTR;
            int numeroDeLinhas = countLinesNew(filename);
            int i = 0;
            while ((idSTR = buffer_entrada.readLine()) != null) {
                try {
                    String[] arrayEntrada = idSTR.split(";");
                    if (arrayEntrada.length > 4 || arrayEntrada.length < 4) {
                        throw new Exception("Erro De leitura Arquivo de VOtos! Dados obrigatórios não foram preenchidos");
                    }
                    this.votos = new Voto[numeroDeLinhas + 1];

                    this.votos[i] = new Voto();
                    this.votos[i].setTituloEleitoral(Double.parseDouble(arrayEntrada[0]));
                    this.votos[i].setNumeroCandidatoVereador(Double.parseDouble(arrayEntrada[1]));
                    this.votos[i].setNumeroCanditatoPrefeito(Double.parseDouble(arrayEntrada[2]));
                    this.votos[i].setZonaEleitoral(arrayEntrada[3]);
                    aux.inserir(votos[i]);
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

    public void armazenaVoto(Voto[] votosGerais){
            String filename = "VotosGerais.txt";
            try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(filename))){
                for (Voto voto : votosGerais){
                    BigDecimal val = new BigDecimal(voto.getTituloEleitoral());
                    buffer_saida.write(val.toString());
                    buffer_saida.write(";");
                    buffer_saida.write(String.valueOf(voto.getNumeroCandidatoVereador()));
                    buffer_saida.write(";");
                    buffer_saida.write(String.valueOf(voto.getNumeroCanditatoPrefeito()));
                    buffer_saida.write(";");
                    buffer_saida.write(voto.getZonaEleitoral());
                    buffer_saida.newLine();
                }
            }
            catch (Exception e) {
                System.out.println("Erro na abertura do Arquivo de Escrita! \n Verifique o nome do arquivo e tente novamente.");
                System.exit(1);
            }
    }

    public void armazenaVencedor(ABBVoto votos, ABBCandidatos candidatos, PilhaUrna urnas){
        Candidatos[] arrayCandidato = candidatos.retornaCandidato();
        Urnas[] arrayUrnas = urnas.retornaUrnas();

        for (Urnas urna : arrayUrnas){
            String filename = urna.getNomeDoMunicípio()+ "_" + urna.getZonaEleitoral() + "_Resultado.txt";
            try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(filename))){
                for(Candidatos cn : arrayCandidato){
                    buffer_saida.write("Nome: ");
                    buffer_saida.write(cn.getNome());
                    buffer_saida.write(" ;Numero: ");
                    buffer_saida.write(String.valueOf(cn.getNumero()));
                    buffer_saida.write(" ;Cargo: ");
                    buffer_saida.write(cn.getCargo());
                    buffer_saida.write(" ;Votos: ");
                    buffer_saida.write(String.valueOf(votos.retornaQuantidadeVotos(votos.retornaVotosPorZonaEleitoral(urna.getZonaEleitoral()), cn.getNumero())));
                    buffer_saida.newLine();
                }
                buffer_saida.newLine();



            }
            catch (Exception e) {
                System.out.println("Erro na abertura do Arquivo de Escrita! \n Verifique o nome do arquivo e tente novamente.");
                System.exit(1);
            }
        }
    }
        public void armazenaAusencia(Voto voto){

        String filename = "Ausencias.txt";
        try (BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(filename))){
            buffer_saida.write(String.valueOf(voto.getTituloEleitoral()));
            buffer_saida.write(";");
            buffer_saida.newLine();
        }
        catch (Exception e) {
            System.out.println("Erro na abertura do Arquivo de Escrita! \n Verifique o nome do arquivo e tente novamente.");
            System.exit(1);
        }
    }

}

