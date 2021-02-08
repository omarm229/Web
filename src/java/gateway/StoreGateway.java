/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gateway;

import dto.StoreDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import manager.DBManager;

/**
 *
 * @author a023572g
 */
public class StoreGateway
{
    public ArrayList<StoreDTO> viewStore()
    {
        ArrayList<StoreDTO> stores = new ArrayList<>();
        try
        {

            Connection conn = DBManager.getConnection();

            String sqlStr = "SELECT * FROM A023572G.Store";
            PreparedStatement stmt = conn.prepareStatement(sqlStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                StoreDTO store = new StoreDTO(
                        rs.getInt("Store_ID_PK"),
                        rs.getString("Name"),
                        rs.getString("Country"),
                        rs.getString("PostCode"));
                stores.add(store);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch (SQLException sqle)
        {
        }
        return stores;
    }
}
