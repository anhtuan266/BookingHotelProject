/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.bookingDetails;

import java.util.HashMap;
import java.util.Map;
import sample.combine.CombineDTO;

/**
 *
 * @author Admin
 */
public class bookingCartOBJ {
    private Map<CombineDTO,Integer> items;

    public bookingCartOBJ() {
    }

    public bookingCartOBJ(Map<CombineDTO, Integer> items) {
        this.items = items;
    }

   
    
    public void addItemToCart(CombineDTO item)
    {
        if ( this.getItems() == null)
        {
            this.setItems(new HashMap<>());
        }
        
        int quantity = 1;
        if (this.getItems().containsKey(item))
        {
            quantity = this.getItems().get(item) + 1;
        }
        this.getItems().put(item, quantity);
    }
    
    public void removeItemFromCart(String roomid)
    {
        if (this.getItems() == null)
        {
            return;
        }
        CombineDTO dto = new CombineDTO(roomid, "", "", "", "", "",0,0);
        if (this.getItems().containsKey(dto))
        {
            this.getItems().remove(dto);
            if (this.getItems().isEmpty())
            {
                this.setItems(null);
            }
        }
    }
    
    public void UpdateItemQuantity(String roomid,int quantity)
    {
        if (this.getItems() == null)
        {
            return;
        }
        CombineDTO dto = new CombineDTO(roomid, "", "", "", "", "",0,0);
        if (this.getItems().containsKey(dto))
        {
            this.getItems().replace(dto,quantity);
        }
    }

    /**
     * @return the items
     */
    public Map<CombineDTO,Integer> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Map<CombineDTO,Integer> items) {
        this.items = items;
    }


  
   
}
