# Java Debugging in Visual Studio Code 

- [Maven + Jetty version](https://github.com/kasecato/vscode-javadebug-sample/tree/maven-jetty)

## Getting Started

### Install Extension

* Microsoft, Java Extension Pack, https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack

### Setting the JDK

* Oracle, Java SE Downloads, http://www.oracle.com/technetwork/java/javase/downloads/index.html

The path to the Java Development Kit is searched in the following order:

* the `java.home` setting in VS Code settings (workspace then user settings)
* the `JDK_HOME` environment variable
* the `JAVA_HOME` environment variable
* on the current system path

### Gradle Configurations

* build.gradle
```groovy
repositories {
    mavenCentral()
}

apply plugin: 'java'

sourceCompatibility = 1.8
targetCompatibility = 1.8

compileTestJava {
    options.compilerArgs += '-parameters'
}

test {
    useJUnitPlatform()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.2.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.2.0'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher:1.2.0'
}

task wrapper(type: Wrapper) {
    description = 'Generates gradlew[.bat] scripts'
    gradleVersion = '4.7'
}

```

### Build Configurations

* .vscode/tasks.json
```json
{
  "version": "2.0.0",
  "echoCommand": true,
  "command": "./gradlew",
  "presentation": {
    "echo": true,
    "reveal": "always",
    "focus": false,
    "panel": "shared"
  },
  "tasks": [
    {
      "label": "compileJava",
      "type": "shell"
    },
    {
      "label": "test",
      "type": "shell",
      "group": {
        "kind": "test",
        "isDefault": true
      }
    },
    {
      "label": "clean",
      "type": "shell"
    },
    {
      "label": "build",
      "type": "shell",
      "group": {
        "kind": "build",
        "isDefault": true
      }
    }
  ]
}
```

### Java Debugger Configurations

* .vscode/launch.json
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Debug (Launch)",
            "request": "launch",
            "vmArgs": "",
            "preLaunchTask": "compileJava"
        }
    ]
}
```

### Java programming

* src/main/java/com/vscode/demo/Main.java
```java
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
```

* src/main/java/com/vscode/demo/MainTest.java
```java
package com.vscode.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("main")
class MainTest {

    @Test
    @DisplayName("VS Code JUnit 5 test")
    void testMain() {

        // arrange
        final List<String> list = Arrays.asList("React", "Angular", "Vue");

        // act
        final String actual = list.stream()
            .filter(x -> Objects.equals(x, "React"))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);

        // assert
        assertEquals("React", actual, () -> "Main Succeed");
    }

}
```

### Debugging

1. Open `Main.java`
1. Press `F5`

### Testing

1. Open Command Pallete `cmd+shift+p` (macOS) or `ctrl+shift+p` (Windonws/Linux)
1. `Tasks Run Test Task`
1. `test`

# References

1. Visual Studio Code, Java in VS Code, https://code.visualstudio.com/docs/languages/java
1. GitHub, Language Support for Java(TM) by Red Hat, https://github.com/redhat-developer/vscode-java
1. Visual Studio Code, Integrate with External Tools via Tasks, Variable substitution, https://code.visualstudio.com/Docs/editor/tasks#_variable-substitution
1. Gradle, Chapter 47. The Java Plugin, https://docs.gradle.org/current/userguide/java_plugin.html
1. GitHub, JUnit 5 Samples, https://github.com/junit-team/junit5-samples
1. Gradle, Gradle 4.6 Release Notes - JUnit 5 support, https://docs.gradle.org/4.6/release-notes.html#junit-5-support
