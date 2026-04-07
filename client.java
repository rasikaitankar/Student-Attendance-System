import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.*;
import java.net.*;
import java.io.*;

public class client {

    JFrame frame = new JFrame();
    JLabel l1 = new JLabel("Enter Enrollment : ");
    JLabel l2 = new JLabel("Enter password : ");
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JTextField t3 = new JTextField();
    JTextField t4 = new JTextField();
    JButton button1 = new JButton("Check");
    JButton button2 = new JButton("Submit");
    JButton button3 = new JButton("Fill");
    Socket socket;
    PrintWriter out;
    Scanner in;
    boolean c = true;

    client() {
        t3.setEditable(true);
        l1.setBounds(10, 100, 200, 20);
        l1.setForeground(Color.WHITE);
        l2.setBounds(10, 200, 200, 20);
        l2.setForeground(Color.WHITE);
        t1.setBounds(230, 100, 200, 20);
        t2.setBounds(230, 200, 200, 20);
        t3.setBounds(10, 300, 80, 15);
        t4.setBounds(10, 320, 80, 15);
        button1.setBounds(300, 360, 100, 20);
        button2.setBounds(100, 360, 100, 20);
        button3.setBounds(100, 400, 100, 20);
        button3.setEnabled(false);
        button2.setEnabled(false);

        frame.add(l1);
        frame.add(l2);
        frame.add(t1);
        frame.add(t3);
        frame.add(t2);
        frame.add(t4);
        frame.add(button2);
        frame.add(button3);
        frame.add(button1);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(300, 300, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            socket = new Socket("localhost", 4441);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    socket = new Socket("localhost", 4441);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new Scanner(socket.getInputStream());
                    String message = t1.getText();
                    String password = t2.getText();
                    out.println(message);
                    out.println(password);
                    String response = in.nextLine();
                    if (response.equals("1")) {
                        JOptionPane.showMessageDialog(null, "Enrollment and password are valid!");
                      
                        button3.setEnabled(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid enrollment or password!");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // after clicking on button2 data should be transfer to server and store in
                // database
                try {
                    socket = new Socket("localhost", 4441);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new Scanner(socket.getInputStream());
                    out.println(t1.getText());
                    out.println(t3.getText());
                    out.println(t4.getText());
                    String res = in.nextLine();
                    if (res.equals("1")) {
                        JOptionPane.showMessageDialog(null, "data saved");
                    } else {
                        JOptionPane.showMessageDialog(null, "error" + res);
                    }
                } catch (Exception e) {

                }

            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                String s = currentDateTime.toString();
                t3.setText(s.substring(0, 10));
                t4.setText(s.substring(11, 19));
                button2.setEnabled(true);
            }
        });

    }

    public static void main(String[] args) {
        new client();
    }
}
