<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.vo.*"%>
<%
	Member m = (Member) session.getAttribute("member");
%>
<nav>
	<!— 메뉴 선택을 위한 nav 태그 작성 -->
	<ul>
		<li><a href="#">메뉴1</a></li>
		<li><a href="#">메뉴2</a></li>
		<li><a href="#">메뉴3</a></li>
		<li><a href="#">메뉴4</a></li>
	</ul>
</nav>
<%
	if (m == null) {
%>
<div class="box">
	<!— 로그인 From -->
	<p>
		&nbsp; &nbsp;아이디 : <input type="text" name="userid" id="userid">
	</p>
	<p>
		비밀번호 : <input type="password" name="userpwd" id="userpwd">
	</p>
	<button type="button" id="btnLogin">로그인하기</button>
	&nbsp;&nbsp;
	<button type="button" id="btnEnroll"
		onclick="location.href='views/member/enroll.html';">회원가입</button>
</div>
<%
	} else {
%>
<div class="box">
	<h3>
		환영합니다,
		<%=m.getName()%>님!!!
	</h3>
	<p>오늘도 좋은 하루 되세요~!!</p>
	<button onclick="location.href='views/member/myInfo.jsp'">회원정보보기</button>
	<button onclick="location.href='logout.lo'">로그아웃</button>
</div>
<%
	}
%>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#btnLogin").on('click', function() {
		console.log("ajax시작");
		// $(함수); 과 비슷  $.ajax();
		// $.ajax(오브젝트타입);
		// $.ajax({ key1:v1  , key2:v2  ,  ....});
		// object에서 key는 string타입, value에는 함수,숫자,문자,배열,오브젝트 등 모든타입가능
		//  
		$.ajax({ // JQuery 를 통한 ajax 호출 방식 사용
			type : "post",
			url : "login.lo",
			data : {
					id : $("#userid").val(),
					passwd : $("#userpwd").val()
				},
			dataType : "json", // 전달받을 객체는 JSON 이다.
			success : function(d) {
					// /* 이부분은 예시 1,2 경우에 가능
					if(d.result == "ok"){ 
						var text = 
						"<h3>환영합니다, "+d["name"]+"님!!!</h3>"+"<p>오늘도 좋은 하루 되세요~!!</p><button onclick='location.href=\"views/member/myInfo.jsp\"'>회원정보 보기</button><button onclick=\"location.href='logout.lo'\">로그아웃</button>";
						$(".box").html(text);
						
						//예시 1
						console.log(d.memberInfo);//결과 {id: 'user11', passwd: 'pass11', name: '사용자1', email: 'user11@test.or.kr'}
						console.log(d.memberInfo.id);//결과 user11
						//예시 2
						//console.log(d.memberInfo);//결과 0: {id: 'user11', passwd: 'pass11', name: '사용자1', email: 'user11@test.or.kr'} 1: {id: 'user11', passwd: 'pass11', name: '사용자1', email: 'user11@test.or.kr'} length: 2
						//console.log(d.memberInfo[0].id);//결과 user11

					} else {
						alert("로그인 실패!\nID와 비밀번호를 다시 확인하세요.");
					}
					// */
					
					//예시 3
					//console.log(d);//결과  {id: 'user11', passwd: 'pass11', name: '사용자1', email: 'user11@test.or.kr'}
					//console.log(d.id);//결과 user11
					
					//예시 4
					//console.log(d);//결과 0: {id: 'user11', passwd: 'pass11', name: '사용자1', email: 'user11@test.or.kr'}	1: {id: 'user11', passwd: 'pass11', name: '사용자1', email: 'user11@test.or.kr'}	length: 2
					//console.log(d[0].id);//결과 user11					
				},
			// error 상황을 만들어보려면 url: 없는 url을 작성. 또는 지원하지 않는 type 
			// 예) url : "login.loa",
			// 예) type : "get",
			error : function(request,status,error) {
				alert("code:"+request.status+"\n"+"message:"+request.responseText+
				"\n"+"error:"+error);
				}
		});
	});
});
</script>
<hr style="clear: both;">
<!— 이전 float 속성을 지우고 구분선을 표시한다. -->