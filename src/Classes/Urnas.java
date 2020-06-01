package Classes;

public class Urnas {

    private String nomeDoMunicípio;
    private String zonaEleitoral;
    private String secaoEleitoral;

    public Urnas(String nomeDoMunicípio, String zonaEleitoral, String secaoEleitoral){
        this.nomeDoMunicípio = nomeDoMunicípio;
        this.zonaEleitoral = zonaEleitoral;
        this.secaoEleitoral = secaoEleitoral;
    }

    public Urnas(){}

    public String getNomeDoMunicípio() {
        return nomeDoMunicípio;
    }

    public void setNomeDoMunicípio(String nomeDoMunicípio) {
        this.nomeDoMunicípio = nomeDoMunicípio;
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
