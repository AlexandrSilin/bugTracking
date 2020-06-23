import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Storage {
    private Set<User> users = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();
    private Set<Project> projects = new HashSet<>();

    public void saveStorage(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write("Users " + users.size() + '\n');
        for (User user : users)
            fileWriter.write(user.getUserName() + ' ' + user.getFirstName() + ' ' + user.getLastName() + '\n');
        fileWriter.write("Projects " + projects.size() + '\n');
        for (Project project : projects)
            fileWriter.write(project.getTitle() + '\n');
        fileWriter.write("Tasks " + tasks.size() + '\n');
        for (Task task : tasks)
            fileWriter.write(task.getTheme() + ' ' + task.getType() + ' ' + task.getPriority() + ' ' +
                    task.getDescription() + ' ' + task.getProject().getTitle() + ' ' + task.getUser().getUserName() + '\n');
        fileWriter.close();
    }

    public void loadStorage(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String string = reader.readLine();
        String [] subStr;
        int tmp = Character.getNumericValue(string.charAt(string.length() - 1));
        for (int i = 0; i < tmp; i++){
            string = reader.readLine();
            subStr = string.split(" ");
            User user = new User(subStr[0], subStr[1], subStr[2]);
            users.add(user);
        }
        string = reader.readLine();
        tmp = Character.getNumericValue(string.charAt(string.length() - 1));
        for (int i = 0; i < tmp; i++){
            string = reader.readLine();
            Project project = new Project(string);
            projects.add(project);
        }
        string = reader.readLine();
        tmp = Character.getNumericValue(string.charAt(string.length() - 1));
        for (int i = 0; i < tmp; i++){
            string = reader.readLine();
            subStr = string.split(" ");
            Task task = new Task(subStr[0], subStr[1], subStr[2], subStr[3]);
            Project project = getProject(subStr[4]);
            task.setProject(project);
            User user = getUser(subStr[5]);
            task.setUser(user);
            tasks.add(task);
        }
    }

    public boolean removeProject(String title){
        for (Task task : tasks)
            if (task.getProject().getTitle().equals(title)){
                System.out.println("Cannot delete current project");
                return false;
            }
        for (Project project : projects)
            if (project.getTitle().equals(title)){
                projects.remove(project);
                return true;
            }
        System.out.println("No such project exists");
        return false;
    }

    public boolean removeTask(String theme, String type){
        for (Task task : tasks)
            if (task.getTheme().equals(theme) && task.getType().equals(type)) {
                tasks.remove(task);
                return true;
            }
        return false;
    }

    public boolean removeUser (String userName){
        for (Task task : tasks)
            if (task.getUser().getUserName().equals(userName)){
                System.out.println("Cannot delete current user");
                return false;
            }
        for (User user : users)
            if (user.getUserName().equals(userName)) {
                users.remove(user);
                return true;
            }
        System.out.println("No such user exists");
        return false;
    }

    public User getUser(String userName){
        for (User u : users)
            if (u.getUserName().equals(userName))
                return u;
        return null;
    }

    public Task getTask(String theme, String type, String projectTitle){
        for (Task task : tasks)
            if (task.getProject().getTitle().equals(projectTitle) && task.getTheme().equals(theme)
                    && task.getType().equals(type))
                return task;
        return null;
    }

    public boolean getAllUsers(){
        if(users.isEmpty())
            return true;
        else
           for (User u : users)
               System.out.println("Username: " + u.getUserName() + " First name: " + u.getFirstName() +
                       " Last name: " + u.getLastName());
        return false;
    }

    public Project getProject(String title){
        for (Project project : projects)
            if (project.getTitle().equals(title))
                return project;
        return null;
    }

    public boolean getAllProjects(){
        if (projects.isEmpty())
            return true;
        else
            for (Project p : projects)
                System.out.println(p.getTitle());
        return false;
    }

    public void getAllTasksInProject(Project project){
        if (tasks.isEmpty())
            System.out.println("No tasks");
        else
            for (Task t : tasks)
                if (t.getProject().getTitle().equals(project.getTitle()))
                    System.out.println("Theme: " + t.getTheme() + " Type: " + t.getType() +
                            " Priority: " + t.getPriority());
    }

    public void getAllUserTasks(String userName){
        boolean out = false;
        for (Task t : tasks)
            if (t.getUser().getUserName().equals(userName)) {
                System.out.println("Theme: " + t.getTheme() + " Type: " + t.getType() +
                        " Priority: " + t.getPriority());
                out = true;
            }
        if (!out)
            System.out.println("This user has no task(s)");
    }

    public boolean isEmptyUsers(){
        return users.isEmpty();
    }

    public boolean isEmptyTasks(){
        return tasks.isEmpty();
    }

    public boolean isEmptyProjects(){
        return projects.isEmpty();
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void addProject(Project project){
        projects.add(project);
    }
}
