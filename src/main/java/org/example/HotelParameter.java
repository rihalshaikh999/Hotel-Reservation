package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
public class HotelParameter {
    public String hotelName;
    public Double price;
    public Date startDate;
    public Date endDate;
    public HotelParameter(String hotelName, Double price, Date startDate, Date endDate) {
        this.hotelName = hotelName;
        this.price = price;
        this.startDate=startDate;
        this.endDate=endDate;
    }
    public static HotelParameter createHotel(String hotelName, Double price,Date startDate, Date endDate) {
        return new HotelParameter(hotelName, price, startDate, endDate);
    }
    public static HotelParameter cheapHotel(HashMap<String, HotelParameter>hm)
    {
        HotelParameter cheap=null;
        for(HotelParameter hotel:hm.values())
        {
            if(cheap==null||hotel.price<cheap.price)
                cheap=hotel;
        }
        return cheap;
    }
    public static void main(String[] args) {
        HotelParameter ob1 = createHotel("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"));
        HotelParameter ob2 = createHotel("Bridgewood", 160.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"));
        HotelParameter ob3 = createHotel("Ridgewood", 210.0,parseDate("10/sep/2020"), parseDate("11/sep/2020"));
        HashMap<String, HotelParameter> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);
//        for (Map.Entry<String, HotelParameter> entry : hm.entrySet()) {
//            HotelParameter ob = entry.getValue();
//            System.out.println(ob.hotelName + " " + ob.price + " Start Date: " + ob.startDate + " End Date: " + ob.endDate);
//        }

        Date rangeStartDate = parseDate("10/sep/2020");
        Date rangeEndDate = parseDate("11/sep/2020");
        showHotelByDateRange(hm, rangeStartDate, rangeEndDate);
        HotelParameter cheap=cheapHotel(hm);
        if(cheap!=null)
            System.out.println("Cheap Hotel: "+cheap.hotelName +" "+cheap.price);
        else
            System.out.println("No Hotels Found...!!!");
    }

    public static void showHotelByDateRange(HashMap<String, HotelParameter> hm, Date startDate, Date endDate) {
        for (HotelParameter hotel : hm.values()) {
            if (isDateRangeOverlap(startDate, endDate, hotel.startDate, hotel.endDate)) {
                System.out.println("Range: " + startDate + " - " + endDate + "_" + hotel.hotelName + "_" + hotel.price);
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
//    public static boolean isDateInRange(Date targetDate, Date startDate, Date endDate) {
//        return targetDate.compareTo(startDate) >= 0 && targetDate.compareTo(endDate) <= 0;
//    }
}