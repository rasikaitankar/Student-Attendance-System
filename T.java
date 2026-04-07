import java.sql.*;

public class T {
    public static void main(String[] args) throws Exception{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "123456");
        Statement st = con.createStatement();
        

    }
}
