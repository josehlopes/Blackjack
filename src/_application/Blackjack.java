package _application;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import _data.Baralho;
import _data.Carta;
import _data.Numero;

public class Blackjack {

    private Baralho baralho;
    boolean verificar_carta = false;

    public Blackjack() {
        baralho = new Baralho();
        baralho.embaralhar();
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public boolean verificarAs(Jogador jogador) {
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

    public boolean verificarVitoria(Jogador j, int indice) {
        int soma = Contar_mao(j, indice);
    
        if (soma > 21) {
            return false;
        } else if (soma == 21) {
            System.out.println("Você Venceu!");
            return true;
        } else {
            return false;
        }
    }
    

    public void Surrender(Jogador player) {

        float recuperarSaldo = player.getSaldo();
        recuperarSaldo /= 2;
        player.setSaldo(recuperarSaldo);

    }

    public void Hit(Jogador j, int quantidade, int indice, List<Carta> cartas) {
        distribuir(j, quantidade, indice, cartas);
    }

    public boolean Split(Jogador j, int indice) {
        List<List<Carta>> maosDoJogador = j.getMaos();
    
        if (indice < 0 || indice >= maosDoJogador.size()) {
            System.out.println("Índice de mão inválido.");
            return false;
        }
    
        List<Carta> mao = maosDoJogador.get(indice);

        if (mao.size() < 2) {
            System.out.println("A mão não tem cartas suficientes para dividir.");
            return false;
        }
    
        Carta carta1 = mao.get(0);
        Carta carta2 = mao.get(1);
    
        if (carta1.getNumero() == carta2.getNumero()) {
            List<Carta> novaMao = new ArrayList<>();
            novaMao.add(carta1);
    
            mao.remove(0);
    
            maosDoJogador.add(novaMao);
            return true;
    
        } else {
            System.out.println("As cartas não têm o mesmo número, não é possível dividir.");
            return false;
        }
    }
    public void distribuir(Jogador j, int qtd, int indice, List<Carta> cartas) {
        List<List<Carta>> maosDoJogador = j.getMaos();
        if (maosDoJogador.size() <= indice) {
            maosDoJogador.add(new ArrayList<>());
        }
        for (int i = 0; i < qtd; i++) {
            int carta = new Random().nextInt(cartas.size());
            Carta c = cartas.remove(carta);
            List<Carta> mao = maosDoJogador.get(indice);
            mao.add(c);
        }
    }

    public void iniciarJogo(Jogador player, Jogador dealer, int indice, List<Carta> cartas) {
        distribuir(player, 2, 0, cartas);
        distribuir(dealer, 2, 0, cartas);
    }
}