package gateway;

import dto.OrderLineDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import manager.DBManager;

public class OrderGateway
{
    public int createOrderLine(int custID, int prodID)
    {
        
        int orderID = 0, prodID1 , order,  num , numAvailable = 0;
        float total, price = 0; 
        try
        {
            Connection conn = DBManager.getConnection();
            
            PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM CUSTOMERORDER WHERE CUSTOMER_ID_FK = ?");
            stmt1.setInt(1, custID);
            ResultSet rs = stmt1.executeQuery();
            if(rs.next())
            {
                orderID = rs.getInt("CUSTOMERORDER_ID_PK");
            }
            else
            {
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO CUSTOMERORDER (TOTAL, CUSTOMER_ID_FK) values ( 0, ?)");
                stmt2.setInt(1, custID);
                stmt2.executeUpdate();
                stmt1.setInt(1, custID);
                rs = stmt1.executeQuery();
                if(rs.next())
                {
                orderID = rs.getInt("CUSTOMERORDER_ID_PK");
                }
                stmt2.close();
            }
            
            PreparedStatement stmt3 = conn.prepareStatement("SELECT ORDERLINE.STORE_PRODUCT_ID_FK, ORDERLINE.NUMOFITEM, ORDERLINE.ORDERLINE_ID_PK, STORE_PRODUCT.PRICE, STORE_PRODUCT.NUMAVAILABLE  FROM ORDERLINE INNER JOIN STORE_PRODUCT ON ORDERLINE.STORE_PRODUCT_ID_FK = STORE_PRODUCT.STORE_PRODUCT_ID_PK WHERE CUSTOMERORDER_ID_FK = ?");
            stmt3.setInt(1, orderID);
            ResultSet rs1 = stmt3.executeQuery();
            while(rs1.next())
            {
                prodID1 = rs1.getInt("STORE_PRODUCT_ID_FK");
                order = rs1.getInt("ORDERLINE_ID_PK");
                if(prodID1==prodID)
                {
                    
                    num = rs1.getInt("NUMOFITEM");
                    numAvailable = rs1.getInt("NUMAVAILABLE");
                    price = rs1.getFloat("PRICE");
                    total = price * (num+1);
                    
                    PreparedStatement stmt4 = conn.prepareStatement("Update OrderLine SET Store_Product_ID_FK = ?, numOfItem = ?, TOTAL = ?, DATEORDERED = '13/01/20' WHERE OrderLine_ID_PK = ?");
                    stmt4.setInt(1, prodID);
                    stmt4.setInt(2, (num+1));
                    stmt4.setFloat(3, total);
                    stmt4.setInt(4, order);
                    stmt4.executeUpdate();
                    
                    PreparedStatement stmt7 = conn.prepareStatement("Update STORE_PRODUCT SET NUMAVAILABLE = ? WHERE Store_Product_ID_PK = ?");
                    stmt7.setInt(1, (numAvailable - 1));
                    stmt7.setInt(2, prodID);
                    stmt7.executeUpdate();
                    
                    stmt4.close();
                    stmt7.close();
                    return orderID;
                }
            }
            PreparedStatement stmt6 = conn.prepareStatement("SELECT PRICE, NUMAVAILABLE  FROM STORE_PRODUCT WHERE STORE_PRODUCT_ID_PK = ?");
            stmt6.setInt(1, prodID);
            ResultSet rs2 = stmt6.executeQuery();
            if(rs2.next())
            {
                numAvailable = rs2.getInt("NUMAVAILABLE");
                price = rs2.getFloat("PRICE");
            }
            
            PreparedStatement stmt8 = conn.prepareStatement("Update STORE_PRODUCT SET NUMAVAILABLE = ? WHERE Store_Product_ID_PK = ?");
            stmt8.setInt(1, (numAvailable - 1));
            stmt8.setInt(2, prodID);
            stmt8.executeUpdate();
                    
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO OrderLine (total, CustomerOrder_ID_FK, Store_Product_ID_FK, numOfItem, DATEORDERED ) values ( ?, ?, ?, 1, '13/01/20')");
            stmt.setFloat(1, price);
            stmt.setInt(2, orderID);
            stmt.setInt(3, prodID);
            
            int rows = stmt.executeUpdate();

            rs.close();
            rs1.close();
            stmt.close();
            stmt3.close();
            stmt1.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return orderID;
    }
    
     public boolean deleteOrderLine(int orderID)
    {
        
        try
        {
            Connection conn = DBManager.getConnection();
            
            String sqlStr1 = "SELECT OrderLine.CustomerOrder_ID_PK, ORDERLINE.DateOrdered, ORDERLINE.Total, ORDERLINE.CustomerOrder_ID_FK, ORDERLINE.Store_Product_ID_FK, ORDERLINE.NumOfItems, STORE_PRODUCT.PRICE, STORE_PRODUCT.NUMAVAILABLE FROM OrderLine INNER JOIN STORE_PRODUCT ON ORDERLINE.STORE_PRODUCT_ID_FK = STORE_PRODUCT.STORE_PRODUCT_ID_PK  WHERE CUSTOMERORDER_ID_FK = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sqlStr1);
            stmt1.setInt(1, orderID);
            ResultSet rs = stmt1.executeQuery();
            if(rs.next())
            {
               int prodID = rs.getInt("STORE_PRODUCT_ID_FK");
               
               PreparedStatement stmt8 = conn.prepareStatement("Update STORE_PRODUCT SET NUMAVAILABLE = ? WHERE Store_Product_ID_PK = ?");
               stmt8.setInt(1, 50);
               stmt8.setInt(2, prodID);
               stmt8.executeUpdate();
            }

            String sqlStr = "Delete * FROM OrderLine WHERE OrderLine_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, orderID);
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return true;
    }
     
    public OrderLineDTO editOrderLine(int orderID, int numOfItem)
    {
        OrderLineDTO orderDetails = null;
        int numAvailable = 0, prodID = 0;
        float price = 0;
         try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT OrderLine.CustomerOrder_ID_PK, ORDERLINE.DateOrdered, ORDERLINE.Total, ORDERLINE.CustomerOrder_ID_FK, ORDERLINE.Store_Product_ID_FK, ORDERLINE.NumOfItems, STORE_PRODUCT.PRICE, STORE_PRODUCT.NUMAVAILABLE FROM OrderLine INNER JOIN STORE_PRODUCT ON ORDERLINE.STORE_PRODUCT_ID_FK = STORE_PRODUCT.STORE_PRODUCT_ID_PK  WHERE CUSTOMERORDER_ID_FK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                price = rs.getFloat("PRICE");
                numAvailable = rs.getInt("NUMAVAILABLE");
                prodID = rs.getInt("STORE_PRODUCT_ID_FK");
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        try
        {
            Connection conn = DBManager.getConnection();
            
            PreparedStatement stmt8 = conn.prepareStatement("Update STORE_PRODUCT SET NUMAVAILABLE = ? WHERE Store_Product_ID_PK = ?");
            stmt8.setInt(1, (numAvailable - numOfItem));
            stmt8.setInt(2, prodID);
            stmt8.executeUpdate();

            String sqlStr = "Update OrderLine SET numOfItem = ?, TOTAL = ? WHERE OrderLine_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, numOfItem);
            stmt.setFloat(2, (price*numOfItem));
            stmt.setInt(3, orderID);
            stmt.executeUpdate();
            
            
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
         try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT OrderLine.CustomerOrder_ID_PK, ORDERLINE.DateOrdered, ORDERLINE.Total, ORDERLINE.CustomerOrder_ID_FK, ORDERLINE.Store_Product_ID_FK, ORDERLINE.NumOfItems, STORE_PRODUCT.NAME FROM OrderLine INNER JOIN STORE_PRODUCT ON ORDERLINE.STORE_PRODUCT_ID_FK = STORE_PRODUCT.STORE_PRODUCT_ID_PK  WHERE CUSTOMERORDER_ID_FK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                orderDetails = new OrderLineDTO(
                        rs.getInt("ORDERLINE_ID_PK"),
                        rs.getString("DateOrdered"),
                        rs.getString("NAME"),
                        rs.getFloat("Total"),
                        rs.getInt("CustomerOrder_ID_FK"),
                        rs.getInt("Store_Product_ID_FK"),
                        rs.getInt("NumOfItems"));
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return orderDetails;
    }
     
     public ArrayList<OrderLineDTO> viewOrder(int custID)
    {
        ArrayList<OrderLineDTO> allOrderDetails = new ArrayList<>();
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT OrderLine.CustomerOrder_ID_PK, ORDERLINE.DateOrdered, ORDERLINE.Total, ORDERLINE.CustomerOrder_ID_FK, ORDERLINE.Store_Product_ID_FK, ORDERLINE.NumOfItems, STORE_PRODUCT.NAME FROM OrderLine INNER JOIN STORE_PRODUCT ON ORDERLINE.STORE_PRODUCT_ID_FK = STORE_PRODUCT.STORE_PRODUCT_ID_PK  WHERE CUSTOMERORDER_ID_FK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, custID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                OrderLineDTO orderDetails = new OrderLineDTO(
                        rs.getInt("ORDERLINE_ID_PK"),
                        rs.getString("DateOrdered"),
                        rs.getString("NAME"),
                        rs.getFloat("Total"),
                        rs.getInt("CustomerOrder_ID_FK"),
                        rs.getInt("Store_Product_ID_FK"),
                        rs.getInt("NumOfItems"));
                allOrderDetails.add(orderDetails);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return allOrderDetails;
    }
     
     public ArrayList<OrderLineDTO> viewSales(String month)
    {
        ArrayList<OrderLineDTO> sales = new ArrayList<>();
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT OrderLine.CustomerOrder_ID_PK, ORDERLINE.DateOrdered, ORDERLINE.Total, ORDERLINE.CustomerOrder_ID_FK, ORDERLINE.Store_Product_ID_FK, ORDERLINE.NumOfItems, STORE_PRODUCT.NAME FROM OrderLine INNER JOIN STORE_PRODUCT ON ORDERLINE.STORE_PRODUCT_ID_FK = STORE_PRODUCT.STORE_PRODUCT_ID_PK  WHERE DATEORDERED = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setString(1, month);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                OrderLineDTO orderDetails = new OrderLineDTO(
                        rs.getInt("ORDERLINE_ID_PK"),
                        rs.getString("DateOrdered"),
                        rs.getString("NAME"),
                        rs.getFloat("Total"),
                        rs.getInt("CustomerOrder_ID_FK"),
                        rs.getInt("Store_Product_ID_FK"),
                        rs.getInt("NumOfItems"));
                sales.add(orderDetails);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return sales;
    }

}
