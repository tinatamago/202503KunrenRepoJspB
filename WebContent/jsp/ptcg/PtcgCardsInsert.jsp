<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ptcg/style.css" media="all">
<title>登録画面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ptcg.js"></script>
</head>
<body>
	<%-- 共通部分をinclude(ステータス表示) --%>
	<jsp:include page="status.jsp" />
	<h1>登録フォーム</h1>
	<form id="entryForm" action="javascript:void(0)" method="post" enctype="multipart/form-data">
		<ul>
			<li>
				<label for="id_entry_field">カードID</label>
				<input type="text" pattern="^[0-9]+$" title="数字のみを入力してください" id="id_entry_field" name="card_id" value="" maxlength="9" required>
			</li>

			<li>
				<label for="name_entry_field">カード名称</label>
				<input type="text" id="name_entry_field" name="card_name" value="" maxlength="24" required>
			</li>

			<li>
				<label for="category_select_field">カテゴリー</label>
				<select id="category_select_field" name="category_id" required>
					<option value="" disabled selected>未選択</option>
					<c:forEach items="${categories}" var="category">
						<option value="${category.categoryId}">${category.categoryName}</option>
					</c:forEach>
				</select>
			</li>

			<li>
				<label for="file_upload_field">画像アップロード</label><!-- 参考 https://qiita.com/sueasen/items/f3e026f35f3b7323a98b -->
				<input type="file" id="file_upload_field" name="image" accept="image/gif,image/jpeg,image/png">
			</li>

			<li>
				<label for="expansion_select_field">エキスパンション</label>
				<select id="expansion_select_field" name="card_expansion" required>
					<option value="" disabled selected>未選択</option>
					<c:forEach items="${expansions}" var="expansion">
						<option value="${expansion.expansionCode}">${expansion.expansionName}</option>
					</c:forEach>
				</select>
			</li>

			<li>
				<label for="price_entry_field">価格</label>
				<%--TODO暫定対応　コピペで負の値を入れることが可能だが対策が複雑化するので許容 --%>
				<input type="number" id="price_entry_field" name="card_price" min="0" max="2147483647" oninput="javascript: this.value = this.value.slice(0, 9);">
			</li>
		</ul>

		<!-- ボタン群をまとめるdiv -->
		<div class="btns">
			<input type="submit" value="登録" class="btn btn-insert" onclick="insertCard(this.form)">
			<input type="button" value="検索画面へ" class="btn btn-backward" onclick="transitSearchPage(this.form)">
		</div>
	</form>
</body>
</html>