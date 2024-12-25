package Project.StudentCtrlSystem;

public class Student {
    private String id;
    private String name;
    private String gender;
    private String add;
    private int age;
    public Student(){

    }
    public Student(String id,String name,String gender,int age,String add){
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.add=add;
        this.age=age;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setGender(String gender){
        this.gender=gender;
    }
    public String getGender(){
        return gender;
    }
    public void setAdd(String add){
        this.add=add;
    }
    public String getAdd(){
        return add;
    }
    public void setAge(int age){
        this.age=age;
    }
    public int getAge(){
        return age;
    }

}
