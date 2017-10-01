package com.vscode.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello VS Code!");

        final List<String> list = Arrays.asList("React", "Angular", "Vue");
        list.stream()
            .filter(x -> Objects.equals(x, "React"))
            .forEach(System.out::println);

    }

}