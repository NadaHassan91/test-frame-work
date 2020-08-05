package api_test;

public class ListShipment {
    String key;
    String commodity;
    String vehicleType;
    int numberOfBids;
    float price;
    Address[] addresses;
}

class Address {
    int order;
    String key;
    String name;
    float longitude;
    float latitude;
}