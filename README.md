# Java Debugging in Visual Studio Code 

## Getting Started

### Install Extension

* Don Jayamanne, Java Debugger, https://marketplace.visualstudio.com/items?itemName=donjayamanne.javaDebugger
* George Fraser, Java Language Support, https://marketplace.visualstudio.com/items?itemName=georgewfraser.vscode-javac

### Build Configurations

* .vscode/tasks.json
```json
{
  "version": "0.1.0",
  "windows": {
  },
  "osx": {
      "options": {
          "env": {
              "JAVA_HOME": "/usr" // OSX: /usr/bin/java
          }
      }
  },
  "command": "mvn",
  "isShellCommand": true,
  "args": [
//    "-DskipTests"
  ],
  "showOutput": "always",
  "tasks": [
    {
      "taskName": "clean"
    },
    {
      "taskName": "compile"
    },
    {
      "taskName": "test"
    },
    {
      "taskName": "package"
    },
    {
      "taskName": "verify"
    },
    {
      "taskName": "spring-boot:run",
      "args": [
        "-Drun.profiles=dev"
      ]
    }
  ]
}
```

### Java Debugger Configurations

* .vscode/tasks.json
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "name": "Java",
            "type": "java",
            "request": "launch",
            "stopOnEntry": true,
            "cwd": "${fileDirname}", // ${fileDirname}: the current opened file's dirname
            "startupClass": "com.github.vscode.${fileBasename}", // ${fileBasename}: the current opened file's basename
            "options": [
                "-classpath",
                "${workspaceRoot}/target/classes" // ${fileDirname}: the current opened file's dirname
            ],
            "jdkPath": "${env.JAVA_HOME}/bin", // ${env.JAVA_HOME}: reference environment variables
            "preLaunchTask": "compile"
        },
        {
            "name": "Java Console App",
            "type": "java",
            "request": "launch",
            "stopOnEntry": true,
            "cwd": "${fileDirname}",
            "startupClass": "${fileBasename}",
            "options": [
                "-classpath",
                "\"${fileDirname};.\""
            ],
            "externalConsole": true,
            "jdkPath": "${env.JAVA_HOME}/bin",
            "preLaunchTask": "compile"
        }
    ]
}
```

### Java Language Support Configurations

* javaconfig.json
```json
{
    "sourcePath": ["src"],
    "classPathFile": "classpath.txt",
    "outputDirectory": "target/classes"
}
```

### Maven Configurations

* pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.github.vscode</groupId>
    <artifactId>vscode-debugger</artifactId>
    <version>0.1.0</version>
    <name>vscode-debugger</name>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.4</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-library</artifactId>
            <version>1.3</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <directory>${project.basedir}/target</directory>
        <outputDirectory>${project.build.directory}/classes</outputDirectory>
        <finalName>${project.artifactId}-${project.version}</finalName>
        <testOutputDirectory>${project.build.directory}/test-classes</testOutputDirectory>
        <sourceDirectory>${project.basedir}/src/</sourceDirectory>
        <testSourceDirectory>${project.basedir}/test/</testSourceDirectory>
    </build>
    <profiles>
        <profile>
            <id>default</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <version>3.5.1</version>
                        <configuration>
                            <verbose>true</verbose>
                            <fork>true</fork>
                            <compilerVersion>1.8</compilerVersion>
                            <source>1.8</source>
                            <target>1.8</target>
                            <meminitial>128m</meminitial>
                            <maxmem>1024m</maxmem>
                            <encoding>UTF-8</encoding>
                        </configuration>
                    </plugin>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-dependency-plugin</artifactId>
                        <version>2.9</version>
                        <executions>
                            <execution>
                                <id>build-classpath</id>
                                <phase>generate-sources</phase>
                                <goals>
                                    <goal>build-classpath</goal>
                                </goals>
                            </execution>
                        </executions>
                        <configuration>
                            <outputFile>classpath.txt</outputFile>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
</project>
```


### Java programming

* src/com/github/k--kato/Main.java
```java
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello VS Code!");

        List<String> list = Arrays.asList("React", "Angular", "Ember");
        list.stream().filter(x -> x.equals("Angular")).forEach(System.out::println);

    }

}
```

### Debugging

1. Open Command Pallete ` Ctrl+Shift+p` ` 
1. `Tasks Run Task`
1. `compile`
1. Open `Main.java`
1. Press `F5`


# Appendix

## Variable substitution

> * ${workspaceRoot} the path of the folder opened in VS Code
> * ${file} the current opened file
> * ${fileBasename} the current opened file's basename
> * ${fileDirname} the current opened file's dirname
> * ${fileExtname} the current opened file's extension
> * ${cwd} the task runner's current working directory on startup


# References

1. Don Jayamanne, Java Debugger, https://marketplace.visualstudio.com/items?itemName=donjayamanne.javaDebugger
1. George Fraser, Java Language Support, https://marketplace.visualstudio.com/items?itemName=georgewfraser.vscode-javac
1. Visual Studio Code, Integrate with External Tools via Tasks, Variable substitution, https://code.visualstudio.com/Docs/editor/tasks#_variable-substitution
