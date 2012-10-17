
package android.skymobi.messenger.net.beans.ppa;

import android.skymobi.messenger.net.beans.header.PPAResponseHeader;

import com.skymobi.android.bean.esb.annotation.EsbSignal;
import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

@EsbSignal(messageCode = 0xC226)
public class GetUserInfoWithTokenResp extends PPAResponseHeader {
    @TLVAttribute(tag=1005,description="")
    private int skyId;
    @TLVAttribute(tag=11010003,description="")
    private String userName = "";
    @TLVAttribute(tag=11010005, description="")
    private String nickname = "";
    @TLVAttribute(tag=14010006,description="")
    private String   usex = "";
    @TLVAttribute(tag=14010007,description="")
    private String ubirthday = "";
    @TLVAttribute(tag=14010009,description="")
    private String   ustar = "";
    @TLVAttribute(tag=14010012,description="")
    private String   uportraitid = "";
    @TLVAttribute(tag=14010037, description="")
    private String idcardno = "";    
    
    @TLVAttribute(tag=14010013,description="")
    private String udefineportrait = "";
    @TLVAttribute(tag=14010005,description="")
    private String urealname = "";
    @TLVAttribute(tag=14010008,description="")
    private String   uanimals = "";
    @TLVAttribute(tag=14010010,description="")
    private String   ublood = "";
    @TLVAttribute(tag=14010011,description="")
    private String   umarried = "";
    @TLVAttribute(tag=14010014,description="")
    private String ucountry = "";
    @TLVAttribute(tag=14010015,description="")
    private String uprovince = "";
    @TLVAttribute(tag=14010016,description="")
    private String ucity = "";
    @TLVAttribute(tag=14010017,description="")
    private String uhometown = "";
    @TLVAttribute(tag=14010018,description="")
    private String ulongitude = "";
    @TLVAttribute(tag=14010019,description="")
    private String ulatitude = "";
    @TLVAttribute(tag=14010020,description="")
    private String usignature = "";
    @TLVAttribute(tag=14010021,description="")
    private String udesc = "";
    @TLVAttribute(tag=14010026,description="")
    private String utelephone = "";
    @TLVAttribute(tag=14010027,description="")
    private String uvocation = "";
    @TLVAttribute(tag=14010028,description="")
    private String uschoolgraduated = "";
    @TLVAttribute(tag=14010029,description="")
    private String   uprivacy = "";
    @TLVAttribute(tag=14010038,description="")
    private String   haspic = "";
    @TLVAttribute(tag=14010042,description="")
    private String uindustry = "";
    @TLVAttribute(tag=14010043,description="")
    private String uhobbies = "";
    @TLVAttribute(tag=14010044,description="")
    private String ucorporation = "";
    @TLVAttribute(tag=14010067,description="")
    private String personnickname = "";
    @TLVAttribute(tag=14010022,description="")
    private String email = "";
    @TLVAttribute(tag=14010023,description="")
    private String uemailchecked = "";
    @TLVAttribute(tag=11010006,description="")
    private String umobile = "";
    
