package year2020.day13;

import base.Base;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Base {
    private static final String INPUT = "2020/day13-input.txt";

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
        //main.part1();
        System.out.println("PART2:");
        main.part2();
    }
    @Override
    public void part1() throws IOException {
        int closestBus = 0;
        int closestDiff = Integer.MAX_VALUE;
        int target = Integer.parseInt(instructions.get(0));
        String[] busses = instructions.get(1).split(",");
        for (int i = 0; i < busses.length; i++) {
            try {
                int bus = Integer.parseInt(busses[i]);
                int divided = (int) Math.ceil((double) target / (double) bus);

                int arriveTime = bus * divided;
                closestDiff = arriveTime - target < closestDiff ? arriveTime - target : closestDiff;
                closestBus = arriveTime - target <= closestDiff ? bus : closestBus;
            } catch (NumberFormatException e) {
                System.out.println("not a numbber so no action");
            }
        }

        int outcome = closestBus * closestDiff;
        System.out.println(outcome);
    }

    @Override
    public void part2() throws IOException {
        String[] busses = instructions.get(1).split(",");

        ArrayList<Integer> ns = new ArrayList<Integer>(); // n[i] == busID
        ArrayList<Integer> bs = new ArrayList<Integer>(); // b[i] == (-1*i)%busID + busID
        ArrayList<Long> Ns = new ArrayList<Long>(); // N[i] == N/n[i] given N == prod of all n[i]
        ArrayList<Integer> xs = new ArrayList<Integer>(); // xs == inverse of N[i]... found manually by getInverse()
        // function

        long N = 1; // N == product of all ns
        for (int i = 0; i < busses.length; i++) {
            if (!busses[i].equals("x")) {
                ns.add(Integer.parseInt(busses[i]));
                bs.add((-1 * i) % Integer.parseInt(busses[i]) + Integer.parseInt(busses[i]));
                N *= Integer.parseInt(busses[i]);
            }
        }

        // populate Ns[] and xs[]
        for (int i = 0; i < ns.size(); i++) {
            Ns.add(N / ns.get(i));
            xs.add(getInverse(Ns.get(i), ns.get(i)));
        }

        // time to get an answer
        long sum = 0;
        for (int i = 0; i < ns.size(); i++) {
            sum += bs.get(i) * Ns.get(i) * xs.get(i);
        }
        sum %= N;
        System.out.println(sum);
    }

    private static int getInverse(long Ni, int ni) {
        // Ni * xi == 1 (mod ni)
        // incrememnt xi until solution is found
        int xi = 1;
        while ((Ni * xi) % ni != 1) {
            xi++;
        }
        return xi;
    }
/*
// 17,x,x,x,x,x,x,41,x,x,x,37,x,x,x,x,x,367,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,613,x,x,x,x,x,x,x,x,x,x,x,x,13
        // 7,13,x,x,59,x,31,19
        String[] busses = instructions.get(1).split(",");
        BigInteger count = new BigInteger("100047835965687");
        //BigInteger count = new BigInteger("0");
        boolean found = false;

        while(!found) {
            for (int i = 0; i < busses.length; i++) {
                if ("x".equals(busses[i])) {
                    continue;
                }

                BigInteger busNum = new BigInteger(busses[i]);
                BigInteger idx = BigInteger.valueOf(i);

                if ((idx == BigInteger.ZERO && count.mod(busNum) != idx) || (idx != BigInteger.ZERO && !busNum.subtract(count.mod(busNum)).equals(idx))) {
                    count = count.add(BigInteger.valueOf(17));
                    break;
                }

                if (i == busses.length - 1) {
                    // if you reach this, you found the solution
                    found = true;
                }
            }


        }

        System.out.println(count);
*/
/*
        String[] busses = instructions.get(1).split(",");
        BigInteger count = new BigInteger("0");
        boolean found = false;
        BigInteger step = BigInteger.valueOf(102);

        int size = 4;

        while(!found) {
            for (int i = 0; i < size; i++) {
                if ("x".equals(busses[i])) {
                    continue;
                }

                BigInteger busNum = new BigInteger(busses[i]);
                BigInteger idx = BigInteger.valueOf(i);

                if (
                        (idx == BigInteger.ZERO && count.mod(busNum) != idx) ||
                        (idx != BigInteger.ZERO && !busNum.subtract(count.mod(busNum)).equals(idx)) ||
                        (count.compareTo(busNum) == -1)

                ) {
                    count = count.add(step);
                    break;
                }



                if (i == size - 1) {
                    // if you reach this, you found the solution
                    found = true;
                }
            }
        }
*/
/*

        String[] busses = instructions.get(1).split(",");
        List<Integer> busNums = new ArrayList<>();
        List<Integer> intervals = new ArrayList<>();

        for (int i = 0; i < busses.length; i ++) {
            if ("x".equals(busses[i])) {
                continue;
            }

            busNums.add(Integer.parseInt(busses[i]));
            intervals.add(i);
        }

        Integer[] bus = new Integer[busNums.size()];
        bus = busNums.toArray(bus);

        Integer[] ints = new Integer[intervals.size()];
        ints = intervals.toArray(ints);

        int result = computeMinX(ints, bus);

        System.out.println(result);*/




    public static int computeMinX(Integer[] rem, Integer[] num){
        //STEP 1
        int product = 1;
        for (int i=0; i<num.length; i++ ) {
            product *= num[i];
        }

        print("Product of all numbers is: "+product);

        int partialProduct[] = new int[num.length];
        int inverse[] = new int[num.length];
        int sum = 0;

        for (int i=0; i<num.length; i++) {
            partialProduct[i] = product/num[i];//floor division
            inverse[i] = computeInverse(partialProduct[i],num[i]);
            sum += partialProduct[i] * inverse[i] * rem[i];
        }

        print("Partial product array is: "+ Arrays.toString(partialProduct)+"\n");
        print("Multiplicative inverse modulo of partial product: "+Arrays.toString(inverse)+"\n");
        print("Sum = "+sum+"\n");

        return sum % product;

    }

    public static int computeInverse(int a, int b)
    {
        int m = b, t, q;
        int x = 0, y = 1;

        if (b == 1)
            return 0;

        // Apply extended Euclid Algorithm
        while (a > 1)
        {
            // q is quotient
            q = a / b;
            t = b;

            // m is remainder now, process
            // same as euclid's algo
            b = a % b;a = t;

            t = x;

            x = y - q * x;

            y = t;
        }

        // Make x1 positive
        if (y < 0)
            y += m;

        return y;
    }

    public static void print(String arg){
        System.out.println(arg);
    }
}
