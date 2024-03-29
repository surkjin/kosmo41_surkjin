package com.study.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.MembersDAO;
import com.study.jsp.MembersDTO;

public class BModifyMemberCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("UTF-8");
		
			response.setContentType("text/html; charset=UTF-8");
			
			MembersDTO dto = new MembersDTO();
			String id = (String)request.getSession().getAttribute("id");
			
			dto.setId(id);
			String quit = request.getParameter("quit");
			dto.setPw(request.getParameter("pw"));
			dto.seteMail(request.getParameter("eMail"));
			dto.setAddress(request.getParameter("address"));
			
			MembersDAO dao = MembersDAO.getInstance();
			
			int ri = 1;
			if(quit.equals("1")) 
				ri = dao.updateMember(dto);
			else 
				ri = dao.quitMember(dto);
			PrintWriter wr = response.getWriter();
			
			if(ri == 1){	
				wr.println("<html><head></head><body>");
				wr.println("<script language='javascript'>");
				wr.println("alert('정보가 수정되었습니다.');");
				wr.println("document.location.href=\'client.jsp\';");
				wr.println("</script>");
				wr.println("</body></html>");
			} else if(ri == 0){
				wr.println("<html><head></head><body>");
				wr.println("<script language='javascript'>");
				wr.println("alert('정보수정에 실패했습니다.');");
				wr.println("history.go(-1);");
				wr.println("</script>");
				wr.println("</body></html>");
			}else if(ri == -1){
				wr.println("<html><head></head><body>");
				wr.println("<script language='javascript'>");
				wr.println("alert('회원탈퇴에 실패했습니다.');");
				wr.println("history.go(-1);");
				wr.println("</script>");
				wr.println("</body></html>");
			}else if(ri == 2){
				wr.println("<html><head></head><body>");
				wr.println("<script language='javascript'>");
				wr.println("alert('회원탈퇴에 성공했습니다.');");
				wr.println("document.location.href=\'login.jsp\';");
				wr.println("</script>");
				wr.println("</body></html>");
			}
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
