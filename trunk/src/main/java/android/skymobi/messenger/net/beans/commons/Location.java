package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: Location 位置对象
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午05:10:49
 */
public class Location {

    @TLVAttribute(tag=34010012,description="纬度")
    private String latitude;
    
    @TLVAttribute(tag=34010013,description="经度")
    private String longitude;
    
    @TLVAttribute(tag=14010014,description="国家")
    private String county;
    
    @TLVAttribute(tag=14010015,description="省份")
    private String province;
    
    @TLVAttribute(tag=14010016,description="城市")
    private String city;
    
    @TLVAttribute(tag=34010014,description="街道")
    private String street;

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }
    
}
