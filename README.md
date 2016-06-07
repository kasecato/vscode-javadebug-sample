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
    "command": "C:\\Program Files\\Java\\jdk1.8.0_91\\bin\\javac",
    "isShellCommand": true,
    "showOutput": "always",
    "isWatching": true,
    "suppressTaskName": true,
    "tasks": [
        {
            "taskName": "build",
            "args": ["-g", "${file}"]
        } // ${file}: the current opened file
    ]
}
```

### Launch Configurations

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
            "startupClass": "${fileBasename}", // ${fileBasename}: the current opened file's basename
            "options": [
                "-classpath",
                "\"${fileDirname};.\"" // ${fileDirname}: the current opened file's dirname
            ],
            "jdkPath": "C:\\Program Files\\Java\\jdk1.8.0_91\\bin",
            "preLaunchTask": "build"
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
            "jdkPath": "C:\\Program Files\\Java\\jdk1.8.0_91\\bin",
            "preLaunchTask": "build"
        }
    ]
}
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
