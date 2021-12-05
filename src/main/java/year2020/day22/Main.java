package year2020.day22;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main extends Base {
    private static final String INPUT = "2020/day22-input.txt";
    private static List<String> instructions;


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
        List<Integer> cards = new ArrayList<>();
        Integer id = null;
        // create players
        for (String instruction : instructions) {
            if ("".equals(instruction)) {
                continue;
            }
            if (instruction.contains("Player")) {
                if (cards.size() > 0) {
                    players.add(new Player(id, cards));
                }
                id = Integer.parseInt(instruction.split(" ")[1].replace(":", ""));
                cards = new ArrayList<>();
            } else {
                cards.add(Integer.valueOf(instruction));
            }
        }
        players.add(new Player(id, cards));


        Player winner = play(players);

        long score = calculateScore(winner);

        System.out.println(score);
    }

    private long calculateScore(Player winner) {
        long score = 0;
        int multiplier = 1;
        for (int i = winner.getDeck().size() - 1; i >= 0; i--) {
            score += winner.getDeck().get(i) * multiplier++;
        }

        return score;
    }

    private Player play(List<Player> players) {
        while(!players.get(0).getDeck().isEmpty() && !players.get(1).getDeck().isEmpty()) {
            int cardPlayer1 = players.get(0).getDeck().get(0);
            int cardPlayer2 = players.get(1).getDeck().get(0);

            if (cardPlayer1 > cardPlayer2) {
                players.get(0).getDeck().add(players.get(0).getDeck().remove(0));
                players.get(0).getDeck().add(players.get(1).getDeck().remove(0));
            } else {
                players.get(1).getDeck().add(players.get(1).getDeck().remove(0));
                players.get(1).getDeck().add(players.get(0).getDeck().remove(0));
            }
        }

        return players.get(0).getDeck().isEmpty() ? players.get(1) : players.get(0);
    }

    @Override
    public void part2() throws IOException {

    }

    class Player {
        private int id;
        private List<Integer> deck;

        public Player(int id, List<Integer> deck) {
            this.id = id;
            this.deck = deck;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Integer> getDeck() {
            return deck;
        }

        public void setDeck(List<Integer> deck) {
            this.deck = deck;
        }
    }
}
