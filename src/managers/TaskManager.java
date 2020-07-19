package managers;

import storage.Storage;
import subjects.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Класс предназначен для добавления, удаления или просмотра объектов задача в системе
 */
public class TaskManager {
    static Storage storage;
    static Logger logger;
    static {
        storage = Storage.getStorageInstance();
        try (FileInputStream fileInputStream = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
            logger = Logger.getLogger(ProjectManager.class.getName());
        } catch (FileNotFoundException ex) {
            System.out.println("log.config not found");
        } catch (IOException ex) {
            System.out.println("Couldn't logging");
        }
    }

    /**
     * Добавляет объект задача в систему
     * @param task задача
     * @param tmpTitle название проекта
     * @param userName логин пользователя
     * @return Сообщение, как результат добавления задачи
     */
    public static String addTask(Task task, String tmpTitle, String userName) {
        if(storage.isEmptyUsers() || storage.isEmptyProjects())
            return "At least one user and one project are needed to add a task(s)";
        else
            while (!(task.getPriority().equals("Low") || task.getPriority().equals("Mid") ||
                    task.getPriority().equals("High")))
                return "Wrong priority value (priority value may be equals High, Mid or Low";
            if (storage.getTask(task.getTheme(), task.getType(), tmpTitle) != null) {
                logger.log(Level.SEVERE, "This task is already exists");
                return "This task is already exists";
            }
            if (storage.getProject(tmpTitle) != null)
                task.setProject(storage.getProject(tmpTitle));
            else
                if(storage.getProject(tmpTitle) == null)
                    return "No such project exists\nEnter project title";
            if(storage.getUser(userName)!= null) {
                task.setUser(storage.getUser(userName));
                storage.addTask(task);
                logger.log(Level.INFO, "Task " + task.getTheme() + " type: " + task.getType()
                        + " added to project " + tmpTitle);
                return "Task " + task.getTheme() + " type: " + task.getType()
                        + " added to project " + tmpTitle;
            }
            else
                if (storage.getUser(userName) == null)
                    return "No such user exists";
        return null;
    }

    /**
     * Удаление объекта задача из системы
     * @param projTitle название проекта
     * @param theme тема задачи
     * @param Type тип задачи
     * @return сообщение, как результат удаления объекта
     */
    public static String removeTask(String projTitle, String theme, String Type) {
        if (storage.getProject(projTitle) == null)
            return "No such project exists";
        if (storage.removeTask(theme, Type, projTitle)) {
            logger.log(Level.INFO, "Task " + theme + " type: " + Type + " removed");
            return "Task " + theme + " type: " + Type + " removed";
        }
        else {
            logger.log(Level.SEVERE, "Error (delete task): No such tusk exists.");
            return "Error (delete task): No such tusk exists.";
        }
    }

    /**
     * Получение всех задач в проекте
     * @param tmpTitle название проекта
     * @return задачи в виде String
     */
    public static String getTasksInProject(String tmpTitle){
        if (storage.isEmptyTasks())
            return "No tasks";
        String tasks = storage.getAllTasksInProject(storage.getProject(tmpTitle));
        if (storage.getProject(tmpTitle) != null)
            if (tasks.length() == 0)
                return "No tasks in project";
            else
                return tasks;
        else
            return "No such project exists";
    }
}
