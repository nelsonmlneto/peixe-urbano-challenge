package br.com.peixeurbano.challenge.unit;

import br.com.peixeurbano.challenge.models.BuyOption;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class BuyOptionUnitTest {

    private BuyOption buyOption;

    private static long CUMPOMS = 5;

    @Before
    public void setUp() throws Exception {
        this.buyOption = new BuyOption();
        this.buyOption.setTitle("Option 1");
        this.buyOption.setQuantityCupom(CUMPOMS);
        this.buyOption.setNormalPrice(new Double(100.00));
        this.buyOption.setPercentageDiscount(new Double(10.00));
        this.buyOption.setStartDate(new Date());
        this.buyOption.setEndDate(this.getTomorrowDate());
    }

    private Date getTomorrowDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    @Test
    public void shouldRemoveOneCupom(){
        this.buyOption.removeCupom();
        assertEquals(this.buyOption.getQuantityCupom(), 4);
    }

    @Test
    public void shouldRemoveTwoCupoms(){
        this.buyOption.removeCupom();
        this.buyOption.removeCupom();
        assertEquals(this.buyOption.getQuantityCupom(), 3);
    }

    @Test
    public void shouldBeDisabled(){
        for (int i = 0; i < CUMPOMS; i++){
            this.buyOption.removeCupom();
        }
        assertEquals(this.buyOption.isOutOfStock(), true);
    }

    @Test
    public void shouldHaveTenPercentDiscount(){
        this.buyOption.calculateSaleOptionPrice();
        assertEquals(this.buyOption.getSalePrice(), new Double(90.00));
    }
}
