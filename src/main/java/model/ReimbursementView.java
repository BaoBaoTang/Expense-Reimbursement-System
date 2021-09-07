package model;

/**
 * @author Zimi Li
 *
 *
 */
public class ReimbursementView {
    private Integer id;
    private Integer amount;
    private String description;
    private String author;
    private String status;
    private String type;

    public ReimbursementView() {
    }

    public ReimbursementView(Integer id, Integer amount, String description, String author, String status, String type) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.author = author;
        this.status = status;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReimbursementView{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
