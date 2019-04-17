package Application.model;

public class PostRequestForm {
    private String email;
    private Integer amount;

    public PostRequestForm() {}
    public PostRequestForm(String email, Integer amount) {
        this.email = email;
        this.amount = amount;
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
}
