import java.util.Scanner;

import com.i2i.cms.controller.GradeController;
import com.i2i.cms.controller.SportController;
import com.i2i.cms.controller.StudentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * <p>
 * The Main class serves as the entry point of the application.
 * Users can create, fetch, search, update, and delete records related to students,
 * grades, and sports.
 * </p>
 */
@Component
@ComponentScan(basePackages = "com.i2i.cms")
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    @Autowired
    private StudentController studentController;

    @Autowired
    private GradeController gradeController;

    @Autowired
    private SportController sportController;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main mainController = context.getBean(Main.class);
        mainController.startApplication();
    }
    
    /**
     * <p>
     * Allows the user to choose from various options such as creating, fetching,
     * searching, updating, and deleting records.
     * </p>
     */
    public void startApplication() {
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("\n|-------------------------------------------|");
            System.out.println("| 1. Create  - Add a new student.           |");
            System.out.println("| 2. Fetch   - View details of a student.   |");
            System.out.println("| 3. Search  - Find a student by ID.        |");
            System.out.println("| 4. Update  - Update a student record.     |");
            System.out.println("| 5. Delete  - Remove a student.            |");
            System.out.println("| 6. Exit    - Quit the application.        |");
            System.out.println("=============================================");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("\n1. Creating a Student record");
                    System.out.println("2. Creating a Grade record");
                    System.out.println("3. Creating a Sport record");
                    System.out.print("Enter your choice: ");
                    int createChoice = scanner.nextInt();
                    switch (createChoice) {
                        case 1:
                            studentController.addStudent();
                            break;
                        case 2:
                            gradeController.addGrade();
                            break;
                        case 3:
                            sportController.addSport();
                            break;
                        default:
                            System.out.println("Invalid option. Try again.");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\n1. Fetching the Student records");
                    studentController.fetchAllStudents();
                    break;
                case 3:
                    System.out.println("\n1. Searching a Student Detail");
                    System.out.println("2. Searching a Grade Detail");
                    System.out.print("Enter your choice: ");
                    int searchChoice = scanner.nextInt();
                    switch (searchChoice) {
                        case 1:
                            studentController.searchStudentByStudentId();
                            break;
                        case 2:
                            gradeController.searchStudentsByGradeId();
                            break;
                        default:
                            System.out.println("Invalid option. Try again.");
                            break;
                    }
                    break;
                case 4:
                    studentController.updateStudentById();
                    break;
                case 5:
                    System.out.println("\nDeleting the Student detail");
                    studentController.deleteStudentById();
                    break;
                case 6:
                    System.out.println("\nExiting the application...");
                    isFlag = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Try again.");
                    break;
            }
        }
    }
}
