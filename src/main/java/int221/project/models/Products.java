package int221.project.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Products
{
	@Id
    private String     productcode  ; // Id or Primary Key

    private String name ;
    private String desciption ;
    private double price ;
    private Date date ;
    private String url ;
    private double warranty ;
    private int brandid ;
    

	public void setProductcode( String productcode ) {
        this.productcode = productcode ;
    }

	public String getProductcode() {
        return this.productcode;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDesciption( String desciption ) {
        this.desciption = desciption;
    }

    public String getDesciption() {
        return this.desciption;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setWarranty( double warranty ) {
        this.warranty = warranty;
    }

    public double getWarranty() {
        return this.warranty;
    }

    public void setBrandid( int brandid ) {
        this.brandid = brandid;
    }

    public int getBrandid() {
        return this.brandid;
    }

}
