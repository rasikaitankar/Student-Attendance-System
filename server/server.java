import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.*;

public class server {

    JFrame frame = new JFrame();
    JLabel l1 = new JLabel("");
    JButton button = new JButton("Show Details");
    JButton button2 = new JButton("+ Add New Candidate");
    JButton button3 = new JButton("- Remove Candidate");
    JButton button4 = new JButton("Show student registered");

    JTextField t1 = new JTextField();
    String[] enroll = { "1", "2", "3", "4", "77" };
    String[] password = { "1", "2", "3", "4" };

    ServerSocket serverSocket;
    String s;
    int c = 0;
    int p;
    Socket clientSocket;
    PrintWriter out;
    Scanner in;

    server() {

        l1.setBounds(10, 200, 200, 20);
        button4.setBounds(70, 100, 200, 20);
        button.setBounds(70, 150, 200, 20);
        button2.setBounds(70, 200, 200, 20);
        button3.setBounds(70,250,200,20);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                database d = new database();
                try {

                    
                    boolean t = d.table();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        });
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                database d = new database();
                try {

                    
                    boolean t = d.addCandidate();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                database d = new database();
                try {
                    boolean t = d.removeCandidate();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        button4.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                database d = new database();
                try {
                    boolean t = d.showdetails();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                
            }
        });
        frame.add(button);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(l1);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setBounds(300, 300, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            serverSocket = new ServerSocket(4441);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ServerThread(clientSocket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new server();
    }

    class ServerThread extends Thread {
        private Socket clientSocket;

        ServerThread(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                database d = new database();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Scanner in = new Scanner(clientSocket.getInputStream());
                if (c == 1) {
                    String s1 = in.nextLine();
                    String s2 = in.nextLine();
                    String s3 = in.nextLine();
                    int t = d.add(s1, s2, s3);
                    c = 0;
                    System.out.println(t);
                    out.println(t);
                } else {
                    String s = in.nextLine();
                    String t = in.nextLine();
                    boolean founde = d.checkenroll(s), foundp = d.checkpass(t);
                    if (founde && foundp) {
                        c = 1;
                        out.println("1");
                    } else {
                        out.println("0");
                    }
                }
                out.close();
                in.close();
                clientSocket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
