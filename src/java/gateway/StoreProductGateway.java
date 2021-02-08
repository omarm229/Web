package gateway;

import dto.StoreProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import manager.DBManager;


public class StoreProductGateway
{
    public ArrayList<StoreProductDTO> viewAllProducts(int storeID)
    {
        ArrayList<StoreProductDTO> products= new ArrayList<>();
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT Store_Product.Store_Product_ID_PK, Store_Product.NumAvailable, Store_Product.PRICE, Store_Product.DESCRIPTION, Store_Product.Product_ID_FK, Store_Product.Store_ID_FK, PRODUCT.NAME FROM Store_Product INNER JOIN PRODUCT ON Store_Product.PRODUCT_ID_FK = PRODUCT.PRODUCT_ID_PK WHERE STORE_ID_FK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, storeID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                StoreProductDTO product = new StoreProductDTO(
                        rs.getInt("Store_Product_ID_PK"),
                        rs.getInt("NumAvailable"),
                        rs.getFloat("Price"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("NAME"),
                        rs.getInt("Product_ID_FK"),
                        rs.getInt("Store_ID_FK"));
                        
               products.add(product);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return products;
    }
        
    public boolean deleteProduct(int productID)
    {
        
        try
        {
            Connection conn = DBManager.getConnection();
            
            String sqlStr1 = "Delete * FROM ORDERLINE WHERE Store_Product_ID_FK = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sqlStr1);
            stmt1.setInt(1, productID);
            stmt1.executeUpdate();
            
            stmt1.close();

            String sqlStr = "Delete * FROM Store_Product WHERE Store_Product_ID_PK = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            stmt.setInt(1, productID);
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return true;
    }
}
