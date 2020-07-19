import managers.ProjectManager;
import managers.TaskManager;
import managers.UserManager;
import storage.FileRead;
import storage.FileWrite;
import subjects.Project;
import subjects.Task;
import subjects.User;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Класс предназначен для осуществления операций в системе пользователем
 */
public class UserConsole {
    static Logger logger;
    static {
        try (FileInputStream fileInputStream = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
            logger = Logger.getLogger(UserConsole.class.getName());
        } catch (FileNotFoundException ex) {
            System.out.println("log.config not found");
        } catch (IOException ex) {
            System.out.println("Couldn't logging");
        }
    }

    /**
     * Предоставляет пользователю список доступных операций
     * @param choice номер операции
     */
    public static void choice(int choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case Constants.ADD_USER: //add user
                addUser(scanner);
                break;
            case Constants.ADD_PROJECT: //add project
                addProject(scanner);
                break;
            case Constants.ADD_TASK: //add task
                addTask(scanner);
                break;
            case Constants.REMOVE_USER: //remove user
                removeUser(scanner);
                break;
            case Constants.REMOVE_PROJECT: //remove project
                removeProject(scanner);
                break;
            case Constants.REMOVE_TASK: //remove task
                removeTask(scanner);
                break;
            case Constants.GET_ALL_USERS: //get list of users
                getAllUsers();
                break;
            case Constants.GET_ALL_PROJECTS: //get list of projects
                getAllProjects();
                break;
            case Constants.GET_ALL_TASKS_IN_PROJECT: //get list of tasks in project
                getAllTasksInProject(scanner);
                break;
            case Constants.GET_ALL_USER_TASKS: //get list of user tasks
                getAllUsersTask(scanner);
                break;
            default:
                break;
        }
    }

    /**
     * Вывод в консоль всех задач в проекте
     * @param scanner
     */
    private static void getAllTasksInProject(Scanner scanner){
        System.out.println("Enter project title");
        String title = scanner.nextLine();
        System.out.println(TaskManager.getTasksInProject(title));
    }

    /**
     * Вывод в консоль всех проектов
     */
    private static void getAllProjects(){
        System.out.println(ProjectManager.getAllProjects());
    }

    /**
     * Вывод в консоль всех пользователей
     */
    private static void getAllUsers(){
        System.out.println(UserManager.getAllUsers());
    }

    private static void getAllUsersTask(Scanner scanner) {
        System.out.println("Enter username");
        String userName = scanner.nextLine();
        System.out.println(UserManager.getAllUserTasks(userName));
    }

    /**
     * Удаление задачи из системы
     * @param scanner
     */
    private static void removeTask(Scanner scanner){
        System.out.println("Enter name of project from you want to remove task");
        String projTitle = scanner.nextLine();
        System.out.println("Enter theme task's");
        String theme = scanner.nextLine();
        System.out.println("Enter type of task");
        String Type = scanner.nextLine();
        System.out.println(TaskManager.removeTask(projTitle, theme, Type));
    }

    /**
     * Удаление проекта из системы
     * @param scanner
     */
    private static void removeProject(Scanner scanner) {
        System.out.println("Enter project title");
        String Title = scanner.nextLine();
        ProjectManager.removeProject(Title);
    }

    /**
     * Удаление пользователя из системы
     * @param scanner
     */
    private static void removeUser(Scanner scanner) {
        System.out.println("Enter username");
        String userName = scanner.nextLine();
        System.out.println(UserManager.removeUser(userName));
    }

    /**
     * Добавление задачи в систему
     * @param scanner
     */
    private static void addTask(Scanner scanner){
        System.out.println("Enter theme, type, priority and description of task");
        String theme = scanner.nextLine();
        String type = scanner.nextLine();
        String priority = scanner.nextLine();
        String description = scanner.nextLine();
        System.out.println("Enter project title");
        String title = scanner.nextLine();
        System.out.println("Enter username");
        String userName = scanner.nextLine();
        Task task = new Task(theme, type, priority, description);
        System.out.println(TaskManager.addTask(task, title, userName));
    }

    /**
     * Добавление пользователя в систему
     * @param scanner
     */
    private static void addUser(Scanner scanner){
        System.out.println("Enter username");
        String userName = scanner.nextLine();
        System.out.println("Enter first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine();
        User newUser = new User(userName, firstName, lastName);
        System.out.println(UserManager.addUser(newUser));
    }

    /**
     * Добавление проекта в систему
     * @param scanner
     */
    private static void addProject(Scanner scanner){
        System.out.println("Enter project title");
        String title = scanner.nextLine();
        Project newProject = new Project(title);
        System.out.println(ProjectManager.addProject(newProject));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileRead storage = new FileRead();
        String fileName = "Storage.txt";
        System.out.println("Load storage? y/n");
        char ans = bufferedReader.readLine().charAt(0);
        while (!(ans == 'y' || ans == 'n')) {
            System.out.println("Bad answer, enter correct answer (y/n)");
            ans = bufferedReader.readLine().charAt(0);
        }
        if (ans == 'y') {
            String flag = " ";
            while (flag != null) {
                System.out.println("Enter filename");
                fileName = bufferedReader.readLine();
                flag = FileRead.loadStorage(fileName);
                if (flag != null)
                    logger.log(Level.SEVERE, flag);
                else {
                    logger.log(Level.INFO, "Storage loaded from " + fileName);
                    break;
                }
                System.out.println("Try again? (y/n)");
                ans = bufferedReader.readLine().charAt(0);
                while (!(ans == 'y' || ans == 'n')) {
                    System.out.println("Bad answer, enter correct answer (y/n)");
                    ans = bufferedReader.readLine().charAt(0);
                }
                if (ans == 'n')
                    flag = null;
            }
        }
        int choice = 1;
        while(choice != 0){
            System.out.println("Commands:\n" + Constants.ADD_USER + " - Add user\n" + Constants.ADD_PROJECT +
                    " - Add project\n" + Constants.ADD_TASK + " - Add task\n" + Constants.REMOVE_USER + " - remove user" +
                    "\n" + Constants.REMOVE_PROJECT +" - remove project\n" + Constants.REMOVE_TASK + " - remove task\n" +
                    Constants.GET_ALL_USERS + " - get list of users\n" + Constants.GET_ALL_PROJECTS + " - get list " +
                    "of projects\n" + Constants.GET_ALL_TASKS_IN_PROJECT + " - get list of tasks in project\n" +
                    Constants.GET_ALL_USER_TASKS + " - get list of user tasks\n" + Constants.EXIT + " - Exit");
            choice = Integer.parseInt(bufferedReader.readLine());
            if (choice < 0 || choice > 10)
                System.out.println("Wrong command");
            else
                choice(choice);
        }
        String save = FileWrite.saveStorage(fileName);
        if (save == null)
            logger.log(Level.INFO, "Storage saved to " + fileName);
        else
            logger.log(Level.SEVERE, save);
    }
}