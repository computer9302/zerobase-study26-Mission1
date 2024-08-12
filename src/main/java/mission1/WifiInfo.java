package mission1;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class WifiInfo {
	@SerializedName("TbPublicWifiInfo")
	TbPublicWifiInfo tbPublicWifiInfo;
	
	@ToString
	@Getter
	@Setter
	class TbPublicWifiInfo{
		@SerializedName("list_total_count")
		int listTotalCount;
		
		@SerializedName("RESULT")
		Result result;
		
		@SerializedName("row")
		List<ApiDto> apiDtoList;
		
		class Result{
			@SerializedName("CODE")
			String code;
			
			@SerializedName("MESSAGE")
			String message;
		}
	}
}
