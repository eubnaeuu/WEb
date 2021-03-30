		
		var pop;
		function proc(){
			pop = window.open("http://google.com");
		}
		
		function proc2(){
			pop.close();
		}
		
		function showMsg(){
// 			window.setTimeout();
			setTimeout(alert(alertMsg),1); // 메서드괄호 ()를 붙이면 바로 실행됨. 빼면 n초후에 실행됨
		}
		
//		function showMsg(){
//			setTimeout(alert("안됨?"),3000);  
//		}
		
//		function showMsg(){
//			setTimeout(function alertMsg(){
//				alert("ㅁㄴㅇ람;ㄴ러ㅑㅁ;더럄니");
//		}
//		}
		
		function changeBgColor(){
			setInterval(changeColor,3000);
		}
		
		function changeColor(){
			// 랜덤으로 색을 만들어서 p태그의 배경을 넣어주기
			// 1. 랜덤 색 뽑기
			// 2. p태그에 배경색 주기
			var r=0, g=0, b=0;
			r = Math.floor(Math.random()*256) + 1;
			g = Math.floor(Math.random()*256) + 1;
			b = Math.floor(Math.random()*256) + 1;
			document.getElementById("pInterval").style.backgroundColor = "red"; // js에서는 대쉬(-)적용 X. 이후문자를 대문자로.
			document.getElementById("pInterval").style.backgroundColor = "rgb("+r+","+g+","+b+")";
			// "red", "#ff0055", "rgb(255,0,255)";
		}
		
		
