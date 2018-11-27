/**
*@Author: yusha
*@Date: 2018-11-27
*/
package com.clubfactory.demo.test.pojo;

import lombok.Builder;

import java.io.Serializable;

/**
* @ClassName: Book
* @Description:
* @author yusha
* @date 2018-11-27
*/
@Builder
public class BookEntity implements Serializable{

    private static final long serialVersionUID = -1;

    /**
     * 
     */
	private Integer id;
    /**
     * 
     */
	private String name;
    /**
     * 
     */
	private Double price;
    /**
     * 
     */
	private String author;


    public void setId(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(Double value) {
        this.price = value;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setAuthor(String value) {
        this.author = value;
    }

    public String getAuthor() {
        return this.author;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ",name=" + name +
            ",price=" + price +
            ",author=" + author +
        '}';
    }
}

