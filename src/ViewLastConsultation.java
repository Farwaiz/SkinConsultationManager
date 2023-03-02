import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class ViewLastConsultation extends JFrame {
    JTable myTable;
    private final String[] columnNames = {"Patient Name","Doctor Name","Cost","Appointment Date","Appointment Time","Notes"};
    public ViewLastConsultation(ArrayList<Consultation> consult, String notes){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Object [][] data={{consult.get(consult.size()-1).getPatient().getName(),consult.get(consult.size()-1).getDoctor().getName(),consult.get(consult.size()-1).getCost(),consult.get(consult.size()-1).getDate(),consult.get(consult.size()-1).getTime(),notes}};
        myTable=new JTable();
        TableModel model=new DefaultTableModel(data,columnNames);
        myTable.setModel(model);
        myTable.setRowHeight(25);

        JScrollPane scrollPane=new JScrollPane(myTable);
        myTable.setGridColor(Color.BLACK);
        add(scrollPane);



    }
}
