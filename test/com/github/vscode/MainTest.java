package com.github.vscode;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class MainTest {

    @Test
    public void main() {

        // arrange
        List<String> list = Arrays.asList("React", "Angular", "Ember");

        // act
        final String actual = list.stream().filter(x -> x.equals("Angular")).findFirst().orElse(null);

        // assert
        assertEquals("Angular", actual);
    }

}