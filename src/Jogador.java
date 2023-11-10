import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Henrique
 * @guidance Ícaro
 * 
 */

public class Jogador {

    private List<Carta> mao; //Lista de cartas privada do jogador (Mão)
    private float saldo; //Saldo de aposta do jogador

    public Jogador(float valor) {
        this.mao = new ArrayList<>(); // Inicialize a lista mao com a lista passada como argumento
        this.saldo = valor;
    }
    
    public List<Carta> getMao() {
        return mao;
    }

    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}

