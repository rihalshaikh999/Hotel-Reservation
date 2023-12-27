package org.example;
import java.util.HashMap;
import java.util.Map;
public class HotelParameter {
    public String hotelName;
    public Double price;
    public HotelParameter(String hotelName, Double price) {
        this.hotelName = hotelName;
        this.price = price;
    }
    public static HotelParameter createHotel(String hotelName, Double price) {
        return new HotelParameter(hotelName, price);
    }
    public static void main(String[] args) {
        HotelParameter ob1 = createHotel("Lakewood", 110.0);
        HotelParameter ob2 = createHotel("Bridgewood", 160.0);
        HotelParameter ob3 = createHotel("Ridgewood", 210.0);
        HashMap<String, HotelParameter> hm = new HashMap<>();
        hm.put(ob1.hotelName, ob1);
        hm.put(ob2.hotelName, ob2);
        hm.put(ob3.hotelName, ob3);
        for (Map.Entry<String, HotelParameter> entry : hm.entrySet()) {
            String key = entry.getKey();
            HotelParameter ob = entry.getValue();
            System.out.println(ob.hotelName + " " + ob.price);
        }
    }
}