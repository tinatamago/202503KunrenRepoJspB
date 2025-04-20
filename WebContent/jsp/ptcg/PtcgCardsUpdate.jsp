<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%--　TODO readonlyでも開発者ツールから変えられる問題あり 参考 https://qiita.com/nasuB7373/items/10bebd8e9f0b3331f348 --%>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ptcg/style.css" media="all">
<title>更新画面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ptcg.js"></script>
</head>
<body>
	<%-- 共通部分をinclude(ステータス表示) --%>
	<jsp:include page="status.jsp" />
	<h1>更新フォーム</h1>
	<form id="entryForm" action="javascript:void(0)" method="post" enctype="multipart/form-data">
		<ul>
			<li>
				<label for="id_entry_field">カードID</label>
				<input type="text" pattern="^[0-9]+$" title="数字のみを入力してください" id="id_entry_field" name="card_id" value="${cardId}" maxlength="9" readonly>
			</li>

			<li>
				<label for="name_entry_field">カード名称</label>
				<input type="text" id="name_entry_field" name="card_name" value="${cardName}" maxlength="24" required>
			</li>

			<li>
				<label for="category_select_field">カテゴリー</label>
				<select id="category_select_field" name="category_id" required>
					<c:forEach items="${categories}" var="category">
					<c:choose><%-- categoryIdが前ページで設定したものと一致している場合だけ selected 属性を付け出力する--%>
					<c:when test="${category.categoryId == celectedCategoryId}">
						<option value="${category.categoryId}" selected>${category.categoryName}</option>
					</c:when>
					<c:otherwise>
						<option value="${category.categoryId}">${category.categoryName}</option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
				</select>
			</li>

			<li>
				<label for="file_upload_field">画像アップロード</label><!-- 参考 https://qiita.com/sueasen/items/f3e026f35f3b7323a98b -->
				<input type="file" id="file_upload_field" name="image" accept="image/gif,image/jpeg,image/png">
			</li>

			<li>
				<label for="expansion_select_field">エキスパンション</label>
				<select id="expansion_select_field" name="card_expansion">
					<c:forEach items="${expansions}" var="expansion">
					<c:choose><%-- expansionCodeが前ページで設定したものと一致している場合だけ selected 属性を付け出力する--%>
					<c:when test="${expansion.expansionCode == celectedExpansionCode}">
						<option value="${expansion.expansionCode}" selected>${expansion.expansionName}</option>
					</c:when>
					<c:otherwise>
						<option value="${expansion.expansionCode}">${expansion.expansionName}</option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
				</select>
			</li>

			<li>
				<label for="price_entry_field">価格</label>
				<%--TODO暫定対応　コピペで負の値を入れることが可能だが対策が複雑化するので許容 --%>
				<input type="number" id="price_entry_field" name="card_price" min="0" max="2147483647" value="${price}" oninput="javascript: this.value = this.value.slice(0, 9);">
			</li>
		</ul>

		<!-- ボタン群をまとめるdiv -->
		<div class="btns">
			<input type="submit" value="更新" class="btn btn-insert" onclick="updateCard(this.form)">
			<input type="button" value="検索画面へ" class="btn btn-backward" onclick="transitSearchPage(this.form)">
		</div>
	</form>
</body>
</html>