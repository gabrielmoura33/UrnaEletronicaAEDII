package DAO;

import java.io.*;
import Classes.Candidatos;
import EstruturasDeDados.Candidatos.ABBCandidatos;
import EstruturasDeDados.Eleitor.ABBEleitor;

public class CandidatosDAO {
	
	private Candidatos[] candidatos;
	private String filename;
	
	public CandidatosDAO(String filename) {
		this.filename = filename;
	}
	
	public ABBCandidatos getall() {
		ABBCandidatos arvore = readFromFile();
		return arvore;
	}
	
	private ABBCandidatos readFromFile() {
		ABBCandidatos aux = new ABBCandidatos();
		
		try(BufferedReader buffer_entrada = new BufferedReader(new FileReader(filename))){
			String idSTR;
			int numeroDeLinhas = countLinesNew(filename);
			int i = 0;
			
			while((idSTR = buffer_entrada.readLine()) != null) {
				try {
					String[] arrayEntrada = idSTR.split(";");
					if(arrayEntrada.length > 5 || arrayEntrada.length < 5) {
						throw new Exception("Erro de leitura! Dados obrigatorios n�o foram preenchidos");
					}
					this.candidatos = new Candidatos[numeroDeLinhas + 1];
					
					this.candidatos[i] = new Candidatos();
					this.candidatos[i].setNome(arrayEntrada[0]);
					this.candidatos[i].setNumero(Double.parseDouble(arrayEntrada[1]));
					this.candidatos[i].setMunicipio(arrayEntrada[2]);
					this.candidatos[i].setPartidoPolitico(arrayEntrada[3]);
					this.candidatos[i].setCargo(arrayEntrada[4].charAt(1));
					
					aux.inserir(candidatos[i]);
				}catch(Exception e) {
					System.out.println(e);
				}
				i++;
			}
			return aux;
		}catch(Exception e) {
			System.out.println("Erro na abertura do Arquivo! \n Verifique o nome do arquivo e tente novamente");
		}
		return null;
	}
	
	public static int countLinesNew(String filename) throws IOException{
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			
			int readChars = is.read(c);
			if(readChars == -1) {
				return 0;
			}
			
			int count = 0;
			while(readChars == 1024) {
				for(int i = 0; i<1024;) {
					if(c[i++] == '\n') {
						count++;
					}
				}
				readChars = is.read(c);
			}
			
			while(readChars != -1) {

				for(int i=0; i<readChars; i++) {
					if(c[i] == '\n') {
						count++;
					}
				}
				readChars = is.read(c);
			}
			
			return count == 0 ? 1 : count;
		}finally {
			is.close();
		}
	}
}
