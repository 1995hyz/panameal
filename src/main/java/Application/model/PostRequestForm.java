package Application.model;

public class PostRequestForm {
    private String user_id;
    private Integer amount;

    public PostRequestForm() {}
    public PostRequestForm(String user_id, Integer amount) {
        this.user_id = user_id;
        this.amount = amount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
