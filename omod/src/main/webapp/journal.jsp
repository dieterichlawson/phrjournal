<%@ include file="/WEB-INF/template/include.jsp"%>
<c:if test="${hasPermission}">
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
					<form id="search-form" method="get" action="<openmrs:contextPath/>/module/journaling/journal.form">  
						<input type="text" id="search-box" name="search" ></input>
						<input id="search-button" type="submit" value="Search"></input>
					</form>
				</div>
			</div>
			<c:if test="${empty entries}">
				<div id="no-results">
					<span id="no-results-text">You don't have any journal entries yet.<br><br> Why don't you <a href="<openmrs:contextPath/>/module/journaling/new_entry.form">write</a> one?</span>
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
		$j("#search-box").val(gup("search"));
	});

	function gup(name){
	  name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	  var regexS = "[\\?&]"+name+"=([^&#]*)";
	  var regex = new RegExp( regexS );
	  var results = regex.exec( window.location.href );
	  if( results == null )
	    return "";
	  else
	    return results[1];
	}
	
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
		var listHTML = "<li><span class=\"month-link\" onclick=\"expand('#id-list')\" id=\"#id-link\">#mon</span><ol id=\"#id-list\" style=\"display:none;\"></ol></li>"
		var date = new Date(posts[0].dateCreated);
		var idString = date.getMonth() + "-" + date.getFullYear();
		var monthString = months[date.getMonth()];
		listHTML = listHTML.replace(new RegExp("#id",'g'),idString);
		listHTML = listHTML.replace(new RegExp("#mon",'g'),monthString);
		var monthList = $j(listHTML);
		for(var i = 0; i < posts.length; i++){
			$j("<li><a class=\"post-link\" href=\"<openmrs:contextPath/>/module/journaling/journal.form?id="+posts[i].entryId+"\">"+posts[i].title+"</a></li>").appendTo(monthList.find("#"+idString+"-list"));
		}
		monthList.appendTo("#month-list");
	}
	
	function expand(toExpand){
		$j("#"+toExpand).toggle(100);
	}

</script>
</c:if>
<c:if test="${!hasPermission}">
	<h2>You do not have permission to view this journal entry.</h2>
</c:if>