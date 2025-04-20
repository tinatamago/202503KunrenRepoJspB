<%@ page contentType="text/html ; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1 ">
<meta http-equiv="Content-Script-Type" content="text/javascript">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">

<!--<script src="js/slick.js"></script>-->

<script charset="Shift_JIS">

//フォーカス時
function fnc_focus( form ) {
	form.style.background = "yellow";
}
//フォーカスアウト時
function fnc_blur( form ) {
	form.style.background = "white";
}

//ログインボタン処理
function fnc_submit(form) {

	// ユーザーＩＤ 入力チェック
	if (form.tx_user_id.value == ""){
		alert('ユーザーＩＤを入力してください。');
		return;
	}
	
	// Add 2022.11.08 takahashi パスワード 入力チェック
	if (form.tx_user_pass.value == ""){
		alert('パスワードを入力してください。');
		return;
	}

	form.submit();

}
</script>

<title>〇〇システム</title>

</head>
<body>

        <h1>ログイン画面</h1>
        <hr>
        <div align="center">
            <table border="0">
                <span class="label label-danger"><font color="red">${message}</font></span>
                <form name="form1" action="Login" method="post">
                    <input type="hidden" name="hd" value="login_action">
                    <tr>
                        <th class="login_field">
                            ユーザーＩＤ
                        </th>
                        <td class="login_field">
                            <input type="text" name="tx_user_id" maxlength="20"
                             onfocus="fnc_focus(this);" onblur="fnc_blur(this);" style="background-color: white; ">
                        </td>
                    </tr>
                    <tr>
                        <th class="login_field">
                            パスワード
                        </th>
                        <td class="login_field">
                            <input type="password" name="tx_user_pass" maxlength="20"
                             onfocus="fnc_focus(this);" onblur="fnc_blur(this);" style="background-color: white; ">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" value="ログイン" name="loginbtn" class="btn" onclick="fnc_submit(this.form)">
                        </td>
                    </tr>
                </form>
            </table>
        </div>

</body>
</html>