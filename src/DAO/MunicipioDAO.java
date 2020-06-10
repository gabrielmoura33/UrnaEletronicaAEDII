package DAO;

import Classes.Municipios;
import EstruturasDeDados.Municipios.ListaMunicipios;

import java.io.*;

public class MunicipioDAO {

    private Municipios[] municipios;
    private String filename;
    //	Construtor da classe
    public MunicipioDAO(String filename) {
        this.filename = filename;
    }

//	Método que retorna uma árvore criada com todos os municipios contidos diretamente para quem o chamou
    public ListaMunicipios getAll() {
        ListaMunicipios lista = readFromFile();
        return lista;
    }
    //	Método que lé os atributos dos municipios do arquivo, realiza o split e atribui corretamente
//	a cada municipio
    private ListaMunicipios readFromFile() {
    	ListaMunicipios aux = new ListaMunicipios();
        try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(filename))) {
            String idSTR;
            int numeroDeLinhas = countLinesNew(filename);
            int i = 0;
            while ((idSTR = buffer_entrada.readLine()) != null) {
                try {
                    String[] arrayEntrada = idSTR.split(";");
                    if (arrayEntrada.length > 4 || arrayEntrada.length < 4){
                        throw new Exception("Erro De leitura Arquivo de Municipios! Dados obrigat�rios n�o foram preenchidos");
                    }
                   this.municipios = new Municipios[numeroDeLinhas + 1];

                    this.municipios[i] = new Municipios();
                    this.municipios[i].setNome(arrayEntrada[0]);
                    this.municipios[i].setEstado(arrayEntrada[1]);
                    this.municipios[i].setQuantHabitantes(Double.parseDouble(arrayEntrada[2]));
                    this.municipios[i].setVagasVereador(Double.parseDouble(arrayEntrada[3]));

                    aux.inserirFinal(municipios[i]);
                } catch (Exception e) {
                    System.out.println(e);
                }
                i++;
            }
            return aux;
        } catch (Exception e) {
            System.out.println("Erro na abertura do Arquivo! \n Verifique o nome do arquivo e tente novamente.");
            System.exit(1);

        }
        return null;
    }
    //	Método que conta todas as linhas do arquivo para possibilitar a criação do array com tamanho ideal
//	indiferente da quantidade de informação no arquivo.
    public static int countLinesNew(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                return 0;
            }

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
