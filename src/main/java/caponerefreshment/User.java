package caponerefreshment;

public class User {

    private String name;
    private int age;
    private boolean isMember;

    public User(String name, int age, boolean isMember){
        this.name = name;
        this.age = age;
        this.isMember = isMember;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void greet(){
        System.out.println("Hello! My name is " + getName() + " and I am " + getAge() + " years old.");
    }
}