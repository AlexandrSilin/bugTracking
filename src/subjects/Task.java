package subjects;

/**
 * Класс предназначен для создания объекта задача
 */
public class Task {
    private String theme;
    private String type;
    private String priority;
    private String description;
    private User user;
    private Project project;

    /**
     * Конструктор класса. Задает значения тема, тип, приоритет, описание объекта задача
     * @param theme тема задачи
     * @param type тип задачи
     * @param priority приоритет задачи
     * @param description описание задачи
     */
    public Task(String theme, String type, String priority, String description){
        this.theme = theme;
        this.type = type;
        this.description = description;
        this.priority = priority;
    }

    /**
     * Назначает пользователя к задаче
     * @param user
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     * Получает пользователя, назначенного на задачу
     * @return объект user
     */
    public User getUser(){
        return user;
    }

    /**
     * Добавляет задачу к проекту
     * @param project название приекта
     */
    public void setProject(Project project){
        this.project = project;
    }

    /**
     * Получает проект, в котором находится задача
     * @return объект project
     */
    public Project getProject(){
        return project;
    }

    /**
     * Получает тему задачи
     * @return тема
     */
    public String getTheme(){
        return theme;
    }

    /**
     * Получает тип задачи
     * @return тип
     */
    public String getType(){
        return type;
    }

    /**
     * Получает приоритет задачи
     * @return приоритет
     */
    public String getPriority(){
        return priority;
    }

    /**
     * Получает описание задачи
     * @return описание
     */
    public String getDescription(){
        return description;
    }

}