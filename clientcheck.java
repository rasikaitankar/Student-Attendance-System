import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class clientcheck {
    JFrame frame = new JFrame();
    JLabel l1 = new JLabel("Send : ");
    JLabel l2 = new JLabel("Receive : ");
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JButton button = new JButton("Send");
    Socket socket;
    PrintWriter out;
    Scanner in;
    JTextField t3 = new JTextField();

    clientcheck() {
        l1.setBounds(10, 100, 200, 20);
        l1.setForeground(Color.WHITE);
        l2.setBounds(10, 200, 200, 20);
        l2.setForeground(Color.WHITE);
        t1.setBounds(230, 100, 200, 20);
        t2.setBounds(230, 200, 200, 20);
        t2.setEditable(false);
        button.setBounds(100, 360, 100, 20);

        frame.add(l1);
        frame.add(l2);
        frame.add(t1);

        frame.add(t2);

        frame.add(button);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(300, 300, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String message = t1.getText();
                    out.println(message);
                    t1.setText("");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        try {
            socket = new Socket("localhost", 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

        new Thread(() -> {
            while (true) {
                if (in.hasNextLine()) {
                    String receivedMessage = in.nextLine();
                    t2.setText(receivedMessage);
                }
            }
        }).start();

    }

    public static void main(String[] args) {
        new clientcheck();

    }
}