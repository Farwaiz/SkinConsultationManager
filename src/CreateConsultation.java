import javax.crypto.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CreateConsultation extends JFrame {
    WestminsterSkinConsultationManager we=new WestminsterSkinConsultationManager();
    ArrayList<Consultation> consultDetails=new ArrayList<>();
    private TextField name;
    private TextField surname;
    private TextField mobileNo;
    private TextField dob;
    private TextField NIC;
    private TextField date;
    private TextField notes;
    private JButton backButton;
    private JButton bookButton;
    private JButton viewAllConsultation;
    private JButton viewLastConsultation;
    private JButton resetValues;
    private JComboBox time;
    private int rowIndex;
    private int tempRow;

    public CreateConsultation(int row){

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loadFromFile();

        rowIndex=row;
        tempRow=rowIndex;

        //creation of panels
        JPanel mainFrame=new JPanel(new BorderLayout());
        JPanel grid=new JPanel(new GridLayout(6,2));

        JPanel panel2=new JPanel(new GridLayout(4,1));
        JPanel panel3=new JPanel(new GridLayout(3,1));
        JPanel panel4=new JPanel(new GridLayout(1,1));

        //creation of all fields
        name=new TextField(8);
        surname=new TextField(8);
        dob=new TextField(8);
        mobileNo=new TextField(8);
        NIC=new TextField(8);
        date=new TextField(8);
        String[] docTime ={"Select Time","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00"};
        time=new JComboBox(docTime);
        notes=new TextField(9);

        //adding all those fields to different panels to set them properly on the mainframe
        panel4.add(new JLabel("Notes:"));
        panel4.add(notes);
        panel2.add(new JLabel("Name"));
        panel2.add(name);
        panel2.add(new JLabel("Surname"));
        panel2.add(surname);
        panel2.add(new JLabel("Mobile No."));
        panel2.add(mobileNo);
        panel2.add(new JLabel("NIC"));
        panel2.add(NIC);
        panel3.add(new JLabel("Date of Birth (yyyy-mm-dd)"));
        panel3.add(dob);
        panel3.add(new JLabel("Appointment Date (yyyy-mm-dd)"));
        panel3.add(date);
        panel3.add(new JLabel("Appointment Time"));
        panel3.add(time);

        JPanel panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JLabel("Enter Patient Details"));
        mainFrame.add(panel,BorderLayout.NORTH);

        //adding the panels to grid panel to add it later to the mainframe panel
        grid.add(new JLabel(""));
        grid.add(new JLabel(""));
        grid.add(panel2);
        grid.add(panel3);
        grid.add(new JLabel(""));
        grid.add(new JLabel(""));
        grid.add(panel4);
        grid.add(new JLabel(""));



        mainFrame.add(grid,BorderLayout.CENTER);

        JPanel panel5=new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButton=new JButton("Back");
        bookButton=new JButton("Book");
        resetValues=new JButton("Clear");
        viewAllConsultation =new JButton("View All Consultations");
        viewLastConsultation=new JButton("View Last Consultation");
        viewLastConsultation.setVisible(false);
        panel5.add(viewLastConsultation);
        panel5.add(backButton);
        panel5.add(resetValues);
        panel5.add(bookButton);
        panel5.add(viewAllConsultation);
        mainFrame.add(panel5,BorderLayout.SOUTH);



        add(mainFrame);

        //Adding action listeners
        consult handler=new consult();
        name.addActionListener(handler);
        surname.addActionListener(handler);
        mobileNo.addActionListener(handler);
        dob.addActionListener(handler);
        date.addActionListener(handler);
        time.addActionListener(handler);
        viewLastConsultation.addActionListener(handler);
        resetValues.addActionListener(handler);
        backButton.addActionListener(handler);
        bookButton.addActionListener(handler);
        viewAllConsultation.addActionListener(handler);
    }

    //method to save consultations to file
    public void saveToFile(){
        try {
            File file = new File("PatientDetails.txt");
            FileOutputStream fileOutput = new FileOutputStream(file);
            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
                for (Consultation consultations : consultDetails) {
                    outputStream.writeObject(consultations);
                }
                outputStream.close();
                fileOutput.close();
            } catch (Exception ex) {
                System.out.println("Saving Error");
            }
        }catch(IOException ex){
            System.out.println("Saving Error");
        }
    }

    //method to load the saved consultations from a file
    public void loadFromFile() {
        consultDetails.removeAll(consultDetails);
        try{
            FileInputStream fileInput = new FileInputStream("PatientDetails.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            while(true){
                try{
                    Consultation consultation = (Consultation) inputStream.readObject();
                    consultDetails.add(consultation);
                }catch(Exception e){
                    break;
                }
            }
        } catch (IOException e) {

        }
    }

    //method to check if the dates are within certain ranges
    public String checkDate(String date){
        String[] check=date.split("-",3);
        int year=Integer.parseInt((check[0]));
        int month = Integer.parseInt(check[1]);
        int day=Integer.parseInt(check[2]);
        if (month < 1 || month > 12){
            return "1";
        }
        else if(day < 1 || day > 31){
            return "1";
        }
        else if(year<1000 || year>9999){
            return "1";
        }
        else {
            return we.dateob(year,month,day);
        }
    }
    private class consult implements ActionListener {
        public void actionPerformed(ActionEvent event){
            String string;
            String finalDate;
            String finalDOB;

            int rate=15;
            if (event.getSource()==bookButton){
                String temp;
                loadFromFile();
                rowIndex=tempRow;

                for (int c=0;c<consultDetails.size();c++){
                    //Checks if name textfield is empty
                    temp=name.getText();
                    if (temp.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter a name");
                        break;
                    }
                    //Checks if surname textfield is empty
                    temp=surname.getText();
                    if (temp.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter a surname");
                        break;
                    }
                    //Checks if nic is empty
                    temp=NIC.getText();
                    if (temp.equals("")){
                        JOptionPane.showMessageDialog(null,"Enter your NIC number");
                        break;
                    }
                    temp=dob.getText();
                    Date appDate=new Date();
                    //Checks if mobile number is entered properly
                    try {
                        int numCheck=Integer.parseInt(mobileNo.getText());
                    }
                    catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(null,"Enter only integers for Mobile Number");
                        break;
                    }
                    if(String.valueOf(mobileNo.getText()).length()!=10){
                        JOptionPane.showMessageDialog(null,"Enter 10 digits for mobile number");
                        break;
                    }
                    //checks if data is entered in the right format
                    try {
                        appDate = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
                    } catch (ParseException parseException) {
                        string = String.format("Enter the Date in the right format %s", event.getActionCommand());
                        JOptionPane.showMessageDialog(null,string);
                        break;

                    }
                    temp=date.getText();
                    appDate=new Date();
                    try {
                        appDate = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
                    } catch (ParseException parseException) {
                        string = String.format("Enter the Date in the right format %s", event.getActionCommand());
                        JOptionPane.showMessageDialog(null,string);
                        break;
                    }
                    //Checks both date fields formats
                    if (checkDate(dob.getText())=="1"){
                        JOptionPane.showMessageDialog(null,"Enter a valid date");
                        break;
                    }else {
                        finalDOB=checkDate(dob.getText());
                    }
                    if (checkDate(date.getText())=="1"){
                        JOptionPane.showMessageDialog(null,"Enter a valid date");
                        break;
                    }else {
                        finalDate=checkDate(date.getText());
                    }
                    if (time.getSelectedIndex()==0){
                        JOptionPane.showMessageDialog(null,"Enter a valid time");
                        break;
                    }


                    //checks if an appoitment is booked in the same time,same day and doctor
                    int breaker=0;
                    for (int index=0;index<consultDetails.size();index++){
                        if(consultDetails.get(index).getTime().equals(time.getSelectedItem().toString())&& consultDetails.get(index).getDate().toString().equals(checkDate(date.getText())) && consultDetails.get(index).getDoctor().getName().equals(we.doctor.get(rowIndex).getName())){
                            index=0;
                            Random random = new Random();
                            int value = random.nextInt(we.doctor.size());
                            while (rowIndex==value){
                                value = random.nextInt(we.doctor.size());
                            }
                            while (rowIndex!=value) {
                                rowIndex = value;
                            }
                            breaker++;
                        }
                        if(breaker==40){
                            System.out.println("No doctors");
                            break;
                        }
                        //checks if its a person's first or second to give out a rate
                        if (consultDetails.get(index).getNIC().equals(NIC.getText())){
                            rate=25;
                        }
                    }

                    //encrypts the note field

                    String cipheredText=notes.getText();

                    try {
                        //Generates key
                        KeyGenerator secretKey
                                = KeyGenerator.getInstance("DES");
                        SecretKey myKey = secretKey.generateKey();

                        // Creates an object of Cipher
                        Cipher cipher;
                        cipher = Cipher.getInstance("DES");

                        byte[] text
                                = notes.getText().getBytes("UTF8");

                        // Encrypting text
                        cipher.init(Cipher.ENCRYPT_MODE, myKey);
                        byte[] textEncrypted = cipher.doFinal(text);

                        // Converts encrypted byte array to string
                        cipheredText = new String(textEncrypted);

                        // Decrypting text
                        cipher.init(Cipher.DECRYPT_MODE, myKey);
                        byte[] textDecrypted
                                = cipher.doFinal(textEncrypted);

                        //converts decrypted byte array to string
                        String decryptedText=new String(textDecrypted);

                    }
                    catch (Exception e) {
                        System.out.println("Exception");
                    }

                    //creates patient object with the details taken and stores other details with patient details to Consultation object
                    Patient patientDetails=new Patient(name.getText(),surname.getText(),Integer.parseInt(mobileNo.getText()), LocalDate.parse(finalDOB, DateTimeFormatter.ISO_DATE),NIC.getText());
                    Consultation applicant=new Consultation(patientDetails,we.doctor.get(rowIndex),rate,LocalDate.parse(finalDate, DateTimeFormatter.ISO_DATE),time.getSelectedItem().toString(),cipheredText,NIC.getText());
                    consultDetails.add(applicant);
                    saveToFile();
                    JOptionPane.showMessageDialog(null,"Successfully added booking");
                    viewLastConsultation.setVisible(true);

                    break;
                }
                //Adding patient to the consultation list if the list is empty
                for (int c=0;c<1;c++){
                    if (consultDetails.size()==0){
                        //checks if name textfield empty
                        temp=name.getText();
                        if (temp.equals("")){
                            JOptionPane.showMessageDialog(null,"Enter a name");
                            break;
                        }
                        //checks if surname textfield empty
                        temp=surname.getText();
                        if (temp.equals("")){
                            JOptionPane.showMessageDialog(null,"Enter a surname");
                            break;
                        }
                        //checks if mobile number is entered in the right data type
                        try {
                            int numCheck=Integer.parseInt(mobileNo.getText());
                        }
                        catch (NumberFormatException a) {
                            JOptionPane.showMessageDialog(null,"Enter only integers for Mobile Number");
                            break;
                        }
                        if(String.valueOf(mobileNo.getText()).length()!=10){
                            JOptionPane.showMessageDialog(null,"Enter 10 digits for mobile number");
                            break;
                        }
                        //checks if NIC textfield empty
                        temp=NIC.getText();
                        if (temp.equals("")){
                            JOptionPane.showMessageDialog(null,"Enter your NIC number");
                            break;
                        }
                        //checks if date of birth is entered in the right format
                        temp=dob.getText();
                        Date appDate=new Date();
                        try {
                            appDate = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
                        } catch (ParseException parseException) {
                            string = String.format("Enter the Date in the right format %s", event.getActionCommand());
                            JOptionPane.showMessageDialog(null,string);
                            break;

                        }
                        //checks if date is entered in the right format
                        temp=date.getText();
                        appDate=new Date();
                        try {
                            appDate = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
                        } catch (ParseException parseException) {
                            string = String.format("Enter the Date in the right format %s", event.getActionCommand());
                            JOptionPane.showMessageDialog(null,string);
                            break;
                        }
                        //checks if date is within certain ranges
                        if (checkDate(dob.getText()).equals("1")){
                            JOptionPane.showMessageDialog(null,"Enter a valid date");
                            break;
                        }else {
                            finalDOB=checkDate(dob.getText());
                        }

                        if (checkDate(date.getText()).equals("1")){
                            JOptionPane.showMessageDialog(null,"Enter a valid date");
                            break;
                        }else {
                            finalDate=checkDate(date.getText());
                        }
                        if (time.getSelectedIndex()==0){
                            JOptionPane.showMessageDialog(null,"Enter a valid time");
                            break;
                        }

                        //encrypts the note field

                        String cipheredText=notes.getText();

                        try {
                            //Generates key
                            KeyGenerator secretKey
                                    = KeyGenerator.getInstance("DES");
                            SecretKey myKey = secretKey.generateKey();

                            // Creates an object of Cipher
                            Cipher cipher;
                            cipher = Cipher.getInstance("DES");

                            byte[] text
                                    = notes.getText().getBytes("UTF8");

                            // Encrypting text
                            cipher.init(Cipher.ENCRYPT_MODE, myKey);
                            byte[] textEncrypted = cipher.doFinal(text);

                            // Converts encrypted byte array to string
                            cipheredText = new String(textEncrypted);

                            // Decrypting text
                            cipher.init(Cipher.DECRYPT_MODE, myKey);
                            byte[] textDecrypted
                                    = cipher.doFinal(textEncrypted);

                            //converts decrypted byte array to string
                            String decryptedText=new String(textDecrypted);

                        }
                        catch (Exception e) {
                            System.out.println("Exception");
                        }

                        //creates patient object with the details taken and stores other details with patient details to Consultation object
                        Patient patientDetails=new Patient(name.getText(),surname.getText(),Integer.parseInt(mobileNo.getText()), LocalDate.parse(finalDOB, DateTimeFormatter.ISO_DATE),NIC.getText());
                        Consultation applicant=new Consultation(patientDetails,we.availableDoctors().get(rowIndex),rate,LocalDate.parse(finalDate, DateTimeFormatter.ISO_DATE),time.getSelectedItem().toString(),cipheredText,NIC.getText());
                        consultDetails.add(applicant);

                        saveToFile();
                        JOptionPane.showMessageDialog(null,"Successfully added booking");
                        viewLastConsultation.setVisible(true);
                    }

                }
            }
            //Button event to check all consultations
            if (event.getSource()== viewAllConsultation){
                ViewConsultations consultationDetails=new ViewConsultations(consultDetails);
                consultationDetails.setSize(800,500);
                consultationDetails.setVisible(true);

            }
            //Button event to check the consultation which was made
            if (event.getSource()== viewLastConsultation){
                ViewLastConsultation currentConsultation=new ViewLastConsultation(consultDetails,notes.getText());
                currentConsultation.setSize(800,400);
                currentConsultation.setVisible(true);

            }
            //button event to get back to the doctors list
            if (event.getSource()== backButton){
                GUI currentConsultation=new GUI();
                currentConsultation.setSize(800,700);
                currentConsultation.setVisible(true);
                dispose();
            }
            //button event to clear all details from fields
            if (event.getSource()== resetValues){
                name.setText("");
                surname.setText("");
                mobileNo.setText("");
                NIC.setText("");
                dob.setText("");
                date.setText("");
                notes.setText("");
                time.setSelectedIndex(0);
            }
        }
    }
}
