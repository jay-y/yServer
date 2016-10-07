<%@ page contentType="text/html;charset=UTF-8"%>
<!-- ui-dialog -->
<div id="dialog_simple" title="提示">
	<div id="dialog_simple_content"></div>
</div>
<!-- /ui-dialog -->
<!-- modal -->
<div class="modal fade" id="remoteModal" tabindex="-1" role="dialog" aria-labelledby="remoteModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		</div>
	</div>
</div>
<!-- /modal -->
<script type="text/javascript">
(function($,win) {
	var dialog = {};
	
	dialog.init = function(content,title,confirmCall,cancelCall){
		$('#dialog_simple_content').html(content);
		/*
		 * CONVERT DIALOG TITLE TO HTML
		 * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
		 */
		$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
			_title : function(title) {
				if (!this.options.title) {
					title.html("&#160;");
				} else {
					title.html(this.options.title);
				}
			}
		}));
		$('#dialog_simple').dialog({
			autoOpen : false,
			width : 600,
			resizable : false,
			modal : true,
			title : title == null || title == undefined || title ==""?"<div class='widget-header'><h4><i class='fa fa-info txt-color-orangeDark'></i> Message Dialog</h4></div>":title,
			buttons : [{
				html : "<i class='fa fa-check'></i>&nbsp; OK",
				"class" : "btn btn-success",
				click : function() {
					if(confirmCall != null && confirmCall != undefined){
						confirmCall();
					}
					try{ 
						$(this).dialog("close");
					}catch (e){
						console.log("Cannot call methods on dialog prior to initialization.");
					}
				}
			}, {
				html : "<i class='fa fa-times'></i>&nbsp; CANCEL",
				"class" : "btn btn-default",
				click : function() {
					if(cancelCall != null && cancelCall != undefined){
						cancelCall();
					}
					try{ 
						$(this).dialog("close");
					}catch (e){
						console.log("Cannot call methods on dialog prior to initialization.");
					}
				}
			}]
		});
	}
	
	dialog.info = function(msg,title,confirmCall,cancelCall){
		msg = msg != null && msg != undefined && msg !=""?msg:"";
		title = title != null && title != undefined && title !=""?title:"";
		dialog.init("<p><h3>"+msg+"</h3></p>"
				,"<div class='widget-header'><h4><i class='fa fa-info txt-color-green'></i> "+title+"</h4></div>"
				,confirmCall,cancelCall);
		$('#dialog_simple').dialog('open');
	}
	
	dialog.warn = function(msg,title,confirmCall,cancelCall){
		msg = msg != null && msg != undefined && msg !=""?msg:"";
		title = title != null && title != undefined && title !=""?title:"";
		dialog.init("<p><h3>"+msg+"</h3></p>"
				,"<div class='widget-header'><h4><i class='fa fa-warning txt-color-orangeDark'></i> "+title+"</h4></div>"
				,confirmCall,cancelCall);
		$('#dialog_simple').dialog('open');
	}
	
	dialog.error = function(msg,title,confirmCall,cancelCall){
		msg = msg != null && msg != undefined && msg !=""?msg:"";
		title = title != null && title != undefined && title !=""?title:"";
		dialog.init("<p><h3>"+msg+"</h3></p>"
				,"<div class='widget-header'><h4><i class='fa fa-warning txt-color-red'></i> "+title+"</h4></div>"
				,confirmCall,cancelCall);
		$('#dialog_simple').dialog('open');
	}
	
	win.dialog = dialog;
	dialog.init();
}(jQuery,window));
</script>