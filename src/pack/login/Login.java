/**
 * <P>タイトル : 照会システム　ログイン画面</P>
 * <P>説明 : ログイン認証を行う。</P>
 * @author k_takahashi
 * @version 1.0 2020.11.18
 */
package pack.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cnst.Constant;
import dao.LoginDao;
import dto.LoginDto;

/**
 * <P>タイトル : ログインクラス</P>
 * <P>説明 : ログイン認証を行うクラスです。</P>
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * 初期処理を実行します。
	 *
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	//LoginDaoを使うためにインスタンス生成
    	LoginDao loginDao = new LoginDao();

		try {

			//インスタンスからDaoを使用
			//JDBCドライバを読み込む
			int ret = loginDao.readJdbcDriver(request);

			//単体テストコード
			//ret = -1;

			if(ret == Constant.RET_OK) {

		        //ServletContextに値を保管する
				ServletContext context = this.getServletContext();

				//RequestDispatcherオブジェクトは、画面の遷移先を定義する
		        RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN);

		        //遷移先に転送
		        dispatcher.forward(request, response);

			}else {

				//読込処理：異常終了
		        request.setAttribute("message", Constant.MSG_SYSTEM_ERR_JDBC_DRIVER);
		        request.setAttribute("message2", Constant.MSG_ERR_SYSTEM);

		        //ServletContextに値を保管する
				ServletContext context = this.getServletContext();

				//RequestDispatcherオブジェクトは、画面の遷移先を定義する
		        RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN_ERR);

		        //遷移先に転送
		        dispatcher.forward(request, response);

			}

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			//異常終了
	        request.setAttribute("message", Constant.MSG_ERR_SYSTEM);

	        //ServletContextに値を保管する
			ServletContext context = this.getServletContext();

			//RequestDispatcherオブジェクトは、画面の遷移先を定義する
	        RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN_ERR);

	        //遷移先に転送
	        dispatcher.forward(request, response);

		}

	}

	/**
	 * 検索処理を実行します。
	 *
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//受け取るデータの文字コードをエンコードする
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

		//画面から受け取る
		String strUserId = request.getParameter("tx_user_id");
		String strUserPass = request.getParameter("tx_user_pass");

		//DBからデータ取得
		//int cnt = getSeachDB(request, strUserId, strUserPass);

		//検索Daoを使うためにインスタンス生成
		LoginDao loginDao = new LoginDao();

		//インスタンスからDaoを使用
		List<LoginDto> lst;

		try {

			lst = loginDao.getDBLoginChk(request, strUserId, strUserPass);

			if(lst == null || lst.size() == 0) {

				//認証不可

				//画面にメッセージを返却する
		        request.setAttribute("message", Constant.MSG_ERR_LOGIN01);

		        //ServletContextに値を保管する
				ServletContext context = this.getServletContext();

				//RequestDispatcherオブジェクトは、画面の遷移先を定義する
		        RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN);

		        //遷移先に転送
		        dispatcher.forward(request, response);

			}else {

				//認証成功
				//request.setAttribute("message", "認証ＯＫ");

				//セッション設定
				HttpSession session = request.getSession();
				session.setAttribute("userId", strUserId);

				//ServletContextに値を保管する
				ServletContext context = this.getServletContext();

				//RequestDispatcherオブジェクトは、画面の遷移先を定義する
		        RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.MENU);

		        //遷移先に転送
		        dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			//異常終了
	        request.setAttribute("message", Constant.MSG_ERR_SYSTEM_LOGIN);

	        //ServletContextに値を保管する
			ServletContext context = this.getServletContext();

			//RequestDispatcherオブジェクトは、画面の遷移先を定義する
	        RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.LOGIN_ERR);

	        //遷移先に転送
	        dispatcher.forward(request, response);

		}

	}
}
