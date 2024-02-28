
import java.util.List;

/**
 *
 * @author José Henrique
 * @guidance Ícaro
 *
 */
public class Blackjack {

    private Baralho baralho;
    boolean verificar_carta = false;

    public Blackjack() {
        baralho = new Baralho();
        baralho.embaralhar();
    }

    public void iniciarJogo(Jogador player, Jogador dealer) {
        baralho.distribuir(player, 2);
        baralho.distribuir(dealer, 2);
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public boolean Verificar_As(Jogador jogador) {
        List<List<Carta>> maosDoJogador = jogador.getMaos();

        if (maosDoJogador.size() > 0) {
            List<Carta> mao = maosDoJogador.get(0);

            for (int i = 0; i < mao.size(); i++) {
                Carta as_na_mao = mao.get(i);
                if (as_na_mao.getNumero() == Numero.as) {
                    for (int j = 0; j < mao.size(); j++) {
                        Carta rei_dama_valete = mao.get(j);
                        if (rei_dama_valete.getNumero() == Numero.rei || rei_dama_valete.getNumero() == Numero.dama
                                || rei_dama_valete.getNumero() == Numero.valete) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public int Contar_mao(Jogador jogador) { // Conta e retorna a quantidade de cartas da mão do jogador
        List<List<Carta>> maosDoJogador;
        int soma = 0;
        for (int i = 0; i < maosDoJogador.size(); i++) {
            List<Carta> mao = maosDoJogador.get(0);
            Carta contar = mao.get(i);
            soma += contar.getNumero().getValor();
        }
        return soma;
    }

    }

    public void Surrender(Jogador player) { // Recupera 50% da aposta antes de desistir

        float recuperarSaldo = player.getSaldo();
        recuperarSaldo /= 2;
        player.setSaldo(recuperarSaldo);

    }

    public void Hit(Jogador j, int quantidade, int indice) {
        baralho.distribuir(j, quantidade, indice);
        System.out.println("Quantidade de cartas restantes: " + getBaralho().getCartas().size());
    }
}
