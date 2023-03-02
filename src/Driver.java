import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        int choice;
        Scanner input= new Scanner(System.in);
        int x = 0;
        WestminsterSkinConsultationManager consult=new WestminsterSkinConsultationManager();
        consult.readFile();
        while (x == 0) {
            System.out.println("""
                    100: Add a doctor
                    101: Delete a doctor
                    102: Print Doctor details list
                    103: Save Doctor Details
                    104: GUI
                    999: Quit Program""");
            System.out.println("Enter your choice");
            while (!input.hasNextInt()) {
                System.out.println("Please enter only integers");
                input.next();
            }
            choice=input.nextInt();
            switch (choice) {
                case 100:
                    consult.addDoctor();
                    break;
                case 101:
                    consult.deleteDoctor();
                    break;
                case 102:
                    consult.printDocList();
                    break;
                case 103:
                    consult.saveInfo();
                    break;
                case 104:
                    consult.gui();
                    break;
                case 999:
                    x = 1;
                    break;
            }
        }
    }
}
