import junit.framework.Assert;
import org.example.HotelParameter;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.example.HotelParameter.parseDate;
import static org.junit.Assert.assertEquals;

public class TestHotelReservation {
    @Test
    public void UC1_NamePrice() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(110.0, ob1.price);
        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);
        Assert.assertEquals("Bridgewood", ob2.hotelName);
        Assert.assertEquals(160.0, ob2.price);
        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);
        Assert.assertEquals("Ridgewood", ob3.hotelName);
        Assert.assertEquals(210.0, ob3.price);
    }

    @Test
    public void UC2_DateRangeCheapest() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);
        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);
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
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        Assert.assertEquals(110.0, ob1.weekdayRate);//weekdays
        Assert.assertEquals(90.0, ob1.weekendRate);//weekends

        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);
        Assert.assertEquals(150.0, ob2.weekdayRate);//weekdays
        Assert.assertEquals(50.0, ob2.weekendRate);//weekends

        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);
        Assert.assertEquals(220.0, ob3.weekdayRate);//weekdays
        Assert.assertEquals(150.0, ob3.weekendRate);//weekends

    }

    @Test
    public void UC4_CheapHotel() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(220.0, ob1.calRate(ob1.startDate, ob1.endDate, true), 0.01);

        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);
        Assert.assertEquals("Bridgewood", ob2.hotelName);
        Assert.assertEquals(300.0, ob2.calRate(ob2.startDate, ob2.endDate, true), 0.01);

        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);
        Assert.assertEquals("Ridgewood", ob3.hotelName);
        Assert.assertEquals(440.0, ob3.calRate(ob3.startDate, ob3.endDate, true), 0.01);
    }

    @Test
    public void UC5_AddRating() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        Assert.assertEquals(3, ob1.rating);

        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);
        Assert.assertEquals(4, ob2.rating);

        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);
        Assert.assertEquals(5, ob3.rating);
    }

    @Test
    public void UC6_CheapestPrice_Rating() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        Assert.assertEquals("Lakewood", ob1.hotelName);
        Assert.assertEquals(110.0, ob1.price, 0.01);
        Assert.assertEquals(3, ob1.rating);
    }

    @Test
    public void UC7_Best_Rated_Hotel() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 110.0, 50.0);
        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 100.0, 40.0);

        // Create a HashMap with the hotels
        HashMap<String, HotelParameter> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);

        // Specify the date range
        Date rangeStartDate = parseDate("11/sep/2020");
        Date rangeEndDate = parseDate("12/sep/2020");

        // Call the method to find the best-rated hotel
        HotelParameter bestRated = HotelParameter.bestRatedHotel(hm, rangeStartDate, rangeEndDate,true);

        // Assert the result
        if (bestRated != null) {
            Assert.assertEquals("Ridgewood", bestRated.hotelName);
            Assert.assertEquals(5, bestRated.rating);
            Assert.assertEquals(370.0, bestRated.calRate(rangeStartDate, rangeEndDate, true), 0.01);
        } else {
            Assert.fail("No best-rated hotel found.");
        }
    }
    @Test
    public void UC9_Special_Rates_Reward_Customer() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        Assert.assertEquals(80.0, ob1.rewardWeekdayRate);
        Assert.assertEquals(80.0, ob1.rewardWeekendRate);

        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 50.0, 110.0);
        Assert.assertEquals(110.0, ob2.rewardWeekdayRate);
        Assert.assertEquals(50.0, ob2.rewardWeekendRate);

        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);
        Assert.assertEquals(100.0, ob3.rewardWeekdayRate);
        Assert.assertEquals(40.0, ob3.rewardWeekendRate);
    }

    @Test
    public void UC9_1_Special_Rates_Reward_Customer() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        double calculatedRate = ob1.calRate(parseDate("10/sep/2020"), parseDate("11/sep/2020"), true);
        Assert.assertEquals(160.0, calculatedRate, 0.01);

        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        calculatedRate = ob2.calRate(parseDate("10/sep/2020"), parseDate("11/sep/2020"), true);
        Assert.assertEquals(200.0, calculatedRate, 0.01);

        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);
        calculatedRate = ob3.calRate(parseDate("10/sep/2020"), parseDate("11/sep/2020"), true);
        Assert.assertEquals(200.0, calculatedRate, 0.01);
    }
    @Test
    public void UC10_CheapestBestRatedHotelForRewardCustomer() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);
        HashMap<String, HotelParameter> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);

        ob1.rewardWeekdayRate = 80.0;
        ob1.rewardWeekendRate = 80.0;
        ob2.rewardWeekdayRate = 110.0;
        ob2.rewardWeekendRate = 50.0;
        ob3.rewardWeekdayRate = 100.0;
        ob3.rewardWeekendRate = 40.0;

        Date rangeStartDate = parseDate("11/Sep/2020");
        Date rangeEndDate = parseDate("12/Sep/2020");

        try {
            HotelParameter cheap = HotelParameter.cheapHotel(hm, rangeStartDate, rangeEndDate, true);
            HotelParameter bestRated = HotelParameter.bestRatedHotel(hm, rangeStartDate, rangeEndDate, true);

            assertEquals("Ridgewood", cheap.hotelName);
            assertEquals(5, cheap.rating);
            assertEquals(140.0, cheap.calRate(rangeStartDate, rangeEndDate, true), 0.01);

            assertEquals("Ridgewood", bestRated.hotelName);
            assertEquals(5, bestRated.rating);
            assertEquals(140.0, bestRated.calRate(rangeStartDate, rangeEndDate, true), 0.01);
        } catch (Exception e) {
            // Handle exceptions if needed
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void UC10_InvalidDateRange() {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HashMap<String, HotelParameter> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);

        Date invalidStartDate = parseDate("12/Sep/2020");
        Date invalidEndDate = parseDate("11/Sep/2020");

        HotelParameter.cheapHotel(hm, invalidStartDate, invalidEndDate, true);
    }
}
