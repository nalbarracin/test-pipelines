$(document).ready(function () {
	
	var path = window.location.pathname;	
	
	if ($(location).attr('href').includes("proyectos")) {
		
		$('.btn-menu').removeClass('active');
		$('.btn-proyectos').addClass('active');
		
		
	} else if ($(location).attr('href').includes("desarrolladores")) {

		$('.btn-menu').removeClass('active');
		$('.btn-usuarios').addClass('active');
		
	} else if ($(location).attr('href').includes("permisos")) {
		
		$('.btn-menu').removeClass('active');
		$('.btn-permisos').addClass('active');
		
	} else if ($(location).attr('href').includes("consultas")) {
		
		$('.btn-menu').removeClass('active');
		$('.btn-consultas').addClass('active');
		
	}
    
});