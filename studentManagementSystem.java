package StudentManageSystem;
import java.util.*;

class student {
    private String name;
    private int id;
    private int age;

    public student(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}

public class studentManagementSystem {
    private List<student> students;

    public studentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(student student) {
        students.add(student);
    }

    public void displayAllStudents() {
        for (StudentManageSystem.student student : students) {
            System.out.println(student);
        }
    }

    public student findStudentById(int id) {
        for (StudentManageSystem.student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void deleteStudentById(int id) {
        Iterator<student> iterator = students.iterator();
        while (iterator.hasNext()) {
            student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                System.out.println("student with ID " + id + " deleted successfully.");
                return;
            }
        }
        System.out.println("student with ID " + id + " not found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        studentManagementSystem sms = new studentManagementSystem();

        while (true) {
            System.out.println("\nstudent Management System Menu:");
            System.out.println("1. Add student");
            System.out.println("2. Display All students");
            System.out.println("3. Find student by ID");
            System.out.println("4. Delete student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    sms.addStudent(new student(name, id, age));
                    System.out.println("student added successfully.");
                    break;
                case 2:
                    System.out.println("All students:");
                    sms.displayAllStudents();
                    break;


                case 3:
                    System.out.print("Enter student ID to search: ");
                    int searchId = scanner.nextInt();
                    student foundStudent = sms.findStudentById(searchId);
                    if (foundStudent != null) {
                        System.out.println("student found:");
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("student with ID " + searchId + " not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    sms.deleteStudentById(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
