package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.lo") // ajax
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
	}

	//http://127.0.0.1:8090/web_kh_19/login.lo?id=ej&passwd=1234
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/login.lo 진입");
	
		// 사용자의 정보를 JSON 형식으로 전달하기 위해 ContentType 변경
		response.setContentType("application/json;charset=UTF-8");
		MemberService mservice = new MemberService();
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		System.out.println("id: " + id);
		System.out.println("passwd: " + passwd);
		
		PrintWriter out = response.getWriter();
		
		Gson gob = new GsonBuilder().setPrettyPrinting().create();  // console창에 이쁘게 보이는 것
//		Gson gob = new GsonBuilder().create();
		
		String gobStr = "";
		
		// 입력받은 사용자의 ID와 비밀번호를 인자로 하여 Service의 loginMember() 호출
		List<Member> voList = new ArrayList<Member>(); 
		Member m = mservice.loginMember(id, passwd);
		if (m != null) { 			// 로그인 성공
			System.out.println("로그인성공");
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			
			voList.add(m);
			voList.add(m);
// 예시 5
//			List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
//			Map<String, Object> map3 = new HashMap<String, Object>();
//			map3.put("result", "ok");
//			map3.put("name", m.getName());
//			map3.put("memberInfo", m);
//			listMap.add(map3);
//			listMap.add(map3);
//			listMap.add(map3);
//			gobStr = gob.toJson(listMap);
			
//예시 1
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("result", "ok");
			map2.put("name", m.getName());
			map2.put("memberInfo", m);
			
			gobStr = gob.toJson(map2);
//결과
//				{
//				  "result": "ok",
//				  "memberInfo": {
//				    "id": "user11",
//				    "passwd": "pass11",
//				    "name": "사용자1",
//				    "email": "user11@test.or.kr"
//				  },
//				  "name": "사용자1"
//				}

//예시 2
//			Map<String, Object> map1 = new HashMap<String, Object>();
//			map1.put("result", "ok");
//			map1.put("name", m.getName());
//			map1.put("memberInfo", voList);
//
//			gobStr = gob.toJson(map1);
//결과
//				{
//				  "result": "ok",
//				  "memberInfo": [
//				    {
//				      "id": "user11",
//				      "passwd": "pass11",
//				      "name": "사용자1",
//				      "email": "user11@test.or.kr"
//				    },
//				    {
//				      "id": "user11",
//				      "passwd": "pass11",
//				      "name": "사용자1",
//				      "email": "user11@test.or.kr"
//				    }
//				  ],
//				  "name": "사용자1"
//				}

//예시 3
//			gobStr = gob.toJson(m);
//결과
//			{"id":"user11","passwd":"pass11","name":"사용자1","email":"user11@test.or.kr"}
			
//예시 4
//			gobStr = gob.toJson(voList);
//결과
//			[
//			  {
//			    "id": "user11",
//			    "passwd": "pass11",
//			    "name": "사용자1",
//			    "email": "user11@test.or.kr"
//			  },
//			  {
//			    "id": "user11",
//			    "passwd": "pass11",
//			    "name": "사용자1",
//			    "email": "user11@test.or.kr"
//			  }
//			]
			
			
		} else {			// 로그인 실패
			System.out.println("로그인실패");
			//예시 1
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("result", "fail");
			gobStr = gob.toJson(map2);
		}
		System.out.println("gobStr: " + gobStr);
		out.println(gobStr);
		out.flush();
		out.close();
		//소용없음. ajax 위치로 돌아감. //request.getRequestDispatcher("aaa.jsp").forward(request, response);
	}
	
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("/login.lo 진입");
//	
//		// 사용자의 정보를 JSON 형식으로 전달하기 위해 ContentType 변경
//		response.setContentType("application/json;charset=UTF-8");
//		MemberService mservice = new MemberService();
//		String id = request.getParameter("id");
//		String passwd = request.getParameter("passwd");
//		System.out.println("id: " + id);
//		System.out.println("passwd: " + passwd);
//		
//		PrintWriter out = response.getWriter();
//		
//		JSONObject job = new JSONObject();
//		
//		// 입력받은 사용자의 ID와 비밀번호를 인자로 하여 Service의 loginMember() 호출
//		Member m = mservice.loginMember(id, passwd);
//		if (m != null) { 			// 로그인 성공
//			System.out.println("로그인성공");
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//			
////			job.put("result", "ok");
////			job.put("name", m.getName());
//			
//			
//// simple-json 바로 변환 불가 
////{"result":"ok","memberInfo":Member [id=user11, passwd=pass11, name=사용자1, email=user11@test.or.kr],"name":"사용자1"}
////{"result":"ok","memberInfo": {id:"user11", passwd:"pass11", name:"사용자1", email:"user11@test.or.kr"},"name":"사용자1"}
////			job.put("memberInfo", m);
////			job.put("id", m.getId());
////			job.put("passwd", m.getPasswd());
//			
//		} else {			// 로그인 실패
//			System.out.println("로그인실패");
//			job.put("result", "fail");
//		}
//		System.out.println("job: " + job);
//		System.out.println("job.toJSONString(): " + job.toJSONString());
//		out.println(job.toJSONString());
////		out.println("aaa");
//		out.flush();
//		out.close();
//		//소용없음. ajax 위치로 돌아감. //request.getRequestDispatcher("aaa.jsp").forward(request, response);
//	}

}
