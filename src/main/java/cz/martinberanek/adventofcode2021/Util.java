package cz.martinberanek.adventofcode2021;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Util {

    public static <T> List<T> load(String resource, Function<String,T> transform) {
        try {
            return Files.lines(Paths.get(Objects.requireNonNull(Util.class.getResource(resource)).toURI()))
                    .map(transform)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
