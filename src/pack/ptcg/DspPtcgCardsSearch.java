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
 * <P>タイトル : ディスパッチクラス</P>
 * <P>説明 : 検索画面の初期表示をするクラスです。</P>
 * TODO 全体の課題メモ 
 * TODO SQLインジェクション対策 done
 * TODO エラー関係、例外処理
 * TODO 命名規則の遵守,統一 
 */
@WebServlet("/DspPtcgCardsSearch")
public class DspPtcgCardsSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DspPtcgCardsSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 検索画面の初期処理を実行します。
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッション設定
		//HttpSession session = request.getSession();
		//session.setAttribute("userId", strUserId);

		//ServletContextに値を保管する
		//ServletContext context = this.getServletContext();

		try {
			// ptcg_cardsテーブルを取得してリクエストスコープにセット
			List<PtcgDto> cardList = new PtcgDao().getAllCards();
			request.setAttribute("cards", cardList);

			// ptcg_categoriesテーブルを取得してリクエストスコープにセット
			List<PtcgDto> categoryList = new PtcgDao().getCategories();
			request.setAttribute("categories", categoryList);

			// ptcg_expansionsテーブルを取得してリクエストスコープにセット
			List<PtcgDto> expansionList = new PtcgDao().getExpansions();
			request.setAttribute("expansions", expansionList);

			if (cardList == null | categoryList == null | expansionList == null) {
				request.setAttribute("errorMessage", "いずれかのデータベースの読み込みに失敗しました");
			}

			//エンコード設定
			response.setCharacterEncoding("UTF-8");
			//ポケカ検索画面 にページ遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ptcg/PtcgCardsSearch.jsp");
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
