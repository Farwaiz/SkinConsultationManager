import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class GUI extends JFrame {
    ArrayList<Consultation> consultDetails=new ArrayList<>();
    ArrayList<Doctor> doctorList=new ArrayList<>();
    private JTable myTable;
    private JButton viewAllConsultation;
    private TextField index;

    public GUI(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loadFromFile();
        doctorList=WestminsterSkinConsultationManager.availableDoctors();
        setTitle("Doctor Details");

        JPanel mainFrame=new JPanel(new BorderLayout ());

        DoctorTableModel tableModel=new DoctorTableModel(doctorList);
        myTable=new JTable(tableModel);
        myTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
        myTable.setRowHeight(25);



        myTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(myTable);
        scrollPane.setPreferredSize(new Dimension(680,400));

        viewAllConsultation=new JButton("View Consultations");
        index= new TextField(8);

        JPanel panel = new JPanel();
        JPanel panel2=new JPanel();

        panel.add(scrollPane);
        panel.add(new JLabel("                "));
        panel.add(new JLabel("Enter Index"));
        panel.add(index);
        panel2.add(new JLabel("Doctor Details"));
        panel2.add(new JLabel("(Click on the column header to sort the table)"));
        mainFrame.add(panel, BorderLayout.CENTER );
        mainFrame.add(panel2,BorderLayout.NORTH);
        mainFrame.add(viewAllConsultation,BorderLayout.SOUTH);
        add(mainFrame);



        viewAllConsultation.addActionListener(new view());
        index.addActionListener(new view());

    }
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

    public class view implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if (event.getSource()== viewAllConsultation) {
                ViewConsultations consultationDetails = new ViewConsultations(consultDetails);
                consultationDetails.setSize(800, 500);
                consultationDetails.setVisible(true);
            }
            if (event.getSource()==index){
                while (true){
                    try {
                        int numCheck=Integer.parseInt(index.getText());
                    }
                    catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(null,"Enter only integers for index");
                        break;
                    }
                    if(Integer.parseInt(index.getText())<=0 ||Integer.parseInt(index.getText())>doctorList.size()){
                        JOptionPane.showMessageDialog(null,"Enter a valid index");
                        break;
                    }
                    else{
                        CreateConsultation frame=new CreateConsultation(Integer.parseInt(index.getText())-1);
                        frame.setSize(800,700);
                        frame.setVisible(true);
                        dispose();
                        break;
                    }

                }

            }
        }
    }

}