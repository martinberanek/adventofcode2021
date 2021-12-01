package cz.martinberanek.adventofcode2021;

import java.util.List;

public class Day1 extends Day<List<Long>> {

    public static void main(String[] args) {
        new Day1().run(Util.load("/Day1.txt", Long::parseLong));
    }

    @Override
    protected long part1(List<Long> input) {
        return countIncreasing(input, 1);
    }

    @Override
    protected long part2(List<Long> input) {
        return countIncreasing(input, 3);
    }

    private long countIncreasing(List<Long> input, int window) {
        long result = 0L;
        for (int i = window; i < input.size(); i++) {
            long a = input.subList(i - window, i).stream().reduce(0L, Long::sum);
            long b = input.subList(i - window + 1, i + 1).stream().reduce(0L, Long::sum);
            result += a < b ? 1 : 0;
        }
        return result;
    }
}
