package model;

import java.util.Arrays;

/**
 * @author Zimi Li
 */
public class ReimbursementDetailView {
    private Integer id;
    private Integer amount;
    private String submitted;
    private String resolved;
    private String description;
    private byte[] receipt;
    private String author;
    private String resolver;
    private String status;
    private String type;
    private Integer authorID;

    public ReimbursementDetailView(Integer id, Integer amount, String submitted, String resolved, String description, byte[] receipt, String author, String resolver, String status, String type, Integer authorID) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.status = status;
        this.type = type;
        this.authorID = authorID;
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

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
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

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    @Override
    public String toString() {
        return "ReimbursementDetailView{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", description='" + description + '\'' +
                ", receipt=" + Arrays.toString(receipt) +
                ", author='" + author + '\'' +
                ", resolver='" + resolver + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", authorID=" + authorID +
                '}';
    }
}
