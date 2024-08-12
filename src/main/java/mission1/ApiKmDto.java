package mission1;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ApiKmDto {
	private double km;
	private String mrgNo;
	private String wrdofc;
	private String mainNm;
	private String adress1;
	private String adress2;
	private String instlFloor;
	private String instlTy;
	private String instlMby;
	private String svcSe;
	private String cmcWr;
	private int cnstcYear;
	private String inoutDoor;
	private String remars3;
	private double lat;
	private double lnt;
	private String workDttm;
	
	public ApiKmDto() {
		
	}
	
	public ApiKmDto(String mrgNo, double km) {
		this.mrgNo = mrgNo;
		this.km = km;	
	}

	public double getKm() {
		return km;
	}
}
