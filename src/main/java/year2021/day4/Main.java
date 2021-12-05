package year2021.day4;

import base.Base;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2021/day4-input.txt";

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
        List<String> numbers = getNumbers();
        List<BingoCard> cards = createBingoCards();

        for (String number : numbers) {
            if (checkCards(cards, number, true)) {
                break;
            }
        }
    }

    private boolean checkCards(List<BingoCard> cards, String number, boolean stopOnBingo) {
        Integer lastId = null;
        if (!stopOnBingo) {
            if (cards.stream().filter(x -> !x.isHasBingo()).count() == 1) {
                lastId = cards.stream().filter(x -> !x.isHasBingo()).findFirst().get().getId();
            }
        }
        for (BingoCard bc : cards) {
            if (checkCard(bc, number, stopOnBingo, lastId)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCard(BingoCard bc, String number, boolean stopOnBingo, Integer lastId) {
        boolean bingo = false;
        int[] colCounters = {0,0,0,0,0};
        for (int i = 0; i < bc.getCardNumbers().length; i++) {
            if (!bc.isHasBingo()) {
                String[] cardRow = bc.getCardNumbers()[i];
                int rowCount = 0;
                for (int j = 0; j < cardRow.length; j++) {
                    if (cardRow[j].equals("X")) {
                        rowCount++;
                        colCounters[j] += 1;
                    }
                    if (cardRow[j].equals(number)) {
                        cardRow[j] = "X";
                        rowCount++;
                        colCounters[j] += 1;
                    }
                    if (colCounters[j] >= 5) {
                        bingo = stopOnBingo;
                        bc.setHasBingo(true);
                        System.out.println("Full column: " + j + " on card: " + bc.getId());
                        break;
                    }
                }
                if (rowCount >= 5 || bingo) {
                    System.out.println("Full column see above or full row: " + i + " on card: " + bc.getId());
                    bingo = stopOnBingo;
                    bc.setHasBingo(true);
                    break;
                }
            }
        }

        if (bingo || lastId != null) {
            int total = 0;

            for (int x = 0; x < bc.getCardNumbers().length; x++) {
                String[] cardRow = bc.getCardNumbers()[x];
                for (int y = 0; y < cardRow.length; y++) {
                    try {
                        total += Integer.parseInt(cardRow[y]);
                    } catch (Exception e) {
                        //System.out.println("Do not count 'X'");
                    }
                }
            }

            if (bingo || (lastId != null && lastId.intValue() == bc.getId())) {
                System.out.println("Score: " + total * Integer.parseInt(number));
            }
        }
        return bingo;
    }

    private List<String> getNumbers() {
        return Arrays.asList(instructions.get(0).split(","));
    }

    private List<BingoCard> createBingoCards() {
        List<BingoCard> cards = new ArrayList<>();
        int cardId = 0;
        int rowCount = 0;
        BingoCard bingoCard = new BingoCard(cardId++);
        String[][] cardNumbers = new String[5][5];

        for (int i = 2; i < instructions.size(); i++) {
            String instruction = instructions.get(i);

            if (StringUtils.isEmpty(instruction)) {
                bingoCard.setCardNumbers(cardNumbers);
                cards.add(bingoCard);
                bingoCard = new BingoCard(cardId++);
                cardNumbers = new String[5][5];
                rowCount = 0;
            } else {
                cardNumbers[rowCount++] = Arrays.stream(instruction.trim().split(" ")).filter(x -> !StringUtils.isEmpty(x)).toArray(String[]::new);
            }
        }
        bingoCard.setCardNumbers(cardNumbers);
        cards.add(bingoCard);
        return cards;
    }

    @Override
    public void part2() throws IOException {
        List<String> numbers = getNumbers();
        List<BingoCard> cards = createBingoCards();

        for (String number : numbers) {
            if (checkCards(cards, number, false)) {
                break;
            }
        }
    }

    class BingoCard {
        int id;
        String[][] cardNumbers;
        boolean hasBingo;

        public BingoCard() {
        }

        public BingoCard(int id) {
            this.id = id;
            this.hasBingo = false;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String[][] getCardNumbers() {
            return cardNumbers;
        }

        public void setCardNumbers(String[][] cardNumbers) {
            this.cardNumbers = cardNumbers;
        }

        public boolean isHasBingo() {
            return hasBingo;
        }

        public void setHasBingo(boolean hasBingo) {
            this.hasBingo = hasBingo;
        }
    }
}
