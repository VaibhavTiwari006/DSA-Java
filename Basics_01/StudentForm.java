package Basics_01;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StudentForm extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField t1, t2;
    JButton b1;

    StudentForm() {

        l1 = new JLabel("Name");
        l1.setBounds(50, 50, 100, 30);

        l2 = new JLabel("Roll No");
        l2.setBounds(50, 100, 100, 30);

        t1 = new JTextField();
        t1.setBounds(150, 50, 150, 30);

        t2 = new JTextField();
        t2.setBounds(150, 100, 150, 30);

        b1 = new JButton("Submit");
        b1.setBounds(120, 160, 100, 30);

        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(b1);

        b1.addActionListener(this);

        setSize(400, 300);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "password");

            PreparedStatement ps = con.prepareStatement(
                    "insert into students values(?,?)");

            ps.setString(1, t1.getText());
            ps.setString(2, t2.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Record Inserted Successfully");

            con.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        new StudentForm();
    }
}