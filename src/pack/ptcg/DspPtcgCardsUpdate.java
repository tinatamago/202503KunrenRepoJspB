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
 * <P>説明 : 更新画面を表示するクラスです。</P>
 * コピペにつき、必要に応じて共通化も検討
 */
@WebServlet("/DspPtcgCardsUpdate")
public class DspPtcgCardsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DspPtcgCardsUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 更新画面へ遷移するための初期処理を実行します。
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//受け取るデータの文字コードをエンコードする
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//画面から対象のカードIDを受け取り、リクエストスコープにセット
		String updateCardId = request.getParameter("target_id");
		request.setAttribute("cardId", updateCardId);

		try {
			// カードIDをもとにptcg_cardテーブルから情報取得 TODO 本当はDBアクセス減らすためにセッションから取った方がいい
			PtcgDto celectedCard = new PtcgDao().selectCardById(updateCardId);
			//不足情報をリクエストスコープへセット
			request.setAttribute("cardName", celectedCard.getCardName());
			request.setAttribute("celectedCategoryId", celectedCard.getCategoryId());
			request.setAttribute("celectedExpansionCode", celectedCard.getExpansionCode());
			request.setAttribute("price", celectedCard.getPrice());
			
			// ptcg_categoriesテーブルを取得してリクエストスコープにセット TODO 本当はDBアクセス減らすためにセッションから取った方がいい
			List<PtcgDto> categoryList = new PtcgDao().getCategories();
			request.setAttribute("categories", categoryList);

			// ptcg_expansionsテーブルを取得してリクエストスコープにセット TODO 本当はDBアクセス減らすためにセッションから取った方がいい
			List<PtcgDto> expansionList = new PtcgDao().getExpansions();
			request.setAttribute("expansions", expansionList);

			response.setCharacterEncoding("UTF-8");

			//ポケカ更新画面 にページ遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ptcg/PtcgCardsUpdate.jsp");
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
