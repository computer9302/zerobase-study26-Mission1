<%@page import="mission1.ApiKmDto"%>
<%@page import="mission1.ApiExplorer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    
    <script type="text/javascript">
    function accessToGeo(position){
    	const positionObj = {
    			latitude: position.coords.latitude,
    			longitude: position.coords.longitude
    	};
    	
    	document.getElementById('lat').value = positionObj.latitude;
    	document.getElementById('lnt').value = positionObj.longitude;
    
  
    	fetch('positionForm', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'lat': positionObj.latitude,
                'lnt': positionObj.longitude
            })
        })
        .then(response => response.text())
        .then(data => {
            console.log('Success:', data);
           
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    
    }
    
    function askForLocation(){
    	if(navigator.geolocation){
    		navigator.geolocation.getCurrentPosition(
    		accessToGeo,
    		(error) => {
    			console.error("위치 정보를 가져올 수 없습니다. 오류 코드" + error.code);
    		}
    		);
    	}else{
    		console.error("이 브라우저는 위치 정보를 지원하지 않습니다.");
    	}
    }
  
    </script>
    
    <script type="text/javascript">
  	function nearWifiInfo(){
  		insertNearWifiInfoApi();
  	
  	}
  	
  	function insertNearWifiInfoApi(){
  		fetch('insertNearWifiInfoApi')
  		.then(response => response.text()) // JSON 대신 text로 받기
  		.then(data => {
  		    console.log('Response Text:', data);
  		
  			selectNearWifiInfo();
  	  
  		})
  		.catch(error => {
  		    console.error('Error:', error);
  		});
  	}
  	
    </script>
    
    <script>
    function selectNearWifiInfo(){
  	  fetch('selectNearWifiInfo')
    		.then(response => response.json())
    		.then(data => {
    			console.log('Fetched Data:', data);
    			const tableBody = document.getElementById('wifiTableBody');
    			const tableFoot = document.querySelector('tfoot');
    			
    			tableBody.innerHTML = '';
    			tableFoot.style.display = data.length > 0 ? 'none' : 'table-row-group';
    			
    			data.forEach((wifi) =>{
    				console.log('wifi Entry:', wifi);
    				console.log('km:', wifi.km);
    				console.log('mrgNo:', wifi.mrgNo);
    				console.log('instlFloor:', wifi.instlFloor);
    				console.log('remars3:', wifi.remars3);
    				
    			
    				const row = document.createElement('tr');
    				row.innerHTML = `
    				    <td>${wifi.km}</td>
    					<td>${wifi.mrgNo}</td>
                        <td>${wifi.wrdofc}</td>
                        <td>${wifi.mainNm}</td>
                        <td>${wifi.adress1}</td>
                        <td>${wifi.adress2}</td>
                        <td>${wifi.instlFloor}</td>
                        <td>${wifi.instlTy}</td>
                        <td>${wifi.instlMby}</td>
                        <td>${wifi.svcSe}</td>
                        <td>${wifi.cmcWr}</td>
                        <td>${wifi.cnstcYear}</td>
                        <td>${wifi.inoutDoor}</td>
                        <td>${wifi.remars3}</td>
                        <td>${wifi.lat}</td>
                        <td>${wifi.lnt}</td>
                        <td>${wifi.workDttm}</td>
                        `;
    				tableBody.appendChild(row);
    			});
    		})
    		.catch(error => console.error('Error:', error));
    		
    }
    </script>
    
</head>
<body>
<h2>와이파이 정보 구하기</h2>
    <nav>
        <ul>
            <li><a href="index.jsp">홈</a></li>
            <li><a href="locationHistoryList.jsp">위치 히스토리 목록</a></li>
            <li><a href="wifi_info.jsp">Open Api 와이파이 정보 가져오기</a></li>
        </ul>
    </nav>
    <br>
   <div class="container">
        <form action="positionForm" method="post">
            <label for="lat">lat</label>
            <input type="text" id="lat" name="lat" required>
            <label for="lnt">lnt</label>
            <input type="text" id="lnt" name="lnt" required>
        </form>
        <button onclick="askForLocation()">내 위치 가져오기</button>
        <button onclick="nearWifiInfo()">가까운 와이파이 보기</button>
    </div>
    
    
    <table border="1">
    	<thead>
    		<tr>
    			<th>거리(Km)</th>
    			<th>관리번호</th>
    			<th>자치구</th>
    			<th>와이파이명</th>
    			<th>도로명주소</th>
    			<th>상세주소</th>
    			<th>설치위치(층)</th>
    			<th>설치유형</th>
    			<th>설치기관</th>
    			<th>서비스구분</th>
    			<th>망종류</th>
    			<th>설치년도</th>
    			<th>실내외구분</th>
    			<th>WIFI접속 환경</th>
    			<th>X좌표</th>
    			<th>Y좌표</th>
    			<th>작업일자</th>
    		</tr>
    	</thead>
    	<tbody id="wifiTableBody">
    		
    	</tbody>
    	<tfoot>
    		<tr>
    			<td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    		</tr>
    	</tfoot>
    </table>
    
    
</body>
</html>