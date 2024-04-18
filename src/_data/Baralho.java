package _data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {

    private List<Carta> cartas;

    public Baralho() {

        cartas = new ArrayList<>();
        Naipe[] naipes = Naipe.values();
        Numero[] numeros = Numero.values();
        for (int i = 0; i < Naipe.values().length; i++) {
            for (int j = 0; j < Numero.values().length; j++) {
                Carta c = new Carta(naipes[i], numeros[j]);
                cartas.add(c);
            }
        }
    }

    public void embaralhar() {
        Collections.shuffle(cartas);
    }

    public void removerCarta(int posicao) {
        cartas.remove(posicao);
    }



    public static void main(String[] args) {
        new Baralho();
    }

    public List<Carta> getCartas() {
        return cartas;
    }

}
