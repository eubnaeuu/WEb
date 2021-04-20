$(document).ready(function(){
//	var idchk = document.getElementById("deleChk").value;
//	console.log(idchk);
//	if(idchk==="활성화"){
//	$("#deleChk").css('background','rgb(65, 120, 240)');	
//	}else{
//	$("#deleChk").css('background','#fd5d5d');	
//	}
});


function changeStatus(inputObj, memId){
	var active = inputObj.value;
	console.log(memId);
	console.log(active);
	//<%if(memDelete.equals("활성화")){%>
    //status-nonactive<%}else{%>status--active<%} %>
	//status-nonactive
    if(inputObj.value == '활성화'){
    	//비활성화 만들 것임
    	var pdata = {"active":"Y","memId":memId}
    	$.ajax({
    		url : "/admin/memberList.do"
    		,type : "post" 
    		,data : pdata    
    		,success : function(data){
    
    			console.log("활성화");
    			inputObj.value = '비활성화';
    			$(inputObj).addClass("status--nonactive");
    	
    		}
    		,error : function(xhr){
    			console.log(xhr)
    			alert("오류발생\n"+xhr.status);
    		}
    	})
    	
    	
    }else{
    	var active = inputObj.value;
    	var pdata = {"active":"N","memId":memId}
    	//활성화 만들것임
    	$.ajax({
    		url : "/admin/memberList.do"
    		,type : "post" 
    		,data : pdata   
    		,success : function(data){
    			
    			inputObj.value = '활성화';
    			$(inputObj).css('background','rgb(65, 120, 240)');
    			$(inputObj).addClass("status--active");
    			//console.log(data);
    		}
    		,error : function(xhr){
    			console.log(xhr)
    			alert("오류발생\n"+xhr.status);
    		}
    	});
    	
    }
} 