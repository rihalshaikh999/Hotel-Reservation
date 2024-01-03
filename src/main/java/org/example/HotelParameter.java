package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Date;
public class HotelParameter {
    public String hotelName;
    public Double price;
    public Date startDate;
    public Date endDate;
    public static Double weekdayRate;
    public Double weekendRate;
    public int rating;

    public HotelParameter(String hotelName, Double price, Date startDate, Date endDate, Double weekdayRate, Double weekendRate, int rating) {
        this.hotelName = hotelName;
        this.price = price;
        this.startDate=startDate;
        this.endDate=endDate;
        this.weekdayRate=weekdayRate;
        this.weekendRate=weekendRate;
        this.rating = rating;
    }
    public static HotelParameter createHotel(String hotelName, Double price, Date startDate, Date endDate, Double weekdayRate, Double weekendRate, int rating) {
        return new HotelParameter(hotelName, price, startDate, endDate, weekdayRate, weekendRate, rating);
    }
    public static HotelParameter cheapHotel(HashMap<String, HotelParameter> hm, Date startDate, Date endDate) {
        HotelParameter cheapest = null;
        for (HotelParameter hotel : hm.values()) {
            double totalRate = hotel.calRate(startDate, endDate);
            if (cheapest == null || totalRate < cheapest.calRate(startDate, endDate)) {
                cheapest = hotel;
            }
        }
    return cheapest;
}
    public static void main(String[] args) {
        HotelParameter ob1 = createHotel("Lakewood", 110.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 110.0, 90.0, 3);
        HotelParameter ob2 = createHotel("Bridgewood", 160.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 150.0, 50.0, 4);
        HotelParameter ob3 = createHotel("Ridgewood", 210.0, parseDate("10/Sep/2020"), parseDate("11/Sep/2020"), 220.0, 150.0, 5);
        HashMap<String, HotelParameter> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);
        Date rangeStartDate = parseDate("11/Sep/2020");
        Date rangeEndDate = parseDate("12/Sep/2020");
        showHotelByDateRange(hm, rangeStartDate, rangeEndDate);
        HotelParameter cheap = cheapHotel(hm, rangeStartDate, rangeEndDate);

        if (cheap != null) {
            System.out.println("Cheap Hotel: " + cheap.hotelName + " " + cheap.calRate(rangeStartDate, rangeEndDate));
        } else {
            System.out.println("No Hotels Found...!!!");
        }
    }
    public static void showHotelByDateRange(HashMap<String, HotelParameter> hm, Date startDate, Date endDate) {
        for (HotelParameter hotel : hm.values()) {
            if (isDateRangeOverlap(startDate, endDate, hotel.startDate, hotel.endDate)) {
                System.out.println("Range: " + startDate + " - " + endDate + "_" + hotel.hotelName + "_" + hotel.calRate(startDate, endDate)+"_ "+ hotel.rating);
            }
        }
    }
    public static boolean isDateRangeOverlap(Date targetStartDate, Date targetEndDate, Date startDate, Date endDate) {
        return (targetStartDate.compareTo(endDate) <= 0 && targetEndDate.compareTo(startDate) >= 0);
    }
    public static Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public double calRate(Date startDate, Date endDate) {
        int weekdays = 0;
        int weekends = 0;
        Date currentDate = startDate;
        while (!currentDate.after(endDate)) {
            int dayOfWeek = currentDate.getDay();
            if (dayOfWeek >= 1 && dayOfWeek <= 5)
                weekdays++;
            else
                weekends++;
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);
        }
        return (weekdays * weekdayRate + weekends * weekendRate);
    }
}