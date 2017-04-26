package cmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bdao;
import dto.Bdto;

public class BReplyViewCommand implements BCommand{
	
	public void execute(HttpServletRequest request, HttpServletResponse response){
		String bId = request.getParameter("bId");
		Bdao dao = new Bdao();
		Bdto dto = dao.replyView(bId);
		
		request.setAttribute("replyView", dto);
	}
}