    @TLVAttribute(tag = 14010073, description = "uuid形式自定义头像")
    private String uuidPortrait = "";
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the ubirthday
     */
    public String getUbirthday() {
        return ubirthday;
    }
    /**
     * @param ubirthday the ubirthday to set
     */
    public void setUbirthday(String ubirthday) {
        this.ubirthday = ubirthday;
    }
    /**
     * @return the ustar
     */
    public String getUstar() {
        return ustar;
    }
    /**
     * @param ustar the ustar to set
     */
    public void setUstar(String ustar) {
        this.ustar = ustar;
    }
    /**
     * @return the uportraitid
     */
    public String getUportraitid() {
        return uportraitid;
    }
    /**
     * @param uportraitid the uportraitid to set
     */
    public void setUportraitid(String uportraitid) {
        this.uportraitid = uportraitid;
    }
    /**
     * @return the idcardno
     */
    public String getIdcardno() {
        return idcardno;
    }
    /**
     * @param idcardno the idcardno to set
     */
    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }
    /**
     * @return the udefineportrait
     */
    public String getUdefineportrait() {
        return udefineportrait;
    }
    /**
     * @param udefineportrait the udefineportrait to set
     */
    public void setUdefineportrait(String udefineportrait) {
        this.udefineportrait = udefineportrait;
    }
    /**
     * @return the urealname
     */
    public String getUrealname() {
        return urealname;
    }
    /**
     * @param urealname the urealname to set
     */
    public void setUrealname(String urealname) {
        this.urealname = urealname;
    }
    /**
     * @return the uanimals
     */
    public String getUanimals() {
        return uanimals;
    }
    /**
     * @param uanimals the uanimals to set
     */
    public void setUanimals(String uanimals) {
        this.uanimals = uanimals;
    }
    /**
     * @return the ublood
     */
    public String getUblood() {
        return ublood;
    }
    /**
     * @param ublood the ublood to set
     */
    public void setUblood(String ublood) {
        this.ublood = ublood;
    }
    /**
     * @return the umarried
     */
    public String getUmarried() {
        return umarried;
    }
    /**
     * @param umarried the umarried to set
     */
    public void setUmarried(String umarried) {
        this.umarried = umarried;
    }
    /**
     * @return the ucountry
     */
    public String getUcountry() {
        return ucountry;
    }
    /**
     * @param ucountry the ucountry to set
     */
    public void setUcountry(String ucountry) {
        this.ucountry = ucountry;
    }
    /**
     * @return the uprovince
     */
    public String getUprovince() {
        return uprovince;
    }
    /**
     * @param uprovince the uprovince to set
     */
    public void setUprovince(String uprovince) {
        this.uprovince = uprovince;
    }
    /**
     * @return the ucity
     */
    public String getUcity() {
        return ucity;
    }
    /**
     * @param ucity the ucity to set
     */
    public void setUcity(String ucity) {
        this.ucity = ucity;
    }
    /**
     * @return the uhometown
     */
    public String getUhometown() {
        return uhometown;
    }
    /**
     * @param uhometown the uhometown to set
     */
    public void setUhometown(String uhometown) {
        this.uhometown = uhometown;
    }
    /**
     * @return the ulongitude
     */
    public String getUlongitude() {
        return ulongitude;
    }
    /**
     * @param ulongitude the ulongitude to set
     */
    public void setUlongitude(String ulongitude) {
        this.ulongitude = ulongitude;
    }
    /**
     * @return the ulatitude
     */
    public String getUlatitude() {
        return ulatitude;
    }
    /**
     * @param ulatitude the ulatitude to set
     */
    public void setUlatitude(String ulatitude) {
        this.ulatitude = ulatitude;
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
     * @return the udesc
     */
    public String getUdesc() {
        return udesc;
    }
    /**
     * @param udesc the udesc to set
     */
    public void setUdesc(String udesc) {
        this.udesc = udesc;
    }
    /**
     * @return the utelephone
     */
    public String getUtelephone() {
        return utelephone;
    }
    /**
     * @param utelephone the utelephone to set
     */
    public void setUtelephone(String utelephone) {
        this.utelephone = utelephone;
    }
    /**
     * @return the uvocation
     */
    public String getUvocation() {
        return uvocation;
    }
    /**
     * @param uvocation the uvocation to set
     */
    public void setUvocation(String uvocation) {
        this.uvocation = uvocation;
    }
    /**
     * @return the uschoolgraduated
     */
    public String getUschoolgraduated() {
        return uschoolgraduated;
    }
    /**
     * @param uschoolgraduated the uschoolgraduated to set
     */
    public void setUschoolgraduated(String uschoolgraduated) {
        this.uschoolgraduated = uschoolgraduated;
    }
    /**
     * @return the uprivacy
     */
    public String getUprivacy() {
        return uprivacy;
    }
    /**
     * @param uprivacy the uprivacy to set
     */
    public void setUprivacy(String uprivacy) {
        this.uprivacy = uprivacy;
    }
    /**
     * @return the haspic
     */
    public String getHaspic() {
        return haspic;
    }
    /**
     * @param haspic the haspic to set
     */
    public void setHaspic(String haspic) {
        this.haspic = haspic;
    }
    /**
     * @return the uindustry
     */
    public String getUindustry() {
        return uindustry;
    }
    /**
     * @param uindustry the uindustry to set
     */
    public void setUindustry(String uindustry) {
        this.uindustry = uindustry;
    }
    /**
     * @return the uhobbies
     */
    public String getUhobbies() {
        return uhobbies;
    }
    /**
     * @param uhobbies the uhobbies to set
     */
    public void setUhobbies(String uhobbies) {
        this.uhobbies = uhobbies;
    }
    /**
     * @return the ucorporation
     */
    public String getUcorporation() {
        return ucorporation;
    }
    /**
     * @param ucorporation the ucorporation to set
     */
    public void setUcorporation(String ucorporation) {
        this.ucorporation = ucorporation;
    }
    /**
     * @return the personnickname
     */
    public String getPersonnickname() {
        return personnickname;
    }
    /**
     * @param personnickname the personnickname to set
     */
    public void setPersonnickname(String personnickname) {
        this.personnickname = personnickname;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the umobile
     */
    public String getUmobile() {
        return umobile;
    }
    /**
     * @param umobile the umobile to set
     */
    public void setUmobile(String umobile) {
        this.umobile = umobile;
    }
    /**
     * @return the uemailchecked
     */
    public String getUemailchecked() {
        return uemailchecked;
    }
    /**
     * @param uemailchecked the uemailchecked to set
     */
    public void setUemailchecked(String uemailchecked) {
        this.uemailchecked = uemailchecked;
    }
    /**
     * @return the uuidPortrait
     */
    public String getUuidPortrait() {
        return uuidPortrait;
    }
    /**
     * @param uuidPortrait the uuidPortrait to set
     */
    public void setUuidPortrait(String uuidPortrait) {
        this.uuidPortrait = uuidPortrait;
    }
    
}
