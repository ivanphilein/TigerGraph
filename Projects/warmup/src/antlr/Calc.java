package antlr;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.InputStream;


public class Calc {
    public static void evalValue(String expression) throws Exception{
        CalcLexer lexer = new CalcLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser parser = new CalcParser(tokens);
        ParseTree tree = parser.prog();
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
        //System.out.println(tree.toStringTree(parser));
    }
    public static void main(String[] args) throws Exception {
        String expression = "1+2*(3+1)";
        evalValue(expression);
    }
}
