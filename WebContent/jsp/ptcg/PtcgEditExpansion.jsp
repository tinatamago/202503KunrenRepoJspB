<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ptcg/style.css" media="all">
<title>エキスパンション情報編集画面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ptcg.js"></script>
</head>
<body>
	<%-- 共通部分をinclude(ステータス表示) --%>
	<jsp:include page="status.jsp" />
	<h1>エキスパンション情報の編集</h1>
	<form id="editForm" action="javascript:void(0)" method="post">
		<ul>
			<li>
				<label for="expansion_code_field">コード</label> 
				<input type="text" id="expansion_code_field" name="expansion_code" maxlength="8" value="${expansion.expansionCode}">
			</li>
			<li>
				<label for="expansion_name_field">名称</label> 
				<input type="text" id="expansion_name_field" name="expansion_name" maxlength="16" value="${expansion.expansionName}">
			</li>
			<li>
				<label for="release_date_field">発売日</label> 
				<input type="date" id="release_date_field" name="release_date" value="${expansion.releaseDate}">
			</li>
		</ul>
		<p>※未登録のコードを入力した場合は新規追加されます</p>
		<table id="expansionTable">
			<caption>登録済み情報一覧</caption>
			<colgroup>
				<col class="check" />
				<col class="expansion_code" />
				<col class="expansion_name" />
				<col class="release" />
			</colgroup>
			<thead>
				<tr>
					<th>&#x2713;<input type="radio" name="target_code" class="choice" value="" checked="checked" style="display: none;"></th>
					<th>エキスパンションコード</th>
					<th>エキスパンション名称</th>
					<th>発売日</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${expansions}" var="expansion">
					<tr>
						<td><input type="radio" name="target_code" class="choice" value="${expansion.expansionCode}"></td>
						<td>${expansion.expansionCode}</td>
						<td>${expansion.expansionName}</td>
						<td>${expansion.releaseDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- ボタン群をまとめるdiv -->
		<div class="btns">
			<input type="button" value="&#x2713;を反映" class="btn" onclick="setExpansionData()">
			<input type="submit" value="編集を確定" class="btn btn-revise" onclick="editExpansion(this.form)">
			<input type="button" value="検索画面へ" class="btn btn-backward" onclick="transitSearchPage(this.form)">
			<input type="button" value="&#x2713;を削除" class="btn" onclick="deleteExpansion(this.form)">
		</div>
	</form>
</body>
</html>