/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.area;

/**
 *
 * @author Admin
 */
public class AreaDTO {
    private String areaCode;
    private String area;

    public AreaDTO(String areaCode, String area) {
        this.areaCode = areaCode;
        this.area = area;
    }

    public AreaDTO() {
    }

    
    /**
     * @return the areaCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode the areaCode to set
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }
    
}
