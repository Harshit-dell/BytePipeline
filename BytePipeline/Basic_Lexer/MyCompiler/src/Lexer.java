public class Lexer {
    private final String input;
    private  int position =0;
    public Lexer(String value){
            this.input=value;
    }

    private char peek(){
        if(position >=input.length()){
            return '\0';
        }
        // this is null character ASSI value 0
        return  input.charAt(position);
    }

    public Token nextToken(){
        while(Character.isWhitespace(peek())){
            position++;
        }

        char current=peek();
        if(current=='\0'){
            return new Token(Token.Type.EOF,"");
        }

        if(Character.isAlphabetic(current)){
            StringBuilder builder=new StringBuilder();
            while(Character.isAlphabetic(peek())){
                builder.append(peek());
                position++;
            }
            if(builder.toString().equals("tell")){
                return new Token(Token.Type.PRINT,"tell");
            }
            throw new RuntimeException("FuCk you"+builder);

        }

        if(Character.isDigit(current)){
            StringBuilder builder=new StringBuilder();
            while(Character.isDigit(peek())){
                builder.append(peek());
                position++;
            }
            return new Token(Token.Type.NUMBER,builder.toString());
        }

        switch (current) {
            case '+':
                position++;
                    return new Token(Token.Type.PLUS, "+");
            case '-': position++; return new Token(Token.Type.MINUS, "-");
            case '*': position++; return new Token(Token.Type.STAR, "*");
            case ';':
                position++; return new Token(Token.Type.SEMICOLON, ";");
            case '/':
                position++; return new Token(Token.Type.SLASH, "/");
        }

        throw new RuntimeException("Write someting meaningfukl"+current);
    }


}
