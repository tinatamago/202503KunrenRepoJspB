package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cm.ComServlet;
import cnst.Constant;
import dto.PtcgDto;

/**
 * <P>タイトル : ポケモントレーディングカードゲーム（PTCG）のDAOクラス</P>
 * <P>説明 : PTCG検索システムのDB接続を担うクラスです。</P>
 * @author kensyu
 * @version 1.0 2025/02/20
 */
public class PtcgDao {

	/**
	 * カード情報を全件取得をするメソッド
	 * @note 引数がないのでSQLインジェクションは考慮しない
	 * @return cardsとcategoriesとexpantionsを組み合わせた表({@code List<PtcgDto>}型)
	 * @throws SQLException
	 */
	public List<PtcgDto> getAllCards() throws SQLException {

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION, //URL
				Constant.JDBC_USER, //ユーザー名
				Constant.JDBC_PASS //パスワード
		);

		//Statementオブジェクトの生成
		Statement stmt = conn.createStatement();

		//Listオブジェクトの生成
		List<PtcgDto> list = new ArrayList<PtcgDto>();

		//SQL文の生成
		// TODO ベタ張りのため、のちほど成形する
		String sql = "SELECT "
				+ " cards.card_id "
				+ ",cards.card_name "
				+ ",categories.category_name "
				+ ",expantions.expansion_name "
				+ ",cards.image_file_name "
				+ ",expantions.release_date "
				+ ",cards.registration_time "
				+ ",cards.update_time "
				+ ",cards.price "
				+ "FROM ptcg_cards AS cards "
				+ "    LEFT OUTER JOIN ptcg_categories AS categories "
				+ "        ON cards.category_id = categories.category_id "
				+ "    LEFT OUTER JOIN ptcg_expansions AS expantions "
				+ "        ON cards.expansion_code = expantions.expansion_code "
				+ "ORDER BY cards.card_id;";

		try {

			//SQLの実行
			ResultSet rs = stmt.executeQuery(sql);

			//listに追加
			while (rs.next()) {
				list.add(new PtcgDto(
						rs.getInt("card_id"),
						rs.getString("card_name"),
						rs.getString("category_name"),
						rs.getString("expansion_name"),
						rs.getString("image_file_name"),
						rs.getDate("release_date"),
						rs.getTimestamp("registration_time"),
						rs.getTimestamp("update_time"),
						rs.getInt("price")));
			}

			//JDBCのリソース解放
			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			//異常終了
			return null;

		} finally {
			//ステートメントが残っていれば閉じる
			if (stmt != null) {
				stmt.close();
			}
			//コネクションを繋いでいればクローズする
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}

