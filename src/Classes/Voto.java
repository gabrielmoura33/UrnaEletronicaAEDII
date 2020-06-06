package Classes;

public class Voto {

    private double numeroCandidatoVereador;
    private double numeroCanditatoPrefeito;

    private boolean justificouAusencia;
    private String secaoEleitoral;
    private double tituloEleitoral;

    public String getSecaoEleitoral() {
        return secaoEleitoral;
    }
    public void setSecaoEleitoral(String secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }

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
            System.out.println("Impossivel Buscar titulo eleitoral de quem não justificou ausencia!");
            return 0;
        }
    }
    public double getNumeroCandidatoVereador() {
        return numeroCandidatoVereador;
    }

    public double getNumeroCanditatoPrefeito() {
        return numeroCanditatoPrefeito;
    }

    public void setTituloEleitoral(double tituloEleitoral) {
        if (this.justificouAusencia)
            this.tituloEleitoral = tituloEleitoral;
        else {
            System.out.println("Impossivel Armazenar titulo eleitoral de quem não justificou ausencia!");
        }
    }
}
