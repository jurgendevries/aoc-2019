package base.utils;

import java.util.ArrayList;
import java.util.List;

public class IntCodeComputer2020 {
    private long cursor;
    private long accumulator;
    private List<Long> visitedCursorPositions = new ArrayList<>();
    private boolean loopDetected = false;

    public IntCodeComputer2020() {
    }

    public IntCodeComputer2020(long cursor, long accumulator) {
        this.cursor = cursor;
        this.accumulator = accumulator;
    }

    public long getCursor() {
        return cursor;
    }

    public void setCursor(long cursor) {
        if (visitedCursorPositions.contains(cursor)) {
            loopDetected = true;
        } else {
            visitedCursorPositions.add(cursor);
            this.cursor = cursor;
        }
    }

    public long getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(long accumulator) {
        this.accumulator = accumulator;
    }

    public long process(List<String> instructions) {
        while (!loopDetected && this.cursor < instructions.size()) {
            String instruction = instructions.get(Math.toIntExact(cursor));

            // split instruction
            String action = instruction.split(" ")[0];
            String value = instruction.split(" ")[1];

            if (value.startsWith("+")) {
                processCommand(action, Long.valueOf(value.substring(1)));
            } else {
                processCommand(action, -(Long.valueOf(value.substring(1))));
            }
        }

        return this.accumulator;
    }

    private void processCommand(String action, long value) {
        switch (action) {
            case "acc":
                accCommand(value);
                break;
            case "jmp":
                jmpCommand(value);
                break;
            case "nop":
                nopCommand(value);
                break;
            default:
                throw new IllegalArgumentException("Operation not implemented!");
        }
    }

    private void accCommand(long value) {
        setCursor(getCursor() + 1);
        setAccumulator(getAccumulator() + value);

    }

    private void nopCommand(long value) {
        setCursor(getCursor() + 1);
    }

    private void jmpCommand(long value) {
        setCursor(getCursor() + value);
    }
}
