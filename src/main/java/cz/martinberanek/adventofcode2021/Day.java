package cz.martinberanek.adventofcode2021;

public abstract class Day<T> {

    protected abstract long part1(T input);

    protected abstract long part2(T input);

    public void run(T input) {
        System.out.println("part1: " + part1(input));
        System.out.println("part2: " + part2(input));
    }
}
