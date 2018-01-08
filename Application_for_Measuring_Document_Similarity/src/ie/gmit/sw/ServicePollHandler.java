package ie.gmit.sw;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.gmit.sw.similarity.ResultInfo;
import ie.gmit.sw.similarity.SimilarityDBResult;

public class ServicePollHandler extends HttpServlet {
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html"); 
		PrintWriter out = resp.getWriter(); 
		
		String title = req.getParameter("txtTitle");
		String taskNumber = req.getParameter("frmTaskNumber");
		int counter = 1;
		if (req.getParameter("counter") != null){
			counter = Integer.parseInt(req.getParameter("counter"));
			counter++;
		}
		
		List<ResultInfo> results = SimilarityDBResult.fetchResult(taskNumber);

		out.print("<html><head><title>A JEE Application for Measuring Document Similarity</title>");		
		out.print("</head>");		
		out.print("<body>");
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<H3>Document Title: " + title + "</H3>");
		out.print("<b><font color=\"ff0000\">A total of " + counter + " polls have been made for this request.</font></b> ");
//		out.print("Place the final response here... a nice table (or graphic!) of the document similarity...");
		if (results == null) {
			out.print("Still waiting for backend processing...");
		} else {
			out.print("<table>");
			out.print("<tr><td>Title</td><td>Similarity</td></tr>");
			for (ResultInfo resultInfo : results) {
				String similarity;
				double d = resultInfo.getSimilarity();
				d = d * 100;
				DecimalFormat decimalFormat = new DecimalFormat("0.##");
				similarity = decimalFormat.format(d);
				out.print("<tr><td>" + resultInfo.getTitle() + "</td><td>" + similarity + "%</td></tr>");
			}
			out.print("</table>");
		}
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"txtTitle\" type=\"hidden\" value=\"" + title + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("<input name=\"counter\" type=\"hidden\" value=\"" + counter + "\">");
		out.print("</form>");								
		out.print("</body>");	
		out.print("</html>");	
		
		if (results == null) {
			out.print("<script>");
			out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 5000);"); //Refresh every 5 seconds
			out.print("</script>");
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}