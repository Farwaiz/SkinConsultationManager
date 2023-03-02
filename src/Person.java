import java.io.Serializable;
import java.time.LocalDate;
public class Person implements Serializable {
    private String name;
    private String surname;
    private Integer mobileno;
    private LocalDate dob;

    public Person (String Name, String Surname, Integer MobileNo, LocalDate dob){
        this.name=Name;
        this.surname=Surname;
        this.mobileno=MobileNo;
        this.dob=dob;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Integer getMobileno() {
        return mobileno;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMobileno(Integer mobileno) {
        this.mobileno = mobileno;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
