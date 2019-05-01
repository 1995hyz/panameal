package Application.model;

public class PostRequestForm {
    private String email;
    private Integer amount;
    private Integer begin;

    public PostRequestForm() {}
    public PostRequestForm(String email, Integer amount, Integer begin) {
        this.email = email;
        this.amount = amount;
        this.begin = begin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }
}
