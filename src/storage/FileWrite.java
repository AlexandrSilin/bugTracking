package storage;

import subjects.Project;
import subjects.Task;
import subjects.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * Класс предназначен для сохранения данных в файл
 */
public class FileWrite {
    /**
     * Сохраняет данные системы в файл
     * @param fileName имя файла
     * @return сообщение об ошибке, если сохранение не было призведено
     */
    public static String saveStorage(String fileName) {
        try {
            Storage storage = Storage.getStorageInstance();
            FileWriter fileWriter = new FileWriter(fileName);
            Collection<User> users = storage.getAllUsersSet();
            Set<Task> tasks = storage.getAllTasksSet();
            Collection<Project> projects = storage.getAllProjectsSet();
            fileWriter.write("Users " + users.size() + '\n');
            for (User user : users)
                fileWriter.write(user.getUserName() + '\n' + user.getFirstName() + '\n' + user.getLastName() + '\n');
            fileWriter.write("Projects " + projects.size() + '\n');
            for (Project project : projects)
                fileWriter.write(project.getTitle() + '\n');
            fileWriter.write("Tasks " + tasks.size() + '\n');
            for (Task task : tasks)
                fileWriter.write(task.getTheme() + '\n' + task.getType() + '\n' + task.getPriority() + '\n' +
                        task.getDescription() + '\n' + task.getProject().getTitle() + '\n' + task.getUser().getUserName() + '\n');
            fileWriter.close();
            return null;
        } catch (IOException ex) {
            return "Couldn't saved";
        }
    }
}
