import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

//brief description: a user database constricted by a user array.
public class UserDB {
    public User[] UserDataB;

    public UserDB(User[] UserDataB){
        this.UserDataB = UserDataB;
    }

    public String toString(){
        String str = "";
        for(int i=0; i<UserDataB.length; i++){
            str+="  "+UserDataB[i].toString()+"\n";
        }
        return str;
    }

    // load the user file into a 2D string array and then put it into the a User object array
    // and return it.
    public static User[] loadUserFile(String UserFileName) throws Exception{
        File db = new File(UserFileName);
        Scanner sc1 = new Scanner(db);
        Scanner sc2 = new Scanner(db);

        int strSize = 0;
        while(sc1.hasNextLine()) {
            strSize ++;
            String count = sc1.nextLine();
        }
        sc1.close();

        String[] str = new String[strSize];
        for(int i=0; sc2.hasNextLine(); i++) {
            str[i] = sc2.nextLine();
        }
        sc2.close();

        String[][] token = new String[str.length][4];

        for(int i=0; i<str.length; i++){
            String str2 = "";
            int parameter = 0;
            char oldC = '0';
            for(int j=0; j<str[i].length(); j++){
                char newC = str[i].charAt(j);
                if(parameter == 3){                        //if parameter = 3, which mean is time to load the full name, it is set
                    str2+=newC;                               // to load everything include the space into a string
                    if(j==str[i].length()-1){
                        token[i][parameter]=str2;
                        parameter=0;
                    }
                }else if(newC!=' '){
                    str2+=newC;
                } else if(oldC!=' '&& newC==' '){             //for example "jckyau " --> detect "u " then save to the array only, prevent saving double space
                    token[i][parameter]=str2;
                    parameter++;
                    str2="";
                    if(parameter==3){
                        j++;
                    }
                }
                oldC = newC;
            }
        }

        int lufLength = 0;
        for(int i=0; i<token.length; i++){
            if(token[i].length==4){
                lufLength++;
            }
        }
        User[] LUF = new User[lufLength];
        lufLength=0;
        for(int i=0; i< token.length; i++){
            if(token[i].length==4){
                LUF[lufLength]=new User(token[i][0], token[i][1], token[i][2], token[i][3]);
                lufLength++;
            }
        }
        return LUF;
    }


    // method that load the new user data base file and convert the content into 2D string array
    public static String[][] loadNewUserDBStr(String UserFileName) throws Exception{
        File db = new File(UserFileName);
        Scanner sc1 = new Scanner(db);
        Scanner sc2 = new Scanner(db);

        int strSize = 0;
        while(sc1.hasNextLine()) {
            strSize ++;
            String count = sc1.nextLine();
        }
        sc1.close();

        String[] str = new String[strSize];
        for(int i=0; sc2.hasNextLine(); i++) {
            str[i] = sc2.nextLine();
        }
        sc2.close();

        String[][] token = new String[str.length][4];

        for(int i=0; i<str.length; i++){
            String str2 = "";
            int parameter = 0;
            char oldC = '0';
            for(int j=0; j<str[i].length(); j++){
                char newC = str[i].charAt(j);
                if(parameter == 3){                        //if parameter = 3, which mean is time to load the full name, it is set
                    str2+=newC;                               // to load everything include the space into a string
                    if(j==str[i].length()-1){
                        token[i][parameter]=str2;
                        parameter=0;
                    }
                }else if(newC!=' '){
                    str2+=newC;
                } else if(oldC!=' '&& newC==' '){             //for example "jckyau " --> detect "u " then save to the array only, prevent saving double space
                    token[i][parameter]=str2;
                    parameter++;
                    str2="";
                    if(parameter==3){
                        j++;
                    }
                }
                oldC = newC;
            }
        }
        return token;
    }

    // merge the new user database (in 2D string array format) into the original user database array by do them one by one so that to check whether duplication of user happen
    public void mergeUser(String[][] token, String userFileName){
        int loadNum = 0;
        for (int i = 0; i < token.length; i++) {
            if (token[i].length == 4 && token[0]!=null && token[1]!=null&& token[2]!=null&& token[3]!=null) {
                User newUser = new User(token[i][0], token[i][1], token[i][2], token[i][3]);
                if (newUser.userID != null && newUser.password != null && newUser.userType != null && newUser.fullName != null) {
                    if (!this.addUser(newUser)) {
                        System.out.println("error adding record on line " + (i + 1) + " of " + userFileName + " Duplicated user record (" + newUser.userID + ")");
                    } else {
                        loadNum++;
                    }
                }
            }
        }
        System.out.println(loadNum + " user records loaded");
    }


    // a method which return boolean by checking whether the UserDataB array have the user id (parameter), if yes return true.
    public boolean hvUser(String command){
        boolean correctUser = false;
        for(int i=0; i<this.UserDataB.length; i++){
            if(command.equals(this.UserDataB[i].userID)){
                correctUser = true;
                break;
            } else {
                correctUser = false;
            }
        }
        return correctUser;
    }



