package subjects;

/**
 * Класс предназначен для создания объекта проект
 */
public class Project {
    private String title;

    /**
     * Конструктор класса. Задает навзвание обьекту проект
     * @param title название проекта
     */
    public Project(String title) {
        this.title = title;
    }

    /**
     * Получение название объекта проект
     * @return название проекта
     */
    public String getTitle(){
        return title;
    }
}