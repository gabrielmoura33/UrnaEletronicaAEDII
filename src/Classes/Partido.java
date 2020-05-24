package Classes;

public class Partido {
    private String nome;
    private String sigla;

    public Partido(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Partido() {super();}

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }


}
