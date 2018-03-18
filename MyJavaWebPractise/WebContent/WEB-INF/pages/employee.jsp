<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="Utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<!-- 
1.获取#city,添加change响应函数
2.使得#department只保留第一个option子节点
3.获取#city选择的值，若该值为“”，即选择的是“请选择...”，此时不需要发送Ajax请求
4.若值不为“”，说明的确是city发生了变化，准备AJAX请求
 4.1 url:EmployeeServlet?method=listDepartments
 4.2 args:locationId,time
5.返回的是一个JSON数组
	5.1 若返回的数组中的元素为0：提示：“当前城市没有部门”
	5.2若返回的数组中元素不为0：遍历，创建<option value='departmentId'>departmentName</option>,
	并把新创建的option节点加为#department的子节点。
 -->
<script type="text/javascript">
    /*城市与部门之间的联动实现*/
    function depfromcity(){
    	$("#city").change(function(){		
			//console.log("running");
			$("#department option:not(:first)").remove();
			var city = $(this).val();
			if(city!= ""){	
				var url = "EmployeeServlet?method=listDepartments";
				var args = {"locationId":city,"time":new Date()};
				$.getJSON(url,args,function(data){
					if(data.length==0){
						alert("当前城市没有任何部门");
					}else{
						for(var i=0;i<data.length;i++){
							var dptId = data[i].departmentId;
							var dptName = data[i].departmentName;
							$("#department").append("<option value='"+dptId+"'>"+dptName+"</option>")
						}
					}
				})
			}
		})
    }
    /*员工和部门之间的联动实现*/
    function empfromdep(){
    	$("#department").change(function(){
    		$("#employee option:not(:first)").remove();//每次选项改变都要清空下一级
    		var department = $(this).val();
    		if(department!=""){	
    			var url = "EmployeeServlet?method=listEmployees";
    			var args = {"departmentId":department,"time":new Date()};
    			$.getJSON(url,args,function(data){
    				if(data.length==0){
    					alert("该部门没有任何员工");
    				}else{
    					for(var i=0;i<data.length;i++){
    						var empId = data[i].employeeId;
    						var empName = data[i].lastName;
    	    				$("#employee").append("<option value='"+empId+"'>"+empName+"</option>");
    					}
    				
    				}
    			})
    		}
    	})
    }
    function showEmp(){
    	$("#employee").change(function(){
    		$("#empdetails tr:not(:first)").remove();
    		var employeeId = $(this).val();
    		if(employeeId==""){
    			$("#empdetails").hide();
    		}else{
    			$("#empdetails").show();
				var url = "EmployeeServlet?method=listEmployeeInfo";
				var args = {"employeeId":employeeId,"time":new Date()}
				$.getJSON(url,args,function(data){
					if(data.length!=0){				
							var Id = data.employeeId;
							var Name = data.lastName;
							var Email = data.email;
							var salary = data.salary;
							
							$("#empdetails tr:nth-child(1)")
							.after("<tr><td id='id'>"+Id+"</td><td id='name'>"
									+Name+"</td><td id='email'>"
									+Email+"</td><td id='salary'>"
									+salary+"</td></tr>");
					}
				})
    		}
    	})
    }
	$(function(){
		depfromcity();
		empfromdep();
		showEmp();
	})
	
	
</script>
</head>
<body>
<input type="button" value="点击" id="myButton" /> 
	<img alt="" id="loading" src="images/loading.gif" style="display: none" />
	City:
	<select id="city">
		<option value="">请选择...</option>
		<c:forEach items="${ locations}" var="location">
			<option value="${location.locationId }">${location.city}</option>
		</c:forEach>
	</select> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Department:
	<select id="department">
		<option value="">请选择...</option>
	</select> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Employee:
	<select id="employee">
		<option value="">请选择...</option>
	</select> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
	<table id="empdetails" border="1" cellspacing="0" cellpadding="5" style="display:none" >
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Salary</th>
		</tr>
	</table>
</body>
</html>