package Classes;

public class Eleitor {
    private String nome;
    private double tituloEleitoral;
    private String municipioEleitoral;
    private String zonaEleitoral;
    private String secaoEleitoral;

    public Eleitor(String nome, double tituloEleitoral, String municipioEleitoral, String zonaEleitoral, String secaoEleitoral) {
        this.nome = nome;
        this.tituloEleitoral = tituloEleitoral;
        this.municipioEleitoral = municipioEleitoral;
        this.zonaEleitoral = zonaEleitoral;
        this.secaoEleitoral = secaoEleitoral;
    }

    public Eleitor() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTituloEleitoral() {
        return tituloEleitoral;
    }

    public void setTituloEleitoral(double tituloEleitoral) {
        this.tituloEleitoral = tituloEleitoral;
    }

    public String getMunicipioEleitoral() {
        return municipioEleitoral;
    }

    public void setMunicipioEleitoral(String municipioEleitoral) {
        this.municipioEleitoral = municipioEleitoral;
    }

    public String getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(String zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    public String getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public void setSecaoEleitoral(String secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }
}
