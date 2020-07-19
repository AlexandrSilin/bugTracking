package subjects;

/**
 * Класс предназначен для создания объекта пользователь
 */
public class User {
    private String userName;
    private String firstName;
    private String lastName;

    /**
     * Конструктор класса. Задает параметры userName, firstName, lastName объекта пользователь
     * @param userName логин
     * @param firstName имя
     * @param lastName фамилия
     */
   public User (String userName, String firstName, String lastName){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Получение userName объекта пользователь
     * @return userName
     */
    public String getUserName(){
        return userName;
    }

    /**
     * Получение firstName объекта пользователь
     * @return firstName
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Получение lastName объекта пользователь
     * @return lastName
     */
    public String getLastName(){
        return lastName;
    }

    @Override
    public String toString(){
        return  "User " + userName + ", first name: " + firstName + ", last name: " + lastName;
    }
}