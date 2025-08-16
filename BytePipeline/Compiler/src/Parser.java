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
            throw new RuntimeException("Expected " + type + " but found " + current.type);
        }
    }

    // Parse a print statement}
    public PrintStmt parsePrint() {
        eat(Token.Type.PRINT);
        Expr expr = parseExpression();
        eat(Token.Type.SEMICOLON);
        return new PrintStmt(expr);
    }

    // Parse expressions (only + and - for now)
    private Expr parseExpression() {
        Expr left = parseTerm();
        while (current.type == Token.Type.PLUS || current.type == Token.Type.MINUS) {
            String op = current.value;
            eat(current.type);
            Expr right = parseTerm();
            left = new BinaryExpr(left, op, right);
        }
        return left;
    }

    // Parse terms (only numbers for now)
    private Expr parseTerm() {
        if (current.type == Token.Type.NUMBER) {
            int value = Integer.parseInt(current.value);
            eat(Token.Type.NUMBER);
            return new NumberExpr(value);
        }
        throw new RuntimeException("Unexpected token: " + current);
    }






}
