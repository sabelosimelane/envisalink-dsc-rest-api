function gaTracker(id){
  $.getScript('//www.google-analytics.com/analytics.js'); // jQuery shortcut
  window.ga=window.ga||function(){(ga.q=ga.q||[]).push(arguments)};ga.l=+new Date;
  ga('create', id, 'auto');
  ga('send', 'pageview');
}

function gaTrack(path, title) {
	if (window.location.href.indexOf('localhost') !== -1 ){
		return;
	}
	
	ga('set', { page: path, title: title });
	ga('send', 'pageview');
}