<form class="card" method="post">
	<h2>Ajouter un nouveau livre</h2><br>
	<input type="text" name="title" placeholder="Titre" required="required"><br>
	<input type="text" name="author" placeholder="Auteur" required="required"><br>
	<textarea name="description" placeholder="Description" rows="5"></textarea>
	<div class="button-group">
		<button type="submit" formaction="bibliotheque?id=add&bookadded=true">Valider</button>
		<button type="submit" formaction="bibliotheque?id=add&bookadded=false" formnovalidate>Annuler</button>
	</div>
</form>