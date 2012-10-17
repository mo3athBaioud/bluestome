/**
 * 2010-5-24
 * com.skymobi.application.ups.bean.common
 * UserInfo.java
 */
package android.skymobi.messenger.net.beans.ppa;

import com.skymobi.android.bean.tlv.annotation.TLVAttribute;

/**
 * 用户对象
 * @author Sering Cheng
 * 
 */
public class UserInfo {
	@TLVAttribute(tag = 1005, description = "")
	private int skyId;

	@TLVAttribute(tag = 11010003, description = "")
	private String userName;

	@TLVAttribute(tag = 11010005, description = "")
	private String nickname;

	@TLVAttribute(tag = 14010005, description = "")
	private String urealname;

	@TLVAttribute(tag = 14010006, description = "")
	private String usex;

	@TLVAttribute(tag = 14010007, description = "")
	private String ubirthday;

	@TLVAttribute(tag = 14010008, description = "")
	private String uanimals;

	@TLVAttribute(tag = 14010009, description = "")
	private String ustar;

	@TLVAttribute(tag = 14010010, description = "")
	private String ublood;

	@TLVAttribute(tag = 14010011, description = "")
	private String umarried;

	@TLVAttribute(tag = 14010012, description = "")
	private String uportraitid;

	@TLVAttribute(tag = 14010013, description = "")
	private String udefineportrait;

	@TLVAttribute(tag = 14010014, description = "")
	private String ucountry;

	@TLVAttribute(tag = 14010015, description = "")
	private String uprovince;

	@TLVAttribute(tag = 14010016, description = "")
	private String ucity;

	@TLVAttribute(tag = 14010017, description = "")
	private String uhometown;

	@TLVAttribute(tag = 14010018, description = "")
	private String ulongitude;

	@TLVAttribute(tag = 14010019, description = "")
	private String ulatitude;

	@TLVAttribute(tag = 14010020, description = "")
	private String usignature;

	@TLVAttribute(tag = 14010021, description = "")
	private String udesc;

	@TLVAttribute(tag = 14010022, description = "")
	private String uemail;

	@TLVAttribute(tag = 14010023, description = "")
	private String uemailchecked;

	@TLVAttribute(tag = 14010026, description = "")
	private String utelephone;

	@TLVAttribute(tag = 14010027, description = "")
	private String uvocation;

	@TLVAttribute(tag = 14010028, description = "")
	private String uschoolgraduated;

	@TLVAttribute(tag = 14010029, description = "")
	private String uprivacy;

	@TLVAttribute(tag = 14010030, description = "")
	private String ucreatetime;

	@TLVAttribute(tag = 14010031, description = "")
	private String umodifytime;

	@TLVAttribute(tag = 14010037, description = "")
	private String idcardno;

	@TLVAttribute(tag = 14010038, description = "")
	private String haspic;

	@TLVAttribute(tag = 14010042, description = "")
	private String uindustry;
	@TLVAttribute(tag = 14010043, description = "")
	private String uhobbies;
	@TLVAttribute(tag = 14010044, description = "")
	private String ucorporation;
	@TLVAttribute(tag = 14010045, description = "")
	private String ucensusprovince;
	@TLVAttribute(tag = 14010046, description = "")
	private String ucensuscity;
	@TLVAttribute(tag = 14010047, description = "")
	private String ucensuscounty;
	@TLVAttribute(tag = 14010048, description = "")
	private String ucensushometown;
	@TLVAttribute(tag = 14010049, description = "")
	private String ucensusvillage;

	@TLVAttribute(tag = 14010065, description = "")
	private String countryCode;
	@TLVAttribute(tag = 14010066, description = "")
	private String religion;
	@TLVAttribute(tag = 11010077, description = "")
	private String personNickname;
	@TLVAttribute(tag = 14010068, description = "")
	private String privacyStrategy;
	
    @TLVAttribute(tag = 11010006, description = "")
	private String umobile;

    @TLVAttribute(tag = 14010048, description = "原籍地所在的乡镇（街道）")
    private String ucensusHometown;
    
    @TLVAttribute(tag = 14010049, description = "原籍地所在的村（社区）")
    private String ucensusVillage;
    
    @TLVAttribute(tag = 14010073, description = "uuid形式自定义头像")
    private String uuidPortrait;
    
	public int getSkyId() {
		return skyId;
	}

	public String getUserName() {
		return userName;
	}

	public String getNickname() {
		return nickname;
	}

	public String getUrealname() {
		return urealname;
	}

	public String getUsex() {
		return usex;
	}

	public String getUbirthday() {
		return ubirthday;
	}

	public String getUanimals() {
		return uanimals;
	}

	public String getUstar() {
		return ustar;
	}

	public String getUblood() {
		return ublood;
	}

	public String getUmarried() {
		return umarried;
	}

	public String getUportraitid() {
		return uportraitid;
	}

	public String getUdefineportrait() {
		return udefineportrait;
	}

	public String getUcountry() {
		return ucountry;
	}

	public String getUprovince() {
		return uprovince;
	}

	public String getUcity() {
		return ucity;
	}

	public String getUhometown() {
		return uhometown;
	}

