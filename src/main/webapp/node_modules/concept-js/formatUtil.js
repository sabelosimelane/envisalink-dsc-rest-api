function FormatUtil(){

	this.isInt = function(value) {
		return !isNaN(parseFloat(value)) && isFinite(value);
	}
	
	this.formatAmounts = function(div, dropDecimal){
		var formatUtil = new FormatUtil();
		$(div).find(".amount").each(function(){
			var rawValue = formatUtil.undoFormatting($(this).html());

			var formmattedVal = formatUtil.formatCurrency(rawValue, dropDecimal);
			
			$(this).html('R '+formmattedVal);
		});
	}
	
	this.undoFormatting = function(string){
		
		var temp = string.split(',').join('');
		temp = temp.split('R').join('');
		
		if (!new FormatUtil().isInt(temp.trim())){
			return 0;
		}
		
		return temp.trim();
	}
	
	this.calculateTotal = function(div){
		var total = 0;
		$(div).find(".amount").each(function(){
			if ($(this).html() != ""){
				total += parseFloat($(this).html());
			}
		});
		
		$(div).find(".totalRow .amount").html(total);
	}
	
	this.fomartTable = function(table){
		var formatUtil = new FormatUtil();
		$(table).find(".amount.income").each(function(){
			
			var amount = parseFloat($(this).html());
			if (amount < 0){
				var row = $(this).parent();
				$(row).find('.income').html('');
				$(row).find('.income').removeClass('amount');
				$(row).find('.expense').html('R '+formatUtil.formatCurrency(amount * -1));
			} 
		});
		
		$(table).find(".amount.expense").each(function(){
			
			var amount = parseFloat($(this).html());
			if (amount > 0){
				var row = $(this).parent();
				$(row).find('.expense').html('');
				$(row).find('.expense').removeClass('amount');
				$(row).find('.income').html('R '+formatUtil.formatCurrency(amount));
			} 
		});
		
		$(table).find(".amount.balance").each(function(){
			var formmattedVal = formatUtil.formatCurrency($(this).html());
			$(this).html('R '+formmattedVal);
		});
		
		formatUtil.calculateTotal(table);
	}
	
	this.formatCurrency  = function(n, dropDecimal) {
		var isNegativeVal = false;
		if (parseFloat(n) < 0){
			isNegativeVal = true;
			n = n * -1;
		}
		
		var formatUtil = new FormatUtil();
		
		if (!isFinite(n)) {
			return n;
		}
		
		if (!dropDecimal){
			n = parseFloat(n).toFixed(2);
		} else  {
			n = parseInt(n);
		}
		
		if (n == 0) return '-';
		
		var s = "" + n, abs = Math.abs(n), _, i;
		if (abs >= 1000) {
			_ = ("" + abs).split(/\./);
			i = _[0].length % 3 || 3;
	
			_[0] = s.slice(0, i + (n < 0)) + _[0].slice(i).replace(/(\d{3})/g, ',$1');
	
			s = _.join('.');
		}
		
		var temp = s.split(/\./);
		if (temp[1] != null && temp[1].length == 1){
			s = s+'0';
		}
		
		if (isNegativeVal){
			s = '-'+s;
		}
		
		if (n > 999999){
			s = formatUtil.formatCurrency(n/1000000);
			if (s.substring(s.length-1, s.length) == '0'){
				s = s.slice(0,-1);
			}
			s = s + 'M';
		}
		
		return s;
	}

	this.shortenString = function(inputString, size){
		if(inputString.length > size+1) {
			inputString = inputString.substring(0,size)+"...";
		}
		return inputString;
	}
	
	this.removeLink = function(anchor){
		var text = $(anchor).html();
		$(anchor).parent().html(text);
	}
}