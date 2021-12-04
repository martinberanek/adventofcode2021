package cz.martinberanek.adventofcode2021;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day3 extends Day<List<Integer>> {

    public static void main(String[] args) {
        new Day3(12,0B111111111111).run(Util.load("/Day3.txt", s -> Integer.parseInt(s, 2)));
    }

    private final int positions;
    private final int binaryFilter;

    public Day3(int positions, int binaryFilter) {
        this.positions = positions;
        this.binaryFilter = binaryFilter;
    }

    @Override
    protected long part1(List<Integer> input) {
        int result = 0;
        for (int i = 0; i < positions; i++) {
            final int finalI = i;
            result = setBit(result, i, input.stream().map(j -> getBit(j, finalI)).filter(j -> j == 1).count() >= input.size() / 2 ? 1 : 0);
        }
        return result * (~(result) & binaryFilter);
    }

    @Override
    protected long part2(List<Integer> input) {
        return part2(input, b -> b ? 1 : 0, positions - 1) * part2(input, b -> b ? 0 : 1, positions - 1);
    }

    private int part2(List<Integer> input, Function<Boolean, Integer> decision, final int position) {
        int filterBy = decision.apply(input.stream().map(i -> getBit(i, position)).filter(i -> i == 1).count() >= (double)input.size() / 2);
        List<Integer> sublist = input.stream().filter(i -> getBit(i, position) == filterBy).collect(Collectors.toList());
        if (sublist.size() == 1) {
            return sublist.get(0);
        } else {
            return part2(sublist, decision, position - 1);
        }
    }

    private static int getBit(int input, int position) {
        return (input & (1 << position)) >> position;
    }

    private static int setBit(int input, int position, int value) {
        return value == 1 ? input | (1 << position) : input & ~(1 << position);
    }
}