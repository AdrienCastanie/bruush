<form class="card" method="post">
	<h2>Modifier un livre</h2>
	<br><label>Code : ${book.id}</label><br>
	<input type="text" name="title" placeholder="Titre" value="${book.title}" required="required"><br>
	<input type="text" name="author" placeholder="Auteur" value="${book.author}" required="required"><br>
	<textarea name="description" placeholder="Description" rows="5">${book.description}</textarea>
	<div class="button-group">
		<button type="submit" formaction="bibliotheque?id=update&bookupdated=true&code=${book.id}">Valider</button>
		<button type="submit" formaction="bibliotheque?id=update&bookupdated=false" formnovalidate>Annuler</button>
	</div>
</form>