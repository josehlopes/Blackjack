import java.util.Scanner;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author José Henrique
 * @guidance Ícaro
 * 
 */
public class Jogo {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Variavéis principais do jogo
        Jogador player = new Jogador(0);
        Jogador dealer = new Jogador(50);
        Blackjack blackjack = new Blackjack();
        Scanner scanner = new Scanner(System.in); // Scanner para Inteiros
        Scanner scanner2 = new Scanner(System.in); // Scanner para float
        Scanner scanner3 = new Scanner(System.in); // Scanner para char
        int rodada = 0;
        boolean jogoIniciado = true; // boolean para jogoIniciadoar ou fechar o jogo

        // Menu inicial
        System.out.println("Bem vindo ao Blackjack!\n" +
                "para iniciar o jogo digite 1:\n" +
                "para sair digite 2:");
        int iniciar = scanner.nextInt();

        while (true) {

            if (iniciar == 1) {

                if (jogoIniciado == true) { // Comaçando o jogo com a variavel booleana true

                    blackjack.iniciarJogo(player, dealer);
                    rodada++; // Rodada 1 após inicio do jogo
                    System.out.println("Mão do jogador: " + player.getMao());
                    System.out.println("Mão do dealer: " + dealer.getMao());
                    System.out.println("Quantidade de cartas restantes é: " + blackjack.getBaralho().getCartas().size());
                    System.out.println("Rodada atual: " + rodada);
                    System.out.println("Quanto você deseja apostar: ");
                    float aposta = scanner2.nextFloat();
                    player.setSaldo(aposta);
                    System.out.println("Sua aposta : " + player.getSaldo());
                    System.out.println("Aposta inicial do Dealer: " + dealer.getSaldo());
                    boolean maoDoDealer = blackjack.Verificar_As(dealer.getMao());

                    if (maoDoDealer) {

                        dealer.setSaldo(aposta + dealer.getSaldo());
                        player.setSaldo(aposta - aposta);
                        System.out.println(
                                "O Dealer tem um Blackjack!, você perdeu!\n" + "Seu novo saldo é: "
                                        + player.getSaldo());
                        // Após o jogo iniciado faz o boolean ser false, para que o método de
                        // iniciar
                        // o jogo não seja chamado novamente
                    }

                    jogoIniciado = false;

                } else {
                    if (rodada == 1) {
                        System.out.println("O Dealer não tem um Blackjack, sua vez de jogar!");
                    }

                    System.out.println("Escolha sua ação: \n"
                            + "1 - Hit\n"
                            + "2 - Stand\n"
                            + "3 - Double\n"
                            + "4 - Split\n"
                            + "5 - Surrender");

                    int acao = scanner.nextInt();

                    switch (acao) {

                        case 1: // Ação Hit
                            rodada++;
                            System.out.println("Cartas atuais: " + player.getMao());
                            System.out.println("Quantas cartas deseja comprar?");
                            int qntCartasCompradas = scanner.nextInt();
                            blackjack.Hit(player, qntCartasCompradas);
                            System.out.println("Mão do jogador: " + player.getMao());
                            System.out.println("Soma da mão do jogador: " + blackjack.Contar_mao(player.getMao()));
                            if (blackjack.Contar_mao(player.getMao()) > 21) {
                                System.out.println("Você perdeu!");
                                iniciar = 2;

                            }

                            break;

                        case 2:
                            rodada++;
                            // Chamar Stand
                        case 3:
                            rodada++;
                            // Chamar double
                        case 4:
                            rodada++;
                            // Chamar split
                        case 5: // Chamar desistência
                            rodada++;
                            blackjack.Surrender(player);
                            System.out.println("Desistência\n"
                                    + "Saldo recuperado: " + player.getSaldo());
                            iniciar = 2;
                            break;

                        default:
                            System.out.println("Não foi possível realizar a ação");
                            iniciar = 2; // Encerra o jogo
                            break;
                    }

                }

            } else if (iniciar == 2) {
                System.out.println("Encerrando o jogo...");
                break;
            }
        }

    }
}
