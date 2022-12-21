package helpers.search;

public class BinarySearchHelper {
    private long low;
    private long high;

    public BinarySearchHelper(long low, long high) {
        this.low = low;
        this.high = high;
    }

    /**
     * Calculate next attempt for binary search
     *
     * See 2022 Day21 for example usage
     *
     * @param attempt
     * @param x
     * @param y
     * @return
     */
    public long getNextBinaryResult(long attempt, long x, long y) {
        // stop searching
        if (x == y) {
            return attempt;
        }
        if (x > y) {
            low = attempt;
        } else {
            high = attempt;
        }
        return (high + low) / 2;
    }
}
