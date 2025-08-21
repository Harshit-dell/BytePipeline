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



    //TODO this is main file
    public Token nextToken(){
        while(Character.isWhitespace(peek())){
            position++;
        }
//check if empty
        char current=peek();
        if(current=='\0'){
            return new Token(Token.Type.EOF,"");
        }
//NUMBER
        if(Character.isDigit(current)){
            StringBuilder builder=new StringBuilder();
            while(Character.isDigit(peek())){
                builder.append(peek());
                position++;
            }
            return new Token(Token.Type.NUMBER,builder.toString());
        }


        //Character
        if(Character.isLetter(current)){
           StringBuilder builder =new StringBuilder();
            while(Character.isLetterOrDigit(peek()) || peek()=='_'){
                builder.append(peek());
                position++;
            }
            String value=builder.toString();
            if(value.equals("tell")){
                return  new Token(Token.Type.PRINT,"tell");
            }
//            else if(builder.equals("agar")){
//                return  new Token(Token.Type.IF,"warna");
//            }
//            else if(builder.equals("warna")){
//                return  new Token(Token.Type.ELSE,"warna");
//            }
            else {
                return  new Token(Token.Type.VARIABLE,value);
            }

        }



        switch (current) {
            case '+': position++;return new Token(Token.Type.PLUS, "+");
            case '-': position++; return new Token(Token.Type.MINUS, "-");
            case '*': position++; return new Token(Token.Type.STAR, "*");
            case '/': position++; return new Token(Token.Type.SLASH, "/");
            case '=': position++; return new Token(Token.Type.EQUALS,"=");
            case '(': position++; return  new Token(Token.Type.LBRACKET,"(");
            case ';': position++; return  new Token(Token.Type.SEMICOLON,";");
            case ')': position++; return  new Token(Token.Type.RBRACKET,")");
//            case '}': position++; return new Token(Token.Type.RCURL,"}");
//            case '{': position++; return new Token(Token.Type.LCURL,"{");

         }
        throw new RuntimeException("Write someting meaningfukl"+current);
    }
}