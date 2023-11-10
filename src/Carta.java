/**
 *
 * @author José Henrique
 * @guidance Ícaro
 * 
 */

public class Carta { //Criando a classe carta

    private Naipe naipe;  //Cada carta tem um naipe e um número
    private Numero numero;
    
    public Carta (Naipe naipe, Numero numero) { //Construtor do objeto

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
