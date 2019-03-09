public class Temp {
    private static void addUser() {
        PanamealUser my_user = new PanamealUser("hao2@cooper.edu", "hao2", "121232342");
        if(my_user.addUser() == 1) {
            System.out.println("Add!");
        }
        else {
            System.out.println("Fail!");
        }
    }

    private static void dropUser() {
        if(PanamealUser.dropUser("hao2@cooper.edu") == 1) {
            System.out.println("Drop!");
        }
        else {
            System.out.println("Fail!");
        }
    }

    public static void main(String[] args){
        addUser();
        //dropUser();
    }
}
