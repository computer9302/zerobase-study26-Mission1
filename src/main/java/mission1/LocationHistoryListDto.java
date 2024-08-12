package mission1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LocationHistoryListDto {
	int id;
	double lat;
	double lnt;
	String localDateTime;
	String delete123;
	
	public LocationHistoryListDto() {
		
	}
	
	public LocationHistoryListDto(double lat, double lnt, String localDateTime) {
		super();
		this.lat = lat;
		this.lnt = lnt;
		this.localDateTime = localDateTime;
	}

	public LocationHistoryListDto(int id, double lat, double lnt, String localDateTime, String delete123) {
		super();
		this.id = id;
		this.lat = lat;
		this.lnt = lnt;
		this.localDateTime = localDateTime;
		this.delete123 = delete123;
	}
	
	
	
	

}
