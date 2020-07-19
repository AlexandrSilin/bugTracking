package managers;

import storage.Storage;
import subjects.Project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Класс предназначен для добавления, удаления или просмотра объектов проект в системе
 */
public class ProjectManager {
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
     *Добавляет объект проект
     * @param project объект проект
     * @return сообщение как результат добавления
     */
    public static String addProject(Project project){
        if (storage.getProject(project.getTitle()) != null) {
            logger.log(Level.SEVERE, "This project already exists");
            return "This project already exists";
        }
        storage.addProject(project);
        logger.log(Level.INFO, "Project " + project.getTitle() + " added");
        return "Project " + project.getTitle() + " added";
    }

    /**
     * Удаляет объект проект
     * @param Title название проекта
     * @return сообщение как результат удаления
     */
    public static String removeProject(String Title){
        if (storage.removeProject(Title)) {
            logger.log(Level.INFO, "Project removed.");
            return "Project removed.";
        }
        else
            if (storage.isEmptyProjects()) {
                logger.log(Level.SEVERE, "Error (delete project): no projects.");
                return "Error (delete project): no projects.";
            }
            else if (storage.getProject(Title) == null) {
                logger.log(Level.SEVERE, "Error (delete project): No such project exists.");
                return "Error (delete project): No such project exists.";
            }
            else {
                logger.log(Level.SEVERE, "Error (delete project): Cannot delete current (" + Title
                        + ") project");
                return "Error (delete project): Cannot delete current (" + Title
                        + ") project";
            }
    }

    /**
     * Получаем все проекты в системе
     * @return названия проектов
     */
    public static String getAllProjects(){
        if(storage.isEmptyProjects())
            return "No projects";
        else
            return storage.getAllProjects();
    }
}
