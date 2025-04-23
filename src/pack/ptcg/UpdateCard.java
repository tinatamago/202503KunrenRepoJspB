package pack.ptcg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cnst.Constant;
import dao.PtcgDao;

/**
 * <P>タイトル : カード更新クラス</P>
 * <P>説明 : カード情報の更新を担当するクラス</P>
 */
@WebServlet("/UpdateCard")
@MultipartConfig
public class UpdateCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 追加処理を実行します。
	 *
	 * @param  request  HttpServletRequestオブジェクト
	 * @param  response HttpServletResponseオブジェクト
	 * @throws Exception 
	 * TODO エラーハンドリングをする
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//受け渡しデータの文字コード設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		//画面から受け取る name属性で受け取る（"jsp内で定義したname"）
		String updateCardId = request.getParameter("card_id");
		String updateCardName = request.getParameter("card_name");
		String strCategoryId = request.getParameter("category_id");

		//以下コピペ--参考 https://joytas.net/programming/jsp_servlet/fileupload ---------
		//name属性がimageのファイルをPartオブジェクトとして取得
		Part part = request.getPart("image");
		//ファイル名を取得
		//String filename=part.getSubmittedFileName();//ie対応が不要な場合
		String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		//System.out.println("filename : " + filename);

		//アップロードするフォルダ
		String path = getServletContext().getRealPath("/upload/ptcg");
		//絶対パスで指定
		//String path = "C:\\java_2022\\pleiades\\workspace\\202503KunrenRepoJspB\\WebContent\\upload\\ptcg\\";
		//実際にファイルが保存されるパス確認
		//System.out.println(path);

		//書き込み
		//part.write(path + File.separator + filename);//画像設定しなかった場合にIOException
		//例外で処理するのか、ifで切り分けるか TODO 実装の再検討
		if (!filename.isEmpty()) {
			part.write(path + File.separator + filename);
		}

		request.setAttribute("filename", filename);
		//------------------------------------------

		String expansionCode = request.getParameter("card_expansion");
		String strPrice = request.getParameter("card_price");

		try {
			//更新対象がデータベース上にあるか確認
			if (!new PtcgDao().cardIdExists(updateCardId)) {
				request.setAttribute("errorMessage", "更新対象が存在しませんでした");
			} else {
				// データベースからデータを更新するメソッドを呼び出す。
				int count = new PtcgDao().updateCard(
						updateCardId,
						updateCardName,
						strCategoryId,
						filename,
						expansionCode,
						strPrice);

				//追加件数をメッセージとしてリクエストスコープに渡す
				request.setAttribute("message", count + "件のデータを更新しました");

			}
			//ポケカ検索画面 にページ遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("DspPtcgCardsSearch");
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
