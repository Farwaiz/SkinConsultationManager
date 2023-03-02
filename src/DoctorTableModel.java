import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DoctorTableModel extends AbstractTableModel {
    private String[] columnNames = {"Index","Name","Surname","Mobile Number","Date of Birth","Specialisation","Medical License No"};
    private ArrayList<Doctor> list;

    public DoctorTableModel(ArrayList<Doctor> doctorList){
        list = doctorList;
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
            temp=rowIndex+1;
        }
        if (columnIndex == 1) {
            temp = list.get(rowIndex).getName();
        }
        else if (columnIndex == 2) {
            temp = list.get(rowIndex).getSurname();
        }
        else if (columnIndex == 3) {
            temp = list.get(rowIndex).getMobileno();
        }
        else if (columnIndex == 4) {
            temp = list.get(rowIndex).getDob();
        }
        else if (columnIndex == 5) {
            temp = list.get(rowIndex).getSpecialisation();
        }
        else if (columnIndex == 6) {
            temp = list.get(rowIndex).getMedicalLicenseNo();
        }
        return temp;
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }
}
