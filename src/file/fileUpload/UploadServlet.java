package file.fileUpload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8"); 
		 response.setContentType("text/html; charset=UTF-8"); 
		 PrintWriter out = response.getWriter(); 
		 // 파일 저장 경로 (web 경로 밑에 해당 폴더를 생성해 주어야 한다) 
		 String fileSavePath = "upload";
		 
		// 파일 크기 10M 제한 
		 int uploadSizeLimit = 10 * 1024 * 1024; 
		 String encType = "UTF-8";
		 
		//enctype="multipart/form-data" 로 전송되었는지 확인 
		 if(!ServletFileUpload.isMultipartContent(request)) 
		 response.sendRedirect("view/error/Error.jsp");
		 
		 ServletContext context = getServletContext();
		 String uploadPath = context.getRealPath(fileSavePath); 
		 System.out.println(uploadPath);
		 
		 MultipartRequest multi = new MultipartRequest(request, // request 객체 
				 uploadPath, // 서버 상 업로드 될 디렉토리 
				 uploadSizeLimit, // 업로드 파일 크기 제한 
				 encType, // 인코딩 방법 
				 new DefaultFileRenamePolicy() // 동일 이름 존재 시 새로운 이름 부여 방식 
				 );
		 
		// 업로드 된 파일 이름 얻어오기 
		 String file = multi.getFilesystemName("uploadFile");
		 
		 if (file == null) { 
			 // 업로드 실패 시 
			 System.out.println("업로드 실패"); 
			 } else { 
			 // TODO : 보통은 DB에 저장하러 감.
			 // 업로드 성공 시 
			 out.println("<br> 글쓴이 : " + multi.getParameter("name")); 
			 out.println("<br> 제목 : " + multi.getParameter("title")); 
			 out.println("<br> 첨부파일명 : " + file); 
			 out.println("<br> 업로드 성공!!!"); 
			 }
		 
		 
	}
	
	

}
