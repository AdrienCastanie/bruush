<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="table-wrapper">
	<table class="fl-table">
		<thead>
			<tr>
				<th>Code</th>
				<th>Titre</th>
				<th>Auteur</th>
				<th>Description</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${ catalog }">
				<tr>
					<td>${book.id}</td>
					<td type="left">${book.title}</td>
					<td>${book.author}</td>
					<td type="left">${book.description}</td>
					<td>
						<a href="bibliotheque?id=edit&code=${book.id}"><i class="fa fa-edit fa-2x"></i></a>
						<a href="bibliotheque?id=delete&code=${book.id}"><i class="fa fa-trash fa-2x"></i></a>
					</td>
				</tr>
	        </c:forEach>
		</tbody>
	</table>
</div>