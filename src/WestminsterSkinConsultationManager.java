import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Scanner;

class WestminsterSkinConsultationManager implements SkinConsultationManager {
    static ArrayList<Doctor> doctor = new ArrayList<>();
    String Name;
    String surname;
    Integer mobileNo;
    int medicalLicenseNo;
    String Specialisation;
    LocalDate dob;
    Scanner input = new Scanner(System.in);


    //method to return the details of the doctor of the given index
    public String Details(int index){
        String firstname=doctor.get(index).getName();
        String surname=doctor.get(index).getSurname();
        int mobileno=doctor.get(index).getMobileno();
        LocalDate docb =doctor.get(index).getDob();
        String specialisation=doctor.get(index).getSpecialisation();
        int medicalLicense=doctor.get(index).getMedicalLicenseNo();
        return (firstname + "," + surname + "," + mobileno+ "," + docb + "," + specialisation + "," + medicalLicense);
    }
    public void addfromfile(String [] deta){
        Doctor doctor1 = new Doctor(deta[0], deta[1], Integer.parseInt(String.valueOf(deta[2])) , LocalDate.parse(deta[3]), deta[4], Integer.parseInt(String.valueOf(deta[5])));
        doctor.add(doctor1);
    }
    public String dateob(int year, int month, int day){
        String date;
        if (String.valueOf(month).length()==1){
            date= year +"-"+ "0"+month;
        }
        else {
            date= year +"-"+ month;
        }
        if (String.valueOf(day).length()==1){
            date=date+"-"+"0"+day;
        }
        else {
            date= date +"-"+ day;
        }
        return date;
    }

