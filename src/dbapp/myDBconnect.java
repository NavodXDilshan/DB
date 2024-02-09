/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbapp;

import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author Admin
 */


public class myDBconnect {
    void myDBconnect(String Fname, String Minit, String Lname, String Ssn, String Bdate, String Address, String Sex, String Salary, 
            String Super_ssn, String Dno) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp", "root", "");
            Statement st = con.createStatement();
            
            String salaryString = Salary;
            int salary = Integer.parseInt(salaryString);
            
           String query = "INSERT INTO `employee details` (`Fname`, `Minit`, `Lname`, `Ssn`, `Bdate`, `Address`, `Sex`, `Salary`,"
                   + " `Super_ssn`, `Dno`)"
                    + " VALUES ('" + Fname + "', '" + Minit + "', '" + Lname + "', '" + Ssn + "', '" + Bdate + "', '" + Address + 
                   "', '" + Sex + "', " + salary + ", '" + Super_ssn + "', '" + Dno + "')";

            st.executeUpdate(query);
            con.close();
            JOptionPane.showMessageDialog(null, "Successfully Added");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void deleteEmployee(String Ssn) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp", "root", "");
            Statement st = con.createStatement();

            String query = "DELETE FROM `employee details` WHERE `Ssn` = '" + Ssn + "'";
            int rowsAffected = st.executeUpdate(query);

            con.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Employee deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No employee found with the given ID");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
 
public void updateEmployee(String Fname, String Minit, String Lname, String Ssn, String Bdate, String Address, String Sex,
        String Salary, String Super_ssn, String Dno) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp", "root", "");

        String salaryString = Salary;
        int salary = Integer.parseInt(salaryString);

        String query = "UPDATE `employee details` SET `Fname` = ?, `Minit` = ?, `Lname` = ?, `Ssn` = ?, `Bdate` = ?,"
                + " `Address` = ?, `Sex` = ?, `Salary` = ?, `Super_ssn` = ?, `Dno` = ? WHERE `Ssn` = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, Fname);
        statement.setString(2, Minit);
        statement.setString(3, Lname);
        statement.setString(4, Ssn);
        statement.setString(5, Bdate);
        statement.setString(6, Address);
        statement.setString(7, Sex);
        statement.setInt(8, salary);
        statement.setString(9, Super_ssn);
        statement.setString(10, Dno);
        statement.setString(11, Ssn);

        int rowsAffected = statement.executeUpdate();

        con.close();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Data updated successfully");
        } else {
            JOptionPane.showMessageDialog(null, "No employee found with the given ID");
        }
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
   


