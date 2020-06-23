import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class main {
    public static void choice(int choice, Storage storage, FileWriter fileWriter) throws IOException {
        Date date = new Date();
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1: //add user
                System.out.println("Enter username");
                String userName = scanner.nextLine();
                while (storage.getUser(userName) != null) {
                    System.out.println("This username already exists, enter another username");
                    fileWriter.write('[' + date.toString() + "] Error (add user): this username already exists.\n");
                    userName = scanner.nextLine();
                }
                System.out.println("Enter first name");
                String firstName = scanner.nextLine();
                System.out.println("Enter last name");
                String lastName = scanner.nextLine();
                User newUser = new User(userName, firstName, lastName);
                storage.addUser(newUser);
                fileWriter.write('[' + date.toString() + "] User " + userName + ", first name: " + firstName +
                        ", last name: " + lastName + " added" + '\n');
                break;
            case 2: //add project
                System.out.println("Enter project title");
                String title = scanner.nextLine();
                if (storage.getProject(title) != null) {
                    System.out.println("This project already exists");
                    fileWriter.write('[' + date.toString() + "] Error (add project): this project already exists.\n");
                    break;
                }
                Project newProject = new Project(title);
                storage.addProject(newProject);
                fileWriter.write('[' + date.toString() + "] Project " + title + " added" + '\n');
                break;
            case 3: //add task
                if(storage.isEmptyUsers() || storage.isEmptyProjects())
                    System.out.println("At least one user and one project are needed to add a task(s)");
                else {
                    System.out.println("Enter theme, type, priority and description of task");
                    String theme = scanner.nextLine();
                    String type = scanner.nextLine();
                    String priority = scanner.nextLine();
                    while (!(priority.equals("Low") || priority.equals("Mid") || priority.equals("High"))) {
                        System.out.println("Wrong priority value (priority value may be equals High, Mid or Low" +
                                "\nEnter correct priority value");
                        fileWriter.write('[' + date.toString() + "] Error (add task): wrong priority value.\n");
                        priority = scanner.nextLine();
                    }
                    String description = scanner.nextLine();
                    System.out.println("Enter project title");
                    String tmpTitle = scanner.nextLine();
                    if (storage.getTask(theme, type, tmpTitle) != null) {
                        System.out.println("This task is already exists");
                        fileWriter.write('[' + date.toString() + "] Error (add task): this task is already exists.\n");
                        break;
                    }
                    Task task = new Task(theme, type, priority, description);
                    if (storage.getProject(tmpTitle) != null)
                        task.setProject(storage.getProject(tmpTitle));
                    else
                        while(storage.getProject(tmpTitle) == null) {
                            System.out.println("No such project exists\nEnter project title");
                            fileWriter.write('[' + date.toString() + "] Error (add task): no such project exists.\n");
                            tmpTitle = scanner.nextLine();
                        }
                    System.out.println("Enter username");
                    userName = scanner.nextLine();
                    if(storage.getUser(userName)!= null) {
                        task.setUser(storage.getUser(userName));
                        storage.addTask(task);
                        fileWriter.write('[' + date.toString() + "] Task " + theme + " type: " + type +
                                " added to project " + tmpTitle + '\n');
                    }
                    else
                        while(storage.getUser(userName) == null) {
                            System.out.println("No such user exists\nEnter user name");
                            fileWriter.write('[' + date.toString() + "] Error (add task): no such user exists.\n");
                            userName = scanner.nextLine();
                        }
                }
                break;
            case 4: //remove user
                System.out.println("Enter username");
                userName = scanner.nextLine();
                if (storage.removeUser(userName)) {
                    System.out.println("Remove complete");
                    fileWriter.write('[' + date.toString() + "] User " + userName + " removed" + '\n');
                }
                else {
                    if (storage.isEmptyUsers())
                        fileWriter.write('[' + date.toString() + "] Error (delete user): no users.\n");
                    else if (storage.getUser(userName) == null)
                        fileWriter.write('[' + date.toString() + "] Error (delete project): Cannot delete" +
                                " current user.\n");
                    else
                        fileWriter.write('[' + date.toString() + "] Error (delete user): no such user exists.\n");
                    System.out.println("Remove incomplete");
                }
                break;
            case 5: //remove project
                System.out.println("Enter project title");
                String Title = scanner.nextLine();
                if (storage.removeProject(Title)) {
                    System.out.println("Remove complete");
                    fileWriter.write('[' + date.toString() + "] Project removed" + '\n');
                }
                else {
                    System.out.println("Remove incomplete");
                    if (storage.isEmptyProjects())
                        fileWriter.write('[' + date.toString() + "] Error (delete project): no projects.\n");
                    else if (storage.getProject(Title) == null)
                        fileWriter.write('[' + date.toString() + "] Error (delete project): No such " +
                                "project exists.\n");
                    else
                        fileWriter.write('[' + date.toString() + "] Error (delete project): Cannot delete " +
                                "current (" + Title + ") project.\n");
                }
                break;
            case 6: //remove task
                System.out.println("Enter theme task's");
                String theme = scanner.nextLine();
                System.out.println("Enter type of task");
                String Type = scanner.nextLine();
                if (storage.removeTask(theme, Type)) {
                    System.out.println("Remove complete");
                    fileWriter.write('[' + date.toString() + "] Task " + theme + " type: " + Type + " removed" + '\n');
                }
                else {
                    fileWriter.write('[' + date.toString() + "] Error (delete task): No such tusk exists.\n");
                    System.out.println("No such tusk exists\nRemove incomplete");
                }
                break;
            case 7: //get list of users
                if (storage.getAllUsers())
                    System.out.println("No users");
                break;
            case 8: //get list of projects
                if(storage.getAllProjects())
                    System.out.println("No projects");
                break;
            case 9: //get list of tasks in project
                System.out.println("Enter project title");
                String tmpTitle = scanner.nextLine();
                if (storage.getProject(tmpTitle) != null)
                    storage.getAllTasksInProject(storage.getProject(tmpTitle));
                else
                    System.out.println("No such project exists");
                break;
            case 10: //get list of user tasks
                if (storage.isEmptyUsers())
                    System.out.println("No users");
                if (storage.isEmptyTasks())
                    System.out.println("No tasks");
                else {
                    System.out.println("Enter username");
                    userName = scanner.nextLine();
                    if (storage.getUser(userName) == null)
                        System.out.println("No such user exists");
                    else
                        storage.getAllUserTasks(userName);
                }
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        Date date = new Date();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Storage storage = new Storage();
        FileWriter fileWriter = new FileWriter("log.txt", true);
        String fileName = "Storage.txt";
        System.out.println("Load storage? y/n");
        char ans = bufferedReader.readLine().charAt(0);
        while (!(ans == 'y' || ans == 'n')) {
            System.out.println("Bad answer, enter correct answer (y/n)");
            ans = bufferedReader.readLine().charAt(0);
        }
        if (ans == 'y') {
            System.out.println("Enter filename");
            fileName = bufferedReader.readLine();
            fileWriter.write('[' + date.toString() + "] Storage loaded from " + fileName + "\n");
            storage.loadStorage(fileName);
        }
        int choice = 1;
        while(choice != 0){
            System.out.println("Commands:\n1 - Add user\n2 - Add project\n3 - Add task\n4 - remove user" +
                    "\n5 - remove project\n6 - remove task\n7 - get list of users\n8 - get list of projects" +
                    "\n9 - get list of tasks in project\n10 - get list of user tasks\n0 - Exit");
            choice = Integer.parseInt(bufferedReader.readLine());
            if (choice < 0 || choice > 10)
                System.out.println("Wrong command");
            else
                choice(choice, storage, fileWriter);
        }
        storage.saveStorage(fileName);
        fileWriter.write('[' + date.toString() + "] Storage saved to " + fileName + "\n");
        fileWriter.close();
    }
}