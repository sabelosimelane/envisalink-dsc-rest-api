function Page() {
		
	this.pageTitle = "";
	
	this.subTitle = "";
}

Page.prototype.confirm = function(succesCB, props){
	
	props.effect = 'flipInY';
	
	nav.loadJSP('includes/confirm.jsp', function(resp){
		page.showModal(resp, function(){
			
			if (props && props.title){
				$("#confirmDlg .title").html(props.title);
			}
			
			if (props && props.message){
				$("#confirmDlg .confirmMsg").html(props.message);
			}
			
			if (props && props.icon){
				$("#confirmDlg #confirmIcon").removeClass('fa-warning').addClass(props.icon);
			}
			
			if (props && props.color){
				$("#confirmDlg #confirmIcon").removeClass('text-warning').addClass(props.color);
			}
			
			$("#confirmDlg #saveBtn").click(function(){
				succesCB();
			});
			
			$("#theModal .modal-content").addClass(props.effect);
		}, props);
	});
	

}

Page.prototype.showModal = function(content, callback, props){
	$("#theModal .modal-content").html(content);
	
	if (props && props.size && props.size == 'large'){
		$("#theModal .modal-dialog").addClass('modal-lg');
		$("#theModal .modal-dialog").removeClass('modal-sm');
	} else if (props && props.size && props.size == 'small'){
		$("#theModal .modal-dialog").addClass('modal-sm');
		$("#theModal .modal-dialog").removeClass('modal-lg');
	} else {
		$("#theModal .modal-dialog").removeClass('modal-lg');
		$("#theModal .modal-dialog").removeClass('modal-sm');
	}
	
	if (props && props.effect){
		$("#theModal .modal-content").removeClass('bounceInRight');
		$("#theModal .modal-content").addClass('flipInY');
	} else {
		$("#theModal .modal-content").removeClass('flipInY');
		$("#theModal .modal-content").removeClass('fadeIn');
		$("#theModal .modal-content").addClass('bounceInRight');
	}
	
	var properties = {};
	
	if (props && props.closable){
		if (props.closable === 'false'){
			properties.backdrop = 'static';
			properties.keyboard = false;
			$("#theModal .close-link").hide();
		}
	}
	
	if (props && props.title){
		$("#theModal .title").html(props.title);
	}
	
	if (props && props.heading){
		$("#theModal .heading").html(props.heading);
	}
	
	$("#theModal").modal(properties);
	
	if (typeof(callback) == "function") {
		callback();
	}
};


/*
 * props.tile = ''
 * props.heading = ''
 * props.size {'small', 'large'}
 * props.effect {'flip', 'bounce'}
*/
Page.prototype.showModal2 = function(content, callback, props){
	$("#theModal2 .modal-content").html(content);
	
	if (props && props.size && props.size == 'large'){
		$("#theModal2 .modal-dialog").addClass('modal-lg');
		$("#theModal2 .modal-dialog").removeClass('modal-sm');
	} else if (props && props.size && props.size == 'small'){
		$("#theModal2 .modal-dialog").addClass('modal-sm');
		$("#theModal2 .modal-dialog").removeClass('modal-lg');
	} else {
		$("#theModal2 .modal-dialog").removeClass('modal-lg');
		$("#theModal2 .modal-dialog").removeClass('modal-sm');
	}
	
	if (props && props.effect && props.effect == 'flip'){
		$("#theModal2 .modal-content").removeClass('bounceInRight');
		$("#theModal2 .modal-content").addClass('flipInY');
	} else {
		$("#theModal2 .modal-content").removeClass('flipInY');
		$("#theModal2 .modal-content").removeClass('fadeIn');
		$("#theModal2 .modal-content").addClass('bounceInRight');
	}
	
	if (props && props.title){
		$("#theModal2 .title").html(props.title);
	}
	
	if (props && props.heading){
		$("#theModal2 .heading").html(props.heading);
	}
	
	$("#theModal2").modal('show');
	
	if (typeof(callback) == "function") {
		callback();
	}
};

