class Pair <V,K>{
    V First;
    K Second;
    Pair(V first,K second){
        this.First=first;
        this.Second=second;
    }
}
class ReturnError extends  RuntimeException{
    Object value;
    ReturnError(Object value){
        this.value=value;
    }

}