    public void addDoctor() {
        if(doctor.size()!=10){
            System.out.println("Please enter the doctor's name");
            Name = input.next();
            System.out.println("Please enter the doctor's surname");
            surname = input.next();
            System.out.println("Please enter the doctor's mobile number");
            while (true) {
                try {
                    mobileNo = input.nextInt();
                    while ((String.valueOf(mobileNo).length()) !=9) {
                        System.out.println("Length of mobile number should be only 10 integers");
                        while (!input.hasNextInt()) {
                            System.out.println("Invalid input . Please enter an integer");
                            input.next();
                        }
                        mobileNo = input.nextInt();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input . Please enter a valid mobile number");
                    input.nextLine();
                }
            }
            System.out.println("Please enter the doctor's year of birth");
            int year;
            while (true) {
                try {
                    year = input.nextInt();
                    while (String.valueOf(year).length() != 4) {
                        System.out.println("Please enter a valid year");
                        while (!input.hasNextInt()) {
                            System.out.println("Invalid input . Please enter an integer");
                            input.next();
                        }
                        year = input.nextInt();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input . Please enter an integer");
                    input.nextLine();
                }
            }
            System.out.println("Please enter the doctor's month of birth");
            int month;
            while (true) {
                try {
                    month = input.nextInt();
                    while (month < 1 || month > 12) {
                        System.out.println("Please enter only numbers from 1 to 12");
                        while (!input.hasNextInt()) {
                            System.out.println("Invalid input . Please enter an integer");
                            input.next();
                        }
                        month = input.nextInt();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input . Please enter an integer");
                    input.nextLine();
                }
            }
            System.out.println("Please enter the doctor's day of birth");
            int day;
            while (true) {
                try {
                    day = input.nextInt();
                    while (day < 1 || day > 31) {
                        System.out.println("Please enter only numbers from 1 to 31");
                        while (!input.hasNextInt()) {
                            System.out.println("Invalid input . Please enter an integer");
                            input.next();
                        }
                        day = input.nextInt();
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input . Please enter an integer");
                    input.nextLine();
                }
            }
            String date = dateob(year, month,day);
            dob = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            System.out.println("Please enter the doctor's specialisation");
            Specialisation = input.next();
            while (true) {
                try {
                    System.out.println("Please enter the doctor's medical license number");
                    medicalLicenseNo = input.nextInt();
                    while ((String.valueOf(medicalLicenseNo).length()) !=5 ) {
                        System.out.println("Number should be 5 digits, try entering a valid number");
                        while (!input.hasNextInt()) {
                            System.out.println("Input is not a number, try inputting a valid number");
                            input.next();
                        }
                        medicalLicenseNo = input.nextInt();
                    }
                    for(int x=0; x<doctor.size();x++){
                        if(doctor.get(x).getMedicalLicenseNo()==medicalLicenseNo){
                            System.out.println("License Number is registered to another doctor, please enter a different license number"+"\n");
                            medicalLicenseNo = 0;
                            break;
                        }
                        else if (x==(doctor.size()-1)){
                            break;
                        }
                    }
                    if (medicalLicenseNo==0){
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input . Please enter a valid medical license number");
                    input.nextLine();
                }
            };
            Doctor doctor1 = new Doctor(Name, surname, mobileNo, dob, Specialisation, medicalLicenseNo);
            doctor.add(doctor1);
        }
        else{
            System.out.println("Delete a doctor before entering a new doctor");
        }
    }

    //method with repetetive code
    public void printdetails(int index){
        System.out.println("Name:" + doctor.get(index).getName());
        System.out.println("Surname:" + doctor.get(index).getSurname());
        System.out.println("Mobile Number:" + doctor.get(index).getMobileno());
        System.out.println("Date of Birth:" + doctor.get(index).getDob());
        System.out.println("Specialisation:" + doctor.get(index).getSpecialisation());
        System.out.println("Medical License Number:" + doctor.get(index).getMedicalLicenseNo());
    }
    //method to delete doctor from the given medical license number
    public void deleteDoctor() {
        System.out.println("Please enter the doctor's medical license number");
        while (!input.hasNextInt()) {
            System.out.println("Please enter a valid license number");
            input.next();
        }
        int temp = input.nextInt();
        if (doctor.size()==0){
            System.out.println("There are no doctors added");
        }
        for (int c=0;c<doctor.size();c++){
            if (doctor.get(c).getMedicalLicenseNo()==temp){
                printdetails(c);
                doctor.remove(c);
                System.out.println("Total number of doctors left is "+doctor.size());
                System.out.println(c);
                break;//add an elif if the medical license is not there by comparing the c value with final size value
            }
            else if(c==(doctor.size()-1)){
                System.out.println("That medical License is not there in the Doctor list");
            }
        }
    }

    //method to print the doctors list in surnames alphabetical order
    @Override
    public void printDocList() {
        ArrayList<String> cloneDetails = new ArrayList<>();

        for(int c=0;c<doctor.size();c++) {
            cloneDetails.add(doctor.get(c).getSurname());
        }

        Collections.sort(cloneDetails);
        for (int j=0;j<doctor.size();j++) {
            for (int c = 0; c < doctor.size(); c++) {
                if (doctor.get(c).getSurname().equals(cloneDetails.get(j))) {
                    printdetails(c);
                }
            }
        }
    }

    @Override
    public void saveInfo() {
        try {
            FileWriter myWriter = new FileWriter("DoctorDetails.txt");
            for (int c = 0; c < doctor.size(); c++) {
                if (c != (doctor.size() - 1)) {
                    myWriter.write(Details(c) + "\r");
                } else if (c==(doctor.size()-1)) {
                    myWriter.write(Details(c));
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("The file is not there");
        }
    }
    //method to read from text file
    public void readFile(){
        doctor.removeAll(doctor);
        try {
            File inputfile = new File("DoctorDetails.txt");
            Scanner rf = new Scanner(inputfile);
            String fileline;
            while (rf.hasNext()) {
                fileline = rf.nextLine();
                String[] details = fileline.split(",", 6);
                addfromfile(details);
            }
            rf.close();
            System.out.println("Loaded data into the file");
        } catch (FileNotFoundException e) {
            System.out.println("The file is not there");
        }
    }

    public void gui() {
        GUI frame=new GUI();
        frame.setSize(800,700);
        frame.setVisible(true);
    }
    public static ArrayList<Doctor> availableDoctors(){
        return doctor;
    }

}