Page.prototype.hideModal = function(callback){
	$("#theModal").modal('hide');
	$("#theModal2").modal('hide');
	if (typeof(callback) == "function") {
		callback();
	}
};

Page.prototype.gotoURI = function (uri){
	window.location = uri;
};

Page.prototype.load = function(navParam, parms, callback){
	var title = this.pageTitle;
	var subtitle = this.subTitle;
	
	nav.call(navParam, parms, function(resp){
		$("#stage").html(resp);
		$("#pageTitle").html(title);
		$("#subTitle").html(subtitle);
		if (typeof(callback) == "function") {
			callback(resp);
		}
	});
};

Page.prototype.init = function(config){
	this.initialize($("body"), config);
}

Page.prototype.initialize = function(element, config){
	if (!element){
		element = $("body");
	}
	
	this.checkbox(element);
	this.dropdown(element, config);
	this.jsswitch(element, config);
	this.datepicker(element, config);
	
	$(element).find('input[type=checkbox]').each(function(){
		$(this).val(true);
	});
}

Page.prototype.datepicker = function(element, config){
	if (!element){
		element = $("body");
	}
	
	if (config && config.datetimepicker) config = config.datetimepicker;
	if (!config) config = {};
	
	var configuration = {
			inline: config.inline || false,
			keepOpen: config.keepOpen || false,
	        format: config.format || 'YYYY-MM-DD',
	        sideBySide: config.sideBySide || false,
	        stepping: 15,
	        minDate: config.minDate || false,
	        maxDate: config.maxDate || false,
	        showTodayButton: config.showTodayButton || true
	    };
		
	$(element).find('.input-group.date').datetimepicker(configuration);	
}

Page.prototype.jsswitch = function(element, config){
	if (!element){
		element = $("body");
	}
	
	if (config && config.toggle) config = config.toggle;
	if (!config) config = {};
		
	var configuration = { color: config.color || '#1AB394', disabled: !config.disabled};
	
	var elem = document.querySelector('.js-switch');
	$(element).find('.js-switch').each(function(){
		new Switchery($(this)[0], configuration);
	});

};

/** DROPDOWN ***/
Page.prototype.dropdown = function(element, config){
	if (!element){
		element = $("body");
	}

	if (config && config.dropdown) config = config.dropdown;
	if (!config) config = {};
		
	configuration = {
            allow_single_deselect: config.allow_single_deselect || true,
            //disable_search_threshold:10,
            no_results_text: config.no_results_text || 'Oops, nothing found!',
            width: config.width || '100%',
            search_contains: config.search_contains || true
        };

	$(element).find('.chosen-select').each(function(){
		
		if ($(this).attr('value')){
			$(this).val($(this).attr('value'));
		}
		$(this).chosen(configuration);
	});
}

Page.prototype.addoption = function(element, value, description){
	$(element).append('<option value="'+ value +'">'+ description +'</option>');
	$(element).trigger("chosen:updated");
}

Page.prototype.setval = function(element, value){
	$(element).val(value).trigger("chosen:updated");
}

/*** END DROPDOWN ****/

Page.prototype.checkbox = function(element){
	if (!element){
		element = $("body");
	}
	
	$(element).find('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
    });
};

Page.prototype.scrollTo = function(elem){
	var container = $('html,body');
	var scrollTo = elem;
 	container.animate({
 	    scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()
 	});
};

Page.prototype.scrollDivTo = function(elem, container){
	var scrollTo = elem;
 	container.animate({
 	    scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()
 	});
};

Page.prototype.serializeForm = function(form){
	
	var parm = {};
	
	//radiobutton
	$(form).find("input[type='radio']:checked").each(function(){
		parm[this.name] = $(this).val();
	});
	
	$(form).find('input,textarea').not("input[type='radio']").each(function(){
		if (this.name){
			parm[this.name] = $(this).val();
		}
	});
	
	return parm;
};