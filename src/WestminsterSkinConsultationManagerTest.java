import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {
    @Test
    void addDoctor() {
        ArrayList<Doctor> doctors=WestminsterSkinConsultationManager.availableDoctors();
        doctors.removeAll(doctors);
        doctors.add(new Doctor("Farwaiz","Firthouse",771234678, LocalDate.parse("2002-12-22", DateTimeFormatter.ISO_DATE),"cosmetic",12345));
        assertEquals(1,doctors.size());
        for (int x=0;x<5;x++){
            doctors.add(new Doctor("Hiraz","Fairooz",762347908, LocalDate.parse("2001-10-19", DateTimeFormatter.ISO_DATE),"cosmetic",12145));
        }
        assertEquals(6,doctors.size());
    }
    @Test
    void deleteDoctor() {
        ArrayList<Doctor> doctors=WestminsterSkinConsultationManager.availableDoctors();
        doctors.removeAll(doctors);
        doctors.add(new Doctor("Farwaiz","Firthouse",771234678, LocalDate.parse("2002-12-22", DateTimeFormatter.ISO_DATE),"cosmetic",12345));
        doctors.add(new Doctor("Hiraz","Fairooz",762347908, LocalDate.parse("2001-10-19", DateTimeFormatter.ISO_DATE),"cosmetic",12145));
        doctors.add(new Doctor("Hiraz","Fairooz",762347908, LocalDate.parse("2001-10-19", DateTimeFormatter.ISO_DATE),"cosmetic",12145));

        assertEquals(3,doctors.size());
        assertEquals(12345,doctors.get(0).getMedicalLicenseNo());
        doctors.remove(0);
        //Checking if doctor was removed and if another doctor has been replaced to place
        assertEquals(2,doctors.size());
        assertEquals(12145,doctors.get(0).getMedicalLicenseNo());

    }
    @Test
    void saveDetails() {
        ArrayList<Doctor> doctors=WestminsterSkinConsultationManager.availableDoctors();
        doctors.removeAll(doctors);
        int counter=0;
        for (int x=0;x<5;x++){
            doctors.add(new Doctor("Farwaiz","Firthouse",771234678, LocalDate.parse("2002-12-22", DateTimeFormatter.ISO_DATE),"cosmetic",12345));
            counter++;
        }
        File file = new File("TestData.txt");
        try{
            FileWriter fileWrite = new FileWriter(file);
            fileWrite.write(counter+"\n");
            for (int x= 0;x<doctors.size();x++){
                fileWrite.write(doctors.get(x).getName()+"\n");
                fileWrite.write(doctors.get(x).getSurname()+"\n");
                fileWrite.write(doctors.get(x).getMobileno()+"\n");
                fileWrite.write(doctors.get(x).getDob()+"\n");
                fileWrite.write(doctors.get(x).getSpecialisation()+"\n");
                fileWrite.write(doctors.get(x).getMedicalLicenseNo()+"\n");
            }
            fileWrite.close();
            FileReader readFile = new FileReader("Testdata.txt");
            Scanner readLines = new Scanner(readFile);
            String firstLine = readLines.nextLine();

            int compare = Integer.parseInt(firstLine);
            assertEquals(5,compare);


        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}