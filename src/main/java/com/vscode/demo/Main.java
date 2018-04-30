package com.vscode.demo;

import java.util.Objects;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello VS Code!");

        Stream.of("React", "Angular", "Vue")
            .filter(x -> Objects.equals(x, "React"))
            .forEach(System.out::println);
    }

}