package storage;

import subjects.Project;
import subjects.Task;
import subjects.User;

import java.util.*;

/**
 * Класс предназначен для хранения, добавления, удаления и поиска данных в системе
 */
public class Storage {
    private Map<String, User> users = new HashMap<>();
    private Set<Task> tasks = new HashSet<>();
    private Map<String, Project> projects = new HashMap<>();

    private static Storage storageInstance = null;

    private Storage() {
    }

    /**
     * Получение коллеции users
     * @return коллекция users
     */
    protected Collection<User> getAllUsersSet(){
        return users.values();
    }

    /**
     * Получение колееции tasks
     * @return коллекция tasks
     */
    protected Set<Task> getAllTasksSet(){
        return tasks;
    }

    /**
     * Получение коллекции projects
     * @return коллекция projects
     */
    protected Collection<Project> getAllProjectsSet(){
        return projects.values();
    }

    /**
     * Создание нового хранилища
     * @return пустое storage
     */
    public static Storage getStorageInstance() {
        if (storageInstance == null)
            storageInstance = new Storage();
        return storageInstance;
    }

    /**
     * Удаляет объект типа project по совпадению title
     * @param title назване проекта
     * @return true, если удаление прошло успешно
     */
    public boolean removeProject(String title) {
        for (Task task : tasks)
            if (task.getProject().getTitle().equals(title))
                return false;
        if (projects.remove(title) == null)
            return false;
        return true;
    }

    /**
     * Удаляет объект задание по совпадению темы, типа и названию проекта
     * @param theme тема
     * @param type  тип
     * @param title название проекта
     * @return true в случае успешного удаления, иначе false
     */
    public boolean removeTask(String theme, String type, String title) {
        for (Task task : tasks)
            if (task.getTheme().equals(theme) && task.getType().equals(type) &&
                    task.getProject().getTitle().equals(title)) {
                tasks.remove(task);
                return true;
            }
        return false;
    }

    /**
     * Удаляет объект пользователь по совпадению userName
     * @param userName логин
     * @return true в случае успешного удаления, иначе false
     */
    public boolean removeUser(String userName) {
        for (Task task : tasks)
            if (task.getUser().getUserName().equals(userName))
                return false;
        if (users.remove(userName) == null)
            return false;
        return true;
    }

    /**
     * поиск задания по одновременному совпадению темы, типа и названия проекта
     * @param theme        тема
     * @param type         тип
     * @param projectTitle название проекта
     * @return Объект задания, если такое задание существует в системе, иначе null
     */
    public Task getTask(String theme, String type, String projectTitle) {
        for (Task task : tasks)
            if (task.getProject().getTitle().equals(projectTitle) && task.getTheme().equals(theme)
                    && task.getType().equals(type))
                return task;
        return null;
    }

    /**
     * Выводит на экран всех пользователей в системе
     * @return true, если пользователей в системе нет, иначе false
     */
    public String getAllUsers() {
        String out = new String();
        for (User u : users.values())
            out += u.toString() + '\n';
        return out;
    }

    /**
     * Выводит на экран все существующие объекты проект в системе
     * @return true, если нет существубщих объектов проект, инчаче false
     */
    public String getAllProjects() {
        String out = new String();
        for (Project p : projects.values())
            out += p.getTitle() + '\n';
        return out;
    }

    /**
     * Выводит на экран тему, тип  и приоритет задачи в проекте, если у такого проекта есть хотя бы одна задача
     * @param project объект типа project
     */
    public String getAllTasksInProject(Project project) {
        String out = new String();
        for (Task t : tasks)
            if (t.getProject().getTitle().equals(project.getTitle()))
                out += "Theme: " + t.getTheme() + " Type: " + t.getType() +
                        " Priority: " + t.getPriority() + '\n';
        return out;
    }

    /**
     * Выводит на экран все задачи пользователя
     * @param userName логин
     */
    public String getAllUserTasks(String userName) {
        String out = new String();
        for (Task t : tasks)
            if (t.getUser().getUserName().equals(userName))
                out += "Theme: " + t.getTheme() + " Type: " + t.getType() +
                        " Priority: " + t.getPriority() + '\n';
        return out;
    }

    /**
     * Проверка на наличие пользователей в системе
     * @return true, если нет объекта пользователь в системе, иначе false
     */
    public boolean isEmptyUsers() {
        return users.isEmpty();
    }

    /**
     * Проверка на наличие задач в системе
     * @return true, если нет объекта задача в системе, иначе false
     */
    public boolean isEmptyTasks() {
        return tasks.isEmpty();
    }

    /**
     * Проверка на наличие проекта в системе
     * @return true, если нет объекта проект в системе, иначе false
     */
    public boolean isEmptyProjects() {
        return projects.isEmpty();
    }

    /**
     * Добавление объекта пользователь
     * @param user объект пользователь
     */
    public void addUser(User user) {
        users.put(user.getUserName(), user);
    }

    /**
     * Добавление объекта задача
     * @param task объект задача
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * поиск объекта пользователь по совпадению userName
     * @param userName логин
     * @return объект пользователь, если такой существует в системе, иначе null
     */
    public User getUser(String userName) {
        return users.get(userName);
    }

    /**
     * Добавление объека проект
     * @param project объект проект
     */
    public void addProject(Project project) {
        projects.put(project.getTitle(), project);
    }

    /**
     * Поиск объекта проект по названию
     * @param title название проекта
     * @return объект проект, если такой существует в системе, иначе null
     */
    public Project getProject(String title) {
        return projects.get(title);
    }
}
