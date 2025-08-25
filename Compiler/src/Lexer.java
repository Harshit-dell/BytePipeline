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
    //debugger
    private Token makeToken(Token.Type type,String value){
        Token t=new Token(type,value);
        System.out.println("TOKEN: " + t.type +"  " +t.value);
        return t;
    }

    //TODO this is main file
    public Token nextToken(){
        while(Character.isWhitespace(peek())){
            position++;
        }


//check if empty
        char current=peek();
        if(current=='\0'){
            return makeToken(Token.Type.EOF,"");
        }
//NUMBER
        if(Character.isDigit(current)){
            StringBuilder builder=new StringBuilder();
            while(Character.isDigit(peek())){
                builder.append(peek());
                position++;
            }
            return makeToken(Token.Type.NUMBER,builder.toString());
        }


        //Character
        if(Character.isLetter(current)){
            StringBuilder builder =new StringBuilder();
            while(Character.isLetterOrDigit(peek()) || peek()=='_'){
                builder.append(peek());
                position++;
            }
            String value=builder.toString();
            return switch (value) {
                case "tell" -> makeToken(Token.Type.PRINT, "tell");
                case "flip" -> new Token(Token.Type.IF, "flip");
                case "flop" -> new Token(Token.Type.ELSE, "flop");
                case "spin" -> new Token(Token.Type.WHILE,"spin");
                default -> makeToken(Token.Type.VARIABLE, value);
            };
        }

        switch (current) {
            case '+': position++;return makeToken(Token.Type.PLUS, "+");
            case '-': position++; return makeToken(Token.Type.MINUS, "-");
            case '*': position++; return makeToken(Token.Type.STAR, "*");
            case '/': position++; return makeToken(Token.Type.SLASH, "/");
            case '(': position++; return  makeToken(Token.Type.LBRACKET,"(");
            case ';': position++; return  makeToken(Token.Type.SEMICOLON,";");
            case ')': position++; return  makeToken(Token.Type.RBRACKET,")");
            case '}': position++; return makeToken(Token.Type.RCURL,"}");
            case '{': position++; return makeToken(Token.Type.LCURL,"{");
            case '=':
                position++;
                if(peek()=='='){
                    position++;
                    return makeToken(Token.Type.EQUAL_EQUAL,"==");
                }
                else{
                    return  makeToken(Token.Type.EQUALS,"=");
                }
            case '<':
                position++;
                if(peek()=='='){
                        position++;
                     return makeToken(Token.Type.LESS_EQUAL,"<=");
                }
                else{
                    return  makeToken(Token.Type.LESS,"<");
                }
            case '>':
                position++;
                if(peek()=='='){
                    position++;
                    return makeToken(Token.Type.BIGGER_EQUAL,">=");
                }
                else{
                    return  makeToken(Token.Type.BIGGER,">");
                }
            case '!':
                position++;
                if(peek()=='='){
                    position++;
                    return  makeToken(Token.Type.NOT_EQUAL,"!=");
                }
                else {
                    throw new RuntimeException(""" 
                            Havent worked on "!" on this""");
                }

        }
        throw new RuntimeException("Write someting meaningfukl"+current);
    }
}
