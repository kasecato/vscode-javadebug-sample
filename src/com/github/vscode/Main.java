package com.github.vscode;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello VS Code!");

        List<String> list = Arrays.asList("React", "Angular", "Ember");
        list.stream().filter(x -> x.equals("Angular")).forEach(System.out::println);

    }

}