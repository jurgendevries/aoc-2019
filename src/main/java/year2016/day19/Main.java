package year2016.day19;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {
    private static final int INPUT = 3005290;
    private static List<Elf> elfs;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
//        main.prepare();
//        main.part1();
        main.prepare();
        main.part2();
    }

    private void prepare() {
        elfs = new ArrayList<>();

        for (int i = 0; i < INPUT; i++) {
            elfs.add(new Elf(i + 1));
        }
    }

    @Override
    public void part1() throws IOException {
        while (elfs.size() > 1) {
            Elf elf = elfs.get(0);
            Elf nextElf = elfs.get(1);

            elf.setNumberOfPresents(elf.getNumberOfPresents() + nextElf.getNumberOfPresents());
            nextElf.setNumberOfPresents(0);
            elfs.remove(nextElf);

            elfs.add(elfs.remove(0));
        }

        System.out.println("" + elfs.get(0).getId());
    }

    @Override
    public void part2() throws IOException {
        while (elfs.size() > 1) {
            Elf elf = elfs.get(0);
            Elf elfAcross = elfs.get(elfs.size() / 2);

            elf.setNumberOfPresents(elf.getNumberOfPresents() + elfAcross.getNumberOfPresents());
            elfAcross.setNumberOfPresents(0);
            elfs.remove(elfAcross);

            elfs.add(elfs.remove(0));
        }

        System.out.println("" + elfs.get(0).getId());
    }

    public class Elf {
        private int id;
        private int numberOfPresents;

        public Elf(int id) {
            this.id = id;
            this.numberOfPresents = 1;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumberOfPresents() {
            return numberOfPresents;
        }

        public void setNumberOfPresents(int numberOfPresents) {
            this.numberOfPresents = numberOfPresents;
        }
    }
}
