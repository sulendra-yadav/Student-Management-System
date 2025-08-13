import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            StudentDAO dao = new StudentDAO();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== Student Management System ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. View Student by Id");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter grade: ");
                        String grade = sc.nextLine();
                        dao.addStudent(new Student(name, age, grade));
                        break;

                    case 2:
                        for (Student s : dao.getAllStudents()) {
                            System.out.println(s.getId() + " | " + s.getName() + " | " + s.getAge() + " | " + s.getGrade());
                        }
                        break;

                    case 3:
                        System.out.print("Enter id: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        for (Object s : dao.getStudentsById(id)) {
                            System.out.print(s+" | ");
                        }
                        break;

                    case 4:
                        System.out.print("Enter ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new age: ");
                        int newAge = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new grade: ");
                        String newGrade = sc.nextLine();
                        dao.updateStudent(uid, newName, newAge, newGrade);
                        break;

                    case 5:
                        System.out.print("Enter ID to delete: ");
                        int did = sc.nextInt();
                        dao.deleteStudent(did);
                        break;

                    case 6:
                        System.out.println("Exiting...");
                        System.exit(0);
                        //return;

                    default:
                        System.out.println("❌ Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
