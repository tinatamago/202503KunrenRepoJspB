package pack.ptcg;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cm.ComServlet;
import cnst.Constant;
import dao.PtcgDao;
import dto.PtcgDto;

/**
 * <P>タイトル : エキスパンション編集クラス</P>
 * <P>説明 : エキスパンション情報の追加更新を担当するクラス</P>
 */
@WebServlet("/EditExpansion")
public class EditExpansion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditExpansion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 追加処理を実行します。
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//共通クラスを使うためインスタンス生成
		ComServlet com = new ComServlet();
		//
		Date releaseDate = null;

		//受け取るデータの文字コードをエンコードする
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		//画面から受け取る name属性で受け取る
		String expansionCode = request.getParameter("expansion_code");
		String expansionName = request.getParameter("expansion_name");
		String strReleaseDate = request.getParameter("release_date");

		//日付の型変換
		if (!com.isNullOrEmpty(strReleaseDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				//String型からDate型へパース
				releaseDate = new Date(sdf.parse(strReleaseDate).getTime());
			} catch (Exception e) {
				// パースが正常にできなかった場合
				e.printStackTrace();
				request.setAttribute("errorMessage", "日付入力に誤りがあります");
			}
		}

		try {
			//TODO 理解が浅い Daoは明確にインスタンス生成して使うのが正しいっぽい
			// データベースからデータを追加するメソッドを呼び出す。
			int count = new PtcgDao().editExpansion(expansionCode, expansionName, releaseDate);

			// ptcg_expansionsテーブルを取得してリクエストスコープにセット
			List<PtcgDto> expansionList = new PtcgDao().getExpansions();
			request.setAttribute("expansions", expansionList);

			//追加件数をメッセージとしてリクエストスコープに渡す
			request.setAttribute("message", count + "件のデータを編集しました");
			response.setCharacterEncoding("UTF-8");

			//登録画面へ再度遷移する
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
