package gateway;

import dto.CustomerDTO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.DBManager;


public class CustomerGateway
{

     public int getId(String userName)
    {
        int id = 0;
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT CUSTOMER_ID_PK FROM CUSTOMER WHERE USERNAME = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                id = rs.getInt("CUSTOMER_ID_PK");        
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return id;
    
    }
     
    public CustomerDTO viewCustomer(int CustID)
    {
        CustomerDTO customerDetails = null;
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT * "
                    + "FROM Customer "
                    + "WHERE Customer_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, CustID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                customerDetails = new CustomerDTO(
                        rs.getInt("Customer_ID_PK"),
                        rs.getString("Name"),
                        rs.getString("UserName"),
                        rs.getString("Address"),
                        rs.getString("Password"),
                        rs.getString("Country"),
                        rs.getString("PostCode"));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return customerDetails;
    }

    public CustomerDTO editCustomer(int custID, String name, String userName, String address, String country, String password, String postCode) throws NoSuchAlgorithmException
    {
        CustomerDTO customerDetails = null;
        try
        {
            byte[] hash
                        = MessageDigest.getInstance("SHA-256")
                                .digest(password.getBytes(StandardCharsets.UTF_8));

                password = Base64.getEncoder().encodeToString(hash);
                
            Connection conn = DBManager.getConnection();

            String sqlStr = "Update Customer SET Name = ?, UserName = ?,  Address = ?, Country = ?,  Password = ?, PostCode = ? WHERE Customer_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setString(1, name);
            stmt.setString(2, userName);
            stmt.setString(3, address);
            stmt.setString(4, country);
            stmt.setString(5, password);
            stmt.setString(6, postCode);
            stmt.setInt(7, custID);
            
            
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        try
        {
           
            Connection conn = DBManager.getConnection();
            
            String sqlStr1 = "SELECT * FROM Customer WHERE Customer_ID_PK = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sqlStr1);
            stmt1.setInt(1, custID);
            ResultSet rs = stmt1.executeQuery();

            if (rs.next())
            {
                customerDetails = new CustomerDTO(
                        rs.getInt("Customer_ID_PK"),
                        rs.getString("UserName"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Password"),
                        rs.getString("Country"),
                        rs.getString("PostCode"));
            }

            rs.close();
            stmt1.close();
            conn.close();
            
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return customerDetails;
    }
    


    public Boolean registerCustomer(String name, String userName, String address, String country, String password1, String password2, String postCode) throws NoSuchAlgorithmException
    {
        boolean dataOK = false;

        if (password1.equals(password2))
        {
            try
            {
                byte[] hash
                        = MessageDigest.getInstance("SHA-256")
                                .digest(password1.getBytes(StandardCharsets.UTF_8));

                password1 = Base64.getEncoder().encodeToString(hash);
                
                DriverManager.registerDriver(
                new org.apache.derby.jdbc.ClientDriver());
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/A023572G", "A023572G", "A023572G");
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Customer (userName, password, name, postcode, country, address) values (?, ?, ?, ?, ?, ?)");
                stmt.setString(1, userName);
                stmt.setString(2, password1);
                stmt.setString(3, name);
                stmt.setString(4, postCode);
                stmt.setString(5, country);
                stmt.setString(6, address);
            
                int rows = stmt.executeUpdate();
                
                dataOK = true;

                stmt.close();
                conn.close();
            }
            catch (SQLException sqle)
            {
                sqle.printStackTrace();
            }
            
        }
        return dataOK;
    }
    public boolean loginCustomer(String userName, String password)
    {
        boolean credentialsOK = false;
        try
        {
            Connection conn = DBManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customer WHERE userName = ?");
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            credentialsOK = rs.next() && rs.getString("password").equals(password);

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            Logger.getLogger(CustomerGateway.class.getName()).log(Level.SEVERE, e.toString());
        }

        
        return credentialsOK;
        

    }
    
     public boolean deleteCustomer(int custID)
    {
        
        try
        {
            Connection conn = DBManager.getConnection();

            String sqlStr = "Delete * FROM Customer WHERE Customer_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, custID);
            stmt.executeUpdate();
            
          
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return true;
    }
     
     public ArrayList<CustomerDTO> viewAllCustomers()
    {
        ArrayList<CustomerDTO> allCust = new ArrayList<>();
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT * FROM CUSTOMER";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
               CustomerDTO cust = new CustomerDTO(
                        rs.getInt("Customer_ID_PK"),
                        rs.getString("Name"),
                        rs.getString("UserName"),
                        rs.getString("Address"),
                        rs.getString("Country"),
                        rs.getString("Password"),
                        rs.getString("PostCode"));
                        
                allCust.add(cust);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return allCust;
    }

}
