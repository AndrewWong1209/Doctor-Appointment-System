import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


//brief description: a appointment database constrict by a appointment array
public class AppointDB {
    public Appointment[] AppointDataB;

    public AppointDB(Appointment[] AppointDataB){
        this.AppointDataB = AppointDataB;
    }

    public String toString(){
        String str = "";
        for(int i=0; i<AppointDataB.length; i++){
            str+="  "+AppointDataB[i].toString()+"\n";
        }
        return str;
    }


    //load  Appointment file and convert it into appointment array
    public static Appointment[] loadAppointFile(String AppointFileName) throws Exception{
        File db = new File(AppointFileName);
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

        String[][] token = new String[str.length][3];

        for(int i=0; i<str.length; i++){
            String str2 = "";
            int parameter = 0;
            char oldC = '0';
            for(int j=0; j<str[i].length(); j++){
                char newC = str[i].charAt(j);
                if(j==str[i].length()-1){
                    str2+=newC;
                    token[i][parameter]=str2;
                } else if(newC!=' '){
                    str2+=newC;
                } else if(oldC!=' '&& newC==' '){             //for example "nathan " --> detect "n " then save to the array only, prevent saving double space
                    token[i][parameter]=str2;
                    parameter++;
                    str2="";
                }
                oldC = newC;
            }
        }

        Appointment[] LAF = new Appointment[token.length];
        for(int i=0; i<LAF.length; i++){
            LAF[i] = new Appointment(token[i][0], token[i][1], token[i][2]);
        }
        return LAF;
    }


    // load the new appointment.db and merge the appointments in that into the original appointment array
    public void mergeAppoint(Appointment[] inputAppoint){
        Appointment[] newAppointDB = new Appointment[this.AppointDataB.length+ inputAppoint.length];
        for(int i=0; i<this.AppointDataB.length; i++){
            newAppointDB[i]=this.AppointDataB[i];
        }
        int arrNum = this.AppointDataB.length;
        for(int i=0; i<inputAppoint.length;i++){
            newAppointDB[arrNum]=inputAppoint[i];
            arrNum++;
        }
        this.AppointDataB=newAppointDB;
        System.out.println(inputAppoint.length+" new appointment loaded.");
    }

    public boolean checkCorrectTime(String time){
        boolean correctDay = false;
        boolean correctTime = false;
        if(getDay(time)=='M'||getDay(time)=='T'||getDay(time)=='W'||getDay(time)=='R'||getDay(time)=='F') {
            correctDay=true;
        }

        if(getHour(time)==9||getHour(time)==10||getHour(time)==11||getHour(time)==12||getHour(time)==13||getHour(time)==14||getHour(time)==15||getHour(time)==16||getHour(time)==17||getHour(time)==18){
            correctTime = true;
        }

        if(correctDay&&correctTime){
            return true;
        } else {
            return false;
        }
    }


    // method that return a appointment array which only contain the appointment of specified doctor, while search the patient by the doctor id
    public Appointment[] searchedDoctorAppoint(String doctorid){
        int arSize = 0;
        for(int i=0; i<AppointDataB.length; i++){
            if(AppointDataB[i].doctorID.equals(doctorid)){
                arSize++;
            }
        }
        Appointment[] searchedDoctorAppoint = new Appointment[arSize];
        int arNum=0;
        for(int i=0; i<AppointDataB.length; i++){
            if(AppointDataB[i].doctorID.equals(doctorid)){
                searchedDoctorAppoint[arNum]=AppointDataB[i];
                arNum++;
            }
        }
        return searchedDoctorAppoint;
    }



    // method that return a appointment array which only contain the appointment of specified patient, while search the patient by the patient id
    public Appointment[] searchedPatientAppoint(String patientid){
        int arSize = 0;
        for(int i=0; i<AppointDataB.length; i++){
            if(AppointDataB[i].patientID.equals(patientid)){
                arSize++;
            }
        }
        Appointment[] searchedPatientAppoint = new Appointment[arSize];
        int arNum=0;
        for(int i=0; i<AppointDataB.length; i++){
            if(AppointDataB[i].patientID.equals(patientid)){
                searchedPatientAppoint[arNum]=AppointDataB[i];
                arNum++;
            }
        }
        return searchedPatientAppoint;
    }


    // method that return boolean by check whether there are duplicate appointment in the appointment data base with the input appointment
    public boolean hvSuchAppoint(Appointment[] userAppointment, Appointment inputAppoint){
        boolean hvSuchAppoint = false;
        for(int i=0; i<userAppointment.length; i++){
            if(userAppointment[i].equals(inputAppoint)){
                hvSuchAppoint = true;
                break;
            }
        }
        return hvSuchAppoint;
    }



