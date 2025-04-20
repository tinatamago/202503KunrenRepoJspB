<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ptcg/style.css" media="all">
<title>検索画面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ptcg.js"></script>
</head>
<body>
	<%-- 共通部分をinclude(ステータス表示) --%>
	<jsp:include page="status.jsp" />
	<div class="search">
		<form id="searchForm" action="#" method="post">
			<ul>
				<li>
					<label for="name_search_field">カード名称</label>
					<input type="search" id="name_search_field" name="card_name" placeholder="名称検索" value="${cardName}">
				</li>
				<details open>
					<summary>詳細条件(AND検索)</summary>
					<li>
						<label for="id_search_field">カードID</label> 
						<input type="search" pattern="^[0-9]+$" title="数字のみを入力してください" id="id_search_field" name="card_id" placeholder="ID検索" value="${cardId}">
					</li>
					<li>
						<label for="category_select_field">カテゴリー</label>
						<select id="category_select_field" name="category_id">
							<option value="">全選択</option>
							<c:forEach items="${requestScope.categories}" var="category">
								<c:choose>
									<%-- categoryIdが前ページで設定したものと一致している場合だけ selected 属性を付け出力する（文字列比較）--%>
									<c:when test="${Integer.toString(category.categoryId).equals(celectedCategoryId)}">
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
						<label for="expansion_select_field">エキスパンション</label>
						<select id="expansion_select_field" name="card_expansion">
							<option value="">全選択</option>
							<c:forEach items="${expansions}" var="expansion">
								<c:choose>
									<%-- expansionCodeが前ページで設定したものと一致している場合だけ selected 属性を付け出力する--%>
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
						<label for="min_price_search_field">価格</label>
						<%--TODO暫定対応　コピペで負の値を入れることが可能だが対策が複雑化するので許容 --%> 
						<input type="number" id="min_price_search_field" name="min_price" placeholder="下限" min="0" max="2147483647" oninput="javascript: this.value = this.value.slice(0, 9);" value="${minPrice}"> ～ 
						<input type="number" id="max_price_search_field" name="max_price" placeholder="上限" min="0" max="2147483647" oninput="javascript: this.value = this.value.slice(0, 9);" value="${maxPrice}"> 円
					</li>
				</details>
				<li>
					<input type="submit" value="検索" class="btn btn-search" onclick="search(this.form)"> 
					<input type="button" value="クリア" class="btn btn-clear" onclick="clearSearchField()">
				</li>
			</ul>
		</form>
	</div>
	<!-- 結果フォーム -->
	<form id="resultForm" action="#" method="post">
		<input type="checkbox" id="detail" checked>
		<label for="detail">詳細表示</label>
		<table class="resultTable">
			<caption>検索結果</caption>
			<colgroup>
				<col class="check" />
				<col class="card_id" />
				<col class="card_name" />
				<col class="category_name" />
				<col class="image" />
				<col class="expansion_name" />
				<col class="release_date" />
				<col class="registration_time" />
				<col class="update_time" />
				<col class="price" />
			</colgroup>
			<thead>
				<tr>
					<th>&#x2713;<input type="radio" name="target_id" class="choice" value="" checked="checked" style="display: none;"></th>
					<th>カードID</th>
					<th>カード名称</th>
					<th>分類</th>
					<th>画像</th>
					<th>収録</th>
					<th>発売日</th>
					<th>登録日時</th>
					<th>更新日時</th>
					<th>価格</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${cards}" var="card">
					<tr>
						<td><input type="radio" name="target_id" class="choice" value="${card.cardId}"></td>
						<td>${card.cardId}</td>
						<td>${card.cardName}</td>
						<td>${card.categoryName}</td>
						<td><img src="${pageContext.request.contextPath}/upload/ptcg/${card.imageFileName}"
						 width="50" height="50" alt="${card.cardName}" 
						 onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/img/no_image.jpeg';"></td>
						<td>${card.expansionName}</td>
						<td>${card.releaseDate}</td>
						<td>${card.registrationTime}</td>
						<td>${card.updateTime}</td>
						<td>${card.price}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

	<form id="pageTransition" action="#" method="post">
		<!-- ボタン系統 -->
		<div class="btns">
			<input type="button" value="登録" class="btn btn-insert" onclick="transitInsertPage(this.form)">
			<input type="button" value="更新" class="btn btn-update" form="resultForm" onclick="transitUpdatePage(this.form)">
			<input type="button" value="削除" class="btn btn-delete" form="resultForm" onclick="deleteCard(this.form)">
			<!-- TODO 削除ボタンのformをresultFormに紐づけるのか、逆にラジオボタン側でformをpageTransitionに紐づけるのか -->
			<input type="button" value="収録情報編集" class="btn btn-csv" onclick="transitEditExpansionPage(this.form)">
			<input type="button" value="csv保存" class="btn btn-csv" form="resultForm" onclick="downloadCSV(this.form)">
		</div>
	</form>
</body>
</html>