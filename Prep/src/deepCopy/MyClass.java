package deepCopy;

import java.util.IdentityHashMap;
import java.util.Map;

public class MyClass {

    private String myString;
    public MyClass other;
    
    public MyClass(String input) {
        this.myString = input;
    }
    
    public MyClass deepCopy() {
        Map<MyClass, MyClass> alreadyCopied = new IdentityHashMap<>();
        
        return deepCopy(this, alreadyCopied);
    }

    private MyClass deepCopy(MyClass myClass, Map<MyClass, MyClass> alreadyCopied) {
        if (myClass != null && !alreadyCopied.containsKey(myClass)) {
            MyClass clone = alreadyCopied.get(myClass);
            if (clone != null) {
                return clone;
            }
            clone = new MyClass(myClass.myString);

            alreadyCopied.put(myClass, clone);
            
            if (myClass.other != null) {
                clone.other = myClass.other.deepCopy(myClass.other, alreadyCopied);
            }
            
            return clone;
        }
        return null;
    }
    
    public static void main(String[] args) {
        MyClass inst = new MyClass("hello");
        inst.other = inst;
        
        MyClass copy = inst.deepCopy();
        
        System.out.println(copy);
    }
}