    // method that return boolean by check whether there are timeCrash between the appointments in a appointment array and the new/input appointment
    // the parameter searchedAppoint mainly is searchPatientAppoint or searchDoctorAppoint, so that to check whether the new input appointment crash with one of the appointment of the patient or doctor
    public boolean timeCrash(Appointment[] searchedAppoint, Appointment newAppointment) {
        boolean timeCrash = false;
        for(int i=0; i< searchedAppoint.length; i++){
            if(searchedAppoint[i].time.equals(newAppointment.time)){
                timeCrash = true;
                break;
            }
        }
        return timeCrash;
    }


    // return char in the time, such as "M" in "M09"
    public char getDay(String time){
        return time.charAt(0);
    }


    // return the int hour in the time, such as "09" in "M09"
    public int getHour(String time){
        int value = 0;
        for(int i=1; i<time.length(); i++) {
            char c = time.charAt(i);
            value = value * 10 + (c-'0');
        }
        return value;
    }


    // add a new appointment (parameter) into the AppointDataB
    public void addAppoint(Appointment newAppointment){
        Appointment[] newAppointmentDB = new Appointment[this.AppointDataB.length + 1];
        for (int i = 0; i < this.AppointDataB.length; i++) {
            newAppointmentDB[i] = this.AppointDataB[i];
        }
        newAppointmentDB[newAppointmentDB.length - 1] = newAppointment;
        this.AppointDataB = newAppointmentDB;
    }



    // delete the specified appointment (parameter) from the AppointDataB
    public void deleteAppoint(Appointment inputAppoint){
        Appointment[] newAppointmentDB = new Appointment[this.AppointDataB.length-1];
        int arrNum=0;
        for(int i=0; i<this.AppointDataB.length; i++){
            if(!this.AppointDataB[i].equals(inputAppoint)){
                newAppointmentDB[arrNum]=this.AppointDataB[i];
                arrNum++;
            }
        }
        this.AppointDataB=newAppointmentDB;
    }



    // delete all appointment related to the specified doctor user which search by the doctor id
    public void delAllDoctorAppoint(String userid){
        int arrSize=0;
        for(int i=0;i<this.AppointDataB.length; i++){
            if(!AppointDataB[i].doctorID.equals(userid)){
                arrSize++;
            }
        }

        Appointment[] newAppointment = new Appointment[arrSize];
        arrSize=0;

        for(int i=0;i<this.AppointDataB.length; i++){
            if(!AppointDataB[i].doctorID.equals(userid)){
                newAppointment[arrSize]=AppointDataB[i];
                arrSize++;
            }
        }

        AppointDataB = newAppointment;
    }


    // delete all appointment related to the specified patient user which search by the patient id
    public void delAllPatientAppoint(String userid){
        int arrSize=0;
        for(int i=0;i<this.AppointDataB.length; i++){
            if(!AppointDataB[i].patientID.equals(userid)){
                arrSize++;
            }
        }

        Appointment[] newAppointment = new Appointment[arrSize];
        arrSize=0;

        for(int i=0;i<this.AppointDataB.length; i++){
            if(!AppointDataB[i].patientID.equals(userid)){
                newAppointment[arrSize]=AppointDataB[i];
                arrSize++;
            }
        }

        AppointDataB = newAppointment;
    }



    // method that show the reminder of the specified doctor
    // parameter inputAppointments mainly is searchDoctorAppoint, so that a appointment list of specified doctor could be input
    public void showDoctorReminder(Appointment[] inputAppointments, UserDB userdatab){
        int appointments = 0;
        for(int i=0; i<inputAppointments.length; i++){
            System.out.println(inputAppointments[i].time+" -- Patient: "+inputAppointments[i].patientID +" ("+userdatab.searchedUser(inputAppointments[i].patientID).fullName+")");
            appointments++;
        }
        System.out.println(appointments+" appointments found.");
    }



    // method that show the reminder of the specified patient
    // parameter inputAppointments mainly is searchPatientAppoint, so that a appointment list of specified patient could be input
    public void showPatientReminder(Appointment[] inputAppointments, UserDB userdatab){
        int appointments = 0;
        for(int i=0; i<inputAppointments.length; i++){
            System.out.println(inputAppointments[i].time+" -- Doctor: "+inputAppointments[i].doctorID +" ("+userdatab.searchedUser(inputAppointments[i].doctorID).fullName+")");
            appointments++;
        }
        System.out.println(appointments+" appointments found.");
    }


    //method that check whether there are duplicate appointment between the new input appointment and the appointments in the AppointDataB
    public boolean dubAppoint(Appointment newAppointment){
        boolean dubAppoint = false;
        for(int i=0; i<this.AppointDataB.length; i++){
            if(this.AppointDataB[i].equals(newAppointment)){
                dubAppoint = true;
                System.out.println("Duplicated appointment: "+newAppointment);
                break;
            }
        }
        return dubAppoint;
    }


    //method that out print the AppointDataB into the output file
    public void saveAppoint(String outFName) throws Exception{
        PrintWriter outPrint = new PrintWriter(outFName);
        for(int i=0; i<this.AppointDataB.length; i++){
            outPrint.println(this.AppointDataB[i]);
        }
        outPrint.close();
    }

}
