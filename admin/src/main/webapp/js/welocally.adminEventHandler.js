function WELOCALLY_AdminEventHandler (cfg) {			
	this.cfg;
		
	this.init = function() {				
		this.cfg = cfg;	
		
		return this;		
	};
	
	
}


WELOCALLY_AdminEventHandler.prototype.publisherDetailsHandler = function(event) {
	jQuery(event.data.dialog).dialog('open');	
	return false;
};

WELOCALLY_AdminEventHandler.prototype.userDeleteHandler = function(event) {
	
	WELOCALLY_AdminEventHandler.prototype.deleteRow(event.data.table);
	
    return false;
    
};

WELOCALLY_AdminEventHandler.prototype.userNewHandler = function(event) {
	jQuery.ajax({
  	  url: event.data.endpoint,
  	  cache: false
    }).done(function( html ) { 	  	
  	  	jQuery(event.data.dialog).html(html);	
  	  jQuery(event.data.dialog).dialog('open');
    });

};

WELOCALLY_AdminEventHandler.prototype.userViewHandler = function(event) {	
	jQuery(event.data.dialog).html(Mustache.render(
			'<div class="wl_loading"><div><img style="margin: 0px auto;" src="{{loadingImage}}"/></div><div>Loading...</div></div>', 
			event.data));
	
	var username = jQuery(event.data.table.$('tr.row_selected').find('td')[0]).html();
	jQuery.ajax({
		url: event.data.geodbUserEndpoint+'/'+username+'.json',
		dataType: "jsonp",
		success: function(data) {
			if(data != null && data.errors != null) {
				var status = jQuery('<div></div>');
				WELOCALLY.ui.setStatus(status,'ERROR:'+WELOCALLY.util.getErrorString(data.errors), 'wl_error', false);
				jQuery(event.data.dialog).html(status);
				jQuery(event.data.dialog).append('<div><a href="">Provision</a>&nbsp;<a href="">Delete</a></div>');
			} else {
				var render = '';
				if(data.indexStatus.exists){				
					render = Mustache.render(
						'<div class="wl_dialog_header">GeoDB User Info For {{username}}</div>'+
						'<div>User was found.</div>'+
						'<div>Documents: {{indexStatus.documents}}</div>'+
						'<div>Deleted: {{indexStatus.deleted}}</div>'+
						'<div>Max: {{indexStatus.max}}</div>'+
						'<div>Store Size: {{indexStatus.storeSize}}</div>', 
						data);
				} else {
					render = Mustache.render(
						'<div class="wl_dialog_header">GeoDB User Info For {{username}}</div>'+
						'<div>User was found.</div>'+
						'<div>Index is missing. <a href="">Provision</a></div>', 
						data);		
				}
		
				jQuery(event.data.dialog).html(render);
						
			}			
		}
	});
	
	jQuery(event.data.dialog).dialog({title: 'User Details for '+username});
	jQuery(event.data.dialog).dialog('open');
};




WELOCALLY_AdminEventHandler.prototype.userEditHandler = function(event) {
	jQuery(event.data.dialog).dialog('open');
};

WELOCALLY_AdminEventHandler.prototype.publisherDeleteHandler = function(event) {
	WELOCALLY_AdminEventHandler.prototype.deleteRow(event.data.table);
    return false;	
};

WELOCALLY_AdminEventHandler.prototype.deleteRow = function(table) {
	var anSelected = table.$('tr.row_selected');
    if ( anSelected.length !== 0 ) {
   	 	table.fnDeleteRow( anSelected[0] );
    }    
    return false;	
};

WELOCALLY_AdminEventHandler.prototype.publisherViewHandler = function(event) {
	jQuery(event.data.dialog).dialog('open');
};

WELOCALLY_AdminEventHandler.prototype.publisherEditHandler = function(event) {
	jQuery(event.data.dialog).dialog('open');
};

