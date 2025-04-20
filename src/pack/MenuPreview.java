/**
 * <P>タイトル : 〇〇システム　ログイン画面</P>
 * <P>説明 : ログイン認証を行う。</P>
 * @author k_takahashi
 * @version 1.0 2020.11.18
 */
package pack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cnst.Constant;

/**
 * <P>タイトル : メニュークラス</P>
 * <P>説明 : メニュー画面の表示を行うクラスです。</P>
 */
@WebServlet("/MenuPreview")
public class MenuPreview extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuPreview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 初期処理を実行します。
	 *
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//セッション設定
		HttpSession session = request.getSession();
		//session.setAttribute("userId", strUserId);

		//ServletContextに値を保管する
		ServletContext context = this.getServletContext();

		//RequestDispatcherオブジェクトは、画面の遷移先を定義する
        RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.MENU);

        //遷移先に転送
        dispatcher.forward(request, response);

	}
}
