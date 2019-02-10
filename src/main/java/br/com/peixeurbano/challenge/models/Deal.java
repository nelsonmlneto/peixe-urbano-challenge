package br.com.peixeurbano.challenge.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Deal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    private String title;

    private String text;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String url;

    private long totalSold;

    private Integer validDays;

    @Enumerated(EnumType.STRING)
    private DealType type;

    @OneToMany(mappedBy = "deal", cascade = CascadeType.ALL)
    private Set<BuyOption> buyOptions;

    public Deal(){
        buyOptions = new HashSet<>();
    }

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(long totalSold) {
        this.totalSold = totalSold;
    }

    public DealType getType() {
        return type;
    }

    public void setType(DealType type) {
        this.type = type;
    }

    public Set<BuyOption> getBuyOptions() {
        return buyOptions;
    }

    public void setBuyOptions(Set<BuyOption> buyOptions) {
        this.buyOptions = buyOptions;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void addBuyOption(BuyOption buyOption){
        if (this.buyOptions != null){
            buyOptions.add(buyOption);
        }
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public boolean isVisible() {
        Date today = new Date();
        return this.publishDate.compareTo(today) * today.compareTo(this.endDate) > 0;
    }

    public void addSale(){
        this.totalSold++;
    }
}
