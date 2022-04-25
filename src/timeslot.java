
// brief description: a timeslot class constructed by an appointment array of specific patient or doctor
public class timeslot {
    public String[][] calendar = new String[10][5];                                      // a double string array to present the appointment of such patient/doctor in different time
    public Appointment[] appointmentDataBForTime;

    public timeslot(Appointment[] appointmentDataBForTime){
        this.appointmentDataBForTime = appointmentDataBForTime;
    }


    //method that convert the appointments with patients of such doctor to a timetable
    public void doctorStrToTime(){
        for(int i=0; i<calendar.length; i++){
            for(int j=0; j<calendar[i].length; j++){
                calendar[i][j]=" ";
            }
        }
        for(int i=0; i<appointmentDataBForTime.length; i++) {
            int dayNum;
            char day = appointmentDataBForTime[i].getDay(appointmentDataBForTime[i].time);
            if (day == 'M') {
                dayNum = 0;
            } else if (day == 'T') {
                dayNum = 1;
            } else if (day == 'W') {
                dayNum = 2;
            } else if (day == 'R') {
                dayNum = 3;
            } else {
                dayNum = 4;
            }

            int hourNum;
            int hour = appointmentDataBForTime[i].getHour(appointmentDataBForTime[i].time);
            if (hour == 9) {
                hourNum = 0;
            } else if (hour == 10) {
                hourNum = 1;
            } else if (hour == 11) {
                hourNum = 2;
            } else if (hour == 12) {
                hourNum = 3;
            } else if (hour == 13) {
                hourNum = 4;
            } else if (hour == 14) {
                hourNum = 5;
            } else if (hour == 15) {
                hourNum = 6;
            } else if (hour == 16) {
                hourNum = 7;
            } else if (hour == 17) {
                hourNum = 8;
            } else {
                hourNum = 9;
            }
            calendar[hourNum][dayNum] = appointmentDataBForTime[i].patientID;
        }
        System.out.println("         Mon            Tue            Wed            Thu            Fri      ");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print(" 9 |");
        for(int i=0; i<calendar[0].length; i++){
            System.out.printf("%10s   ||", calendar[0][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("10 |");
        for(int i=0; i<calendar[1].length; i++){
            System.out.printf("%10s   ||", calendar[1][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("11 |");
        for(int i=0; i<calendar[2].length; i++){
            System.out.printf("%10s   ||", calendar[2][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("12 |");
        for(int i=0; i<calendar[3].length; i++){
            System.out.printf("%10s   ||", calendar[3][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("13 |");
        for(int i=0; i<calendar[4].length; i++){
            System.out.printf("%10s   ||", calendar[4][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("14 |");
        for(int i=0; i<calendar[5].length; i++){
            System.out.printf("%10s   ||", calendar[5][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("15 |");
        for(int i=0; i<calendar[6].length; i++){
            System.out.printf("%10s   ||", calendar[6][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("16 |");
        for(int i=0; i<calendar[7].length; i++){
            System.out.printf("%10s   ||", calendar[7][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("17 |");
        for(int i=0; i<calendar[8].length; i++){
            System.out.printf("%10s   ||", calendar[8][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("18 |");
        for(int i=0; i<calendar[9].length; i++){
            System.out.printf("%10s   ||", calendar[9][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
    }



    //method that convert the appointments with doctors of such patient to a timetable
    public void PatientStrToTime(){
        for(int i=0; i<calendar.length; i++){
            for(int j=0; j<calendar[i].length; j++){
                calendar[i][j]=" ";
            }
        }
        for(int i=0; i<appointmentDataBForTime.length; i++) {
            int dayNum;
            char day = appointmentDataBForTime[i].getDay(appointmentDataBForTime[i].time);
            if (day == 'M') {
                dayNum = 0;
            } else if (day == 'T') {
                dayNum = 1;
            } else if (day == 'W') {
                dayNum = 2;
            } else if (day == 'R') {
                dayNum = 3;
            } else {
                dayNum = 4;
            }

            int hourNum;
            int hour = appointmentDataBForTime[i].getHour(appointmentDataBForTime[i].time);
            if (hour == 9) {
                hourNum = 0;
            } else if (hour == 10) {
                hourNum = 1;
            } else if (hour == 11) {
                hourNum = 2;
            } else if (hour == 12) {
                hourNum = 3;
            } else if (hour == 13) {
                hourNum = 4;
            } else if (hour == 14) {
                hourNum = 5;
            } else if (hour == 15) {
                hourNum = 6;
            } else if (hour == 16) {
                hourNum = 7;
            } else if (hour == 17) {
                hourNum = 8;
            } else {
                hourNum = 9;
            }
            calendar[hourNum][dayNum] = appointmentDataBForTime[i].doctorID;
        }
        System.out.println("         Mon            Tue            Wed            Thu            Fri      ");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print(" 9 |");
        for(int i=0; i<calendar[0].length; i++){
            System.out.printf("%10s   ||", calendar[0][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("10 |");
        for(int i=0; i<calendar[1].length; i++){
            System.out.printf("%10s   ||", calendar[1][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("11 |");
        for(int i=0; i<calendar[2].length; i++){
            System.out.printf("%10s   ||", calendar[2][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("12 |");
        for(int i=0; i<calendar[3].length; i++){
            System.out.printf("%10s   ||", calendar[3][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("13 |");
        for(int i=0; i<calendar[4].length; i++){
            System.out.printf("%10s   ||", calendar[4][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("14 |");
        for(int i=0; i<calendar[5].length; i++){
            System.out.printf("%10s   ||", calendar[5][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("15 |");
        for(int i=0; i<calendar[6].length; i++){
            System.out.printf("%10s   ||", calendar[6][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("16 |");
        for(int i=0; i<calendar[7].length; i++){
            System.out.printf("%10s   ||", calendar[7][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("17 |");
        for(int i=0; i<calendar[8].length; i++){
            System.out.printf("%10s   ||", calendar[8][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.print("18 |");
        for(int i=0; i<calendar[9].length; i++){
            System.out.printf("%10s   ||", calendar[9][i]);
        }
        System.out.println();
        System.out.println("   |             ||             ||             ||             ||             ||");
        System.out.println("   +-------------++-------------++-------------++-------------++-------------++");
    }
}
