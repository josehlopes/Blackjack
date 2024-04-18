package _data;

public class Carta {

    private Naipe naipe;
    private Numero numero;
    
    public Carta (Naipe naipe, Numero numero) {

        this.naipe = naipe;
        this.numero = numero;

    }
    
    public Naipe getNaipe() {
        return naipe;
    }

    public void setNaipe(Naipe naipe) {
        this.naipe = naipe;
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }
    
    @Override
    public String toString() { //Formatando o retorno
        return "Carta{" + "naipe=" + naipe + ", numero=" + numero + '}';
    }

}
