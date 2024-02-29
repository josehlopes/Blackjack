import java.util.ArrayList;
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

    public void iniciarJogo(Jogador player, Jogador dealer, int indice) {
        baralho.distribuir(player, 2, 0);
        baralho.distribuir(dealer, 2, 0);
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

    public int Contar_mao(Jogador jogador, int indice) {
        List<List<Carta>> maosDoJogador = jogador.getMaos();
        List<Carta> mao = maosDoJogador.get(indice);

        int soma = 0;
        for (Carta carta : mao) {
            soma += carta.getNumero().getValor();
        }

        return soma;
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

    public void Split(Jogador j, int indice) {
        List<List<Carta>> maosDoJogador = j.getMaos();
        List<Carta> mao = maosDoJogador.get(indice);
        Carta carta1 = mao.get(0);
        Carta carta2 = mao.get(1);

        if (carta1.getNumero() == carta2.getNumero()) {
            List<Carta> novaMao = new ArrayList<>();
            novaMao.add(carta1);

            mao.remove(0);

            maosDoJogador.add(novaMao);
        } else {
            System.out.println("Não foi possível fazer o split");
        }
    }

}