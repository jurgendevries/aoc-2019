package helpers.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImageBuilder {
    private static final int MARGIN = 10;

    private static List<Color[][]> colorGridList = new ArrayList<>();
    private static Map<String, Color> colorMap;
    private static String filename;
    private static Graphics2D graphics;
    private static String frameCount;
    private static int imageHeight;
    private static int imageWidth;
    private static BufferedImage img;
    private static int xFrame;
    private static int yFrame;
    private static int scale;

    private ImageBuilder() {

    }

    public static void addGrid(String[][] grid) {
        Color[][] coloredGrid = new Color[grid.length][grid[0].length];
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                coloredGrid[y][x] = colorMap.get(grid[y][x]);
            }
        }
        colorGridList.add(coloredGrid);
    }

    public static void clear() {
        colorGridList.clear();
    }

    public static void makeGif() {
        if (colorGridList.isEmpty()) {
            return;
        }
        xFrame = colorGridList.get(0)[0].length;
        yFrame = colorGridList.get(0).length;
        scale = 10;

        imageHeight = yFrame * scale + 2 * MARGIN;
        imageWidth = xFrame * scale + 2 * MARGIN;
        frameCount = String.valueOf(colorGridList.size());
        img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        graphics = img.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(MARGIN, MARGIN, xFrame * scale + MARGIN, yFrame * scale + MARGIN);

        int count = 0;
        int frameRate = colorGridList.size() / 24;
        int msPerFrame = 1000 * 24 / colorGridList.size();
        System.out.println(String.format("ms per frame %d", msPerFrame));

        try {
            GifSequenceWriter.startGif(filename, msPerFrame);
            for (int frameNumber = 0; frameNumber < colorGridList.size(); frameNumber++) {
                addFrame(colorGridList.get(frameNumber), count++);
            }
            for (int i = 0; i < frameRate * 3; i++) {
                addFrame(colorGridList.get(colorGridList.size() - 1), count);
            }
            GifSequenceWriter.closeGif();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(String.format("Written gif to %s", filename));
    }

    private static void addFrame(Color[][] frame, int number) throws IOException {
        String count = Integer.toString(number);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, xFrame * scale, MARGIN - 1);
        graphics.setColor(Color.BLACK);
        graphics.drawString(count + '/' + frameCount, MARGIN, 22);

        for (int x = 0; x < xFrame; x++) {
            for (int y = 0; y < yFrame; y++) {
                graphics.setColor(frame[y][x]);
                graphics.fillRect(MARGIN + x * scale, MARGIN + y * scale, scale, scale);
            }
        }
        GifSequenceWriter.addImage(img);
    }

    public static void setFilename(int year, int day) {
        filename = String.format("%dday%02d.gif", year, day);
    }

    public static void setColorMap(Map<String, Color> colorMap) {
        ImageBuilder.colorMap = colorMap;
    }

}
