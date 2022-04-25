
// Brief description: a user class which construct by userID, password, userType and full name
public class User {
    public String userID;
    public String password;
    public String userType;
    public String fullName;

    public User(String userID, String password, String userType, String fullName){
        this.userID = userID;
        this.password = password;
        this.userType = userType;
        this.fullName = fullName;
    }

    public User(){
        this("", "", "", "");
    }

    public boolean equals(User inputUser){
        return this.userID.equals(inputUser.userID);
    }

    public String toString(){
        return  userID+ "    "+ password+"     "+userType+"    "+fullName;
    }
}
