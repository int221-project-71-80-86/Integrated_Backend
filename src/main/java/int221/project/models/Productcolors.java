/*
 * Java bean class for entity "productcolors" 
 * Created on 2021-04-22 ( Date ISO 2021-04-22 - Time 15:23:42 )
 * Generated by Telosys Tools Generator ( version 3.1.2 )
 */

package int221.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Productcolors 
{
	@Id
    private String     productcode  ; // Id or Primary Key
    private int        colorid      ; // Id or Primary Key

	public void setProductcode( String productcode ) {
        this.productcode = productcode ;
    }

	public String getProductcode() {
        return this.productcode;
    }

	public void setColorid( int colorid ) {
        this.colorid = colorid ;
    }

	public int getColorid() {
        return this.colorid;
    }

}
