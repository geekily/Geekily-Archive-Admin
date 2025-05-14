<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jquery/jquery-3.7.1.min.js"/>"></script>
	<title>GEEKILY LAB</title>
</head>
<body>

<canvas id="myChart" width="300" height="300"></canvas>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/chartjs/chart.umd.min.js"/>"></script>
<script>
let myCt = document.getElementById('myChart');

let myChart = new Chart(myCt, {
  type: 'line',
  data: {
	  labels: ['2020', '2021', '2022', '2023'],
	    datasets: [
	      {
	        label: 'Dataset1',
	        data: [10,20,30,40],
	        backgroundColor: '#00C7E2',
	        maxBarThickness: 30
	      },
	      {
	        label: 'Dataset2',
	        data: [5,10,45,20],
	        backgroundColor: '#FF7DA8',
	        maxBarThickness: 30
	      }
	    ]
	},
	options: {
		  responsive: true, // 반응형 여부 (기본값 true)
		  maintainAspectRatio: false, // 크기 고정
		  plugins: {
		  	tooltip: { // 튤팁 스타일링
		      enabled: true, // 튤팁 활성화 (기본값 true)
		      backgroundColor: '#000', // 튤팁 색상
		      padding: 10 // 튤팁 패딩
		    },
		    legend: { // 범례 스타일링
		    	display: true, // 범례 활성화 (기본값 true)
		        position: 'bottom' // 범례 위치
		    }
		  },
		  scales: { // x축과 y축에 대한 설정
			x: {
		      grid: { // 축에 대한 격자선
		        display: false, // grid 활성화 (기본값 true)
		      }
		   	},
		    y: {
		      min: 0, // y축에 대한 최소값
		      max: 100, // y축에 대한 최대값
		      border: { // 축에 대한 테두리 속성
		      	dash: [5, 5] // 점선 형태
		      },
		    }
		  }
		}
});
</script>

</body>
</html>