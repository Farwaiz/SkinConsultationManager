import java.io.Serializable;
import java.time.LocalDate;

class Consultation implements Serializable {
    private LocalDate date;
    private Integer cost;
    private String notes;
    private String time;
    private Patient patient;
    private Doctor  doctor;
    private String NIC;



    public Consultation( Patient patient, Doctor doctor, Integer cost,LocalDate date,  String time,String notes,String NIC) {
        this.date = date;
        this.cost = cost;
        this.notes = notes;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
        this.NIC = NIC;
    }

    public LocalDate getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

    public Integer getCost() {
        return cost;
    }

    public String getNotes() {
        return notes;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getNIC() {
        return NIC;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
