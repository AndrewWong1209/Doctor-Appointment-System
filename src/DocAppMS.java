import java.util.Scanner;


//(1) This program is support by a user class, user data base class, appointment class, appointment data base class, a timeslot class and a considerable amount of if statement and for loop
//    The program wil first activate by the argument input to load the user.db and appointment.db file
//    Then it will build a user data base (UserDB) object userDataBase and a appointment data base (AppointDB) object appointmentDataBase convert from the user file and appointment file
//    Then it will let user to input command and according to the command content to perform different instruction, which is support by a lot of if statement

//(2) Wong Shing Him

//(3) 19223706

public class DocAppMS {
    public static void main(String[] args) throws Exception{
        new DocAppMS().runApp(args);
    }

    void runApp(String[] args)throws Exception{
        String UserFileName = args[0];
        String AppointFileName = args[1];

        UserDB userDataBase = new UserDB(UserDB.loadUserFile(UserFileName));
        AppointDB appointmentDataBase = new AppointDB(AppointDB.loadAppointFile(AppointFileName));

        welcomePage(UserFileName, AppointFileName, userDataBase.UserDataB.length, appointmentDataBase.AppointDataB.length);

        boolean login = false;

        String[] command = new String[0];

        User loginUser = new User();

        while(true){
            // only user input user id which exist in the user data base with correct password, the login boolean will turn to true, otherwise it will loop
            while(!login){
                LoginCover();
                command = command();
                if(command.length==2 && command[0].equals("login")){
                    if(userDataBase.hvUser(command[1])){
                        loginUser = specificUser(command, userDataBase);
                        if(correctPassword(loginUser)){
                            login = true;
                            System.out.println("\nLogin success! Welcome to Doctor Appointment Management System.");
                        } else {
                            System.out.println("\nInvalid password");
                        }
                    } else {
                        System.out.println("\ninvalid User ID");
                    }
                } else {
                    System.out.println("\nUnknown Command: "+ command[0]);
                    login = false;
                }
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // doctor part
            if(loginUser.userType.equals("d")){
                command = command();

                // doctor: add
                if(command[0].equals("add")) {
                    if (command.length == 3 && userDataBase.hvUser(command[1]) && appointmentDataBase.checkCorrectTime(command[2])) {
                        if (userDataBase.hvSuchPatient(command[1])) {
                            Appointment newAppointment = new Appointment(loginUser.userID, command[1], command[2]);
                            if (appointmentDataBase.timeCrash(appointmentDataBase.searchedDoctorAppoint(loginUser.userID), newAppointment)) {
                                System.out.println("Doctor appointment time clash (" + loginUser.fullName + ": " + newAppointment.time + ")");
                            } else if (appointmentDataBase.timeCrash(appointmentDataBase.searchedPatientAppoint(command[1]), newAppointment)) {
                                System.out.println("Patient appointment time clash (" + command[1] + ": " + newAppointment.time + ")");
                            } else {
                                appointmentDataBase.addAppoint(newAppointment);
                                System.out.println("Adding new appointment, (" + newAppointment.doctorID + ", " + newAppointment.patientID + ", " + newAppointment.time + "): done!");
                            }
                        } else {
                            System.out.println("No such patient: " + command[1]);
                        }
                    } else {
                        System.out.println("Invalid Add argument.");
                    }
                } else if

                // doctor: delete
                  (command[0].equals("delete")){
                    if (command.length ==3 && userDataBase.hvUser(command[1]) && appointmentDataBase.checkCorrectTime(command[2])){
                        Appointment inputAppointment = new Appointment(loginUser.userID, command[1], command[2]);
                        if(appointmentDataBase.hvSuchAppoint(appointmentDataBase.searchedDoctorAppoint(loginUser.userID), inputAppointment)){
                            appointmentDataBase.deleteAppoint(inputAppointment);
                            System.out.println("Deleting appointment, ("+inputAppointment.doctorID+ ", " + inputAppointment.patientID + ", " + inputAppointment.time + "): done!");
                        }else{
                            System.out.println("No such appointment (" +inputAppointment.doctorID+", "+inputAppointment.patientID+", "+inputAppointment.time+")");
                        }
                    }else{
                        System.out.println("Invalid Delete argument.");
                    }
                } else if

                //doctor: show
                (command[0].equals("show")){

                    //reminder
                    if(command.length==2 && command[1].equals("reminder")){
                        System.out.println(loginUser.fullName +" ("+loginUser.userID+") is a doctor, and has following appointment.");
                        appointmentDataBase.showDoctorReminder(appointmentDataBase.searchedDoctorAppoint(loginUser.userID), userDataBase);
                    } else if

                    //timetable
                    (command.length==2 && command[1].equals(("timetable"))){
                        timeslot timetable = new timeslot(appointmentDataBase.searchedDoctorAppoint(loginUser.userID));
                        timetable.doctorStrToTime();
                    } else {
                        System.out.println("Invalid show argument.");
                    }
                } else if


                //doctor: help
                (command[0].equals("help")){
                    if(command.length==1) {
                        System.out.println("User: " + loginUser.userID + "  *** Doctor ***\n");
                        System.out.println("Available commands:");
                        System.out.println("  show   [ reminder | timetable ]");
                        System.out.println("  add    appointment");
                        System.out.println("  delete appointment");
                        System.out.println("  help   [ command ]");
                        System.out.println("  logout");
                    }  else {System.out.println("Invalid help argument");}
                } else if

                //doctor: logout
                (command[0].equals("logout")){
                    if(command.length==1){
                        login=false;
                    }
                }

                else {
                    System.out.println("Unknown Command");
                }



            } else if

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // patient part
            (loginUser.userType.equals("p")){
                command = command();

                //patient: add
                if(command[0].equals("add")){
                    if(command.length==3 && userDataBase.hvUser(command[1]) && appointmentDataBase.checkCorrectTime(command[2])) {
                        if (userDataBase.hvSuchDoctor(command[1])) {
                            Appointment newAppointment = new Appointment(command[1], loginUser.userID, command[2]);
                            if (appointmentDataBase.timeCrash(appointmentDataBase.searchedPatientAppoint(loginUser.userID), newAppointment)) {
                                System.out.println("Patient appointment time clash (" + loginUser.fullName + ": " + newAppointment.time + ")");
                            } else if (appointmentDataBase.timeCrash(appointmentDataBase.searchedDoctorAppoint(command[1]), newAppointment)) {
                                System.out.println("Doctor appointment time clash (" + command[1] + ": " + newAppointment.time + ")");
                            } else {
                                appointmentDataBase.addAppoint(newAppointment);
                                System.out.println("Adding new appointment, (" + newAppointment.doctorID + ", " + newAppointment.patientID + ", " + newAppointment.time + "): done!");
                            }
                        }
                    } else {
                        System.out.println("Invalid Add argument.");
                    }
                } else if


                //patient: delete
                (command[0].equals("delete")){
                    if (command.length ==3 && userDataBase.hvUser(command[1]) && appointmentDataBase.checkCorrectTime(command[2])){
                        Appointment inputAppointment = new Appointment(command[1], loginUser.userID, command[2]);
                        if(appointmentDataBase.hvSuchAppoint(appointmentDataBase.searchedPatientAppoint(loginUser.userID), inputAppointment)){
                            appointmentDataBase.deleteAppoint(inputAppointment);
                            System.out.println("Deleting appointment, ("+inputAppointment.doctorID+ ", " + inputAppointment.patientID + ", " + inputAppointment.time + "): done!");
                        }else{
                            System.out.println("No such appointment (" +inputAppointment.doctorID+", "+inputAppointment.patientID+", "+inputAppointment.time+")");
                        }
                    }else{
                        System.out.println("Invalid Delete argument.");
                    }
                }else if

                 // patient: show
                (command[0].equals("show")){

                    //reminder
                    if(command.length==2 && command[1].equals("reminder")){
                        System.out.println(loginUser.fullName +" ("+loginUser.userID+") is a patient, and has following appointment.");
                        appointmentDataBase.showPatientReminder(appointmentDataBase.searchedPatientAppoint(loginUser.userID), userDataBase);
                    } else if

                    //timetable
                    (command.length==2 && command[1].equals(("timetable"))){
                        timeslot timetable = new timeslot(appointmentDataBase.searchedPatientAppoint(loginUser.userID));
                        timetable.PatientStrToTime();
                    } else { System.out.println("Invalid show argument.");}
                }else if


                    //patient: help
                (command[0].equals("help")){
                    if(command.length==1) {
                        System.out.println("User: " + loginUser.userID + "  *** Patient ***\n");
                        System.out.println("Available commands:");
                        System.out.println("  show   [ reminder | timetable ]");
                        System.out.println("  add    appointment");
                        System.out.println("  delete appointment");
                        System.out.println("  help   [ command ]");
                        System.out.println("  logout");
                    }  else {System.out.println("Invalid help argument");}
                } else if

                //patient: logout
                (command[0].equals("logout")){
                    if(command.length==1){
                        login=false;
                    }
                }

                else {
                    System.out.println("Unknown Command");
                }
            } else if
             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

             // administrator part
            (loginUser.userType.equals("a")){
                command = command();

                //admin: list
                if(command[0].equals("list")){
                    if(command.length==2 && command[1].equals("user")){
                        System.out.println(userDataBase);
                        System.out.println(userDataBase.UserDataB.length+" users in found.");
                    } else if(command.length==2 && command[1].equals("appointment")){
                        System.out.println(appointmentDataBase);
                        System.out.println(appointmentDataBase.AppointDataB.length+" appointments in found. ");
                    } else {
                        System.out.println("Invalid list argument. ");
                    }
                } else if


                //admin: add
                (command[0].equals("add")){

                    //add admin
                    if(command.length==5 && command[1].equals("admin")){
                        User newUser = new User(command[2], command[3], "a", command[4]);
                        if(userDataBase.addUser(newUser)){
                            System.out.println("Adding new user, "+newUser.fullName+" ("+newUser.userID+") as admin: done!");
                        } else {
                            System.out.println("Adding new user, "+newUser.fullName+" ("+newUser.userID+") as admin: fail!");
                            System.out.println("Duplicated user record ("+newUser.userID+")");
                        }
                    } else if

                    //add doctor
                    (command.length==5 && command[1].equals("doctor")){
                        User newUser = new User(command[2], command[3], "d", command[4]);
                        if(userDataBase.addUser(newUser)){
                            System.out.println("Adding new user, "+newUser.fullName+" ("+newUser.userID+") as doctor: done!");
                        } else {
                            System.out.println("Adding new user, "+newUser.fullName+" ("+newUser.userID+") as doctor: fail!");
                            System.out.println("Duplicated user record ("+newUser.userID+")");
                        }
                    } else if

                    //add patient
                    (command.length==5 && command[1].equals("patient")){
                        User newUser = new User(command[2], command[3], "p", command[4]);
                        if(userDataBase.addUser(newUser)){
                            System.out.println("Adding new user, "+newUser.fullName+" ("+newUser.userID+") as patient: done!");
                        } else {
                            System.out.println("Adding new user, "+newUser.fullName+" ("+newUser.userID+") as patient: fail!");
                            System.out.println("Duplicated user record ("+newUser.userID+")");
                        }
                    } else if

                    // add appointment
                    (command.length==5 && command[1].equals("appointment")){
                        Appointment newAppointment = new Appointment(command[2], command[3], command[4]);
                        if(userDataBase.hvSuchDoctor(command[2]) && userDataBase.hvSuchPatient(command[3]) && newAppointment.checkCorrectTime(newAppointment.time)){
                            if (appointmentDataBase.timeCrash(appointmentDataBase.searchedDoctorAppoint(newAppointment.doctorID), newAppointment)) {
                                System.out.println("Doctor appointment time clash (" + newAppointment.doctorID + ": " + newAppointment.time + ")");
                                System.out.println("Adding new appointment ("+newAppointment+"): fail!");
                            } else if (appointmentDataBase.timeCrash(appointmentDataBase.searchedPatientAppoint(newAppointment.patientID), newAppointment)) {
                                System.out.println("Patient appointment time clash (" + newAppointment.patientID + ": " + newAppointment.time + ")");
                                System.out.println("Adding new appointment ("+newAppointment+"): fail!");
                            }else if(!appointmentDataBase.dubAppoint(newAppointment)){
                                appointmentDataBase.addAppoint(newAppointment);
                                System.out.println("Adding new appointment ("+newAppointment+"): done!");
                            } else {System.out.println("Adding new appointment ("+newAppointment+"): fail!");}
                        }else {System.out.println("Adding new appointment ("+newAppointment+"): fail!");}
                    } else {System.out.println("Invalid add argument.");}
                } else if

                //admin: delete
                (command[0].equals("delete")){

                    //delete admin
                    if(command.length==3 && command[1].equals("admin")){
                        if(!userDataBase.hvSuchAdmin(command[2])){
                            System.out.println("Delete admin ("+command[2]+")...fail!");
                            System.out.println("No such admin: "+command[2]);
                        } else {
                            userDataBase.delUser(command[2]);
                            System.out.println("Delete admin ("+command[2]+")...done!");
                        }
                    } else if

                    //delete doctor
                    (command.length==3 && command[1].equals("doctor")){
                        if(!userDataBase.hvSuchDoctor(command[2])){
                            System.out.println("Delete doctor ("+command[2]+")...fail!");
                        } else {
                            appointmentDataBase.delAllDoctorAppoint(command[2]);
                            userDataBase.delUser(command[2]);
                            System.out.println("Delete doctor ("+command[2]+")...done!");
                        }
                    }

                    //delete patient
                    else if(command.length==3 && command[1].equals("patient")){
                        if(!userDataBase.hvSuchPatient(command[2])){
                            System.out.println("Delete patient ("+command[2]+")...fail!");
                        } else {
                            appointmentDataBase.delAllPatientAppoint(command[2]);
                            userDataBase.delUser(command[2]);
                            System.out.println("Delete patient ("+command[2]+")...done!");
                        }
                    } else if

                    //delete appointment
                    (command.length==5 && command[1].equals("appointment")){
                        Appointment targetAppoint = new Appointment(command[2], command[3], command[4]);
                        if(appointmentDataBase.hvSuchAppoint(appointmentDataBase.AppointDataB, targetAppoint)){
                            appointmentDataBase.deleteAppoint(targetAppoint);
                            System.out.println("Deleting appointment ("+targetAppoint+")...done!");
                        } else {
                            System.out.println("Deleting appointment ("+targetAppoint+")...fail!");
                            System.out.println("No such appointment: ("+targetAppoint+")");
                        }
                    } else {
                        System.out.println("Invalid delete argument.");
                    }
                } else if

                //admin: show
                (command[0].equals("show")){

                    //show timetable
                    if(command.length==3 && command[1].equals("timetable")){
                        if(userDataBase.hvUser(command[2])){
                            if(userDataBase.hvSuchDoctor(command[2])){
                                timeslot timetable = new timeslot(appointmentDataBase.searchedDoctorAppoint(command[2]));
                                timetable.doctorStrToTime();
                            } else if(userDataBase.hvSuchPatient(command[2])){
                                timeslot timetable = new timeslot(appointmentDataBase.searchedPatientAppoint(command[2]));
                                timetable.PatientStrToTime();
                            }
                        } else {
                            System.out.println("No such User: "+command[2]);
                        }
                    } else if

                    //show reminder
                    (command.length==3 && command[1].equals("reminder")){
                        if(userDataBase.hvUser(command[2])){
                            if(userDataBase.hvSuchDoctor(command[2])){
                                System.out.println(command[2]+" is a doctor, and has following appointment.");
                                appointmentDataBase.showDoctorReminder(appointmentDataBase.searchedDoctorAppoint(command[2]), userDataBase);
                            } else if(userDataBase.hvSuchPatient(command[2])){
                                System.out.println(command[2]+" is a patient, and has following appointment.");
                                appointmentDataBase.showPatientReminder(appointmentDataBase.searchedPatientAppoint(command[2]), userDataBase);
                            }
                        } else {
                            System.out.println("No such User: "+command[2]);
                        }
                    }
                } else if

                //admin: load
                (command[0].equals("load")){
                    if(command.length==3 && command[1].equals("user")){
                        String[][] newLoadUserDB = UserDB.loadNewUserDBStr(command[2]);
                        userDataBase.mergeUser(newLoadUserDB, command[2]);
                    } else if (command.length==3 && command[1].equals("appointment")){
                        appointmentDataBase.mergeAppoint(AppointDB.loadAppointFile(command[2]));
                    } else {
                        System.out.println("Invalid load argument.");
                    }
                }else if

                //admin: save
                (command[0].equals("save")){
                    if(command.length==3 && command[1].equals("user")){
                        userDataBase.saveUser(command[2]);
                        System.out.println(userDataBase.UserDataB.length+" users records save to "+command[2]+".");
                    } else if(command.length==3 && command[1].equals("appointment")){
                        appointmentDataBase.saveAppoint(command[2]);
                        System.out.println(appointmentDataBase.AppointDataB.length+" appointments records save to "+command[2]+".");
                    } else {
                        System.out.println("Invalid save arguments.");
                    }
                } else if

                //admin: help
                (command[0].equals("help")){
                    if(command.length==1) {
                        System.out.println("User: " + loginUser.userID + "  *** ADMIN ***\n");
                        System.out.println("Available commands:");
                        System.out.println("  load   [ user | appointment ]");
                        System.out.println("  save   [ user | appointment ]");
                        System.out.println("  list   [ user | appointment ]");
                        System.out.println("  show   [ reminder | timetable ]");
                        System.out.println("  add    [ user | appointment ]");
                        System.out.println("  delete [ user | appointment ]");
                        System.out.println("  help   [ command ]");
                        System.out.println("  logout");
                    } else {System.out.println("Invalid help argument");}
                } else if

                //admin: logout
                (command[0].equals("logout")){
                    if(command.length==1){
                        login=false;
                    }
                } else {
                    System.out.println("Unknown Command.");
                }

            }


        }


    }


    // method that print the welcome page
    void welcomePage(String userFName, String AppointFName, int userS, int AppointS){
        System.out.println("*** System Startup: begin ***");
        System.out.println("Loading user db from " +userFName+ "..."+userS+" user records loaded.");
        System.out.println("Loading appointment db from "+AppointFName+"..."+AppointS+" appointment records loaded.");
        System.out.println("*** System Startup: done! ***");
        System.out.print("\n");
    }


    // method that print the login cover
    void LoginCover(){
        System.out.print("\n\n");
        System.out.println("+------------------------------------------+");
        System.out.println("|                                          |");
        System.out.println("|   Doctor Appointment Management System   |");
        System.out.println("|                                          |");
        System.out.println("+------------------------------------------+");

        System.out.println();

        System.out.println("Available commands:");
        System.out.println("  Login User");
        System.out.println("  Quit");
        System.out.println();
    }



    // method that let user to input command and break the command line to string array then return it
    // ***** (very useful!!) *****
    String[] command(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nready> ");
        String commandLine = input.nextLine();

        int wordCount = 0;
        char oldC = '0';
        for(int i=0; i<commandLine.length(); i++){
            char newC = commandLine.charAt(i);
            if(i==commandLine.length()-1){
                wordCount++;
            }else if(newC==' '&& oldC!=' '){
                wordCount++;
            }
            oldC = newC;
        }

        String[] command = new String[wordCount];
        String str="";
        int num = 0;
        for(int i=0; i<commandLine.length(); i++){
            char newC = commandLine.charAt(i);
            if(i==commandLine.length()-1){
                str+=newC;
                command[num]=str;
            } else if(newC!=' '){
                str+=newC;
            } else if(newC==' '&& oldC!=' '){
                command[num]=str;
                num++;
                str="";
            }
        }

        command[0] = command[0].toLowerCase();                  // convert command[0] to lower case so that to exclude input case such as "Login", "ADD", "deLEte

        return command;
    }


    //search the user information from the user data base according to the input user id and return the searched user
    User specificUser(String[] command, UserDB userDataBase){
        User specificUser = new User();
        for(int i=0; i<userDataBase.UserDataB.length; i++){
            if(command[1].equals(userDataBase.UserDataB[i].userID)){
                specificUser = userDataBase.UserDataB[i];
                break;
            }
        }
        return specificUser;
    }


    //let user to input password and according to the specified user information, check whether the password is correct
    boolean correctPassword(User specificUser){
        System.out.print("Password: ");
        Scanner input2 = new Scanner(System.in);
        String password = input2.next();
        return specificUser.password.equals(password);
    }



}