    // a method which return boolean by checking whether the admin id (parameter) exist in the administrators of UserDataB
    public boolean hvSuchAdmin(String adminid){
        //set up a array contain only administrator from the UserDataB
        int arrSize=0;
        for(int i=0; i<UserDataB.length; i++){
            if(UserDataB[i].userType.equals("a")){
                arrSize++;
            }
        }
        User[] adminAr = new User[arrSize];
        int arrNum = 0;
        for(int i=0; i<UserDataB.length; i++) {
            if (UserDataB[i].userType.equals("a")) {
                adminAr[arrNum] = UserDataB[i];
                arrNum++;
            }
        }
        ///////////////////////////////////////////
        boolean hvSuchAdmin=false;
        for(int i=0; i<adminAr.length; i++){
            if(adminAr[i].userID.equals(adminid)){
                hvSuchAdmin=true;
                break;
            }
        }
        if(!hvSuchAdmin){
            System.out.println("no such doctor: "+adminid);
        }
        return hvSuchAdmin;
    }



    // a method which return boolean by checking whether the doctor id (parameter) exist in the doctors of UserDataB
    public boolean hvSuchDoctor(String doctorid){
        //set up a array contain only doctors from the UserDataB
        int arrSize=0;
        for(int i=0; i<UserDataB.length; i++){
            if(UserDataB[i].userType.equals("d")){
                arrSize++;
            }
        }
        User[] doctorAr = new User[arrSize];
        int arrNum = 0;
        for(int i=0; i<UserDataB.length; i++){
            if(UserDataB[i].userType.equals("d")){
                doctorAr[arrNum]=UserDataB[i];
                arrNum++;
            }
        }
        ////////////////////////////////////////////////////

        boolean hvSuchDoctor=false;
        for(int i=0; i<doctorAr.length; i++){
            if(doctorAr[i].userID.equals(doctorid)){
                hvSuchDoctor=true;
                break;
            }
        }

        if(!hvSuchDoctor){
            System.out.println("no such doctor: "+doctorid);
        }
        return hvSuchDoctor;
    }



    // a method which return boolean by checking whether the patient id (parameter) exist in the patients of UserDataB
    public boolean hvSuchPatient(String patientid){
        //set up a array contain only patients from the UserDataB
        int arrSize=0;
        for(int i=0; i<UserDataB.length; i++){
            if(UserDataB[i].userType.equals("p")){
                arrSize++;
            }
        }
        User[] patientAr = new User[arrSize];
        int arrNum = 0;
        for(int i=0; i<UserDataB.length; i++){
            if(UserDataB[i].userType.equals("p")){
                patientAr[arrNum]=UserDataB[i];
                arrNum++;
            }
        }
        ////////////////////////////////////////////////////////////////
        boolean hvSuchPatient=false;
        for(int i=0; i<patientAr.length; i++){
            if(patientAr[i].userID.equals(patientid)){
                hvSuchPatient=true;
                break;
            }
        }
        if(!hvSuchPatient){
            System.out.println("no such patient: "+patientid);
        }
        return hvSuchPatient;
    }




    // method that return a user by searching from the UserDataB with the user id (parameter)
    public User searchedUser(String userid){
        User searchedUser = new User();
        for(int i=0; i<this.UserDataB.length; i++){
            if(this.UserDataB[i].userID.equals(userid)){
                searchedUser = this.UserDataB[i];
                break;
            }
        }
        return searchedUser;
    }




    // method return boolean plus adding new user (parameter) into the UserDataB array
    public boolean addUser(User newUser){
        User[] newUserDataB = new User[UserDataB.length+1];

        for(int i=0; i<this.UserDataB.length; i++){
            if(this.UserDataB[i].equals(newUser)){
                return false;
            }
        }

        for(int i=0; i<this.UserDataB.length; i++){
            newUserDataB[i]=this.UserDataB[i];
        }
        newUserDataB[newUserDataB.length-1]=newUser;
        this.UserDataB=newUserDataB;
        return true;
    }

    // method that delete the user specified by the user id from the UserDataB array
    public void delUser(String userid){
        User[] newUserDataB = new User[UserDataB.length-1];
        int arrNum = 0;

        for(int i=0; i<this.UserDataB.length; i++){
            if(!UserDataB[i].userID.equals(userid)){
                newUserDataB[arrNum]=UserDataB[i];
                arrNum++;
            }
        }
        UserDataB = newUserDataB;
    }

    // method that out print the user data base to a output file
    public void saveUser(String outFName) throws Exception{
        PrintWriter outPrint = new PrintWriter(outFName);
        for(int i=0; i<this.UserDataB.length; i++){
            outPrint.println(this.UserDataB[i]);
        }
        outPrint.close();
    }
}
