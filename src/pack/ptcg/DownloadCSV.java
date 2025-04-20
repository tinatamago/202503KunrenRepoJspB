package pack.ptcg;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class DownloadCSV
 */
@WebServlet("/DownloadCSV")
public class DownloadCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadCSV() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 参考 https://w.atwiki.jp/chapati4it/pages/143.html
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字コード設定 csvをExcelで開くと初期でShift-JISのため、UTF-8ではなくShift-JIS
		response.setContentType("text/html; charset=Shift-JIS");
		// ファイル名設定（ファイル名を設定しないと、htmlとして画面に表示されてしまいます
		response.setHeader("Content-Disposition", "attachment; filename=\"CardList.csv\"");
		try {
			List<PtcgDto> cardList = new PtcgDao().getAllCards();

			// CSVデータ作成　TODO そもそもここで作るべきなのか検討が必要
			StringBuffer sb = new StringBuffer();
			for (PtcgDto card : cardList) {
				//TODO カンマと改行を適宜挟み CSVを作る
				//1行分のデータをカンマでつなぐ
				String row = String.join("\",\"",
						String.valueOf(card.getCardId()),
						card.getCardName(),
						card.getCategoryName(),
						card.getExpansionName(),
						card.getImageFileName(),
						String.valueOf(card.getReleaseDate()),
						String.valueOf(card.getRegistrationTime()),
						String.valueOf(card.getUpdateTime()),
						String.valueOf(card.getPrice()));
				row = "\"" + row + "\"\n";
				//"null"(文字列) を nullにもどす（ゴリ押し実装 nullという名前のカード名などがあったときにnullになる ←一旦許容） 
				row = row.replace("\"null\"", "");
				sb.append(row);
			}

			// レスポンスにCSV出力
			PrintWriter w = response.getWriter();
			w.print(sb.toString());
			w.flush();

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
