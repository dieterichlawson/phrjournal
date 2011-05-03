<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<link rel="stylesheet" href="<openmrs:contextPath/>/moduleResources/journaling/css/journal.css" type="text/css"/>

	<div id="module-content">
		<div id="nav-bar">
			<button id="new-entry-button" type="button" onclick="newEntry(); return false;">New Entry</button>
			<label for="search-box">Search</label><input type="text" id="search-box"></input>
			<div id="entries-by-month-nav">
				<ol>
					<li>August
						<ol>
							<li>Walk in the Park</li>
							<li>Walk in the Field</li>
							<li>Walk in the Hay</li>
						</ol>
					</li>
					<li>May
						<ol>
							<li>Walk in the Park</li>
							<li>Walk in the Field</li>
							<li>Walk in the Hay</li>
						</ol>
					</li>
				</ol>
			</div>
		</div>
		<div id="entry-pane">
			<c:forEach var="entry" items="${entries}">
				<div class="entry">
					<div class="title-bar">
						<span class="entry-title">${entry.title}</span>
						<span class="entry-date">${entry.dateCreated}</span>
					</div>
					<div class="entry-content" >${entry.content}</div>
				</div>
			</c:forEach>
		</div>
	</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>
<script type="text/javascript">

	function newEntry(){
		window.location="new_entry.form";
	}
</script>