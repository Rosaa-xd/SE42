package api;

/**
 * Created by Shadowphoenix on 19/12/2017
 */
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Boolean goldCard;
    private Integer score;

    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.goldCard = false;
        this.score = 0;
    }

    public User(Integer id, String firstName, String lastName, String password, String email, Boolean goldCard, Integer score) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.goldCard = goldCard;
        this.score = 0;
    }
}
