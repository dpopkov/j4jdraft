package ru.j4jdraft.io.find;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ArgsTest {

    @Test
    public void whenArgsContainAllValuesThenReturnAllArgumentValues() {
        String[] arguments = splitArguments("-d c:\\ -n *.txt -m -o result.txt");
        Args args = new Args(arguments);
        assertThat(args.getDirectory(), is(Paths.get("c:\\")));
        assertThat(args.getName(), is("*.txt"));
        assertThat(args.getSearchBy(), is(SearchBy.MASK));
        assertThat(args.getOutput(), is("result.txt"));
    }

    @Test
    public void whenArgsContainAllValuesInRandomOrderThenReturnAllArgumentValues() {
        String[] arguments = splitArguments("-r -n [a-z]+\\d?.\\w{2,4} -o result.txt -d c:\\test");
        Args args = new Args(arguments);
        assertThat(args.getDirectory(), is(Paths.get("c:\\test")));
        assertThat(args.getName(), is("[a-z]+\\d?.\\w{2,4}"));
        assertThat(args.getSearchBy(), is(SearchBy.REGEX));
        assertThat(args.getOutput(), is("result.txt"));
    }

    @Test
    public void whenArgsDoNotContainAllValuesThenReturnDefaultArgumentValues() {
        String[] arguments = splitArguments("-n *.txt");
        Args args = new Args(arguments);
        final Path currentDirectory = Paths.get("");
        assertThat(args.getDirectory(), is(currentDirectory));
        assertThat(args.getName(), is("*.txt"));
        assertThat(args.getSearchBy(), is(SearchBy.FULL));
        assertThat(args.getOutput(), is("log.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsWithNonCorrectArgumentsThenThrowException() {
        String[] arguments = splitArguments("-z result.txt");
        new Args(arguments);
    }

    private String[] splitArguments(String args) {
        return args.split("\\s+");
    }
}
