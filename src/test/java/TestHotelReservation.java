import junit.framework.Assert;
import org.example.HotelParameter;
import org.junit.Test;
public class TestHotelReservation
{
    @Test
    public void UC1_1() {
        HotelParameter ob=new HotelParameter("Lakewood",110.0);//actual
        Assert.assertEquals("Lakewood", ob.hotelName);
        Assert.assertEquals(110.0, ob.price);
    }
    @Test
    public void UC1_2() {
        HotelParameter ob=new HotelParameter("Bridgewood", 160.0);//actual
        Assert.assertEquals("Bridgewood", ob.hotelName);
        Assert.assertEquals(160.0, ob.price);
    }
    @Test
    public void UC1_3() {
        HotelParameter ob1=new HotelParameter("Ridgewood", 210.0);//actual
        Assert.assertEquals("Ridgewood", ob1.hotelName);
        Assert.assertEquals(210.0, ob1.price);
    }
}