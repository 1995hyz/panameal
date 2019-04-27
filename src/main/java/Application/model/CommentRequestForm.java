package Application.model;

public class CommentRequestForm {
    private Integer post_id;
    private Integer amount;

    public CommentRequestForm(Integer post_id, Integer amount) {
        this.post_id = post_id;
        this.amount = amount;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
