package managers;

import storage.Storage;
import subjects.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Класс предназначен для добавления, удаления или просмотра объектов пользователь в системе
 */
public class UserManager {
    static Storage storage;
    static Logger logger;
    static {
        storage = Storage.getStorageInstance();
        try (FileInputStream fileInputStream = new FileInputStream("log.config")) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
            logger = Logger.getLogger(UserManager.class.getName());
        } catch (FileNotFoundException ex) {
            System.out.println("log.config not found");
        } catch (IOException ex) {
            System.out.println("Couldn't logging");
        }
    }

    /**
     * Добавляет объект пользователь в систему
     * @param user объект пользователь
     * @return сообщение, как результат добавления пользователя
     */
    public static String addUser(User user){
        if (storage.getUser(user.getUserName()) != null) {
            logger.log(Level.SEVERE, "This username already exists");
            return "This username already exists";
        }
        storage.addUser(user);
        logger.log(Level.INFO, user.toString() + " added");
        return "User " + user.toString() + " added";
    }

    /**
     * Удаляет объект пользователь из системы
     * @param userName логин пользователя
     * @return сообщение, как результат удаления объекта
     */
    public static String removeUser(String userName){
        if (storage.removeUser(userName)) {
            logger.log(Level.INFO, "Remove complete");
            return "Remove complete";
        }
        else {
            if (storage.isEmptyUsers()) {
                logger.log(Level.SEVERE, "Error (delete user): no users.");
                return "Error (delete user): no users.";
            }
            else if (storage.getUser(userName) == null) {
                logger.log(Level.SEVERE, "Error (delete user): no such user exists.");
                return "Error (delete user): no such user exists.";
            }
            else {
                logger.log(Level.SEVERE, "Error (delete user): Cannot delete current user.");
                return "Error (delete user): Cannot delete current user.";
            }
        }
    }

    /**
     * Получение всех пользователей
     * @return String, как список пользователей
     */
    public static String getAllUsers(){
        if (storage.isEmptyUsers())
            return "No users";
        else
            return storage.getAllUsers();
    }

    /**
     * Получение всех задач, назначенных на конкретного пользователя
     * @param userName логин пользователя
     * @return String, как список задач
     */
    public static String getAllUserTasks(String userName){
        if (storage.isEmptyUsers())
            return "No users";
        if (storage.isEmptyTasks())
            return "No tasks";
        else {
            if (storage.getUser(userName) == null)
                return "No such user exists";
            else {
                String userTasks = storage.getAllUserTasks(userName);
                if (userTasks.length() == 0)
                    return "This user has no tasks";
                else
                    return userTasks;
            }
        }
    }
}
