package model;

/**
 * @author Zimi Li
 *
 * CREATE TABLE ers_users (
 * 	ers_users_id serial PRIMARY KEY,
 * 	ers_username varchar(50) UNIQUE NOT NULL,
 * 	ers_password varchar(50) NOT NULL,
 * 	user_first_name varchar(100) NOT NULL,
 * 	user_last_name varchar(100) NOT NULL,
 * 	user_email varchar(150) UNIQUE NOT NULL,
 * 	user_role_id int REFERENCES ers_user_roles(ers_user_role_id) NOT NULL
 * );
 *
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Integer role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
