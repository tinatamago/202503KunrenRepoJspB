package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cnst.Constant;
import dto.LoginDto;

/**
 * <P>タイトル : ログインDAOクラス</P>
 * <P>説明 : ログインのDB接続関係のクラスです。</P>
 * @author  
 * @version
 */
public class LoginDao {
	
	/**
	 * メソッド（JDBCドライバ読込）
	 *
	 * @param request  HttpServletRequestオブジェクト
	 */
	public int readJdbcDriver(HttpServletRequest request) throws Exception {

		// JDBCドライバの読み込み
	    try {
	    	// postgresSQLのJDBCドライバを読込
	    	Class.forName("org.postgresql.Driver");
	    } catch(ClassNotFoundException e) {
	    	// JDBCドライバが見つからない場合
	    	e.printStackTrace();

	    	//異常終了
	    	return Constant.RET_NG;

	    }
	    
	    //正常終了
	    return Constant.RET_OK;

	}

	/**
	 * メソッド（ログインチェック）
	 *
	 * @param request  HttpServletRequestオブジェクト
	 * @param userId   ユーザーID
	 * @param userPass パスワード
	 */
	public List<LoginDto> getDBLoginChk(HttpServletRequest request, String userId, String userPass) throws Exception {

		//-----------------
		// 接続
		//-----------------
		//PostgreSQLへの接続
    	Connection conn = DriverManager.getConnection(
    			Constant.JDBC_CONNECTION ,		//URL
    			Constant.JDBC_USER,				//ユーザー名
    			Constant.JDBC_PASS				//パスワード
    	);

		//Statementオブジェクトの生成
		Statement stmt = conn.createStatement();

		//Listオブジェクトの生成
		List<LoginDto> list = new ArrayList<LoginDto>();

	    try {

	    	//ログインSQLの組み立て
            String sql = "SELECT user_id, user_pass  FROM m_user ";
            sql = sql + " WHERE user_id = '" + userId + "'";
            sql = sql + "   AND user_pass = '" + userPass + "'";

			//sql文をexcuteする
			ResultSet rs = stmt.executeQuery(sql);

			//SELECT文の結果を1行ずつ読み込む
			while (rs.next()) {
				//listオブジェクトに格納
			    list.add(
			    		// getter・setterを利用する
			    		new LoginDto(
			    				// SQLのカラム名で取得。ResultSetに格納
			    				rs.getString("user_id"),
			    				rs.getString("user_pass")
			    		)
			    );
			}

			//JDBCのリソース解放
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

			//異常終了
			return null;

	    } finally {

    		if (conn != null){
	    		//コネクションをクローズする
	    		conn.close();
	    	}
	    }

	    //正常終了
		return list;

	}

}
