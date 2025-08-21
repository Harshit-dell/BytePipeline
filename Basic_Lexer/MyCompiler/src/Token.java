public class Token {
    public enum  Type{
        PRINT, NUMBER, PLUS, MINUS, STAR, SLASH, SEMICOLON, EOF
    }
//    PLUS  +
//    MINUS  -
//    STAR  *
//    SLASH  /
//    SEMICOLON  ;
//    EOF /0
    public final Type type;
    public  final String Value;

    public Token(Type type,String value){
            this.type=type;
            this.Value=value;
    }
    @Override
    public String toString(){
        return type +"(" +Value +")";
    }
}
