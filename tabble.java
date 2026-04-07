import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class tabble {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CHECK_TABLE");

            JFrame frame = new JFrame("Table Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a DefaultTableModel with some data
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Enroll");
            model.addColumn("checktime");
            model.addColumn("checkDate");
            JTable table = new JTable(model);

            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

            // Populate the table with data
            while (rs.next()) {
                String enroll = rs.getString("enroll");
                String checkd = rs.getString("checkd");
                String checkt = rs.getString("checkt");

                model.addRow(new Object[] { enroll, checkd, checkt });
            }

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane); // Add the scroll pane to the frame

            frame.setSize(400, 300);
            frame.setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
