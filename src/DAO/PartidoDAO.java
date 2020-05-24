package DAO;

import Classes.Partido;
import EstruturasDeDados.Partido.ABBPartido;

import java.io.*;

public class PartidoDAO {

    private Partido[] Partido;
    private String filename;
    public PartidoDAO(String filename) {
        this.filename = filename;
    }

    public ABBPartido getAll() {
        ABBPartido arvore = readFromFile();
        return arvore;
    }

    private ABBPartido readFromFile() {
        ABBPartido aux = new ABBPartido();
        try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(filename))) {
            String idSTR;
            int numeroDeLinhas = countLinesNew(filename);
            int i = 0;
            while ((idSTR = buffer_entrada.readLine()) != null) {
                try {
                    String[] arrayEntrada = idSTR.split(";");
                    if (arrayEntrada.length > 2 || arrayEntrada.length < 2){
                        throw new Exception("Erro De leitura! Dados obrigatórios não foram preenchidos");
                    }
                    this.Partido = new Partido[numeroDeLinhas + 1];

                    this.Partido[i] = new Partido();
                    this.Partido[i].setNome(arrayEntrada[0]);
                    this.Partido[i].setSigla(arrayEntrada[1]);
                    aux.inserir(Partido[i]);
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
                for (int i=0; i<1024;) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
                System.out.println(readChars);
                for (int i=0; i<readChars; ++i) {
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
