<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Data Display</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            function loadData() {
                $.ajax({
                    url: 'fetchData',  // 서블릿 URL
                    method: 'GET',
                    dataType: 'json',
                    success: function(data) {
                        var tableBody = $('#dataTable tbody');
                        tableBody.empty(); // 기존 테이블 내용을 비움
                        $.each(data, function(index, item) {
                            var row = '<tr>' +
                                      '<td>' + item.id + '</td>' +
                                      '<td>' + item.lat + '</td>' +
                                      '<td>' + item.lnt + '</td>' +
                                      '<td>' + item.localDateTime + '</td>' +
                                      '<td>' + item.delete123 + '</td>' +
                                      '</tr>';
                            tableBody.append(row);
                        });
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.error('AJAX 요청 실패:', textStatus, errorThrown);
                    }
                });
            }
            loadData(); // 페이지 로드 시 데이터 로드
        });
    </script>
</head>
<body>
<h2>위치 히스토리 목록</h2>
    <nav>
        <ul>
            <li><a href="index.jsp">홈</a></li>
            <li><a href="locationHistoryList.jsp">위치 히스토리 목록</a></li>
            <li><a href="wifi_info.jsp">Open Api 와이파이 정보 가져오기</a></li>
        </ul>
    </nav>
    
     <table id="dataTable" border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <!-- AJAX로 동적으로 추가된 데이터가 여기에 표시됩니다 -->
        </tbody>
    </table>
</body>
</html>