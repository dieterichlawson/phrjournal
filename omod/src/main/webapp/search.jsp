<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>
<link rel="stylesheet" href="<openmrs:contextPath/>/moduleResources/journaling/css/journal.css" type="text/css"/>

	<div id="module-content">
		<div id="nav-bar">
			<button id="new-entry-button" type="button" onclick="newEntry(); return false;">New Entry</button>
			<div id="entries-by-month-nav">
				 <span id="month-title-span">Past Entries</span>
				<ol id="month-list"></ol>
			</div>
		</div>
		<div id="entry-pane">
			<div id="search-pane">
				<span id="search-text"></span>
				<div id="search-action-div">
					<form method="get" action="<openmrs:contextPath/>/module/journaling/search.form" id="search-form">    
						<input type="text" id="search-box" name="searchText" value="${searchText}"></input>
						<input id="search-button" type="submit" value="Search"></input>
					</form>
				</div>
			</div>
			<c:if test="${empty entries}">
				<div id="no-results">
					<span id="no-results-text">There were no journal entries matching your search.</span>
				</div>
			</c:if>
			<c:forEach var="entry" items="${entries}">
				<div class="entry">
					<div class="title-bar">
						<span class="entry-title">${entry.title}</span>
						<span class="entry-date"><openmrs:formatDate date="${entry.dateCreated}" format="MM/dd/yyyy K:mm a"/></span>
					</div>
					<div class="entry-content" >${entry.content}</div>
				</div>
			</c:forEach>
		</div>
	</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>
<openmrs:htmlInclude file="/dwr/engine.js"/>
<openmrs:htmlInclude file="/dwr/util.js"/>
<script src="<openmrs:contextPath/>/dwr/interface/DWRJournalEntryService.js"></script>
<script type="text/javascript">
	var months = ["January","February","March","April","May","June","July","August","September","October","November","December"];

	$j(document).ready(function(){
		DWRJournalEntryService.getJournalEntries(function(posts){createNavList(posts)});
	});
	
	function newEntry(){
		window.location="new_entry.form";
	}
	
	function createNavList(posts){
		var postsByMonth={};
		for(var i = 0; i < posts.length; i++){
			var post = posts[i];
			var postMonth = new Date(post.dateCreated).getMonth()
			if(!(postMonth in postsByMonth)){ 
				postsByMonth[postMonth] = [post];
			}else{
				postsByMonth[postMonth].push(post);
			}
		}
		for(var monthSet in postsByMonth){
			createMonth(postsByMonth[monthSet]);
		}
	}
	
	function createMonth(posts){
		var listHTML = "<li><a href=\"#\" class=\"month-link\" onclick=\"expand('#id-list')\" id=\"#id-link\">#mon</a><ol id=\"#id-list\" style=\"display:none;\"></ol></li>"
		var date = new Date(posts[0].dateCreated);
		var idString = date.getMonth() + "-" + date.getFullYear();
		var monthString = months[date.getMonth()];
		listHTML = listHTML.replace(new RegExp("#id",'g'),idString);
		listHTML = listHTML.replace(new RegExp("#mon",'g'),monthString);
		var monthList = $j(listHTML);
		for(var i = 0; i < posts.length; i++){
			$j("<li><a class=\"post-link\" href=\"#\">"+posts[i].title+"</a></li>").appendTo(monthList.find("#"+idString+"-list"));
		}
		monthList.appendTo("#month-list");
	}
	
	function expand(toExpand){
		$j("#"+toExpand).toggle(100);
	}
</script>