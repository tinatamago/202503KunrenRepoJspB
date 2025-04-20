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
 * <P>タイトル : 検索クラス</P>
 * <P>説明 : カード検索をするクラスです。</P>
 */
@WebServlet("/SearchCard")
public class SearchCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 検索処理を実行します。
	 *
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//受け渡しデータの文字コードをUTF-8に統一してセット
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		//フォーム入力から文字列として受け取る
		String strCardId = request.getParameter("card_id");
		String strCardName = request.getParameter("card_name");
		String strMinPrice = request.getParameter("min_price");
		String strMaxPrice = request.getParameter("max_price");
		String strCategoryId = request.getParameter("category_id");
		String expansionCode = request.getParameter("card_expansion");

		//呼び出し元に渡す値（データ）をセット
		request.setAttribute("cardId", strCardId);
		request.setAttribute("cardName", strCardName);
		request.setAttribute("celectedCategoryId", strCategoryId);
		request.setAttribute("celectedExpansionCode", expansionCode);
		request.setAttribute("minPrice", strMinPrice);
		request.setAttribute("maxPrice", strMaxPrice);

		try {
			// ptcg_cardsテーブルを取得してリクエストスコープにセット
			List<PtcgDto> cardList = new PtcgDao().search(
					strCardId,
					strCardName,
					strMinPrice,
					strMaxPrice,
					strCategoryId,
					expansionCode);

			request.setAttribute("cards", cardList);

			//TODO セッションスコープに保持できていれば、以下不要（変更され得ないため）
			// ptcg_categoriesテーブルを取得してリクエストスコープにセット
			List<PtcgDto> categoryList = new PtcgDao().getCategories();
			request.setAttribute("categories", categoryList);

			//TODO セッションスコープに保持できていれば、以下クエリ実行は不要（変更され得ないため）
			// ptcg_expansionsテーブルを取得してリクエストスコープにセット
			List<PtcgDto> expansionList = new PtcgDao().getExpansions();
			request.setAttribute("expansions", expansionList);
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
