public class Token {
    public enum  Type{
         PRINT, NUMBER, PLUS, MINUS,EQUALS, STAR, SLASH, SEMICOLON, EOF , LBRACKET , RBRACKET,VARIABLE
    }
    //    PLUS  +
//    MINUS  -
//    STAR  *
//    SLASH  /
//    SEMICOLON  ;
//    EOF /0
    public final Type type;
    public  final String value;
// for bracket should i treaet it as new token or what

    public Token(Type type,String value){
        this.type=type;
        this.value=value;
    }
    @Override
    public String toString(){
        return type +"(" +value +")";
    }
}