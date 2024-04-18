package _application;
import java.util.ArrayList;
import java.util.List;
import _data.Carta;

/**
 *
 * @author José Henrique
 * @guidance Ícaro
 * 
 */

public class Jogador {

    private List<List<Carta>> maos;
    private float saldo;

    public Jogador(float valor) {
        this.maos = new ArrayList<>(); // Inicialize a lista mao com a lista passada como argumento
        this.saldo = valor;
    }

    public List<List<Carta>> getMaos() {
        return maos;
    }

    public List<Carta> getMao(int indice) {
        if (indice >= 0 && indice < maos.size()) {
            return maos.get(indice);
        } else {
            return null;
        }
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
