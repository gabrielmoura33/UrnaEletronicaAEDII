package Classes;

public class Voto {

    private double numeroCandidatoVereador;
    private double numeroCanditatoPrefeito;
    private String zonaEleitoral;
    private boolean justificouAusencia;
    private double tituloEleitoral;


    public void setNumeroCandidatoVereador(double numeroCandidatoVereador) {
        this.numeroCandidatoVereador = numeroCandidatoVereador;
    }

    public void setNumeroCanditatoPrefeito(double numeroCanditatoPrefeito) {
        this.numeroCanditatoPrefeito = numeroCanditatoPrefeito;
    }

    public boolean JustificouAusencia() {
        return justificouAusencia;
    }


    public void setJustificouAusencia(boolean justificouAusencia) {
        this.justificouAusencia = justificouAusencia;
    }

    public double getTituloEleitoral() {
        if (this.justificouAusencia)
            return tituloEleitoral;
        else {
            return tituloEleitoral;
        }
    }
    public double getNumeroCandidatoVereador() {
        return numeroCandidatoVereador;
    }

    public double getNumeroCanditatoPrefeito() {
        return numeroCanditatoPrefeito;
    }

    public void setTituloEleitoral(double tituloEleitoral) {
        this.tituloEleitoral = tituloEleitoral;
    }
    public String getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(String zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }
}
