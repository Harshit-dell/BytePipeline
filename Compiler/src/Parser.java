import java.util.ArrayList;
import java.util.List;

class Parser {
    private final Lexer lexer;
    private Token current;

    Parser(Lexer lexer) {
        this.lexer = lexer;
        this.current = lexer.nextToken();
    }

    private void eat(Token.Type type) {
        if (current.type == type) {
            current = lexer.nextToken();
        } else {
            throw new RuntimeException("Expected " + type + "  but found (eat-parser)" + current.type);
        }
    }
    public List<Stmt> parseProgram() {
        List<Stmt> program = new ArrayList<>();
        while (current.type != Token.Type.EOF) {
            program.add(Starter());   // keep reading statements until end
        }
        return program;
    }


    private  Stmt Starter(){
        if(current.type==Token.Type.VARIABLE){
            return   VariableParser();
        }
        else if(current.type==Token.Type.PRINT){
            return parsePrint();
        }
        else if(current.type==Token.Type.LCURL){
            return blockParser();
        }
        throw new RuntimeException("starter-parser error (mostly error in the code you wrote )" );
    }

    // TODO have to now do operator precedence ✔.
    //TODO add the starter file in the code for parser

    private BlockStmt blockParser(){
        if(current.type==Token.Type.LCURL){
            eat(Token.Type.LCURL);
            List<Stmt> statment=new ArrayList<>();
            while(current.type!= Token.Type.RCURL && current.type!= Token.Type.EOF){
                statment.add(Starter());
            }
            eat(Token.Type.RCURL);
            return new BlockStmt(statment);
        }
        throw  new RuntimeException("BlockStmt-parser error");
    }


    private  VariableStmt VariableParser(){
            String name=current.value;
            eat(Token.Type.VARIABLE);
            if(current.type==Token.Type.EQUALS){
                eat(Token.Type.EQUALS);
                Expr expr =parseAdditon();
                eat(Token.Type.SEMICOLON);
                return  new VariableStmt(name,expr);
            }
        throw new RuntimeException("varibaleparser error");
    }



    // Parse a print statement
    public PrintStmt parsePrint() {
        eat(Token.Type.PRINT);
        Expr expr = parseAdditon();
        eat(Token.Type.SEMICOLON);
        return new PrintStmt(expr);
    }





    // (only + and - for now)
    private Expr parseAdditon() {

        Expr left = parseMultipilcation();

        while (current.type == Token.Type.PLUS || current.type == Token.Type.MINUS) {
            String op = current.value;
            eat(current.type);
            Expr right = parseMultipilcation();
            left = new BinaryExpr(left, op, right);
        }
        return left;
    }

    //i have to handel the variable as the numbere




    private Expr parseMultipilcation() {
        Expr left = parseNumber();
        while (current.type == Token.Type.STAR || current.type == Token.Type.SLASH) {
            String opertor = current.value;
            eat(current.type);
            Expr right = parseNumber();
            left = new BinaryExpr(left, opertor, right);
        }
        return left;
    }

    //  (only numbers for now)
    private Expr parseNumber() {
        if (current.type == Token.Type.NUMBER) {
            int value = Integer.parseInt(current.value);
            eat(Token.Type.NUMBER);
            return new NumberExpr(value);
        }
        if(current.type == Token.Type.MINUS || current.type == Token.Type.PLUS){
            String operator = current.value;
            eat(current.type);
            Expr value = parseNumber(); // recursively parse after unary
            return new UnaryExpr(operator, value);
        }

        else if (current.type == Token.Type.VARIABLE) {
            String name = current.value;
            eat(Token.Type.VARIABLE);
            return new VariableExpr(name);
        }
        else if (current.type == Token.Type.LBRACKET) {
            eat(Token.Type.LBRACKET);
            Expr expr = parseAdditon();
            eat(Token.Type.RBRACKET);
            return expr;
        }
        throw new RuntimeException("Unexpected token: " + current);
    }

}
