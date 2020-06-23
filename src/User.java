public class User {
    private String userName;
    private String firstName;
    private String lastName;

    User (String userName, String firstName, String lastName){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserName(){
        return userName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
}