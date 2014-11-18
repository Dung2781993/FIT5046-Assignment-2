package yihang.wang.model;

public class GeoInfo {
	private String geoId;
	private String geoAddress;
	private String latitude;
	private String longtitude;
	private int count;
	
	public GeoInfo(){
		geoId = "";
		geoAddress = "";
		latitude = "";
		longtitude = "";
		count = 0;
	}
	
	public GeoInfo(String newGeoId, String newGeoAddress, String newLatitude, String newLongtitude){
		this.geoId = newGeoId;
		this.geoAddress = newGeoAddress;
		this.latitude = newLatitude;
		this.longtitude = newLongtitude;
		this.count = 1;
	}
	
	public int getCount() {
        return count;
    }

    public void setCount() {
        this.count = this.count + 1;
    }
	
	public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public String getGeoAddress() {
        return geoAddress;
    }

    public void setGeoAddress(String geoAddress) {
        this.geoAddress = geoAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
}
