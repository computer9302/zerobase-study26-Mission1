<%@page import="mission1.ApiKmDto"%>
<%@page import="mission1.ApiExplorer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
        nav ul {
            list-style-type: none; /* 불릿(점) 제거 */
            padding: 0; /* 기본 여백 제거 */
            margin: 0; /* 기본 여백 제거 */
            display: flex; /* 가로로 배치 */
        }

        nav ul li {
            margin-right: 20px; /* 항목 간의 간격 설정 */
        }

        nav ul li:last-child {
            margin-right: 0; /* 마지막 항목은 간격 제거 */
        }
        
        .container{
        	display: flex;
        	align-items -: center;
        }
        .container form{
        	margin-right: 10px;
        }
        .container input[type="text"]{
        	margin-right: 5px;
        }
    </style>
    
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
  		    try {
  		        const jsonData = JSON.parse(data); // 수동으로 JSON 파싱
  		        console.log('WIFI Info:', jsonData);
  		    } catch (e) {
  		        console.error('Error parsing JSON:', e);
  		    }
  		    selectNearWifiInfo();
  		})
  		.catch(error => {
  		    console.error('Error:', error);
  		});
  	}
  	
  function selectNearWifiInfo(){
	  fetch('selectNearWifiInfo')
  		.then(response => response.json())
  		.then(data => {
  			console.log('Fetched Wifi Info:', data);
  		})
  		.catch(error => {
  			console.error('Error:', error);
  		});
  }
  	
  
    </script>
    
</head>
<body>
<h2>와이파이 정보 구하기</h2>
    <nav>
        <ul>
            <li><a href="index.jsp">홈</a></li>
            <li><a href="location_history.jsp">위치 히스토리 목록</a></li>
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
    
</body>
</html>