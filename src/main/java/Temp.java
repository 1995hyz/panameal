public class Temp {
    public static void main(String[] args){
        PanamealUser my_user = new PanamealUser("hao2@cooper.edu", "hao2", "121232342");
        if(my_user.addUser() == 1) {
            System.out.println("Add!");
        }
        else {
            System.out.println("Fail!");
        }
    }
}
