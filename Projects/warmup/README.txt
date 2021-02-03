Warm-up Project at TigerGraph
This project is a calculator project implemented using Antlr and Jline3

1. Runnable Jar file
    1.1 # java -jar Calculator.jar
    1.2 The program generates an inline calculator. This is a basic version of calculator, which supports '+', '-', '*', '/', and '^' operations
    1.3 The input numbers can be either integer or float numbers.

2. Compile prerequests
    2.1 Install Jline3 and Antlr
    2.2 Configure the antlr location for Java
        2.2.1 export CLASSPATH=".:/usr/local/lib/antlr-4.9.1-complete.jar:$CLASSPATH"
        2.2.2 alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.9.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
        2.2.3 alias grun='java -Xmx500M -cp "/usr/local/lib/antlr-4.9.1-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
        2.2.4 Make sure to put the correct location of antlr jar file.
    2.3 Configure the jline3 location for Java
        2.3.1 export CLASSPATH=".:~Work/TigerGraph/jline3/jline3/jline/target/jline-3.19.1-SNAPSHOT.jar:$CLASSPATH"
        2.3.2 Make sure to put the correct location of jline jar file.

3. Compile from source files
    3.1 # cd src/antlr
    3.2 # antlr4 -visitor Calc.g4
    3.3 # Add "package antlr;" to each java files under antlr
    3.4 # javac *.java
    3.5 # cd ..
    3.6 # javac *.java antlr/*.java
    3.7 # java Calculator
