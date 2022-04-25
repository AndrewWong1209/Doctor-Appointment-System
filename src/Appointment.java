
//brief description: a appointment class constricted by doctorID, patientID, and time
public class Appointment {
    public String doctorID;
    public String patientID;
    public String time;

    public Appointment(String doctorID, String patientID, String time){
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.time = time;
    }

    public char getDay(String time){
        return time.charAt(0);
    }

    public int getHour(String time){
        int value = 0;
        for(int i=1; i<time.length(); i++) {
            char c = time.charAt(i);
            value = value * 10 + (c-'0');
        }
        return value;
    }


    // method that check whether the time format is correct
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
            System.out.println("Invalid timeslot format: "+time);
            return false;
        }
    }


    public String toString(){
        return doctorID+"    "+patientID+"    "+time;
    }


    public boolean equals(Appointment inputAppoint){
        return this.doctorID.equals(inputAppoint.doctorID) &&this.patientID.equals(inputAppoint.patientID) && this.time.equals(inputAppoint.time);
    }
}
