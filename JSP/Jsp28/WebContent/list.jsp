<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<title>list</title>
<style>
</style>
</head>
<body>
 <div class="container">
 <div class="col-sm-10">
	<table class="table table-hover" >
		<thead class="table-secondary">
		<tr>
			<th scope="col" >번호</th>
			<th scope="col" >이름</th>
			<th scope="col" >제목</th>
			<th scope="col" >날짜</th>
			<th scope="col" >히트</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bId}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a>
			</td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<thead class="thead-light" align="right">
		<tr>
			<th colspan="5" align="right"> 
			<a href="write_view.do">글작성</a> &nbsp;
			</th>
		</tr>
		</tbody>
		</thead>
		<thead align="center">
		<tr>
			<th colspan="5" > 
			<c:choose>
			<c:when test="${(page.curPage-1)<1}">
				[처음]
			</c:when>
			<c:otherwise>
				<a href="list.do?page=1">[처음]</a>
			</c:otherwise>
			</c:choose>	
			
			<c:choose>
			<c:when test="${(page.curPage-1)<1}">
				◀이전 &nbsp;&nbsp;
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.curPage-1}">◀이전</a>&nbsp;&nbsp;
			</c:otherwise>
			</c:choose>	
			
			<c:forEach var="num"  begin="${page.startPage}" end="${page.endPage}" step="1">
			<c:choose>
			<c:when test="${page.curPage==num}">
				${num}&nbsp;
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${num}" >${num}</a>&nbsp;
			</c:otherwise>
			</c:choose>	
			</c:forEach>
			&nbsp;
			
			<c:choose>
			<c:when test="${(page.curPage+1) > page.totalPage}">
				다음▶ 
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.curPage+1}">다음▶</a>
			</c:otherwise>
			</c:choose>	
			
			<c:choose>
			<c:when test="${page.curPage== page.totalPage}">
				[끝]
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.totalPage}">[끝]</a>
			</c:otherwise>
			</c:choose>	
			</th>
		</tr>
		</thead>
	</table>
	</div>
	</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<!-- endpage: ${page.endPage} <br> -->
</body>
</html>