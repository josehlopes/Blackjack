import java.util.Scanner;

import _application.Blackjack;
import _application.Jogador;

/**
 *
 * @author José Henrique
 * @guidance Ícaro
 * 
 */
public class Jogo {

    private static float aposta; // Variável de instância para armazenar a aposta

    public static void main(String args[]) {
        // Variavéis principais do jogo
        Jogador player = new Jogador(0);
        Jogador dealer = new Jogador(50);
        Blackjack blackjack = new Blackjack();
        Scanner scanner = new Scanner(System.in);
        int rodada = 0;
        boolean jogoIniciado = true;

        // Menu inicial
        System.out.println("Bem vindo ao Blackjack!\n" +
                "para iniciar o jogo digite 1:\n" +
                "para sair digite 2:");
        int iniciar = scanner.nextInt();

        while (iniciar == 1) {

            if (jogoIniciado) {

                System.out.println("Quanto você deseja apostar: ");
                aposta = scanner.nextFloat();
                player.setSaldo(aposta);
                System.out.println("Sua aposta : " + player.getSaldo());
                System.out.println("Aposta inicial do Dealer: " + dealer.getSaldo());
                blackjack.iniciarJogo(player, dealer, 0, blackjack.getBaralho().getCartas());
                rodada++;
                System.out.println("Mão do jogador: " + player.getMaos());
                System.out.println("Mão do dealer: " + dealer.getMaos());
                System.out
                        .println("Quantidade de cartas restantes é: " + blackjack.getBaralho().getCartas().size());
                System.out.println("Rodada atual: " + rodada);

                boolean maoDoDealer = blackjack.verificarAs(dealer);

                if (maoDoDealer) {

                    dealer.setSaldo(aposta + dealer.getSaldo());
                    player.setSaldo(aposta - aposta);
                    System.out.println("O Dealer tem um Blackjack!, você perdeu!\n" + "Seu novo saldo é: "
                            + player.getSaldo());
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
                int indice;

                switch (acao) {

                    case 1: // Ação Hit
                        rodada++;
                        if (player.getMaos().size() > 1) {
                            System.out.println("Cartas atuais: " + player.getMaos());
                            System.out.println("Para qual mão você deseja comprar cartas:");
                            indice = scanner.nextInt();
                            System.out.println("Quantas cartas deseja comprar?");
                            int qntCartasCompradas = scanner.nextInt();
                            blackjack.Hit(player, qntCartasCompradas, indice, blackjack.getBaralho().getCartas());
                            for (int i = 0; i <= player.getMaos().size(); i++) {
                                System.out.println("Mão " + i + ": " + player.getMaos().get(i));
                                System.out.println(
                                        "\nSoma da mão " + i + " do jogador: " + blackjack.Contar_mao(player, i));
                            }

                            if (blackjack.verificarVitoria(player, indice)) {
                                player.setSaldo(aposta * 3);
                                iniciar = 2;
                            } else {
                                if (blackjack.Contar_mao(player, indice) > 21) {
                                    System.out.println("Você perdeu!");
                                    player.setSaldo(0);
                                    iniciar = 2;
                                }
                            }

                        } else {
                            indice = 0;
                            System.out.println("Cartas atuais: " + player.getMaos());
                            System.out.println("Quantas cartas deseja comprar?");
                            int qntCartasCompradas = scanner.nextInt();
                            blackjack.Hit(player, qntCartasCompradas, indice, blackjack.getBaralho().getCartas());
                            System.out.println("Mãos do jogador: " + player.getMaos());
                            System.out.println("Soma da mão do jogador: " + blackjack.Contar_mao(player, indice));

                            if (blackjack.verificarVitoria(player, indice)) {
                                player.setSaldo(aposta * 3);
                                iniciar = 2;
                            } else {
                                if (blackjack.Contar_mao(player, indice) > 21) {
                                    System.out.println("Você perdeu!");
                                    player.setSaldo(0);
                                    iniciar = 2;
                                }
                            }
                        }

                        // Encerra o jogo
                        break;

                    case 2: // Chamar Stand
                        rodada++;
                        if (player.getMaos().size() > 1) {
                            System.out.println("Você passou a rodada!");
                            System.out.println("Mãos do jogador: " + player.getMaos());
                            for (int i = 0; i <= player.getMaos().size(); i++) {
                                System.out.println(
                                        "Soma da mão" + i + " do jogador: " + blackjack.Contar_mao(player, i));
                            }
                        } else {
                            System.out.println("Você passou a rodada!");
                            System.out.println("Soma da mão do jogador: " + blackjack.Contar_mao(player, 0));
                        }
                        break;
                    case 3: // Chamar double
                        rodada++;
                        if (player.getMaos().size() > 1) {
                            player.setSaldo(player.getSaldo() * 2);
                            System.out.println("Cartas atuais: " + player.getMaos());
                            System.out.println("Qual mão você deseja dobrar");
                            indice = scanner.nextInt();
                            blackjack.Hit(player, 1, indice, blackjack.getBaralho().getCartas());
                            System.out.println("Você dobra a aposta e compra uma carta.");
                            System.out.println("Saldo atual:" + player.getSaldo());
                            System.out.println("Mão do jogador: " + player.getMaos());
                            System.out.println("Soma da mão do jogador: " + blackjack.Contar_mao(player, 0));

                            if (blackjack.verificarVitoria(player, indice)) {
                                player.setSaldo(aposta * 3);
                                iniciar = 2;
                            } else {
                                if (blackjack.Contar_mao(player, indice) > 21) {
                                    System.out.println("Você perdeu!");
                                    player.setSaldo(0);
                                    iniciar = 2;
                                }
                            }
                        } else {
                            player.setSaldo(player.getSaldo() * 2);
                            System.out.println("Cartas atuais: " + player.getMaos());
                            blackjack.Hit(player, 1, 0, blackjack.getBaralho().getCartas());
                            System.out.println("Você dobra a aposta e compra uma carta.");
                            System.out.println("Saldo atual:" + player.getSaldo());
                            System.out.println("Mão do jogador: " + player.getMaos());
                            System.out.println("Soma da mão do jogador: " + blackjack.Contar_mao(player, 0));

                            if (blackjack.verificarVitoria(player, 0)) {
                                player.setSaldo(aposta * 3);
                                iniciar = 2;
                            } else {
                                if (blackjack.Contar_mao(player, 0) > 21) {
                                    System.out.println("Você perdeu!");
                                    player.setSaldo(0);
                                    iniciar = 2;
                                }
                            }
                        }

                        break;

                    case 4: // Split
                        rodada++;
                        if (player.getMaos().size() > 1) {
                            System.out.println("Qual baralho deseja dividir:");
                            indice = scanner.nextInt();
                            blackjack.Split(player, indice);
                            for (int i = 0; i < player.getMaos().size(); i++) {
                                System.out.println("Mão " + (i + 1) + ": " + player.getMaos().get(i));
                                System.out.println(
                                        "\nSoma da mão " + (i + 1) + " do jogador: " + blackjack.Contar_mao(player, i));
                                if (blackjack.verificarVitoria(player, i)) {
                                    player.setSaldo(aposta * 6);
                                    iniciar = 2;
                                } else if (blackjack.Contar_mao(player, i) > 21) {
                                    System.out.println("Você perdeu!");
                                    player.setSaldo(0);
                                    iniciar = 2;
                                }
                            }
                        } else {
                            blackjack.Split(player, 0);
                            for (int i = 0; i < player.getMaos().size(); i++) {
                                System.out.println("\nMão " + (i + 1) + ": " + player.getMaos().get(i));
                                System.out.println(
                                        "\nSoma da mão " + (i + 1) + " do jogador: " + blackjack.Contar_mao(player, i));
                                if (blackjack.verificarVitoria(player, i)) {
                                    player.setSaldo(aposta * 3);
                                    iniciar = 2;
                                } else if (blackjack.Contar_mao(player, i) > 21) {
                                    System.out.println("Você perdeu!");
                                    player.setSaldo(0);
                                    iniciar = 2;
                                }
                            }
                        }
                        break;

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

        }

    }
}
