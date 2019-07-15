<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% pageContext.setAttribute("PATH", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript" src="${PATH}/source/js/jquery-3.4.0.min.js"></script>
<script type="text/javascript" src="${PATH}/source/js/echarts.min.js"></script>
<script type="text/javascript">
var myChart = echarts.init(document.getElementById('main'));

//指定图表的配置项和数据
var option = {
    title: {
        text: '商品信息'
    },
    tooltip: {},
    legend: {
        data:['库存']
    },
    xAxis: {
        data: ["A","B","C","D","E"]
    },
    yAxis: {},
    series: [{
        name: '库存',
        type: 'bar',
        data: [12, 5, 55, 8, 27]
    }]
};
myChart.setOption(option);
</script>
</body>
</html>