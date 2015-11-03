<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="container">

	<section class="view-list col-sm-3">
		<tiles:insertAttribute name="listMenu" />
	</section>
	
	<section class="edit-list col-sm-9">
		<tiles:insertAttribute name="editList" />
	</section>

</div>