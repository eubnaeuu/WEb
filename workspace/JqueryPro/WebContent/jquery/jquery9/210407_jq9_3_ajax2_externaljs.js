
		function fnGet(){
			// submit 실행
			var fm = document.getElementById("fm"); // <form>
			fm.action = "html1.html?userId=test홍&paswword=12324"; // url 세팅
			fm.method = "post"; 
			fm.submit();
		}
		
		function fnAjax(){
			$.ajax({
				url : "intro.html"
				,type : "GET"
// 				,data : 
// 				,dataType : 
				,success : function(data){
					alert(data);
					$("#divResult").html(data);
				}
				,error : function(data){
					console.error(data);
				}
			});
		}
		
		function fnJsonText(){
			$.ajax({
// 				url : "../sample/data_text.txt" // 상대경로
				url : "/JqueryPro/jquery/sample/data_text" // 루트경로 : 맨앞 / 는 루트(webcontent)를 의미
				,type : "get"
// 				,data : 
				,dataType : "text" // html이라고 써도 가능
				,success : function(data){
					$("#divResult").html(data);
					alert(data);
				}
				,error : function(data){
					console.error(data);				
				}
			});
		}
		function fnJsonObj(){
			$.ajax({
				url : "/JqueryPro/jquery/sample/data_json_obj.txt"
				,dataType : "json"
				,success : function(data){
// 					console.log(data); // obj로 가져옴
// 					console.log(data.name);
					var str =""
							+"이름 : "+data.name+"<br>"
							+"나이 : "+data.age+"<br>"
							+"성별 : "+data.gender+"<br>"
							+"직업 : "+data.job;
					$("#divResult").html(str);
				}
				,error : function(data){
					console.error(data);
				}
			})
				}
		function fnJsonArr(){
			$.ajax({
				url : "/JqueryPro/jquery/sample/data_array.txt"
				,dataType : "json"
				,success : function(data){
					console.log(data);

					// for(in)
					var str1 = "<ol>";
					for(var i in data){  
					str1 += "<li>" + data[i] + "</li>"; 	
					}
					str1 += "</ol>";
					
		
					// $.each(arr, function(idx, item){})  
					var str2 = "<ol>";
					$.each(data,function(idx, item){
						str2 += "<li>" 
								+ "idx : "+ idx + " "
								+ "item : " + item + " "
								+ "this : "+this + " "
								+ "</li>";	
					});
					str2 += "</ol>";
						console.log(str2);
					$("#divResult").html(str1+"<hr>"+str2+"");
					}
				,error : function(data){
					console.error(data);
				}
			});
		}

		function fnJsonList() {
			$.ajax({
				url : "/JqueryPro/jquery/sample/data_json_list.txt",
				dataType : "json",
				success : function(data) {
					var str = "";
					for ( var i in data) {
						console.log(i);
						var obj = data[i];

						str += i + "번째 회원<br>" 
								+ "이름 : "+ obj.name + "<br>"
								+ "나이 : " + obj.age + "<br>" 
								+ "성별 : "+ obj.gender + "<br>" 
								+ "직업 : " + obj.job
								+ "<br><br><br>";
					}
					
						makeTableResult(data);
				},
				error : function(data) {
					console.error(data);
				}
			});
			
		}
			function fnXml(param){
				$.ajax({
					url:"cd_catalog.xml"
// 					,type : "get"
// 					,data : {}
				,dataType : "xml"
				,success : function(data){
// 					console.log(data);	 // #document 전체 데이터 가지고 옴
// 					console.log(data.getgElementsByTagName("CATALOG")); //data가 #document이기에 getElement~ 사용가능
// 					console.log(data.getElementsByTagName("CD"));
					if(param=="ARTIST"){
						makeArtistList(data);	
					}else if(param=="TITLE"){
						makeTitleList(data);
					}else if(param == "TABLE"){
						makeTableList(data);
					}else if(param == "TABLE2")
						makeTableList2(data);
				}
				,error : function(xhr){ // error 파라미터는 xhr을 많이 씀
					console.log(xhr);
					alert("오류발생");
					
				}
				})
				
			}
			function makeTableResult(data){
				// table , tr ,td

				var table = "<table border='2px solid'>";
				table += "<tr>";
				table += "<th>번호</th>";
				for(var i in data[0]){
//					for(var i of data[0]){ // error
				table += "<th>" + i + "</th>";
				}
				table += "</tr>";
				var cnt = 0;
				$(data).each(function() {
					cnt++;
					table += "<tr>"
					var tmp = this;
					table += "<td>"+cnt+"</td>";
					for (var i in tmp) {
						table += "<td>";
						table += tmp[i];
						table += "</td>";
						}
					table += "</tr>"
				});
				table += "</table>"
			
			// 					$("#divResult").html(str);
				$("#divResult").html(table);
				};

			
			function makeArtistList(data){
				// data ==> document
// 				console.log(data);	 // #document 전체 데이터 가지고 옴
// 				console.log(data.getgElementsByTagName("CATALOG")); //data가 #document이기에 getElement~ 사용가능
// 				console.log(data.getElementsByTagName("CD"));
				var arr = data.getElementsByTagName("ARTIST");
				
// 				console.log(arr);
// 				console.log(arr[0]);
//				console.log(arr[0].innerHTML);
				
				var str ="";
				for(var i=0; i<arr.length; i++){
					str += arr[i].innerHTML + "<br>";
				}
				$("#divResult").html(str);
				}
			
			function makeTitleList(data){
				var arr = data.getElementsByTagName("TITLE");
				
// 				console.log(arr);
// 				console.log(arr[0]);
// 				console.log(arr[0].innerHTML);
				
// 				console.log(arr[0].childNodes);
// 				console.log(arr[0].childNodes[0]);
// 				console.log(arr[0].childNodes[0].nodeValue);
				// [HTML 교재 10 - DOM순회] pdf에서 참고
				
				var str ="";
				for(var i=0; i<arr.length; i++){
					str += arr[i].childNodes[0].nodeValue + "<br>"; // 가수이름
				}
				$("#divResult").html(str);
					
				}
			
			function makeTableList(data){
				var str = "";
				var tmp = data.getElementsByTagName("CATALOG"); //tmp[0].childNodes[1] => CD
				var tmp2 = tmp[0].children[0];
				
				/*
				 * var cdarr = param.getElementsByTagName("CD");
				 * 
				 * for(var obj of arr){
				 * console.log(obj);
				 * console.log($(obj).children());
				 * console.log($(obj).children().eq(0));
				 * console.log($(obj).children().eq(0).html());
				 * 
				 */
				
				
				
				str += "<table border='2px solid'><tr>";
				for(var i=0; i<tmp2.children.length; i++){
					var th = tmp2.children[i].nodeName;
					str += "<th>"+th+"</th>";
//					console.log(tmp2.children[i].nodeName);
				}
				str += "</tr>";
				
				for(var i=0; i<tmp[0].children.length; i++){
					str += "<tr>";
//					console.log(tmp[0].children[0].children.length);
					for(var j=0; j<tmp[0].children[i].children.length; j++){
						str += "<td>";
						var td = tmp[0].children[i].children[j].innerHTML;
						str += td + "</td>";
					}
					str += "</tr>"
				}
				str += "</table>"
				$("#divResult").html(str);
				
			}		// 		  <COUNTRY>UK</COUNTRY>
			
			//    <PRICE>9.90</PRICE>
			function makeTableList2(data){
				var str = "";
				
				 var cdarr = data.getElementsByTagName("CD");
				 
				 console.log(cdarr);

			}
				
				
			
