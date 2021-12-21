package year2021.day17;

import base.Base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Base {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        System.out.println("PART1:");
        main.part1();
        System.out.println("PART2:");
        main.part2();
    }

    @Override
    public void part1() throws IOException {
        List<String> hitPoints = new ArrayList<>();
        String input = "target area: x=25..67, y=-260..-200";
        int xStart = 7;
        int xEnd = 67; // 34 + 33 = 67 .. groter is er voorbij
        int yEnd = 2000;
        int yStart = -260;

//        String input = "target area: x=20..30, y=-10..-5";
//        int xStart = 6;
//        int xEnd = 30;
//        int yEnd = 100;
//        int yStart = -10;


        String range = input.substring(13);
        String rangeX = range.substring(2).split(",")[0].trim();
        String rangeY = range.substring(2).split(",")[1].substring(3).trim();

        int xMin = Integer.parseInt(rangeX.split("\\.\\.")[0]);
        int xMax = Integer.parseInt(rangeX.split("\\.\\.")[1]);
        int yMin = Integer.parseInt(rangeY.split("\\.\\.")[0]);
        int yMax = Integer.parseInt(rangeY.split("\\.\\.")[1]);

        int maxHeight = 0;
        // at least 6 at most 16 (16 + 15 > 30)
        for (int x = xStart; x <= xEnd; x++) {
            for (int y = yStart; y < yEnd; y++) {
                int xVel = x;
                int yVel = y;
                int currentX = 0;
                int currentY = 0;
                int maxSessionHeight = 0;
                boolean hitsTarget = false;

                while (currentY >= yMin && currentX <= xMax) {
                    currentX += xVel;
                    currentY += yVel;
                    xVel = xVel > 0 ? xVel - 1 : xVel < 0 ? xVel + 1 : xVel;
                    yVel -= 1;

                    maxSessionHeight = currentY > maxSessionHeight ? currentY : maxSessionHeight;
                    if (currentX >= xMin && currentX <= xMax && currentY >= yMin && currentY <= yMax) {
                        hitsTarget = true;
                    }
                }
                if (hitsTarget && !hitPoints.contains(x + "," + y)) {
                    hitPoints.add(x + "," + y);
                    System.out.println(x + "," + y);
                }
                if (hitsTarget && maxSessionHeight > maxHeight) {
                    maxHeight = maxSessionHeight;
                    //System.out.println("new height found: " + maxHeight + ", at: (" + x + "," + y + ")");
                }
            }
        }
        System.out.println(maxHeight);
        System.out.println(hitPoints.size());
    }

    @Override
    public void part2() throws IOException {

    }
}
