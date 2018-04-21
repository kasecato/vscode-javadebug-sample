package com.vscode.demo;

import java.util.Arrays;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello VS Code!");

        Arrays.asList("React", "Angular", "Vue")
            .stream()
            .filter(x -> Objects.equals(x, "React"))
            .forEach(System.out::println);
    }

}