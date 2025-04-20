<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="status">
		<p>
			<span>ＩＤ:${userId}</span> がログイン中
		</p>
		<c:if test="${not empty message}">
			<div class="message">${message}</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="error_message">${errorMessage}</div>
		</c:if>
		<p>
			<img src="./upload/ptcg/${filename}" onerror="this.style.display='none'">
		</p>
		<p>
			<input type="button" class="btn" onclick="location.href='${pageContext.request.contextPath}/Login'" value="ログアウト">
		</p>
	</div>