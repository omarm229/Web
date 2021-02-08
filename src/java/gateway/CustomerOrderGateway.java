package gateway;

import dto.CustomerOrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import manager.DBManager;

public class CustomerOrderGateway
{
    public boolean createOrder(CustomerOrderDTO order)
    {
        boolean insertOK = false;
        try
        {
            Connection conn = DBManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO CustomerOrder (CustomerOrder_ID_PK, total, Customer_ID_FK) values ( ?, ?, ?)");
            stmt.setInt(1, order.getId());
            stmt.setFloat(2, order.getTotal());
            stmt.setInt(3, order.getCustomerID());
            
            int rows = stmt.executeUpdate();
            
            insertOK = rows == 1;

            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return insertOK;
    }
    
    public boolean deleteOrder(int orderID)
    {
        boolean deleteOK = false;
        try
        {
            Connection conn = DBManager.getConnection();
            
            String sqlStr1 = "Delete * FROM OrderLine WHERE CustomerOrder_ID_FK = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sqlStr1);
            stmt1.setInt(1, orderID);
            stmt1.executeUpdate();

            String sqlStr = "Delete * FROM CustomerOrder WHERE CustomerOrder_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, orderID);
            stmt.executeUpdate();
            
            deleteOK = true;
            
            stmt.close();
            stmt1.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return deleteOK;
    }
     
    public CustomerOrderDTO viewOrder(int orderID)
    {
        CustomerOrderDTO orderDetails = null;
        int total = 0;
        try
        {
            

            Connection conn = DBManager.getConnection();
            
            PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM ORDERLINE WHERE CUSTOMERORDER_ID_FK = ?");
            stmt3.setInt(1, orderID);
            ResultSet rs1 = stmt3.executeQuery();
            while(rs1.next())
            {
                total = rs1.getInt("TOTAL") + total;
            }
            
            PreparedStatement stmt4 = conn.prepareStatement("Update CUSTOMERORDER SET TOTAL = ? WHERE CUSTOMERORDER_ID_PK = ?");
            stmt4.setInt(1, total);
            stmt4.setInt(2, orderID);
            stmt4.executeUpdate();

            String sqlStr = "SELECT * "
                    + "FROM CustomerOrder "
                    + "WHERE CustomerOrder_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                orderDetails = new CustomerOrderDTO(
                        rs.getInt("CustomerOrder_ID_PK"),
                        rs.getFloat("Total"),
                        rs.getInt("Customer_ID_FK"));
            }
            
            rs1.close();
            stmt3.close();
            stmt4.close();
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return orderDetails;
    }
    
    

}
