package model;

/**
 * @author Zimi Li
 *
 * CREATE TABLE ers_user_roles(
 * 	ers_user_role_id serial PRIMARY KEY,
 * 	user_role varchar(20) NOT NULL
 * );
 */
public class Role {
    private Integer id;
    private String name;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
