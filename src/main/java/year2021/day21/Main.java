package year2021.day21;

import base.Base;
import java.io.IOException;
import java.util.*;

public class Main extends Base {
    private static final String INPUT = "2021/day21-input.txt";
    private static List<String> instructions;
    private static Map<Integer, Integer> quantumScores = new HashMap<>();
    private static long player1Wins = 0;
    private static long player2Wins = 0;

    private void prepare() throws IOException {
        String line;
        instructions = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            instructions.add(line);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.prepare();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        List<Player> players = new ArrayList<>();
        Player player1 = new Player(1, Integer.parseInt(instructions.get(0).split(" ")[4]));
        Player player2 = new Player(2, Integer.parseInt(instructions.get(1).split(" ")[4]));
        players.add(player1);
        players.add(player2);

        Dice dice = new Dice(0, 100);
        long turns = 0;

        while (player1.score < 1000 && player2.score < 1000) {
            // start turn
            turns++;
            // rol dice
            int score = dice.roll();

            // determines whose score gets updatet
            if (turns % 2 == 0) {
                player2.position = (player2.position + score) % 10 == 0 ? 10 : (player2.position + score) % 10;
                player2.score += player2.position;
            } else {
                player1.position = (player1.position + score) % 10 == 0 ? 10 : (player1.position + score) % 10;
                player1.score += player1.position;
            }
        }

        System.out.println("Score Player1: " + player1.score);
        System.out.println("Score Player2: " + player2.score);
        System.out.println("Total dice throws: " + dice.totalThrows);
        Player loser = players.stream().min(Comparator.comparing(Player::getScore)).get();
        System.out.println("Loser: Player" + loser.id);
        long loserScore = players.stream().min(Comparator.comparing(Player::getScore)).get().getScore();
        System.out.println("Part1 answer: " + loserScore * dice.totalThrows);
    }

    @Override
    public void part2() throws IOException {
        quantumScores.put(3,1);
        quantumScores.put(4,3);
        quantumScores.put(5,6);
        quantumScores.put(6,7);
        quantumScores.put(7,6);
        quantumScores.put(8,3);
        quantumScores.put(9,1);


        List<Player> players = new ArrayList<>();
        Player player1 = new Player(1, Integer.parseInt(instructions.get(0).split(" ")[4]));
        Player player2 = new Player(2, Integer.parseInt(instructions.get(1).split(" ")[4]));
        players.add(player1);
        players.add(player2);
        playGame(player1, player2, 1);
        System.out.println("Player1 wins: " + player1Wins + ", Player2 wins: " + player2Wins);
    }

    private void playGame(Player player1, Player player2, long paths) {
        if (player1.score >= 21) {
            player1Wins += paths;
        } else if (player2.score >= 21){
            player2Wins += paths;
        } else {
            Player turnPlayer = player1.turns > player2.turns ? player2 : player1;

            for (int score : quantumScores.keySet()) {
                Player quantumPlayer1 = new Player(player1.id, player1.position, player1.score);
                Player quantumPlayer2 = new Player(player2.id, player2.position, player2.score);

                if (turnPlayer.id == 1) {
                    quantumPlayer1.position = (quantumPlayer1.position + score) % 10 == 0 ? 10 : (quantumPlayer1.position + score) % 10;
                    quantumPlayer1.score += quantumPlayer1.position;
                    quantumPlayer1.turns++;
                } else {
                    quantumPlayer2.position = (quantumPlayer2.position + score) % 10 == 0 ? 10 : (quantumPlayer2.position + score) % 10;
                    quantumPlayer2.score += quantumPlayer2.position;
                    quantumPlayer2.turns++;
                }

                playGame(quantumPlayer1, quantumPlayer2, paths * quantumScores.get(score));
            }
        }
    }

    class Player {
        private int id;
        private long score;
        private int position;
        private long wins;
        private int turns;
        private int numberOfThrows;

        public Player(int id, int position) {
            this.id = id;
            this.position = position;
        }

        public Player(int id, int position, long score) {
            this.id = id;
            this.position = position;
            this.score = score;
        }

        public long getScore() {
            return score;
        }
    }

    class Dice {
        private int id;
        private int throwScore;
        private int totalThrows;
        private int sides;

        public Dice(int id, int sides) {
            this.id = id;
            this.sides = sides;
        }

        public int roll() {
            int score = 0;
            for (int i = 0; i < 3; i++) {
                totalThrows++;
                throwScore = ++throwScore <= sides ? throwScore : 1;
                score += this.throwScore;
            }
            return score;
        }
    }
}
