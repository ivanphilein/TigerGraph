import org.jline.builtins.Completers;
import org.jline.reader.*;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.NullCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import antlr.Calc;

public class Calculator {

    private static List<String> fileVars = new ArrayList<>();
    private static FileVarsCompleter fileVarsCompleter = new FileVarsCompleter();

    /**
     * Check if the input expression is a valid math expression or not
     * This is only the basic check. To Check it contains only numbers, (, ), +, -, *, and /
     * @param expression
     * @return
     */
    private static Boolean valid(String expression){
        if (Pattern.matches("[0-9\\+\\-\\*/\\(\\)\\.\\^\\ ]*", expression)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * repl function for calculator
     * repl: Read-Eval-Print Loop framework
     */
    private static void repl(LineReader lineReader) throws Exception{
        Calc calclass = new Calc();
        String prompt = "Calculator> ";
        while (true) {
            String line;
            try {
                line = lineReader.readLine(prompt);
                if (valid(line)==false){
                    System.out.println("Input is not valid, please check!");
                    continue;
                }
                //System.out.println(line);
                calclass.evalValue(line);

            } catch (UserInterruptException e) {
                // Do nothing
            } catch (EndOfFileException e) {
                System.out.println("\nBye.");
                return;
            }
        }
    }



    public static void main(String[] args) throws Exception {

        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        Completer createCompleter = new ArgumentCompleter(
                new StringsCompleter("CREATE"),
                new Completers.FileNameCompleter(),
                NullCompleter.INSTANCE
        );

        Completer openCompleter = new ArgumentCompleter(
                new StringsCompleter("OPEN"),
                new Completers.FileNameCompleter(),
                new StringsCompleter("AS"),
                NullCompleter.INSTANCE
        );

        Completer writeCompleter = new ArgumentCompleter(
                new StringsCompleter("WRITE"),
                new StringsCompleter("TIME", "DATE", "LOCATION"),
                new StringsCompleter("TO"),
                fileVarsCompleter,
                NullCompleter.INSTANCE
        );

        Completer fogCompleter = new AggregateCompleter(
                createCompleter,
                openCompleter,
                writeCompleter
        );

        LineReader lineReader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(fogCompleter)
                .history(new CalHistory())
                .build();

        repl(lineReader);
    }

}