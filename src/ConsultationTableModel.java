import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ConsultationTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Patient Name","Doctor Name","Cost","Appointment Date","Appointment Time","Notes"};
    private final ArrayList<Consultation> list;

    public ConsultationTableModel(ArrayList<Consultation> consultationsList){
        list = consultationsList;
    }
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp=null;
        if (columnIndex==0){
            temp = list.get(rowIndex).getPatient().getName();
        }
        if (columnIndex == 1) {
            temp = list.get(rowIndex).getDoctor().getName();
        }
        else if (columnIndex == 2) {
            temp = list.get(rowIndex).getCost();
        }
        else if (columnIndex == 3) {
            temp = list.get(rowIndex).getDate();
        }
        else if (columnIndex == 4) {
            temp = list.get(rowIndex).getTime();
        }
        else if (columnIndex == 5) {
            temp = list.get(rowIndex).getNotes();
        }
        else if (columnIndex == 6) {

        }
        return temp;
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
