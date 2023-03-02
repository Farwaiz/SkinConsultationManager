import java.time.LocalDate;

class Doctor extends Person {
    private int medicalLicenseNo;
    private String Specialisation;

    public Doctor(String Name, String Surname, Integer MobileNo, LocalDate dob, String specialisation, int medicalLicenseNo ) {
        super(Name, Surname, MobileNo, dob);
        this.medicalLicenseNo=medicalLicenseNo;
        Specialisation = specialisation;
    }

    public int getMedicalLicenseNo() {
        return medicalLicenseNo;
    }

    public String getSpecialisation() {
        return Specialisation;
    }

    public void setMedicalLicenseNo(int medicalLicenseNo) {
        this.medicalLicenseNo = medicalLicenseNo;
    }

    public void setSpecialisation(String specialisation) {
        Specialisation = specialisation;
    }
}
