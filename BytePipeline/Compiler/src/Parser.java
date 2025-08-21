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
            throw new RuntimeException("Expected " + type + " rererere but found " + current.type);
        }
    }
    // TODO have to now do operator precedence.
    public Stmt starter(){
        if(current.type==Token.Type.VARIABLE){
            String name=current.value;
            eat(Token.Type.VARIABLE);
            if(current.type==Token.Type.EQUALS){
                eat(Token.Type.EQUALS);
                Expr expr  =parseAdditon();
                eat(Token.Type.SEMICOLON);
                return  new VariableStmt(name,expr);
            }
        }
        else if(current.type==Token.Type.PRINT){
            return  parsePrint();
        }
        throw  new RuntimeException("started.parser failed");
    }



    // Parse a print statement
    public PrintStmt parsePrint() {
        eat(current.type);
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
