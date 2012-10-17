package android.skymobi.messenger.net.beans.commons;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * @ClassName: NearUser
 * @Description: TODO
 * @author Bluestome.Zhang
 * @date 2012-6-20 下午05:43:37
 */
public class NearUser {

    @TLVAttribute(tag=1005)
    private int skyId;
    
    /**
     * 个性昵称
     */
    @TLVAttribute(tag=11010077,description="个性昵称")
    private String nickname;
    
    /**
     * 头像（如果有自定义头像，该字段放的是自定义头像的UUID值，否则放的是设置的系统头像，如果没有则不下发）
     */
    @TLVAttribute(tag=10000011,description="头像（如果有自定义头像，该字段放的是自定义头像的UUID值，否则放的是设置的系统头像，如果没有则不下发）")
    private String imageHead;
    
    /**
     * 签名
     */
    @TLVAttribute(tag=14010020,description="签名")
    private String usignature;
    
    /**
     * 性别
     */
    @TLVAttribute(tag=14010006,description="性别")
    private String usex;
    
    /**
     * 推荐理由，例如可能的值是：“100米以内等”
     */
    @TLVAttribute(tag=10000012,description="推荐理由，例如可能的值是：“100米以内等”")
    private String recommendReason;
    
    /**
     * 距离（米）
     */
    @TLVAttribute(tag=34010015,description="距离（米）")
    private int distance;
    
    /**
     * 查询到的用户类型
     */
    @TLVAttribute(tag=10000070,description="查询到用户的方式。0:代表经纬度查询到的,1:代表同城查询到的，2:代表同省查询的，3：代表随机查询到的")
    private int nearbyUserType;
    

    /**
     * @return the skyId
     */
    public int getSkyId() {
        return skyId;
    }

    /**
     * @param skyId the skyId to set
     */
    public void setSkyId(int skyId) {
        this.skyId = skyId;
    }
    
    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the imageHead
     */
    public String getImageHead() {
        return imageHead;
    }

    /**
     * @param imageHead the imageHead to set
     */
    public void setImageHead(String imageHead) {
        this.imageHead = imageHead;
    }

    /**
     * @return the usignature
     */
    public String getUsignature() {
        return usignature;
    }

    /**
     * @param usignature the usignature to set
     */
    public void setUsignature(String usignature) {
        this.usignature = usignature;
    }

    /**
     * @return the usex
     */
    public String getUsex() {
        return usex;
    }

    /**
     * @param usex the usex to set
     */
    public void setUsex(String usex) {
        this.usex = usex;
    }

    /**
     * @return the recommendReason
     */
    public String getRecommendReason() {
        return recommendReason;
    }

    /**
     * @param recommendReason the recommendReason to set
     */
    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return the nearbyUserType
     */
    public int getNearbyUserType() {
        return nearbyUserType;
    }

    /**
     * @param nearbyUserType the nearbyUserType to set
     */
    public void setNearbyUserType(int nearbyUserType) {
        this.nearbyUserType = nearbyUserType;
    }
    
}
