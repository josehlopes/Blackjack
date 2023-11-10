import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author José Henrique
 * @guidance Ícaro
 * 
 */

public class Baralho {

    private List<Carta> cartas;

    public Baralho() { // Criando a classe baralho

        cartas = new ArrayList<>(); // Criando uma lista de cartas chamada "cartas"
        Naipe[] naipes = Naipe.values(); // Cria uma instãncia de naipes que acessa os valores "ouro, paus, espadas,etc"
        Numero[] numeros = Numero.values(); // Mesma lógica dos naipes
        for (int i = 0; i < Naipe.values().length; i++) { // Percorre os 4 naipes
            for (int j = 0; j < Numero.values().length; j++) { // Percorre os 13 números, Totalizando 52 Cartas
                Carta c = new Carta(naipes[i], numeros[j]); // Durante a primeira iteração se mantém no primeiro valor
                                                            // dos Naipes(Copas), vai para o segundo for e faz 13(Copas)
                                                            // respectivos a cada número das cartas (as, dois, tres,
                                                            // quatro, cinco, seis, sete, oito, nove, dez, valete, dama,
                                                            // rei);
                cartas.add(c); // Adiciona a carta que está na iteração atual I/J na lista "cartas"
            }
        }

        System.out.println(cartas.size());
    }

    public void embaralhar() {
        Collections.shuffle(cartas); // Função para embaralhar as cartas
    }

    public void removerCarta(int posicao) { // Coleta a posição e remove a carta
        cartas.remove(posicao);
    }

    public void distribuir(Jogador j, int qtd) { // Manda a carta para mão do jogador e remove do baralho
        for (int i = 0; i < qtd; i++) {
            int carta = new Random().nextInt(cartas.size());
            Carta c = cartas.remove(carta);
            j.getMao().add(c);
        }
    }

    public static void main(String[] args) {
        new Baralho();
    }

    public List<Carta> getCartas() {
        return cartas;
    }

}
