package mission1;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import mission1.WifiInfo.TbPublicWifiInfo;
import latlnt.LatLntBeanServlet;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import mission1.ApiKmDto;




public class ApiExplorer {
	public void insertApi() throws IOException, ParserConfigurationException, SAXException, InterruptedException{
		 
		int startIdx = 1;
		int endIdx = 1000;
		WifiInfo wifiInfo = new WifiInfo();
		int totalCount = 0;
		do {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
         urlBuilder.append("/" + URLEncoder.encode("6b767a586e636f6d3138726b756a6f", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
         urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
         urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
         urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIdx), "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
         urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endIdx), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
         // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

         // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
         // System.out.println(urlBuilder.toString());
         URL url = new URL(urlBuilder.toString());
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Content-type", "application/xml");
         System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
         BufferedReader rd;

         // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
         if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
             rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         } else {
             rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
         }
         StringBuilder sb = new StringBuilder();
         String line;
         while ((line = rd.readLine()) != null) {
             sb.append(line);
         }
         rd.close();
         conn.disconnect();
         //System.out.println(sb.toString());
         
         // json 파싱
     
      Gson gson = new Gson();
      wifiInfo = gson.fromJson(sb.toString(), WifiInfo.class);
      totalCount = wifiInfo.tbPublicWifiInfo.listTotalCount;
      
      //System.out.println(wifiInfo);
   
     /* 
      for(ApiDto row : wifiInfo.tbPublicWifiInfo.apiDtoList) {
    	  System.out.println(row.getMrgNo());
      	}
      	*/
      for(int i=0; i<wifiInfo.tbPublicWifiInfo.apiDtoList.size();i++) {
      ApiDao apiDao = new ApiDao();
      apiDao.insertApi(wifiInfo.tbPublicWifiInfo.apiDtoList.get(i));
      // Thread.sleep(1)으로 지연시키지 않으면 24577개가 전부 저장되지 않는다.
      // mariaDB 라이브러리를 java 버전이랑 맞추지 않으면 에러가 발생한다.
      Thread.sleep(1);
      }
			startIdx= endIdx + 1;
			endIdx = endIdx + 1000;
			
		if(endIdx > totalCount) {
			endIdx = totalCount;
		}
			
	}while(startIdx <= totalCount);
     
}
	
	public int selectCount() {
		ApiDao apiDao = new ApiDao();
		return apiDao.selectCount();
	}
	
	public ArrayList<ApiDto> selectApi() {
		ApiDao apiDao = new ApiDao();
		return apiDao.selectApi();
	}
	
	public void insertNearWifiInfoApi() throws InterruptedException {
				// 나의 좌표
				LatLntBeanServlet latlntBeanServlet = new LatLntBeanServlet();
				Double lat = latlntBeanServlet.lat;
				Double lnt = latlntBeanServlet.lnt;
				System.out.println(lat+" "+lnt);
			
				// 임의의 좌표 값 = public_wifi 테이블의 lat, lnt 값
				ApiExplorer apiExplorer = new ApiExplorer();
				List<ApiDto> apiDtoList = apiExplorer.selectApi();
				
				// 가까운 좌표값을 비교하기 위한 최고값 변수
				double min = Integer.MAX_VALUE;
				// 거리, 나머지 정보 저장할 객체
				List<ApiKmDto> apiKmDtoList = new ArrayList<>();
				
			
				// 거리를 구해서 저장한다.
				for(int i = 0; i < apiDtoList.size(); i++) {
					apiKmDtoList.add(new ApiKmDto(apiDtoList.get(i).getMrgNo(), Math.sqrt(Math.pow(lat-apiDtoList.get(i).getLat(), 2) + Math.pow(lnt - apiDtoList.get(i).getLnt(), 2))));
				}
				
				for(int i = 0; i < apiKmDtoList.size(); i++) {
					ApiDao apiDao = new ApiDao();
					apiDao.insertnearWifiInfo(apiKmDtoList.get(i));
					// insertApi()와 달리 1로는 부족하다. 2로 설정해야 address error, sqlsyntaxException이 발생하지 않는다.
					Thread.sleep(2);
				}
		
	}
	

	public ArrayList<ApiKmDto> selectNearWifiInfo(){
		
		ApiDao apiDao = new ApiDao();
		ArrayList<ApiKmDto> apiKmDto = new ArrayList<>();
		apiKmDto = apiDao.selectNearWifiInfo();
		
		for(int i=0; i<apiKmDto.size(); i++) {
			System.out.println(apiKmDto.get(i).getKm() + " " + apiKmDto.get(i).getMrgNo() + " " + apiKmDto.get(i).getWrdofc()
								+ " " + apiKmDto.get(i).getMainNm() + " " + apiKmDto.get(i).getAdress1() + " " + apiKmDto.get(i).getAdress2()
								+ " " + apiKmDto.get(i).getInstlFloor() + " " + apiKmDto.get(i).getInstlTy() + " " + apiKmDto.get(i).getInstlMby()
								+ " " + apiKmDto.get(i).getSvcSe() + " " + apiKmDto.get(i).getCmcWr() + " " + apiKmDto.get(i).getCnstcYear()
								+ " " + apiKmDto.get(i).getInoutDoor() + " " + apiKmDto.get(i).getRemars3() + " " + apiKmDto.get(i).getLat()
								+ " " + apiKmDto.get(i).getLnt() + " " + apiKmDto.get(i).getWorkDttm());
		}
		 
		 return apiKmDto;
		
		
	}

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, InterruptedException{
		 ApiExplorer apiExplorer = new ApiExplorer();
		// apiExplorer.insertApi();
		// apiExplorer.selectCount();
		
		// apiExplorer.insertNearWifiInfoApi();
	
	}

}
