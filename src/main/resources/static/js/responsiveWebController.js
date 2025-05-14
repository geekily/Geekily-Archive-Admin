function ResponsiveWebController() {
}

ResponsiveWebController.prototype.initialize = function () {

}

ResponsiveWebController.prototype.resizeOptionLabel = function(value){
	$('.opt-label').addClass('col-xxl-' + value);
}

ResponsiveWebController.prototype.addAndRemoveDflexAt = function(deflexRemovalPosition){
	this.addAndRemoveDflex(deflexRemovalPosition);
	$(window).resize(function() {
		ResponsiveWebController.prototype.addAndRemoveDflex(deflexRemovalPosition);
	});
}

ResponsiveWebController.prototype.addAndRemoveDflex = function(deflexRemovalPosition){
	let windowWidth = $(window).width();
	let $optContent = $('.opt-content');

	if (windowWidth > deflexRemovalPosition) {
		$optContent.addClass('d-flex');
	} else {
		$optContent.removeClass('d-flex')
	}
}

ResponsiveWebController.prototype.print = function(){
	console.log(this.deflexRemovePosition)
}