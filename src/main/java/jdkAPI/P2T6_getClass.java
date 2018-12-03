package jdkAPI;

public class P2T6_getClass {

    public static void main(String[] args) {
        Object obj = new Child();
        Parent parent = new Child();
        Child child = (Child)obj;
        System.out.println(obj.getClass());
        System.out.println(parent.getClass());
        System.out.println(child.getClass());
    }

}

class Parent {

}

class Child extends Parent {

}
