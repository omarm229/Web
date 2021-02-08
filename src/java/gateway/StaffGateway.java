package gateway;

import dto.StaffDTO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import manager.DBManager;


public class StaffGateway
{

    public int getId(String userName)
    {
        int id = 0;
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT STAFF_ID_PK FROM Staff WHERE USERNAME = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                id = rs.getInt("Staff_ID_PK");        
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
    
    public StaffDTO viewStaff(int StaffID)
    {
        StaffDTO staffDetails = null;
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT * FROM Staff WHERE Staff_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, StaffID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                staffDetails = new StaffDTO(
                        rs.getInt("Staff_ID_PK"),
                        rs.getString("UserName"),
                        rs.getString("Name"),
                        rs.getInt("Store_ID_FK"),
                        rs.getString("Password"),
                        rs.getInt("JobApplicant_ID_FK"),
                        rs.getString("PhoneNumber"));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return staffDetails;
    }
    
    
   
    public StaffDTO editStaff(int staffID, String name, String userName, String password, String phoneNumber) throws NoSuchAlgorithmException
    {
        StaffDTO staffDetails = null;
        try
        {
            byte[] hash
                        = MessageDigest.getInstance("SHA-256")
                                .digest(password.getBytes(StandardCharsets.UTF_8));

                password = Base64.getEncoder().encodeToString(hash);
            Connection conn = DBManager.getConnection();

            String sqlStr = "UPDATE Staff SET Name = ?, UserName = ?, Password = ?, PhoneNumber = ? WHERE Staff_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setString(1, name);
            stmt.setString(2, userName);
            stmt.setString(3, password);
            stmt.setString(4, phoneNumber);
            stmt.setInt(5, staffID);
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
            
            String sqlStr1 = "SELECT * FROM Staff WHERE Staff_ID_PK = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sqlStr1);
            stmt1.setInt(1, staffID);
            ResultSet rs = stmt1.executeQuery();

            if (rs.next())
            {
                staffDetails = new StaffDTO(
                        rs.getInt("Staff_ID_PK"),
                        rs.getString("UserName"),
                        rs.getString("Name"),
                        rs.getInt("Store_ID_FK"),
                        rs.getString("Password"),
                        rs.getInt("JobApplicant_ID_FK"),
                        rs.getString("PhoneNumber"));
            }

            rs.close();
            stmt1.close();
            conn.close();
            
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return staffDetails;
    }


    public boolean registerStaff(String userName, String name, int storeID, String password1, String password2, int jobApplicantID, String phoneNumber) throws NoSuchAlgorithmException
    {
        boolean dataOK = false;
        int store = 0;

        if (password1.equals(password2))
        {
            try
            {
                byte[] hash
                        = MessageDigest.getInstance("SHA-256")
                                .digest(password1.getBytes(StandardCharsets.UTF_8));

                password1 = Base64.getEncoder().encodeToString(hash);
        
            Connection conn = DBManager.getConnection();
            
            PreparedStatement stmt1 = conn.prepareStatement("SELECT STORE_ID_FK FROM JOBAPPLICANT WHERE JOBAPPLICANT_ID_PK = ?");
            stmt1.setInt(1, jobApplicantID);
            ResultSet rs = stmt1.executeQuery();
            
            if (rs.next()) {
                store=rs.getInt("STORE_ID_FK");
            }
            rs.close();
            stmt1.close();
            conn.close();
            }
            catch (SQLException sqle)
            {
                sqle.printStackTrace();
            }
            
           if(store==storeID){

                    try
                    {
                         Connection conn = DBManager.getConnection();
                        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Staff (name, userName, Store_ID_FK, JobApplicant_ID_FK, Password, PhoneNumber) values (?, ?, ?, ?, ?, ?)");
                        stmt.setString(1, name);
                        stmt.setString(2, userName);
                        stmt.setInt(3, storeID);
                        stmt.setInt(4, jobApplicantID);
                        stmt.setString(5, password1);
                        stmt.setString(6, phoneNumber);
                        
                        int rows = stmt.executeUpdate();
                        
                        dataOK = rows == 1;
                        stmt.close();
                    
                
              
                conn.close();
            }
            
            catch (SQLException sqle)
            {
                sqle.printStackTrace();
            }
            } 
       
        }
        
        return dataOK;
    }

    public boolean loginStaff(String userName, String password, int storeID)
    {
        boolean credentialsOK = false;
        try
        {
            Connection conn = DBManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Staff WHERE userName = ? AND STORE_ID_FK = ?");
            stmt.setString(1, userName);
            stmt.setInt(2, storeID);
            ResultSet rs = stmt.executeQuery();

            credentialsOK = rs.next() && rs.getString("password").equals(password) && rs.getInt("STORE_ID_FK") == (storeID);

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            Logger.getLogger(StaffGateway.class.getName()).log(Level.SEVERE, e.toString());
        }

       
            return credentialsOK;
        

    }
    
    public boolean deleteStaff(int staffID)
    {
        
        try
        {
            Connection conn = DBManager.getConnection();

            String sqlStr = "Delete * FROM Staff WHERE Staff_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, staffID);
            stmt.executeUpdate();
            
            
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return true;
    }
        
    public ArrayList<StaffDTO> viewAllStaff(int storeID)
    {
        ArrayList<StaffDTO> allStaff = new ArrayList<>();
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT * FROM Staff WHERE STORE_ID_FK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, storeID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                StaffDTO staff = new StaffDTO(
                        rs.getInt("Staff_ID_PK"),
                        rs.getString("UserName"),
                        rs.getString("Name"),
                        rs.getInt("Store_ID_FK"),
                        rs.getString("Password"),
                        rs.getInt("JobApplicant_ID_FK"),
                        rs.getString("PhoneNumber"));
                        
                allStaff.add(staff);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return allStaff;
    }
}

