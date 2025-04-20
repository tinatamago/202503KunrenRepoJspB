package pack.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cnst.Constant;
/**
 * Servlet implementation class SampleClass
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ログアウト
		request.logout();
        
        //ServletContextに値を保管する
		ServletContext context = this.getServletContext();
		
		//RequestDispatcherオブジェクトは、画面の遷移先を定義する
        RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN);
        
        //遷移先に転送
        dispatcher.forward(request, response);
	}


}