	public String getUlongitude() {
		return ulongitude;
	}

	public String getUlatitude() {
		return ulatitude;
	}

	public String getUsignature() {
		return usignature;
	}

	public String getUdesc() {
		return udesc;
	}

	public String getUemail() {
		return uemail;
	}

	public String getUemailchecked() {
		return uemailchecked;
	}

	public String getUtelephone() {
		return utelephone;
	}

	public String getUvocation() {
		return uvocation;
	}

	public String getUschoolgraduated() {
		return uschoolgraduated;
	}

	public String getUprivacy() {
		return uprivacy;
	}

	public String getUcreatetime() {
		return ucreatetime;
	}

	public String getUmodifytime() {
		return umodifytime;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public String getHaspic() {
		return haspic;
	}

	public String getUindustry() {
		return uindustry;
	}

	public String getUhobbies() {
		return uhobbies;
	}

	public String getUcorporation() {
		return ucorporation;
	}

	public String getUcensusprovince() {
		return ucensusprovince;
	}

	public String getUcensuscity() {
		return ucensuscity;
	}

	public String getUcensuscounty() {
		return ucensuscounty;
	}

	public String getUcensushometown() {
		return ucensushometown;
	}

	public String getUcensusvillage() {
		return ucensusvillage;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getReligion() {
		return religion;
	}

	public String getPersonNickname() {
		return personNickname;
	}

	public String getPrivacyStrategy() {
		return privacyStrategy;
	}

	public void setSkyId(int skyId) {
		this.skyId = skyId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setUrealname(String urealname) {
		this.urealname = urealname;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public void setUbirthday(String ubirthday) {
		this.ubirthday = ubirthday;
	}

	public void setUanimals(String uanimals) {
		this.uanimals = uanimals;
	}

	public void setUstar(String ustar) {
		this.ustar = ustar;
	}

	public void setUblood(String ublood) {
		this.ublood = ublood;
	}

	public void setUmarried(String umarried) {
		this.umarried = umarried;
	}

	public void setUportraitid(String uportraitid) {
		this.uportraitid = uportraitid;
	}

	public void setUdefineportrait(String udefineportrait) {
		this.udefineportrait = udefineportrait;
	}

	public void setUcountry(String ucountry) {
		this.ucountry = ucountry;
	}

	public void setUprovince(String uprovince) {
		this.uprovince = uprovince;
	}

	public void setUcity(String ucity) {
		this.ucity = ucity;
	}

	public void setUhometown(String uhometown) {
		this.uhometown = uhometown;
	}

	public void setUlongitude(String ulongitude) {
		this.ulongitude = ulongitude;
	}

	public void setUlatitude(String ulatitude) {
		this.ulatitude = ulatitude;
	}

	public void setUsignature(String usignature) {
		this.usignature = usignature;
	}

	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public void setUemailchecked(String uemailchecked) {
		this.uemailchecked = uemailchecked;
	}

	public void setUtelephone(String utelephone) {
		this.utelephone = utelephone;
	}

	public void setUvocation(String uvocation) {
		this.uvocation = uvocation;
	}

	public void setUschoolgraduated(String uschoolgraduated) {
		this.uschoolgraduated = uschoolgraduated;
	}

	public void setUprivacy(String uprivacy) {
		this.uprivacy = uprivacy;
	}

	public void setUcreatetime(String ucreatetime) {
		this.ucreatetime = ucreatetime;
	}

	public void setUmodifytime(String umodifytime) {
		this.umodifytime = umodifytime;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public void setHaspic(String haspic) {
		this.haspic = haspic;
	}

	public void setUindustry(String uindustry) {
		this.uindustry = uindustry;
	}

	public void setUhobbies(String uhobbies) {
		this.uhobbies = uhobbies;
	}

	public void setUcorporation(String ucorporation) {
		this.ucorporation = ucorporation;
	}

	public void setUcensusprovince(String ucensusprovince) {
		this.ucensusprovince = ucensusprovince;
	}

	public void setUcensuscity(String ucensuscity) {
		this.ucensuscity = ucensuscity;
	}

	public void setUcensuscounty(String ucensuscounty) {
		this.ucensuscounty = ucensuscounty;
	}

	public void setUcensushometown(String ucensushometown) {
		this.ucensushometown = ucensushometown;
	}

	public void setUcensusvillage(String ucensusvillage) {
		this.ucensusvillage = ucensusvillage;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public void setPersonNickname(String personNickname) {
		this.personNickname = personNickname;
	}

	public void setPrivacyStrategy(String privacyStrategy) {
		this.privacyStrategy = privacyStrategy;
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
     * @return the ucensusHometown
     */
    public String getUcensusHometown() {
        return ucensusHometown;
    }

    /**
     * @param ucensusHometown the ucensusHometown to set
     */
    public void setUcensusHometown(String ucensusHometown) {
        this.ucensusHometown = ucensusHometown;
    }

    /**
     * @return the ucensusVillage
     */
    public String getUcensusVillage() {
        return ucensusVillage;
    }

    /**
     * @param ucensusVillage the ucensusVillage to set
     */
    public void setUcensusVillage(String ucensusVillage) {
        this.ucensusVillage = ucensusVillage;
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
