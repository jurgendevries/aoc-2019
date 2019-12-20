package year2016.day10;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Main extends Base {
    private static final String INPUT = "day10-input.txt";

    private static final String STORAGE_TYPE_BOT = "BOT";
    private static final String STORAGE_TYPE_OUTPUT = "OUTPUT";
    private static List<Storage> bots = new ArrayList<>();
    private static List<Storage> outputs = new ArrayList<>();
    private static int responsibleBot;
    private static int compareLow = 17;
    private static int compareHigh = 61;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.mainMethod(INPUT);
        main.part1();
        main.part2();
    }


    @Override
    public void part1() throws IOException {
        String line;
        List<String> instructions = new ArrayList<>();


        while ((line = input.readLine()) != null) {
            if (line.startsWith("value")) {
                addValueToBot(line);
            } else {
                instructions.add(line);
            }
        }

        while (!instructions.isEmpty()) {
            instructions.removeIf(this::executeInstruction);
        }

        System.out.println("responsibleBot = [" + responsibleBot + "]");
    }

    @Override
    public void part2() throws IOException {
        int output0 = outputs.stream().filter(b -> b.getId() == 0).findFirst().get().getValues().get(0);
        int output1 = outputs.stream().filter(b -> b.getId() == 1).findFirst().get().getValues().get(0);
        int output2 = outputs.stream().filter(b -> b.getId() == 2).findFirst().get().getValues().get(0);

        System.out.println("output[0] = " + output0 +
                ", output[1] = " + output1 +
                ", output[2] = " + output2 +
                ", multiplied = " + (output0 * output1 * output2));
    }

    private void addValueToBot(String line) {
        String[] splitLine = line.split(" ");
        int value = Integer.parseInt(splitLine[1]);
        int botNr = Integer.parseInt(splitLine[5]);

        addValueToStorage(botNr, value, bots, STORAGE_TYPE_BOT);
    }

    private void addValueToStorage(int id, int value, List<Storage> storage, String storageType) {
        if (!storage.stream().filter(b -> b.getId() == id).findFirst().isPresent()) {
            Storage bot = new Storage(id, storageType.toUpperCase());
            bot.getValues().add(value);
            storage.add(bot);
        } else {
            storage.stream().filter(b -> b.getId() == id).findFirst().get().getValues().add(value);
        }
    }

    private boolean executeInstruction(String instruction) {
        boolean succeeded = false;
        String[] splitInstruction = instruction.split(" ");

        Optional<Storage> givingBot = bots.stream().filter(b -> b.getId() == Integer.parseInt(splitInstruction[1])).findFirst();
        if (givingBot.isPresent()) {
            Storage bot = givingBot.get();

            if (bot.getValues().size() == 2) {
                int low = bot.getLowValue();
                int high = bot.getHighValue();

                if (low == compareLow && high == compareHigh) {
                    responsibleBot = bot.getId();
                }

                String receivingStorageType1 = splitInstruction[5];
                int receivingStorageId1 = Integer.parseInt(splitInstruction[6]);

                addValueToStorage(receivingStorageType1, receivingStorageId1, low);

                String receivingStorageType2 = splitInstruction[10];
                int receivingStorageId2 = Integer.parseInt(splitInstruction[11]);

                addValueToStorage(receivingStorageType2, receivingStorageId2, high);
                succeeded = true;
            }
        }
        return succeeded;
    }

    private void addValueToStorage(String storageType, int storageId, int value) {
        if (storageType.equals(STORAGE_TYPE_BOT.toLowerCase())) {
            addValueToStorage(storageId, value, bots, storageType);
        } else {
            addValueToStorage(storageId, value, outputs, storageType);
        }
    }

    public class Storage {
        private int id;
        private String type;
        private List<Integer> values;

        public Storage(int id, String type) {
            this.id = id;
            this.type = type;
            this.values = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Integer> getValues() {
            return values;
        }

        public void setValues(List<Integer> values) {
            this.values = values;
        }

        public int getLowValue() {
            int minValue = Collections.min(this.values);
            this.values.remove((Integer) minValue);
            return minValue;

        }

        public int getHighValue() {
            int maxValue = Collections.max(this.values);
            this.values.remove((Integer) maxValue);
            return maxValue;
        }
    }

}
