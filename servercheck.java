import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class servercheck {
    JFrame frame = new JFrame();
    JLabel l1 = new JLabel("Receive : ");
    JLabel l2 = new JLabel("Send : ");
    JTextField t1 = new JTextField();
    JTextField t2 = new JTextField();
    JButton button = new JButton("Send");
    ServerSocket serverSocket;
    Socket clientSocket;
    String[] s = { "1", "2", "3" };
    PrintWriter out;
    Scanner in;
    int c;

    servercheck() {

        l1.setBounds(10, 100, 200, 20);
        l1.setForeground(Color.WHITE);
        l2.setBounds(10, 200, 200, 20);
        l2.setForeground(Color.WHITE);
        t1.setBounds(230, 100, 200, 20);
        t2.setBounds(230, 200, 200, 20);
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
                    String message = t2.getText();
                    out.println(message);
                    t2.setText("");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });

        try {
            serverSocket = new ServerSocket(4444);
            clientSocket = serverSocket.accept();
            in = new Scanner(clientSocket.getInputStream());
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            new Thread(() -> {
                while (true) {
                    if (in.hasNextLine()) {
                        String receivedMessage = in.nextLine();

                        for (String string : s) {
                            if (string.equals(receivedMessage)) {
                                t1.setText(receivedMessage);
                                out.println(receivedMessage);
                                c++;
                            }
                        }

                    }
                }
            }).start();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

    }

    public static void main(String[] args) {
        new servercheck();

    }
}