package _data;

public enum Numero {

    as(1), dois(2), tres(3), quatro(4), cinco(5), seis(6), sete(7), oito(8), nove(9), dez(10), valete(10), dama(10), rei(10);

    private int valor;

    Numero(int valor) {
        this.valor = valor;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
}
