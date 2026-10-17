
class Box<T>{
    private T value;
    void set(T value){
        this.value=value;
    }
    T get(){
        return value;
    }
}
class Pair<A,B>{
    A first;
    B second;
    Pair(A first,B second){
        this.first=first;
        this.second=second;
    }
    void display(){
        System.out.println(first+" "+second);
    }
}
public class GenericDemo{
    static <T>T firstElement(T[] arr){
        return arr[0];
    }
    public static void main(String[] args){
        Box<Integer> b1=new Box<>();
        b1.set(100);
        System.out.println("Box: "+b1.get());
        Box<String> b2=new Box<>();
        b2.set("Hello");
        System.out.println("Box: "+b2.get());
        Pair<String,Integer> p=new Pair<>("Amit",20);
        p.display();
        Integer[] a={10,20,30};
        System.out.println("First element: "+firstElement(a));
    }
}
