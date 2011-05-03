<%@ include file="/WEB-INF/template/include.jsp"%>

<!-- YUI Text Editor includes -->
<link rel="stylesheet" type="text/css" href="<openmrs:contextPath/>/moduleResources/journaling/yui-text-editor/skin.css">
<script type="text/javascript" src="<openmrs:contextPath/>/moduleResources/journaling/yui-text-editor/yahoo-dom-event.js"></script>
<script type="text/javascript" src="<openmrs:contextPath/>/moduleResources/journaling/yui-text-editor/element-min.js"></script>
<script type="text/javascript" src="<openmrs:contextPath/>/moduleResources/journaling/yui-text-editor/container_core-min.js"></script>
<script type="text/javascript" src="<openmrs:contextPath/>/moduleResources/journaling/yui-text-editor/menu-min.js"></script>
<script type="text/javascript" src="<openmrs:contextPath/>/moduleResources/journaling/yui-text-editor/button-min.js"></script>
<script type="text/javascript" src="<openmrs:contextPath/>/moduleResources/journaling/yui-text-editor/editor-min.js"></script>
<script type="text/javascript">	
	var myEditor = new YAHOO.widget.SimpleEditor('entry-content', {
	    height: '20em',
		width: ''
	});
	myEditor.render();
</script>

<link rel="stylesheet" href="<openmrs:contextPath/>/moduleResources/journaling/css/new_entry.css" type="text/css"/>
<%@ include file="/WEB-INF/template/header.jsp"%>



	<div id="module-content">
		<form method="post" action="<openmrs:contextPath/>/module/journaling/create_entry.form" id="new-entry-form">
			<div id="header-bar">
				<span id="title">Compose Journal Entry</span>
				<input type="submit" id="save-button" value="Save"></input>
				<button type="button" id="cancel-button" onclick="cancelPost(); return false;">Cancel</button>
			</div>
			<div id="compose-title-bar">
				<label for="title-textbox">Title</label>
				<input type="text" id="entry-title" name="title" value="${title}"></input>
			</div>
			<div id="compose-entry-container" class="yui-skin-sam">
				<textarea id="entry-content" name="content">${content}</textarea>
			</div>
		</form>
	</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>

<script type="text/javascript">

	$j(document).ready(function() { 
	    // bind form using ajaxForm 
	    $j('#new-entry-form').submit(function(){
	    	myEditor.saveHTML();
		    //The var html will now have the contents of the textarea
		    var html = myEditor.get('element').value, match;
			match = html.match(/<body[^>]*>([\s\S]*?)<\/body>/i);
		    html = match ? match[1] : html;
		    $j("#entry-content").val(html);
	    }); 
	});
	
	function cancelPost(){
		window.location = "journal.form";
	}
	
</script>