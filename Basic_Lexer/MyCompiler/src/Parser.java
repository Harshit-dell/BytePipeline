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
            throw new RuntimeException("Expected" +type +"but found "+ current.type);
        }
    }
    // TODO have to now do operator precedence.
    //check
    
    // Parse a print statement}
    public PrintStmt parsePrint() {
        eat(Token.Type.PRINT);
        Expr expr = parseAdditon();
        eat(Token.Type.SEMICOLON);
        return new PrintStmt(expr);
    }
//first will be bracket
    //second will be * and /


    // (only + and - for now)
    private Expr parseAdditon() {
        Expr left = parseMultipilcation();
        //check for muti
        while (current.type == Token.Type.PLUS || current.type == Token.Type.MINUS) {
            String op = current.value;
            eat(current.type);
            Expr right = parseNumber();
            left = new BinaryExpr(left, op, right);
        }
        return left;
    }
    private  Expr  parseMultipilcation(){
        Expr left= parseNumber();
        while(current.type== Token.Type.STAR || current.type==Token.Type.SLASH){
            String opertor=current.value;
            eat(current.type);
            Expr right=parseNumber();
            left=new BinaryExpr(left,opertor,right);
        }
        return  left;
    }
    //  (only numbers for now)
    private Expr parseNumber() {
        if (current.type == Token.Type.NUMBER) {
            int value = Integer.parseInt(current.value);
            eat(Token.Type.NUMBER);
            return new NumberExpr(value);
        }
        else if(current.type==Token.Type.LBRACKET){
            eat(Token.Type.LBRACKET);
              Expr expr = parseAdditon();
            eat(Token.Type.RBRACKET);
            return expr;
        }
        throw new RuntimeException("Unexpected token: " + current);
    }
}
