package api.jsonplaceholder.users;

public class Geo {
    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
