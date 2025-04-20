/**
 * ptcg検索システムに用いるjavascript
 */
/* ---------------- 判定関数 ---------------- */
function isNumber(numVal){
  // チェック条件パターン
  var pattern = /^\d*$/;
  // 数値チェック
  return pattern.test(numVal);
}
//空欄チェック
function isBlank(field){
	if(field.value == ""){
		return true;
	}
}

/* ---------------- 画面遷移 ---------------- */
//検索画面へ遷移
function transitSearchPage(form) {
	form.action = "DspPtcgCardsSearch";
	form.submit();
}
//登録画面へ遷移
function transitInsertPage(form) {
	form.action = "DspPtcgCardsInsert";
	form.submit();
}
//更新画面へ遷移
function transitUpdatePage(form) {
	//ラジオボタン選択しなかった場合
	if (form.target_id.value == "") {
		alert("更新対象が選択されていません");
		return false;
	}
	form.action = "DspPtcgCardsUpdate";
	form.submit();
}
//収録情報編集画面へ遷移
function transitEditExpansionPage(form) {
	form.action = "DspPtcgEditExpansion";
	form.submit();
}

/* ---------------- 検索画面 ---------------- */
//検索ボタン押下時
function search(form) {
	//空欄でなく、数値でもない場合
	console.log(form.card_id.value);
	if (form.card_id.value != "" && !isNumber(form.card_id.value)) {
		alert("カードIDは空欄または数値を入力してください");
		form.card_id.focus();
		return false;
	}
	//SearchCard.javaを実行
	form.action = "SearchCard";
	form.submit();
}
//削除ボタン押下時
function deleteCard(form) {
	//ラジオボタン選択しなかった場合
	if (form.target_id.value == "") {
		alert("削除対象が選択されていません");
		return false;
	}
	//最終確認
	if (confirm("カードID:" + form.target_id.value + "を削除しますか？")) {
		//DeleteCard.javaの実行へ遷移
		form.action = "DeleteCard";
		form.submit();
	} else {
		alert("削除をキャンセルしました");
	}
}
//csv保存押下時
function downloadCSV(form) {
	alert("csvダウンロードを実行します");
	form.action = "DownloadCSV";
	form.submit();
}

//クリアボタン
function clearSearchField() {
	document.getElementById('name_search_field').value="";
	document.getElementById('id_search_field').value="";
	document.getElementById('category_select_field').options[0].setAttribute("selected", "selected");
	document.getElementById('expansion_select_field').options[0].setAttribute("selected", "selected");
	document.getElementById('min_price_search_field').value="";
	document.getElementById('max_price_search_field').value="";
}

/* ---------------- 登録画面 ---------------- */
//登録ボタン押下時
function insertCard(form) {
	//入力チェック
	if (form.card_id.value == "" || !isNumber(form.card_id.value)) {
		alert("カードIDは数字入力必須です");
		form.card_id.focus();
		return false;
	}
	if (form.card_name.value == "") {
		alert("カード名は入力必須です");
		form.card_name.focus();
		return false;
	}
	if (form.category_id.value == "") {
		alert("カテゴリーは入力必須です");
		form.category_id.focus();
		return false;
	}
	if (form.card_expansion.value == "") {
		alert("エキスパンションは入力必須です");
		form.card_expansion.focus();
		return false;
	}
	//最終確認
	if (confirm(
			  "カードID : " + form.card_id.value + "\r\n"
			+ "カード名 : " + form.card_name.value + "\r\n"
			+ "カテゴリID : " + form.category_id.value + "\r\n"
			+ "エキスパンションコード : " + form.card_expansion.value + "\r\n"
			+ "価格 : " + form.card_price.value + "\r\n"
			+ "データを追加しますか？")) {
		//InsertCard.javaの実行へ遷移
		form.action = "InsertCard";
		form.submit();
	} else {
		alert("登録をキャンセルしました");
	}
}

/* ---------------- 更新画面 ---------------- */
//更新ボタン押下時
function updateCard(form) {
	//入力チェック
	if (form.card_id.value == "" || !isNumber(form.card_id.value)) {
		alert("カードIDは数字入力必須です");
		form.card_id.focus();
		return false;
	}
	if (form.card_name.value == "") {
		alert("カード名は入力必須です");
		form.card_name.focus();
		return false;
	}
	if (form.category_id.value == "") {
		alert("カテゴリーは入力必須です");
		form.category_id.focus();
		return false;
	}
	if (form.card_expansion.value == "") {
		alert("エキスパンションは入力必須です");
		form.card_expansion.focus();
		return false;
	}
	//最終確認
	if (confirm(
			  "カードID : " + form.card_id.value + "\r\n"
			+ "カード名 : " + form.card_name.value + "\r\n"
			+ "カテゴリID : " + form.category_id.value + "\r\n"
			+ "エキスパンションコード : " + form.card_expansion.value + "\r\n"
			+ "価格 : " + form.card_price.value + "\r\n"
			+ "データを更新しますか？")) {
		form.action = "UpdateCard";
		form.submit();
	} else {
		alert("更新をキャンセルしました");
	}
}

/* ---------------- 収録情報編集画面 ---------------- */
function editExpansion(form) {
	//入力チェック
	if (form.expansion_code.value == "") {
		alert("エキスパンションコードは入力必須です");
		form.expansion_code.focus();
		return false;
	}
	if (form.expansion_name.value == "") {
		alert("エキスパンション名は入力必須です");
		form.expansion_name.focus();
		return false;
	}
	if (confirm(
			"エキスパンションコード:" + form.expansion_code.value + "\r\n"
			+ "エキスパンション名:" + form.expansion_name.value + "\r\n"
			+ "発売日:" + form.release_date.value + "\r\n"
			+ "編集を確定しますか？")) {
		form.action = "EditExpansion";
		form.submit();
	} else {
		alert("編集をキャンセルしました");
	}
}

/* 既存の情報を入力欄へ反映する処理 */
function setExpansionData() {

	//テーブルを取得
	let expansionTable = document.getElementById("expansionTable");

	//選択行用変数を初期化
	let selRowIndex = 0;

	//ラジオボタンが選択されている行を探す
	var expansions = document.getElementsByName("target_code");
	for (var i = 0; i < expansions.length; i++) {
		//見つかればインデックスとして保存
		if (expansions[i].checked) {
			selRowIndex = i;
		}
	}

	//編集画面のフォームを取得
	let formElements = document.forms.editForm;

	//選択された行の要素取得
	let selectedRadioElementRow = expansionTable.rows[selRowIndex];

	//各セルの要素を取得し入力欄に反映
	formElements.expansion_code_field.value = selectedRadioElementRow.cells[1].innerHTML;
	formElements.expansion_name_field.value = selectedRadioElementRow.cells[2].innerHTML;
	formElements.release_date_field.value = selectedRadioElementRow.cells[3].innerHTML;
}
//削除ボタン押下時
function deleteExpansion(form) {
	//ラジオボタン選択しなかった場合
	if (form.target_code.value == "") {
		alert("削除対象が選択されていません");
		return false;
	}
	//最終確認
	if (confirm("エキスパンション:" + form.target_code.value + "を削除しますか？")) {
		//DeleteCard.javaの実行へ遷移
		form.action = "DeleteExpansion";
		form.submit();
	} else {
		alert("削除をキャンセルしました");
	}
}