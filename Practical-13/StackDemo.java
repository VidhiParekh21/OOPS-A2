
class MyStack<T>{
    Object[] arr=new Object[5];
    int top=-1;
    void push(T value){
        if(top==4){
            System.out.println("Stack is full");
            return;
        }
        arr[++top]=value;
    }
    @SuppressWarnings("unchecked")
    T pop(){
        if(top==-1){
            System.out.println("Stack is empty");
            return null;
        }
        return (T)arr[top--];
    }
}
public class StackDemo{
    public static void main(String[] args){
        MyStack<Integer> s1=new MyStack<>();
        s1.push(10);
        s1.push(20);
        System.out.println("Integer: "+s1.pop());
        MyStack<String> s2=new MyStack<>();
        s2.push("Java");
        s2.push("Hello");
        System.out.println("String: "+s2.pop());
        System.out.println("Empty case: "+s2.pop());
        System.out.println("Empty case: "+s2.pop());
    }
}
