<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<link rel="stylesheet" href="<openmrs:contextPath/>/moduleResources/journaling/css/new_entry.css" type="text/css"/>

	<div id="module-content">
		<form method="post" action="<openmrs:contextPath/>/module/journaling/create_entry.form">
			<div id="header-bar">
				<span id="title">Compose Journal Entry</span>
				<input type="submit" id="save-button" value="Save"></input>
				<button id="cancel-button">Cancel</button>
			</div>
			<div id="compose-title-bar">
				<label for="title-textbox">Title</label>
				<input type="text" id="entry-title" name="title"></input>
			</div>
			<div id="compose-entry-container">
				<textarea id="entry-content" name="content"></textarea>
			</div>
		</form>
	</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>