package model;

/**
 * @author Zimi Li
 *
 * CREATE TABLE ers_reimbursement (
 * 	reime_id serial PRIMARY KEY,
 * 	reime_amout int NOT NULL,
 * 	reime_submitted timestamp NOT NULL,
 * 	reime_resolved timestamp,
 * 	reime_description varchar(200),
 * 	reime_receipt bytea,
 * 	reime_author int REFERENCES ers_users(ers_users_id) NOT NULL,
 * 	reime_resolver int REFERENCES ers_users(ers_users_id),
 * 	reime_status_id int REFERENCES ers_reimbursement_status(reime_status_id) NOT NULL,
 * 	reime_type_id int REFERENCES ers_reimbursement_type(reime_type_id) NOT NULL
 * );
 */
public class Reimbursement {
    private Integer amount;
    private String description;
    private Integer author;
    private Integer type;
    private byte[] receipt;
    private Boolean hasReceipt;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public Boolean getHasReceipt() {
        return hasReceipt;
    }

    public void setHasReceipt(Boolean hasReceipt) {
        this.hasReceipt = hasReceipt;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", type=" + type +
                '}';
    }
}