	/**
	 * カード情報を検索するメソッド(pstmt利用)
	 * @param strMaxPrice 価格の最大値 
	 * @param strMinPrice 価格の最小値
	 * @param strCardName カード名（前後ワイルドカードにて検索）
	 * @param strCardId カードID（文字列として扱い、前後ワイルドカードにて検索）
	 * @param expansionCode エキスパンションコード検索条件（どのパック、デッキ、頒布物に収録されているか検索）
	 * @param strCategoryId カテゴリ検索条件
	 * @return 検索条件に合致したカードのリスト({@code List<PtcgDto>}型)
	 * @throws SQLException 存在しないまたはclose済みのrsをcloseしようとしたとき
	 */
	public List<PtcgDto> search(String strCardId, String strCardName, String strMinPrice,
			String strMaxPrice, String strCategoryId, String expansionCode) throws SQLException {

		//List<PtcgDto>オブジェクトの生成
		List<PtcgDto> list = new ArrayList<PtcgDto>();
		//共通オブジェクトをインスタンス化
		ComServlet com = new ComServlet();
		//オブジェクトの定義
		ResultSet rs = null;

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION,
				Constant.JDBC_USER,
				Constant.JDBC_PASS)) {

			//SQL文の生成(StringBuilderを使用する)
			StringBuilder sqlSb = new StringBuilder("""
					SELECT
					 cards.card_id
					,cards.card_name
					,categories.category_name
					,expantions.expansion_name
					,cards.image_file_name
					,expantions.release_date
					,cards.registration_time
					,cards.update_time
					,cards.price
					 FROM ptcg_cards AS cards
					    LEFT OUTER JOIN ptcg_categories AS categories
					        ON cards.category_id = categories.category_id
					    LEFT OUTER JOIN ptcg_expansions AS expantions
					        ON cards.expansion_code = expantions.expansion_code
					 WHERE 1=1
					""");

			//検索条件に入力があれば追加
			if (!com.isNullOrEmpty(strCardName)) {
				sqlSb.append(" AND cards.card_name LIKE ? ");
			}
			if (!com.isNullOrEmpty(strCardId)) {
				sqlSb.append(" AND CAST(cards.card_id AS text) LIKE ? ");
			}
			if (!com.isNullOrEmpty(strMinPrice)) {
				sqlSb.append(" AND coalesce(cards.price, 0) >= ? ");
			}
			if (!com.isNullOrEmpty(strMaxPrice)) {
				sqlSb.append(" AND coalesce(cards.price, 0) <= ? ");
			}
			if (!com.isNullOrEmpty(strCategoryId)) {
				sqlSb.append(" AND cards.category_id = ? ");
			}
			if (!com.isNullOrEmpty(expansionCode)) {
				sqlSb.append(" AND cards.expansion_code = ? ");
			}
			//ソート(ORDER BY句)の設定
			sqlSb.append(" ORDER BY cards.card_id;");

			//SQL文をStringへ変換
			String sql = sqlSb.toString();

			//PreparedStatementオブジェクトにsql文を設定
			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				// sql文のパラメータ設定
				int index = 0;
				if (!com.isNullOrEmpty(strCardName)) {
					pstmt.setString(++index, "%" + strCardName + "%");
				}
				if (!com.isNullOrEmpty(strCardId)) {
					pstmt.setString(++index, "%" + strCardId + "%");
				}
				if (!com.isNullOrEmpty(strMinPrice)) {
					pstmt.setInt(++index, Integer.parseInt(strMinPrice));
				}
				if (!com.isNullOrEmpty(strMaxPrice)) {
					pstmt.setInt(++index, Integer.parseInt(strMaxPrice));
				}
				if (!com.isNullOrEmpty(strCategoryId)) {
					pstmt.setInt(++index, Integer.parseInt(strCategoryId));
				}
				if (!com.isNullOrEmpty(expansionCode)) {
					pstmt.setString(++index, expansionCode);
				}
				// SQL文の実行
				rs = pstmt.executeQuery();

				// 検索結果をリストに格納
				while (rs.next()) {
					list.add(new PtcgDto(
							rs.getInt("card_id"),
							rs.getString("card_name"),
							rs.getString("category_name"),
							rs.getString("expansion_name"),
							rs.getString("image_file_name"),
							rs.getDate("release_date"),
							rs.getTimestamp("registration_time"),
							rs.getTimestamp("update_time"),
							rs.getInt("price")));
				}

			} catch (SQLException e) {
				//クエリ実行時のエラーハンドリング 一つ上に投げる
				throw e;
			}

		} catch (Exception e) {
			//接続時のExceptionをcatch（タイムアウトもここでcatch）
			//クエリ実行時Exceptionもここでcatch
			e.printStackTrace();
			//異常終了としてnullを返す
			return null;
		} finally {
			//JDBCリソース解放(pstmt閉じる際に自動closeされるはずだが念のため)
			if (rs != null) {
				rs.close();
			}
		}
		return list;
	}

	/**
	 * カテゴリーの名称を全件取得をするメソッド
	 * @return categoriesテーブル({@code List<PtcgDto>}型)
	 * @throws SQLException 
	 */
	public List<PtcgDto> getCategories() throws SQLException {

		//SQL文の生成
		String sql = "SELECT category_id , category_name FROM ptcg_categories ORDER BY category_id";
		//Listオブジェクトの生成
		List<PtcgDto> list = new ArrayList<PtcgDto>();

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続(try-with-resources) conn stmt rs は自動的にcloseされる
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION,
				Constant.JDBC_USER,
				Constant.JDBC_PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			//rsを PtcgDto型で listに格納
			while (rs.next()) {
				list.add(new PtcgDto(
						rs.getInt("category_id"),
						rs.getString("category_name")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			//異常終了
			return null;
		}
		return list;
	}

	/**
	 * エキスパンションの名称を全件取得をするメソッド
	 * @return categoriesテーブル({@code List<PtcgDto>}型)
	 * @throws SQLException
	 */
	public List<PtcgDto> getExpansions() throws SQLException {

		//SQL文の生成
		String sql = "SELECT expansion_code"
				+ " , expansion_name"
				+ " , release_date"
				+ " FROM ptcg_expansions"
				+ " ORDER BY release_date";
		//Listオブジェクトの生成
		List<PtcgDto> list = new ArrayList<PtcgDto>();

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続(try-with-resources) conn stmt rs は自動的にcloseされる
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION,
				Constant.JDBC_USER,
				Constant.JDBC_PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			//rsを PtcgDto型で listに格納
			while (rs.next()) {
				list.add(new PtcgDto(
						rs.getString("expansion_code"),
						rs.getString("expansion_name"),
						rs.getDate("release_date")));
			}

		} catch (Exception e) {
			e.printStackTrace();
			//異常終了
			return null;
		}
		return list;
	}

	/**
	 * カード情報をIdによって取得するメソッド
	 * @param cardId 取得したいカードのID
	 * @return 取得したカード情報 (PtcgDto型)
	 * @throws SQLException
	 */
	public PtcgDto selectCardById(String cardId) throws SQLException {

		//オブジェクトの定義
		PtcgDto card = null;
		ResultSet rs = null;
		//sql文の生成
		String sql = "SELECT * FROM ptcg_cards WHERE card_id = ?";
		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION,
				Constant.JDBC_USER,
				Constant.JDBC_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			//パラメータ設定
			pstmt.setInt(1, Integer.parseInt(cardId));
			// SQL文の実行
			rs = pstmt.executeQuery();

			// 検索結果を格納
			//はじめはカラム名が入るので一度カーソルをnextへ移動する
			rs.next();
			//一つ分のカード情報を取得
			card = new PtcgDto(
					rs.getInt("card_id"),
					rs.getString("card_name"),
					rs.getInt("category_id"),
					rs.getString("expansion_code"),
					rs.getInt("price"));

			//次のデータがないか確認
			if (rs.next()) {
				//一意制約を満たしていれば、ここには実行されないはず
				throw new SQLException("card_idが重複しています");
			}

		} catch (Exception e) {
			e.printStackTrace();
			//異常終了
			return null;

		} finally {
			//リソース解放(pstmtがcloseするときにrsもcloseされるはずだが念のため)
			if (rs != null) {
				rs.close();
			}
		}
		return card;
	}

	/**
	 * そのIDが存在するかを確認するメソッド
	 * @param cardId 存在確認するカードのID
	 * @return 存在すればtrue しなければfalse
	 * @throws SQLException データベースへのコネクションが確立できなかった時 クエリ実行に失敗した時
	 */
	public boolean cardIdExists(String cardId) throws SQLException {

		//変数定義
		boolean exists = false;
		//オブジェクトの定義
		ResultSet rs = null;
		//sql文の生成
		String sql = "SELECT EXISTS (SELECT 1 FROM ptcg_cards WHERE card_id = ? )";
		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION,
				Constant.JDBC_USER,
				Constant.JDBC_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			//パラメータ設定
			pstmt.setInt(1, Integer.parseInt(cardId));
			// SQL文の実行
			rs = pstmt.executeQuery();

			// 結果を格納
			//はじめはカラム名が入るので一度カーソルをnextへ移動する
			rs.next();
			//存在状況を取得 1つ目の要素がそのままbool値で得られる
			exists = rs.getBoolean(1);

		} finally {
			//リソース解放(pstmtがcloseするときにrsもcloseされるはずだが念のため)
			if (rs != null) {
				rs.close();
			}
		}
		return exists;
	}

	/**
	 * そのエキスパンションコードが存在するかを確認するメソッド
	 * @param expansionCode
	 * @return 存在すればtrue しなければfalse
	 * @throws SQLException
	 */
	public boolean expansionCodeExists(String expansionCode) throws SQLException {
		//変数定義
		boolean exists = false;
		//オブジェクトの定義
		ResultSet rs = null;
		//sql文の生成
		String sql = "SELECT EXISTS (SELECT 1 FROM ptcg_expansions WHERE expansion_code = ? )";
		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION,
				Constant.JDBC_USER,
				Constant.JDBC_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			//パラメータ設定
			pstmt.setString(1, expansionCode);
			// SQL文の実行
			rs = pstmt.executeQuery();

			// 結果を格納
			//はじめはカラム名が入るので一度カーソルをnextへ移動する
			rs.next();
			//存在状況を取得 1つ目の要素がそのままbool値で得られる
			exists = rs.getBoolean(1);

		} finally {
			//リソース解放(pstmtがcloseするときにrsもcloseされるはずだが念のため)
			if (rs != null) {
				rs.close();
			}
		}
		return exists;
	}

	/**
	 * ptcg_cardsテーブルのカード情報を削除するメソッド
	 * @param deleteCardId 削除するカードのID 
	 * @return 削除された件数
	 * @throws SQLException
	 */
	public int deleteCard(String deleteCardId) throws SQLException {

		//削除件数
		int delCount = 0;
		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION, //URL
				Constant.JDBC_USER, //ユーザー名
				Constant.JDBC_PASS //パスワード
		);) {
			//SQL文の生成
			String sql = "DELETE FROM ptcg_cards WHERE card_id = ?";
			//pstmt生成
			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				//パラメータ設定
				pstmt.setInt(1, Integer.parseInt(deleteCardId));
				//sql文の実行
				delCount = pstmt.executeUpdate();
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return delCount;
	}

	/**
	 * ptcg_cardsテーブルへカード情報を追加するメソッド
	 * @param request
	 * @param insertCardId 追加するカードのID
	 * @param insertCardName カードの名称
	 * @param strCategoryId 追加するカードのカテゴリID
	 * @param imageFileName 画像名
	 * @param expansionCode 追加するカードのエキスパンションコード
	 * @param strPrice 金額
	 * @return 追加件数
	 * @throws SQLException
	 */
	public int insertCard(String insertCardId, String insertCardName, String strCategoryId,
			String imageFileName, String expansionCode, String strPrice) throws SQLException {

		//insertされた件数
		int insCount = 0;
		//共通オブジェクトをインスタンス化
		ComServlet com = new ComServlet();

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION, //URL
				Constant.JDBC_USER, //ユーザー名
				Constant.JDBC_PASS //パスワード
		);) {
			//SQL文の生成
			String sql = "INSERT INTO ptcg_cards("
					//必須パラメータ名
					+ "card_id, card_name, category_id, expansion_code"
					//任意パラメータ名
					+ (com.isNullOrEmpty(imageFileName) ? "" : ", image_file_name")
					//固定値
					+ ", registration_time, update_time"
					//任意パラメータ名
					+ (com.isNullOrEmpty(strPrice) ? "" : ", price")
					//必須パラメータ値
					+ ") VALUES (?, ?, ?, ?"
					//任意パラメータ値
					+ (com.isNullOrEmpty(imageFileName) ? "" : ", ?")
					//固定値(CURRENT_TIMESTAMP)
					+ ", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP"
					//任意パラメータ値
					+ (com.isNullOrEmpty(strPrice) ? "" : ", ?")
					+ ");";

			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				// sql文のパラメータ設定
				int index = 0;
				//必須パラメータ設定
				pstmt.setInt(++index, Integer.parseInt(insertCardId));
				pstmt.setString(++index, insertCardName);
				pstmt.setInt(++index, Integer.parseInt(strCategoryId));
				pstmt.setString(++index, expansionCode);
				//任意パラメータ設定(画像ファイル名)
				if (!com.isNullOrEmpty(imageFileName)) {
					pstmt.setString(++index, imageFileName);
				}
				//任意パラメータ設定(価格)
				if (!com.isNullOrEmpty(strPrice)) {
					pstmt.setInt(++index, Integer.parseInt(strPrice));
				}

				// SQL文の実行
				insCount = pstmt.executeUpdate();
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insCount;
	}

	/**
	 * ptcg_cardsテーブルのカード情報を更新するメソッド
	 * @param request
	 * @param updateCardId 更新するカードのID
	 * @param updateCardName 更新するカードの名称
	 * @param strCategoryId 更新するードのカテゴリID
	 * @param imageName 
	 * @param expansionCode 更新するカードのエキスパンションコード
	 * @param strPrice 金額
	 * @return 更新件数
	 * @throws SQLException
	 */
	public int updateCard(String updateCardId, String updateCardName, String strCategoryId,
			String imageFileName, String expansionCode, String strPrice) throws SQLException {

		//updateされた件数
		int updateCount = 0;
		//共通オブジェクトをインスタンス化
		ComServlet com = new ComServlet();

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION, //URL
				Constant.JDBC_USER, //ユーザー名
				Constant.JDBC_PASS //パスワード
		);) {
			//SQL文の生成
			String sql = "UPDATE ptcg_cards SET "
					+ "card_name = ?"
					+ ", category_id = ?"
					+ ", expansion_code = ?"
					+ (com.isNullOrEmpty(imageFileName) ? "" : ", image_file_name = ?")
					+ ", update_time = CURRENT_TIMESTAMP"
					+ (com.isNullOrEmpty(strPrice) ? "" : ", price = ?")
					+ " WHERE card_id = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				// sql文のパラメータ設定
				int index = 0;
				//必須パラメータ設定
				pstmt.setString(++index, updateCardName);
				pstmt.setInt(++index, Integer.parseInt(strCategoryId));
				pstmt.setString(++index, expansionCode);
				//任意パラメータ設定(画像ファイル名)
				if (!com.isNullOrEmpty(imageFileName)) {
					pstmt.setString(++index, imageFileName);
				}
				//任意パラメータ設定(価格)
				if (!com.isNullOrEmpty(strPrice)) {
					pstmt.setInt(++index, Integer.parseInt(strPrice));
				}
				//WHERE句のパラメータ(カードID)
				pstmt.setInt(++index, Integer.parseInt(updateCardId));

				// SQL文の実行
				updateCount = pstmt.executeUpdate();
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateCount;
	}

	/**
	 * ptcg_expansionsテーブルを追加更新するメソッド
	 * <br>
	 * INSERTを試みて、可能であればそのまま実行
	 * プライマリキー(expansionCode)が衝突した場合はUPDATE
	 * @param request
	 * @param expansionCode 対象のエキスパンションコード
	 * @param expansionName 対象の収録元の名称
	 * @param releaseDate 対象の収録元の発売日
	 * @return 変更件数
	 * @throws SQLException
	 * 
	 */
	public int editExpansion(String expansionCode, String expansionName,
			Date releaseDate) throws SQLException {
		//変更された件数
		int editCount = 0;

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION, //URL
				Constant.JDBC_USER, //ユーザー名
				Constant.JDBC_PASS //パスワード
		);) {
			//SQL文の生成
			String sql = "INSERT INTO ptcg_expansions("
					+ "  expansion_code "
					+ ", expansion_name "
					+ ((releaseDate == null) ? "" : ", release_date ")
					+ ") VALUES ( "
					+ "  ? "
					+ ", ? "
					+ ((releaseDate == null) ? "" : ", ? ")
					+ ") "
					+ "ON CONFLICT ON CONSTRAINT ptcg_expansions_pkey "
					+ "DO UPDATE "
					+ " SET expansion_code = ?"
					+ "   , expansion_name = ?"
					+ ((releaseDate == null) ? "" : ", release_date = ?")
					+ " WHERE ptcg_expansions.expansion_code = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
				// パラメータインデックス
				int index = 0;
				//パラメータ設定(INSERT側)
				pstmt.setString(++index, expansionCode);
				pstmt.setString(++index, expansionName);
				if (releaseDate != null) {
					//任意パラメータ設定(releaseDate)
					pstmt.setDate(++index, releaseDate);
				}
				//パラメータ設定(UPDATE側)
				pstmt.setString(++index, expansionCode);
				pstmt.setString(++index, expansionName);
				if (releaseDate != null) {
					//任意パラメータ設定(releaseDate)
					pstmt.setDate(++index, releaseDate);
				}
				//パラメータ設定(WHERE句)
				pstmt.setString(++index, expansionCode);

				// SQL文の実行
				editCount = pstmt.executeUpdate();
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return editCount;
	}

	/**
	 * ptcg_expansionsテーブルを追加更新するメソッド
	 * <br>
	 * INSERTを試みて、可能であればそのまま実行
	 * プライマリキー(expansionCode)が衝突した場合はUPDATE
	 * @param expansionCode 対象のエキスパンションコード
	 * @param request
	 * @return 変更件数
	 * @throws SQLException
	 */
	public int deleteExpansion(String expansionCode) throws SQLException {
		//変更された件数
		int deleteCount = 0;

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
		try (Connection conn = DriverManager.getConnection(
				Constant.JDBC_CONNECTION, //URL
				Constant.JDBC_USER, //ユーザー名
				Constant.JDBC_PASS //パスワード
		);) {
			//SQL文の生成
			String sql = "DELETE FROM ptcg_expansions WHERE expansion_code = ?";

			try (PreparedStatement pstmt = conn.prepareStatement(sql);) {

				//パラメータ設定(WHERE句)
				pstmt.setString(1, expansionCode);

				// SQL文の実行
				deleteCount = pstmt.executeUpdate();
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteCount;
	}
}
