package br.com.peixeurbano.challenge.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BuyOption {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String title;

    private Double normalPrice;

    private Double salePrice;

    private Double percentageDiscount;

    private long quantityCupom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "deal_id")
    private Deal deal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Double normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(Double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public long getQuantityCupom() {
        return quantityCupom;
    }

    public void setQuantityCupom(long quantityCupom) {
        this.quantityCupom = quantityCupom;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public boolean isVisible() {
        Date today = new Date();
        return this.startDate.compareTo(today) * today.compareTo(this.endDate) > 0;
    }

    public boolean isOutOfStock(){
        return Long.compare(this.quantityCupom, 0) == 0;
    }

    public void removeCupom(){
        this.quantityCupom--;
    }

    public void calculateSaleOptionPrice(){
        double salePrice = ((100.00 - this.getPercentageDiscount().doubleValue()) * this.getNormalPrice().doubleValue())/100.00;
        this.salePrice = new Double(salePrice);
    }
}
