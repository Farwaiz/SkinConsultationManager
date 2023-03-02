import java.time.LocalDate;

class Patient extends Person {
    private String patientID;

    public Patient(String Name, String Surname, Integer MobileNo, LocalDate dob,String patientID) {
        super(Name, Surname, MobileNo, dob);
        this.patientID=patientID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
}
