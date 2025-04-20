package pack.ptcg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cnst.Constant;
import dao.PtcgDao;

/**
 * Servlet implementation class DeleteExpansion
 */
@WebServlet("/DeleteExpansion")
public class DeleteExpansion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteExpansion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//受け渡しデータの文字コード設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		//画面から受け取る
		String deleteExpansionCode = request.getParameter("target_code");

		try {
			//削除対象がデータベース上にあるか確認
			if (!new PtcgDao().expansionCodeExists(deleteExpansionCode)) {
				request.setAttribute("errorMessage", "削除対象が存在しませんでした");
			} else {

				// データベースからデータを削除するメソッドを呼び出す。
				int count = new PtcgDao().deleteExpansion(deleteExpansionCode);

				//削除件数をメッセージとしてリクエストスコープに渡す
				request.setAttribute("message", count + "件のデータを削除しました");
			}

			//編集画面へ遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("DspPtcgEditExpansion");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			//想定外のエラーが発生したとき 
			e.printStackTrace();
			//異常終了
			request.setAttribute("message", Constant.MSG_ERR_SYSTEM + e.getMessage());
			//遷移先は共通エラーページ
			ServletContext context = this.getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher(Constant.SYSTEM_ERR);
			dispatcher.forward(request, response);
		} 
	}
}
