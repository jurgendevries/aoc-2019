package year2017.day3;

import base.Base;

import java.io.IOException;

public class Main extends Base {


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        System.out.println("PART1:");
        main.part1();
//        System.out.println("PART2:");
//        main.part2();
    }

    @Override
    public void part1() throws IOException {
        // elke spiraal is 2 breder/hoger dan de voorgaande
        // de spiraal eindigt rechts onder met de waarde van het kwadraat van de breedte: 1, 9, 25, 49, 81, 121, 144 enz
        // loop +2 in het kwadraat is kleiner (of gelijk) dan de gevraagde waarde
        int value = 1089;
        int i = 1;
        while (i * i < value) {
            i += 2;
        }

        int numberOfTiles = i * i;
        int center = (int) Math.floor((double) i / 2);
        int diff = numberOfTiles - value;
        System.out.println(diff);
        while (diff > i) {
            diff -= i;
        }

        int result;
        if (diff == 0) {
            result = center + center;
        } else if (diff < center) {
            result = center - diff + center;
        } else if (diff > center) {
            result = diff - center + center;
        } else {
            result = center;
        }

        System.out.println("i: " + i +
                ", NOT: " + numberOfTiles +
                ", center " + center +
                ", diff " + diff +
                ", result " + result);
    }

    @Override
    public void part2() throws IOException {

    }
}
