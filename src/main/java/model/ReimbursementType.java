package model;

/**
 * @author Zimi Li
 *
 * CREATE TABLE ers_reimbursement_type (
 * 	reime_type_id serial PRIMARY KEY,
 * 	reime_type varchar(10) NOT NULL
 * );
 */
public class ReimbursementType {
    private Integer id;
    private String type;

    public ReimbursementType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
