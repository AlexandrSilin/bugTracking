public class Task {
    private String theme;
    private String type;
    private String priority;
    private String description;
    private User user;
    private Project project;

    Task(String theme, String type, String priority, String description){
        this.theme = theme;
        this.type = type;
        this.description = description;
        this.priority = priority;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    public void setProject(Project project){
        this.project = project;
    }

    public Project getProject(){
        return project;
    }

    public String getTheme(){
        return theme;
    }

    public String getType(){
        return type;
    }

    public String getPriority(){
        return priority;
    }

    public String getDescription(){
        return description;
    }

}