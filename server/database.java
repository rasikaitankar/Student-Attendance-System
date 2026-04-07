import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class database {

    static boolean checkenroll(String n) throws Exception {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STUDENT", "root", "123456");
        Statement st = con.createStatement();
        String enrollmentNumberToCheck = n; // Replace this with the enrollment number you want to check
        String checkQuery = "SELECT * FROM INFO WHERE ENROLL = '" + enrollmentNumberToCheck + "'";
        ResultSet rs = st.executeQuery(checkQuery);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    static boolean addCandidate() throws Exception{
        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
        Statement s = con.createStatement();
        
           JFrame frame = new JFrame("Add Candidate");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
JLabel l1 = new JLabel("Enter Enrollment Number : ");
JLabel l2 = new JLabel("Enter Password :");
JTextField t1 = new JTextField();
JTextField t2 = new JTextField();
JButton button = new JButton("Submit");
JButton button2 = new JButton("Close");

button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO INFO VALUES(?, ?)");
            String enroll = t1.getText();
            String password = t2.getText();
          ps.setString(1,enroll);
          ps.setString(2,password);
          int n=ps.executeUpdate();
          if(n==1){
            JOptionPane.showMessageDialog(null, "Enrollment and password are registered");
          }else{
            JOptionPane.showMessageDialog(null, "Enrollment and password are not registered");
          }
        } catch (Exception ex) {
           System.out.println(ex);
           JOptionPane.showMessageDialog(null, "Enrollment and password are already registered");
        }
        
        }
});

button2.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {
       frame.dispose();
    }
});


button2.setBounds(230,330,200,20);
button.setBounds(230,300,200,20);
l1.setBounds(10,100,200,20);
l2.setBounds(10,200,200,20);
t1.setBounds(230,100,200,20);
t2.setBounds(230,200,200,20);

frame.add(button2);
frame.add(l1);
frame.add(l2);
frame.add(t1);
frame.add(t2);
frame.add(button);
frame.setLayout(null);
frame.setBounds(300, 300, 500, 500);
            frame.setVisible(true); 
        } catch (Exception e) {
           System.out.println(e);
        }
        return true;
    }

    static boolean removeCandidate(){

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
        Statement s = con.createStatement();
        
           JFrame frame = new JFrame("Remove Candidate");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
JLabel l1 = new JLabel("Enter Enrollment Number : ");

JTextField t1 = new JTextField();

JButton button = new JButton("Remove");
JButton button2 = new JButton("Close");

button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM INFO WHERE ENROLL = ?");
            String enroll = t1.getText();
            
          ps.setString(1,enroll);
        
          int n=ps.executeUpdate();
          if(n==1){
            JOptionPane.showMessageDialog(null, "Enrollment and password are removed");
          }else{
            JOptionPane.showMessageDialog(null, "Enrollment and password are already removed");
          }
        } catch (Exception ex) {
           System.out.println(ex);
           JOptionPane.showMessageDialog(null, "Enrollment and password are already removed");
        }
        
        }
});

button2.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {
       frame.dispose();
    }
});


button2.setBounds(230,330,200,20);
button.setBounds(230,300,200,20);
l1.setBounds(10,100,200,20);

t1.setBounds(230,100,200,20);


frame.add(button2);
frame.add(l1);

frame.add(t1);

frame.add(button);
frame.setLayout(null);
frame.setBounds(300, 300, 500, 500);
            frame.setVisible(true); 
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    static boolean showdetails(){
        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM INFO");

        JFrame frame = new JFrame("REGISTERED STUDENT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a DefaultTableModel with some data
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Enroll");
        model.addColumn("Password");
       
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Populate the table with data
        while (rs.next()) {
            String enroll = rs.getString("enroll");
            String checkd = rs.getString("password");
            model.addRow(new Object[] { enroll, checkd});
        }

        JScrollPane scrollPane = new JScrollPane(table);

        // Create a panel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton button = new JButton("Close");
        buttonPanel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        // Add components to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setBounds(300, 300, 400, 400);
        frame.setVisible(true);

    } catch (Exception e) {
        System.out.println(e);
    }
    return true;
  
    }

    static boolean table() throws Exception {
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

            // Create a panel for the button
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton button = new JButton("Close");
            buttonPanel.add(button);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            // Add components to the frame
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.setBounds(300, 300, 400, 400);
            frame.setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    static boolean checkpass(String n) throws Exception {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
        Statement st = con.createStatement();
        String passwordToCheck = n; // Replace this with the enrollment number you want to check
        String checkQuery = "SELECT * FROM INFO WHERE PASSWORD = '" + passwordToCheck + "'";
        ResultSet rs = st.executeQuery(checkQuery);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    static int add(String s1, String s2, String s3) throws Exception {
        String enroll = s1;
        String currentt = s2;
        String currentd = s3;

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");

        Statement st = con.createStatement();

        // The SQL query with placeholders
        String query = "INSERT INTO CHECK_TABLE VALUES(?, ?, ?)";

        // Creating a prepared statement with the query
        PreparedStatement ps = con.prepareStatement(query);

        // Setting values for placeholders
        ps.setString(1, enroll);
        ps.setString(2, currentt);
        ps.setString(3, currentd);

        // Executing the update query
        int n = ps.executeUpdate();

        // Printing the number of rows affected
        System.out.println(n);

        // Returning true if one row was affected, false otherwise
        return n;
    }

    public static void main(String[] args) throws Exception {

    }
}
