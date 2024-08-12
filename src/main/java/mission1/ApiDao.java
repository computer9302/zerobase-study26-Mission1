package mission1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApiDao {
	private final String DB_URL = "jdbc:mariadb://localhost:3306/public_wifi";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "zerobase";

    private Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public void insertApi(ApiDto apiDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // INSERT SQL문
            String sql = "INSERT INTO public_wifi VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            // 데이터 설정
            pstmt.setString(1, apiDto.getMrgNo());
            pstmt.setString(2, apiDto.getWrdofc());
            pstmt.setString(3, apiDto.getMainNm());
            pstmt.setString(4, apiDto.getAdress1());
            pstmt.setString(5, apiDto.getAdress2());
            pstmt.setString(6, apiDto.getInstlFloor());
            pstmt.setString(7, apiDto.getInstlTy());
            pstmt.setString(8, apiDto.getInstlMby());
            pstmt.setString(9, apiDto.getSvcSe());
            pstmt.setString(10, apiDto.getCmcWr());
            pstmt.setInt(11, apiDto.getCnstcYear());
            pstmt.setString(12, apiDto.getInoutDoor());
            pstmt.setString(13, apiDto.getRemars3());
            pstmt.setDouble(14, apiDto.getLat());
            pstmt.setDouble(15, apiDto.getLnt());
            pstmt.setString(16, apiDto.getWorkDttm());


            // SQL 실행
            int rowInserted = pstmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("데이터가 성공적으로 삽입되었습니다.");
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public int selectCount(){
        
    	int i = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // SELECT SQL문
            String sql = "SELECT count(*) from public_wifi";
            pstmt = conn.prepareStatement(sql);

            // SQL 실행
            rs = pstmt.executeQuery();

            // DB -> ApiDto 저장
            
            while (rs.next()) {
           
                i = rs.getInt("count(*)");
                
            }

        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return i;
    }
    
    public ArrayList<ApiDto> selectApi(){
        ArrayList<ApiDto> apiDtoList = new ArrayList<ApiDto>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // SELECT SQL문
            String sql = "SELECT mrgno, wrdofc, mainNm, adress1, adress2, nullif(instlFloor, 0), instlTy, instlMby, svcSe, cmcWr, cnstcYear, inoutDoor, nullif(remars3, 0), lat, lnt, workDttm FROM PUBLIC_WIFI";
            pstmt = conn.prepareStatement(sql);

            // SQL 실행
            rs = pstmt.executeQuery();

            // DB -> ApiDto 저장

            // int i=0;
            while (rs.next()) {
                ApiDto apiDto = new ApiDto();
                apiDto.setMrgNo(rs.getString("mrgNo"));
                apiDto.setWrdofc(rs.getString("wrdofc"));
                apiDto.setMainNm(rs.getString("mainNm"));
                apiDto.setAdress1(rs.getString("adress1"));
                apiDto.setAdress2(rs.getString("adress2"));
                apiDto.setInstlFloor(rs.getString("nullif(instlFloor, 0)"));
                apiDto.setInstlTy(rs.getString("instlTy"));
                apiDto.setInstlMby(rs.getString("instlMby"));
                apiDto.setSvcSe(rs.getString("svcSe"));
                apiDto.setCmcWr(rs.getString("cmcWr"));
                apiDto.setCnstcYear(rs.getInt("cnstcYear"));
                apiDto.setInoutDoor(rs.getString("inoutDoor"));
                apiDto.setRemars3(rs.getString("nullif(remars3, 0)"));
                apiDto.setLat(rs.getDouble("lat"));
                apiDto.setLnt(rs.getDouble("lnt"));
                apiDto.setWorkDttm(rs.getString("workDttm"));
                apiDtoList.add(apiDto);

                // System.out.println(apiDtoList.get(i).getMrgNo());
                //  i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return apiDtoList;
    }
    
    public void insertnearWifiInfo(ApiKmDto apiKmDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // INSERT SQL문
            String sql = "INSERT INTO nearwifiinfo VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            // 데이터 설정
            pstmt.setString(1, apiKmDto.getMrgNo());
            pstmt.setDouble(2, apiKmDto.getKm());
          
            // SQL 실행
            int rowInserted = pstmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("데이터가 성공적으로 삽입되었습니다.");
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<ApiKmDto> selectNearWifiInfo(){
        ArrayList<ApiKmDto> apiKmDtoList = new ArrayList();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // SELECT SQL문
            String sql = "select t1.km, public_wifi.*\r\n"
            		+ "from (select * from nearwifiinfo order by km ASC limit 20) as t1\r\n"
            		+ "inner join public_wifi on t1.mrgNo = public_wifi.mrgNo";
            pstmt = conn.prepareStatement(sql);

            // SQL 실행
            rs = pstmt.executeQuery();

            // DB -> ApiDto 저장

            // int i=0;
            while (rs.next()) {
                ApiKmDto apiKmDto = new ApiKmDto();
                apiKmDto.setKm(rs.getDouble("km"));
                apiKmDto.setMrgNo(rs.getString("mrgNo"));
                apiKmDto.setWrdofc(rs.getString("wrdofc"));
                apiKmDto.setMainNm(rs.getString("mainNm"));
                apiKmDto.setAdress1(rs.getString("adress1"));
                apiKmDto.setAdress2(rs.getString("adress2"));
                apiKmDto.setInstlFloor(rs.getString("instlFloor"));
                apiKmDto.setInstlTy(rs.getString("instlTy"));
                apiKmDto.setInstlMby(rs.getString("instlMby"));
                apiKmDto.setSvcSe(rs.getString("svcSe"));
                apiKmDto.setCmcWr(rs.getString("cmcWr"));
                apiKmDto.setCnstcYear(rs.getInt("cnstcYear"));
                apiKmDto.setInoutDoor(rs.getString("inoutDoor"));
                apiKmDto.setRemars3(rs.getString("remars3"));
                apiKmDto.setLat(rs.getDouble("lat"));
                apiKmDto.setLnt(rs.getDouble("lnt"));
                apiKmDto.setWorkDttm(rs.getString("workDttm"));
                apiKmDtoList.add(apiKmDto);

                // System.out.println(apiDtoList.get(i).getMrgNo());
                //  i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return apiKmDtoList;
    }
    
    public void insertLocationHistoryList(Double lat, Double lnt, String localDateTime) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // INSERT SQL문
            String sql = "INSERT INTO locationhistorylist (lat, lnt, viewDate, deleteButton) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            // 데이터 설정
            pstmt.setDouble(1, lat);
            pstmt.setDouble(2, lnt);
            pstmt.setString(3, localDateTime);
            pstmt.setString(4, "삭제");
            


            // SQL 실행
            int rowInserted = pstmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("데이터가 성공적으로 삽입되었습니다.");
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<LocationHistoryListDto> selectLocationHistoryList(){
        ArrayList<LocationHistoryListDto> lhldDtoList = new ArrayList<LocationHistoryListDto>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // SELECT SQL문
            String sql = "SELECT * FROM locationhistorylist";
            pstmt = conn.prepareStatement(sql);

            // SQL 실행
            rs = pstmt.executeQuery();

            // DB -> ApiDto 저장

            // int i=0;
            while (rs.next()) {
                LocationHistoryListDto lhld = new LocationHistoryListDto();
                lhld.setId(rs.getInt("id"));
                lhld.setLat(rs.getDouble("lat"));
                lhld.setLnt(rs.getDouble("lnt"));
                lhld.setLocalDateTime(rs.getString("viewDate"));
                lhld.setDelete123(rs.getString("deleteButton"));
                
                lhldDtoList.add(lhld);

                // System.out.println(apiDtoList.get(i).getMrgNo());
                //  i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return lhldDtoList;
    }
}

           
