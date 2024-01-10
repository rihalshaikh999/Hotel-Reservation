package org.example;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.*;

public class HotelParameter {
    public String hotelName;
    public Double price;
    public static Date startDate;
    public static Date endDate;
    public Double weekendRate;
    public Double weekdayRate;
    public Double rewardWeekendRate;
    public Double rewardWeekdayRate;
    public int rating;
    public HotelParameter(String hotelName, Double price, Date startDate, Date endDate, Double weekendRate, Double weekdayRate, int rating, Double rewardWeekendRate, Double rewardWeekdayRate) {
        this.hotelName = hotelName;
        this.price = price;
        this.startDate=startDate;
        this.endDate=endDate;
        this.weekendRate = weekendRate;
        this.weekdayRate = weekdayRate;
        this.rating = rating;
        this.rewardWeekendRate = rewardWeekendRate;
        this.rewardWeekdayRate = rewardWeekdayRate;
    }
    public static HotelParameter cheapHotel(HashMap<String, HotelParameter> hm, Date startDate, Date endDate, boolean isRewardCustomer) {
        validateDateRange(startDate, endDate);

        return hm.values()
                .stream()
                .min(Comparator.comparingDouble(hotel -> hotel.calRate(startDate, endDate, isRewardCustomer)))
                .orElse(null);
    }
    public static HotelParameter bestRatedHotel(HashMap<String, HotelParameter> hm, Date startDate, Date endDate, boolean isRewardCustomer) {
        validateDateRange(startDate, endDate);

        return hm.values()
                .stream()
                .filter(hotel -> isDateRangeOverlap(startDate, endDate, hotel.startDate, hotel.endDate))
                .max(Comparator.comparingInt(HotelParameter::getRating)
                        .thenComparingDouble(hotel -> hotel.calRate(startDate, endDate, isRewardCustomer)))
                .orElse(null);
    }
    public int getRating() {
        return rating;
    }
    public static void main(String[] args) {
        HotelParameter ob1 = new HotelParameter("Lakewood", 110.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 90.0, 110.0, 3, 80.0, 80.0);
        HotelParameter ob2 = new HotelParameter("Bridgewood", 160.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 50.0, 150.0, 4, 50.0, 100.0);
        HotelParameter ob3 = new HotelParameter("Ridgewood", 210.0, parseDate("10/sep/2020"), parseDate("11/sep/2020"), 150.0, 220.0, 5, 40.0, 100.0);
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
        try {
            Date rangeStartDate = parseDate("11/Sep/2020");
            Date rangeEndDate = parseDate("12/Sep/2020");
            validateDateRange(rangeStartDate, rangeEndDate);
            HotelParameter cheap = cheapHotel(hm, rangeStartDate, rangeEndDate, true);
            System.out.println(cheap != null
                    ? "Cheapest Hotel: " + cheap.hotelName + " rating: " + cheap.rating + " " + cheap.calRate(rangeStartDate, rangeEndDate, true)
                    : "No Hotels Found...!!!");

            HotelParameter bestRated = bestRatedHotel(hm, rangeStartDate, rangeEndDate, true);
            System.out.println(bestRated != null
                    ? "Best Rated Hotel: " + bestRated.hotelName + " rating: " + bestRated.rating + " " + bestRated.calRate(rangeStartDate, rangeEndDate, true)
                    : "No Hotels Found...!!!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    private static void validateDateRange(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            throw new IllegalArgumentException("Invalid date range. Please provide valid start and end dates.");
        }
    }

    public static void showHotelByDateRange(HashMap<String, HotelParameter> hm, Date startDate, Date endDate) {
        for (HotelParameter hotel : hm.values()) {
            if (isDateRangeOverlap(startDate, endDate, hotel.startDate, hotel.endDate)) {
                System.out.println("Range: " + startDate + " - " + endDate + " " + hotel.hotelName + " " + hotel.calRate(startDate, endDate, true));
            }
        }
    }
    public static boolean isDateRangeOverlap(Date targetStartDate, Date targetEndDate, Date startDate, Date endDate) {
        return (targetStartDate.compareTo(endDate) <= 0 && targetEndDate.compareTo(startDate) >= 0);
    }
    public static Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public double calRate(Date startDate, Date endDate, boolean isRewardCustomer) {
        int weekdays = 0;
        int weekends = 0;
        Date currentDate = startDate;
        while (!currentDate.after(endDate)) {
            int dayOfWeek = currentDate.getDay();
            if (dayOfWeek >= 1 && dayOfWeek <= 5) {
                weekdays++;
            } else {
                weekends++;
            }
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);
        }
        if (isRewardCustomer) {
            return weekdays * rewardWeekdayRate + weekends * rewardWeekendRate;
        } else {
            return weekdays * weekdayRate + weekends * weekendRate;
        }
    }
}