package storage;

import managers.ProjectManager;
import managers.TaskManager;
import managers.UserManager;
import subjects.Project;
import subjects.Task;
import subjects.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс предназначен для загрузки данных из файла
 */
public class FileRead {
    /**
     * Загружает данные из файла
     * @param fileName имя файла
     * @return сообщение об ошибке, если загрузка не была произведена
     */
    public static String loadStorage (String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String [] strings = reader.readLine().split(" ");
            for (int i = 0;  i < Integer.valueOf(strings[1]); i++) {
                String userName = reader.readLine();
                String firstName = reader.readLine();
                String lastName = reader.readLine();
                User user = new User(userName, firstName, lastName);
                UserManager.addUser(user);
            }
            strings = reader.readLine().split(" ");
            for (int i = 0; i < Integer.valueOf(strings[1]); i++) {
                Project project = new Project(reader.readLine());
                ProjectManager.addProject(project);
            }
            strings = reader.readLine().split(" ");
            for (int i = 0; i < Integer.valueOf(strings[1]); i++) {
                String theme = reader.readLine();
                String type = reader.readLine();
                String priority = reader.readLine();
                String description = reader.readLine();
                String project = reader.readLine();
                String userName = reader.readLine();
                Task task = new Task(theme, type, priority, description);
                TaskManager.addTask(task, project, userName);
            }
            return null;
        } catch (FileNotFoundException ex) {
            return  "File not found";
        } catch (IOException ex) {
            return "Couldn't loaded";
        }
    }
}
