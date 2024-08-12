package mission1;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ApiDto {
	
	@SerializedName("X_SWIFI_MGR_NO")
	private String mrgNo;
	@SerializedName("X_SWIFI_WRDOFC")
	private String wrdofc;
	@SerializedName("X_SWIFI_MAIN_NM")
	private String mainNm;
	@SerializedName("X_SWIFI_ADRES1")
	private String adress1;
	@SerializedName("X_SWIFI_ADRES2")
	private String adress2;
	@SerializedName("X_SWIFI_INSTL_FLOOR")
	private String instlFloor;
	@SerializedName("X_SWIFI_INSTL_TY")
	private String instlTy;
	@SerializedName("X_SWIFI_INSTL_MBY")
	private String instlMby;
	@SerializedName("X_SWIFI_SVC_SE")
	private String svcSe;
	@SerializedName("X_SWIFI_CMCWR")
	private String cmcWr;
	@SerializedName("X_SWIFI_CNSTC_YEAR")
	private int cnstcYear;
	@SerializedName("X_SWIFI_INOUT_DOOR")
	private String inoutDoor;
	@SerializedName("X_SWIFI_REMARS3")
	private String remars3;
	@SerializedName("LAT")
	private double lat;
	@SerializedName("LNT")
	private double lnt;
	@SerializedName("WORK_DTTM")
	private String workDttm;
	
}
