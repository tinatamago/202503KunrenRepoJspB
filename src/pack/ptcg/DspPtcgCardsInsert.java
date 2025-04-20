package pack.ptcg;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cnst.Constant;
import dao.PtcgDao;
import dto.PtcgDto;

/**
 * TODO 暫定対応
 * <P>タイトル : ディスパッチクラス</P>
 * <P>説明 : 登録画面を表示するクラスです。</P>
 */
@WebServlet("/DspPtcgCardsInsert")
public class DspPtcgCardsInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DspPtcgCardsInsert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 登録画面遷移時の初期処理を実行します。
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// ptcg_categoriesテーブルを取得してリクエストスコープにセット
			List<PtcgDto> categoryList = new PtcgDao().getCategories();
			request.setAttribute("categories", categoryList);
			
			// ptcg_expansionsテーブルを取得してリクエストスコープにセット
			List<PtcgDto> expansionList = new PtcgDao().getExpansions();
			request.setAttribute("expansions", expansionList);
			
			response.setCharacterEncoding("UTF-8");

			//ポケカ登録画面 にページ遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ptcg/PtcgCardsInsert.jsp");
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
