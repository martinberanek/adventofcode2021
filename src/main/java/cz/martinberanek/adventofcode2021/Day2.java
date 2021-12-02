package cz.martinberanek.adventofcode2021;

import java.util.List;
import java.util.function.BiFunction;

public class Day2 extends Day<List<Day2.Command>> {
    public static void main(String[] args) {
        new Day2().run(Util.load("/Day2.txt", Command::new));
    }

    @Override
    protected long part1(List<Command> input) {
        return stateMachine(input, (command, state) -> {
                    switch (command.direction) {
                        case "forward":
                            return new State(state.horizontal + command.distance, state.depth, state.aim);
                        case "down":
                            return new State(state.horizontal, state.depth + command.distance, state.aim);
                        case "up":
                            return new State(state.horizontal, state.depth - command.distance, state.aim);
                    }
                    throw new IllegalStateException("Unknown command " + command.direction);
                }
        );
    }

    @Override
    protected long part2(List<Command> input) {
        return stateMachine(input, (command, state) -> {
                    switch (command.direction) {
                        case "forward":
                            return new State(state.horizontal + command.distance, state.depth + state.aim * command.distance, state.aim);
                        case "down":
                            return new State(state.horizontal, state.depth, state.aim + command.distance);
                        case "up":
                            return new State(state.horizontal, state.depth, state.aim - command.distance);
                    }
                    throw new IllegalStateException("Unknown command " + command.direction);
                }
        );
    }

    private long stateMachine(List<Command> input, BiFunction<Command, State, State> function) {
        State state = new State(0L, 0L, 0L);
        for (Command command : input) {
            state = function.apply(command, state);
        }
        return state.horizontal * state.depth;
    }

    public static class Command {
        final String direction;
        final int distance;

        Command(String line) {
            String[] parts = line.split(" ");
            direction = parts[0];
            distance = Integer.parseInt(parts[1]);
        }
    }

    public static class State {
        final long horizontal;
        final long depth;
        final long aim;

        public State(long horizontal, long depth, long aim) {
            this.horizontal = horizontal;
            this.depth = depth;
            this.aim = aim;
        }
    }
}
