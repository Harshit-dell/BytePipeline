//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    //Java  version 21
        String sart="tell 1+2;";
        Lexer lexer=new Lexer(sart);

        Token token;
        do{
            token=lexer.nextToken();
            System.out.println(token);
        }
        while(token.type!=Token.Type.EOF);
    }
}