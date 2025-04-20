<%@ page contentType="text/html ; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript">
<script charset="Shift_JIS">
/**
 * 半角英数字チェック。
 *
 * str		文字列
 * return
 *          true ：半角英数字のみ
 *          false：上記以外
 */
function fnc_checknumber(str){

    if (str.length == 0){
        return true;
    }

    var anum = 0x1;
    var ret = 0;
    var m = ""
    for (i=0;i<str.length;i++){
        m = str.charCodeAt(i);
        if ((m>=48 && m<=57) || (m>=65 && m<=90) || (m>=97 && m<=122)){
            ret |= anum;
        }
        else {
            return false;
        }
    }
    if (ret == 1){
        return true;
    }
    else {
        return false;
    }
}

//前後スペース削除(全角半角対応)
function jsTrim( val ) {

	var ret = val;

	ret = ret.replace( /^[\s]*/, "" );
	ret = ret.replace( /[\s]*$/, "" );

	return ret;
}

// オンロード処理
function fnc_dspInit(flg) {

	var tantoCd = "<c:out value="${tantoCd}" />";
	var tantoName = "<c:out value="${tantoName}" />";
	var resultUpdate = "<c:out value="${resultUpdate}" />";

	var frm = parent.document.forms[0];

	if (flg){
		frm.txt_tanto_cd.value = tantoCd;
		frm.txt_tanto_name.value = tantoName;
		//メッセージを出力
		if(resultUpdate != ""){
			alert(resultUpdate);
		}

		return;
	}

}


//ログアウトボタン処理
function fnc_logout(form) {

	form.action = "LoginServlet";
	form.method ="get";
	form.submit();
}

// 更新ボタン処理
function fnc_update(form) {

	// 入力チェック
	if ( jsTrim( form.txt_tanto_name.value ).length == 0 ) {
		alert( "氏名は必須入力です" );
		form.txt_tanto_name.focus();
		return;
	}

	form.action = "TantoUpdate";
	form.submit();
}

//戻るボタン処理
function fnc_back(form) {

	form.action = "TantoSearch";
	form.submit();
}

//削除ボタン処理
function fnc_delete(form) {

	var result = window.confirm('削除します。よろしいですか？');
	if (!result){
		return;
	}

	//ラジオボタンオブジェクトを取得する
	var radios = document.getElementsByName("rd_data") ;
	//選択されているか否かを判定するフラグ
	var flag = false;
	//取得したラジオボタンオブジェクトから選択されたものを探し出す
	var result;

	for(var i=0; i<radios.length; i++){
	  if (radios[i].checked) {
	    //選択されたラジオボタンのvalue値を取得する
	    result = radios[i].value;

	    flag = true;

	    break;
	  }
	}

    // 何も選択されていない場合の処理
    if(!flag){
        alert("項目が選択されていません。");
        return;
    }

	form1.hd_code.value = result;

	form.action = "DeleteClass";
	form.submit();
}

//アップロードボタン処理
function fnc_upload(form) {

	form.action = "UploadServlet";
	form.submit();
}

function fnc_focus( form ) {
	form.style.background = "yellow";
}
function fnc_blur( form ) {
	form.style.background = "white";
}
function fnc_select( form ) {
	//ラジオボタンオブジェクトを取得する
	var selects = document.getElementsByName("color1") ;

	alert(selects);
	var selects = document.getElementsByName("color1")[0];

	alert(selects);

	_val = myFruit.options[selects.selectedIndex].value;
	alert(_val);

}
</script>
<style type="text/css">
<!--
  table.top2 {
    border-width:2px 1px 1px 2px;
    border-style:solid;
    border-color:#606060 #ffffff #ffffff #606060;
    color:#ff0000;
    font-size:12pt;
    font-weight:bold;
  }

  table.header{
    left     : 6px;
    position : relative;
    z-index  : 2;
  }
  table.item{
    top      : -8px;
    height   : 70px;
    border   : 1px solid black;
    position : relative;
    z-index  : 1;
  }

  td.data {
    border-width:2px 1px 1px 2px;
    border-style:solid;
    border-color:#606060 #ffffff #ffffff #606060;
  }

  div.left {
    width:865px; height:400px; overflow-y:scroll;
  }


input.btn1 {
    /* 幅指定 */
    width:75px;
    /* 背景色を濃い青色に指定 */
    background-color: #248;
    /* 文字色を白色に指定 */
    color: #fff;
}
input.btn2 {
    /* 幅指定 */
    width:150px;
    /* 背景色を黒色に指定 */
    background-color: #000000;
    /* 文字色を白色に指定 */
    color: #fff;
}
input.gray {background-color:#dcdcdc;}
table.top1 {border-width:2px 1px 1px 2px; border-style:solid; border-color:#606060 #ffffff #ffffff #606060;}
select.gray {background-color:#dcdcdc;}

input.btnlogout{
     display:block;
     width: 120px;
     height:30px;
     line-height: 25px;
     color: #FFF;
     text-decoration: none;
     text-align: center;
     background-color: #f39800; /*ボタン色*/
     border-radius: 5px; /*角丸*/
     -webkit-transition: all 0.5s;
     transition: all 0.5s;
}
input.btnlogout:hover{
     background-color: #f9c500; /*ボタン色*/
}

-->
</style>

<title>エラー画面</title>
</head>
<body onload="fnc_dspInit(<c:out value="${initFlg}" />);" >

<form name="form1" method="post">

<table cellpadding="0" cellspacing="0" width="850px">
  <tbody>
    <tr height="27px">
      <td width="200px">
        <table class="top2" width="200px" cellpadding="0">
          <tbody>
            <tr>
              <td>更新画面</td>
            </tr>
          </tbody>
        </table>
      </td>
      <td width="120px">
        <table cellpadding="0">
          <tbody>
            <tr>
              <td>　ユーザーID</td>
            </tr>
          </tbody>
        </table>
      </td>
      <td>
        <table class="top2" width="100px" cellpadding="0">
          <tbody>
            <tr>
              <td><c:out value="${userId}" /></td>
            </tr>
          </tbody>
        </table>
      </td>
      <td width="120px">
        <table>
          <tbody>
            <tr>
              <td><input type="button" class="btnlogout" value="ログアウト" onclick="fnc_logout(this.form)"></td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
  </tbody>
</table>
<BR>
<input type="hidden" name="hd_code" value="">
  <table>
    <tr>
      <td>エラーメッセージ</td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td><span class="label label-danger"><font color="red">${message}</font></span></td>
      <td></td>
      <td></td>
    </tr>
  </table>
<br>
<br>
<br>
&nbsp;
<input type="button" value="戻る" class="btn2" onclick="fnc_back(this.form)">
</form>
</body>
</html>