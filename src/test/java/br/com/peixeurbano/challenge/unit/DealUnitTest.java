package br.com.peixeurbano.challenge.unit;

import br.com.peixeurbano.challenge.models.Deal;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DealUnitTest {

    private Deal deal;

    @Before
    public void setUp() throws Exception {
        this.deal = new Deal();
        this.deal.setTitle("Deal 1");
        this.deal.setText("Deal Text 1");
        this.deal.setTotalSold(0);
        this.deal.setCreateDate(new Date());
        this.deal.setPublishDate(new Date());
        this.deal.setValidDays(new Integer(2));
    }

    @Test
    public void shouldAddOneSale(){
        this.deal.addSale();
        assertEquals(this.deal.getTotalSold(), 1);
    }

    @Test
    public void shouldAddTwoSales(){
        this.deal.addSale();
        this.deal.addSale();
        assertEquals(this.deal.getTotalSold(), 2);
    }

    @Test
    public void shouldBeVisible(){
        this.deal.calculateEndDate();
        assertEquals(this.deal.isVisible(), true);
    }

}
