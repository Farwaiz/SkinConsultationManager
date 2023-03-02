import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewConsultations extends JFrame {
    private JTable myTable;
    public ViewConsultations(ArrayList<Consultation> consultationList){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel msi=new JPanel(new BorderLayout ());
        ConsultationTableModel tableModel=new ConsultationTableModel(consultationList);
        myTable=new JTable(tableModel);
        myTable.setPreferredScrollableViewportSize(new Dimension(700, 700));
        myTable.setRowHeight(25);


        myTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(myTable);
        scrollPane.setPreferredSize(new Dimension(700,160));

        JPanel panel = new JPanel();
        panel.add(scrollPane);
        msi.add(panel, BorderLayout.CENTER );
        add(msi);
    }
}
