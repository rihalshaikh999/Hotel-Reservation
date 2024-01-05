import junit.framework.Assert;
import org.example.HotelParameter;
import org.junit.Test;

import static org.example.HotelParameter.parseDate;
import static org.junit.Assert.assertEquals;

public class TestHotelReservation {
    @Test
    public void UC1_NamePrice() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3);//actual
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(110.0, ob1.price);
        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4);//actual
        Assert.assertEquals("Bridgewood", ob2.hotelName);
        Assert.assertEquals(160.0, ob2.price);
        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5);//actual
        Assert.assertEquals("Ridgewood", ob3.hotelName);
        Assert.assertEquals(210.0, ob3.price);
    }

    @Test
    public void UC2_DateRangeCheapest() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3);//actual
        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4);//actual
        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5);//actual
        if (ob1.price < ob2.price && ob1.price < ob3.price) {
            Assert.assertEquals("Lakewood", ob1.hotelName);
            Assert.assertEquals(110.0, ob1.price);
//            Assert.assertEquals("Thu Sep 10 00:00:00 IST 2020", ob1.startDate);
//            Assert.assertEquals("11/sep/2020", ob1.endDate);
            System.out.println("Hotel " + ob1.hotelName + " is Cheapest at Price: " + ob1.price + " Available from:- " + ob1.startDate + " to " + ob1.endDate);
        } else if (ob2.price < ob1.price && ob2.price < ob3.price) {
            Assert.assertEquals("Bridgewood", ob2.hotelName);
            Assert.assertEquals(160.0, ob2.price);
            Assert.assertEquals("Thu Sep 10 00:00:00 IST 2020", ob2.startDate);
            Assert.assertEquals("11/sep/2020", ob2.endDate);
            System.out.println("Hotel " + ob2.hotelName + " is Cheapest at Price: " + ob2.price + " Available from:- " + ob2.startDate + " to " + ob2.endDate);
        } else if (ob3.price < ob1.price && ob3.price < ob2.price) {
            Assert.assertEquals("Ridgewood", ob3.hotelName);
            Assert.assertEquals(210.0, ob3.price);
            Assert.assertEquals("Thu Sep 10 00:00:00 IST 2020", ob3.startDate);
            Assert.assertEquals("11/sep/2020", ob3.endDate);
            System.out.println("Hotel " + ob3.hotelName + " is Cheapest at Price: " + ob3.price + " Available from:- " + ob3.startDate + " to " + ob3.endDate);
        }
    }

    @Test
    public void UC3_AddPriceWeedayWeekends() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3);//actual
        Assert.assertEquals(110.0, ob1.weekdayRate);//weekdays
        Assert.assertEquals(90.0, ob1.weekendRate);//weekends

        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4);//actual
        Assert.assertEquals(150.0, ob2.weekdayRate);//weekdays
        Assert.assertEquals(50.0, ob2.weekendRate);//weekends

        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5);//actual
        Assert.assertEquals(220.0, ob3.weekdayRate);//weekdays
        Assert.assertEquals(150.0, ob3.weekendRate);//weekends

    }

    @Test
    public void UC4_CheapHotel() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3);//actual
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(220.0, ob1.calRate(ob1.startDate, ob1.endDate), 0.01);

        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4);//actual
        Assert.assertEquals("Bridgewood", ob2.hotelName);
        Assert.assertEquals(300.0, ob2.calRate(ob2.startDate, ob2.endDate), 0.01);

        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5);//actual
        Assert.assertEquals("Ridgewood", ob3.hotelName);
        Assert.assertEquals(440.0, ob3.calRate(ob3.startDate, ob3.endDate), 0.01);
    }

    @Test
    public void UC5_AddRating() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3);//actual
        Assert.assertEquals(3, ob1.rating);

        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4);//actual
        Assert.assertEquals(4, ob2.rating);

        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5);//actual
        Assert.assertEquals(4, ob3.rating);
    }

    @Test
    public void UC6_CheapestPrice_Rating() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3);
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(110.0, ob1.price, 0.01);
        Assert.assertEquals(3, ob1.rating);
    }
}
