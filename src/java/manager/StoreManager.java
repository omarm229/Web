/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dto.StoreDTO;
import java.util.ArrayList;
import gateway.StoreGateway;

/**
 *
 * @author a023572g
 */
public class StoreManager
{
    
    private final StoreGateway gateway = new StoreGateway();
    public ArrayList<StoreDTO> viewStore()
    {
         return gateway.viewStore();
    }
}
