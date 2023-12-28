import junit.framework.Assert;
import org.example.HotelParameter;
import org.junit.Test;

import static org.example.HotelParameter.parseDate;
import static org.junit.Assert.assertEquals;

public class TestHotelReservation
{
    @Test
    public void UC1() {
        HotelParameter ob1=new HotelParameter("Lakewood",110.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"),110.0, 90.0);//actual
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(110.0, ob1.price);
        HotelParameter ob2=new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"),150.0, 50.0);//actual
        Assert.assertEquals("Bridgewood", ob2.hotelName);
        Assert.assertEquals(160.0, ob2.price);
        HotelParameter ob3=new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"),220.0,150.0);//actual
        Assert.assertEquals("Ridgewood", ob3.hotelName);
        Assert.assertEquals(210.0, ob3.price);
    }
    @Test
    public void UC2()
    {
        HotelParameter ob1=new HotelParameter("Lakewood",110.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"), 110.0, 90.0);//actual
        HotelParameter ob2=new HotelParameter("Bridgewood", 160.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"),150.0, 50.0);//actual
        HotelParameter ob3=new HotelParameter("Ridgewood", 210.0,parseDate("10/sep/2020"),parseDate("11/sep/2020"),220.0,150.0);//actual
        if (ob1.price<ob2.price&&ob1.price<ob3.price) {
            Assert.assertEquals("Lakewood", ob1.hotelName);
            Assert.assertEquals(110.0, ob1.price);
//            Assert.assertEquals("Thu Sep 10 00:00:00 IST 2020", ob1.startDate);
//            Assert.assertEquals("11/sep/2020", ob1.endDate);
            System.out.println("Hotel "+ob1.hotelName +" is Cheapest at Price: "+ob1.price+" Available from:- "+ob1.startDate+ " to "+ob1.endDate);
        }
        else if (ob2.price<ob1.price&&ob2.price<ob3.price) {
            Assert.assertEquals("Bridgewood", ob2.hotelName);
            Assert.assertEquals(160.0, ob2.price);
            Assert.assertEquals("Thu Sep 10 00:00:00 IST 2020", ob2.startDate);
            Assert.assertEquals("11/sep/2020", ob2.endDate);
            System.out.println("Hotel "+ob2.hotelName +" is Cheapest at Price: "+ob2.price+" Available from:- "+ob2.startDate+ " to "+ob2.endDate);
        }
        else if (ob3.price<ob1.price&&ob3.price<ob2.price) {
            Assert.assertEquals("Ridgewood", ob3.hotelName);
            Assert.assertEquals(210.0, ob3.price);
            Assert.assertEquals("Thu Sep 10 00:00:00 IST 2020", ob3.startDate);
            Assert.assertEquals("11/sep/2020", ob3.endDate);
            System.out.println("Hotel "+ob3.hotelName +" is Cheapest at Price: "+ob3.price+" Available from:- "+ob3.startDate+ " to "+ob3.endDate);
        }
    }
    @Test
    public void UC3()
    {
        HotelParameter ob1=new HotelParameter("Lakewood",110.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"),110.0, 90.0);//actual
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(200.0, ob1.calRate());
        HotelParameter ob2=new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"),150.0, 50.0);//actual
        Assert.assertEquals("Bridgewood", ob2.hotelName);
        Assert.assertEquals(200.0, ob2.calRate());
        HotelParameter ob3=new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"),220.0,150.0);//actual
        Assert.assertEquals("Ridgewood", ob3.hotelName);
        Assert.assertEquals(370.0, ob3.calRate());
    }
}