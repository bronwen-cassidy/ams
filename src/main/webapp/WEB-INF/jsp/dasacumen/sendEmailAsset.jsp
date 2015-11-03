<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="modal-header col-sm-12">
	
	<h5>To: </h5><input class="email-header-to" type="text" value="${emailAddress}" ></input>
	<a class="close" data-dismiss="modal">×</a>
</div>
<div class="content-modal">
	<!-- <div class="input-group col-sm-12 email-padding">
		<span class="input-group-addon input-group-addon-size no-radius">Cc/Bcc:</span>
		<input type="text" class="form-control">
	</div> -->
	<div class="input-group col-sm-12 email-padding">
		<span class="input-group-addon input-group-addon-size no-radius">Subject:</span>
		<input type="text" class="form-control">
	</div>
	<div class="col-sm-12">
		<div class="email-modal-inner-scroll margin-bottom margin-top">
			<table class="col-sm-10 table table-bordered table-hover">
				<tr>
					<th><spring:message code="assetRegister.asset.generalTab.assetNumber"/></th>
					<th><spring:message code="assetRegister.asset.generalTab.name"/></th>
				</tr>
				<tr>
					<td>${assetNumber}</td>
					<td>${assetName}</td>
				</tr>
			</table>
		</div>
		<textarea placeholder="Enter Message Here..." rows="6" cols="50" class="col-sm-12"></textarea>
	</div>
	 
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-danger" data-dismiss="modal">Close</a>
	<a href="#" class="btn btn-success">Send</a>
</div>