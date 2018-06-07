var app = angular.module('app', [ 'app.directives', 'app.services', 'app.filters' ]);

app.run(function($http) {
  $http.defaults.headers.get = { 'gsec-user-token' : localStorage.getItem('gco_auth_token') }
});

app.controller('apiAppCtrl', [ '$scope', '$http', '$timeout', 'serveData', function ($scope, $http, $timeout, serveData) {
	var vm = this;
	vm.showProcessMsg = 0;

	$scope.getDtoSelect = function (consolidadosDiffDTO) {
  		//alert('CONTROLADOR1:'+ recaudosConsolidadosDiffDTO.fecha + ' ' + recaudosConsolidadosDiffDTO.vrpagadoTarjetas);
  		serveData.set(consolidadosDiffDTO); 
	};
	

	vm.consultar = function(formaPago) {
		
		vm.resultadosConsulta = null;
 		vm.curPage = 0;
 		vm.pageSize = 10;	
		
		$http.get('../resources/utils.properties').then(
						function(response) {

							if( (vm.razonsocialSelected== null || vm.razonsocialSelected.cdrazonsocial==null) 
								&& 
								(vm.tiendaSelected==null || vm.tiendaSelected.cdtienda==null)
							) {
								alert('Error realizando la consulta.' + '\n' + 'Debe ingresar una empresa facturadora o una tienda');
								return null;
							}

							if(vm.fechaInicial == null || vm.fechaInicial == '' || vm.fechaInicial.trim()  == '') {
								alert('Error realizando la consulta.' + '\n' + 'Debe ingresar la fecha inicial');
								return null;
							}

							if(vm.fechaFinal == null || vm.fechaFinal == '' || vm.fechaFinal.trim()  == '') {
								alert('Error realizando la consulta.' + '\n' + 'Debe ingresar la fecha final');
								return null;
							}

							var ruta = '';

							if(formaPago == 'RecaudoTarjeta') {
								ruta = '/ConciliadorTarjetasVsGeopos/rest/conciliador/recaudosConsolidadosDiff';
							} else if(formaPago == 'PrimeraCompra') {
								ruta = '/ConciliadorTarjetasVsGeopos/rest/conciliador/primeraCompraConsolidadosDiff';
							} else {
								alert('Error realizando la consulta.' + '\n' + 'Debe ingresar una forma de pago permitida');
								return null;
							}

							vm.showProcessMsg = 1;

							vm.u = response.data + ruta;
							
							$http({
								method : 'GET',
								url : vm.u,
								params : ({
									'cdrazonsocial' : (vm.razonsocialSelected == null ? null : vm.razonsocialSelected.cdrazonsocial),
									'cdtienda' : (vm.tiendaSelected == null? null : vm.tiendaSelected.cdtienda),
									'fechaInicial' : vm.fechaInicial,
									'fechaFinal' : vm.fechaFinal,
									'mostrarSoloDiferencia' : vm.mostrarSoloDiferencia,
								})
							})

							.success(function(respuesta) {
										vm.showProcessMsg = 0;
										vm.resultadosConsulta = respuesta;
										vm.formaPagoConsultada = vm.formaPago;
							})

							.error(function(data, status, headers, config) {
								vm.showProcessMsg = 0;
								$scope.estadoProceso = "Error al consumir el servicio " + vm.u;
								var myHeaders = angular.toJson(headers());
								var myHeadersArray = myHeaders.split('"');
								var messageAlert = "Error realizando la consulta." + '\n' + (myHeadersArray.length>7? myHeadersArray[7] : '');
								alert(messageAlert);
							});

						});
	};

	vm.numberOfPages = function() {
		return vm.resultadosConsulta!=null? Math.ceil(vm.resultadosConsulta.length / vm.pageSize) : 0;
	};


	vm.enviarEmail = function(formaPago) {
		
		vm.resultadosConsulta = null;
 		vm.curPage = 0;
 		vm.pageSize = 10;

		$http.get('../resources/utils.properties').then(
						function(response) {

							if( (vm.razonsocialSelected== null || vm.razonsocialSelected.cdrazonsocial==null) 
								&& 
								(vm.tiendaSelected==null || vm.tiendaSelected.cdtienda==null)
							) {
								alert('Error enviando el E-Mail.' + '\n' + 'Debe ingresar una empresa facturadora o una tienda');
								return null;
							}

							if(vm.fechaInicial == null || vm.fechaInicial == '' || vm.fechaInicial.trim()  == '') {
								alert('Error enviando el E-Mail.' + '\n' + 'Debe ingresar la fecha inicial');
								return null;
							}

							if(vm.fechaFinal == null || vm.fechaFinal == '' || vm.fechaFinal.trim()  == '') {
								alert('Error enviando el E-Mail.' + '\n' + 'Debe ingresar la fecha final');
								return null;
							}

							var ruta = '';

							if(formaPago == 'RecaudoTarjeta') {
								ruta = '/ConciliadorTarjetasVsGeopos/rest/conciliador/recaudosConsolidadosDiffAndSendEmail';
							} else if(formaPago == 'PrimeraCompra') {
								ruta = '/ConciliadorTarjetasVsGeopos/rest/conciliador/primeraCompraConsolidadosDiffAndSendEmail';
							} else {
								alert('Error enviando el E-Mail.' + '\n' + 'Debe ingresar una forma de pago permitida');
								return null;
							}

							vm.showProcessMsg = 1;

							vm.u = response.data + ruta;
							
							$http({
								method : 'GET',
								url : vm.u,
								params : ({
									'cdrazonsocial' : (vm.razonsocialSelected == null ? null : vm.razonsocialSelected.cdrazonsocial),
									'cdtienda' : (vm.tiendaSelected == null? null : vm.tiendaSelected.cdtienda),
									'fechaInicial' : vm.fechaInicial,
									'fechaFinal' : vm.fechaFinal,
									'mostrarSoloDiferencia' : vm.mostrarSoloDiferencia,
								})
							})

							.success(function(respuesta) {
										vm.showProcessMsg = 0;
										vm.resultadosConsulta = respuesta;
										vm.formaPagoConsultada = vm.formaPago;
							})

							.error(function(data, status, headers, config) {
								vm.showProcessMsg = 0;
								$scope.estadoProceso = "Error al consumir el servicio " + vm.u;
								var myHeaders = angular.toJson(headers());
								var myHeadersArray = myHeaders.split('"');
								var messageAlert = 'Error enviando el E-Mail.' + '\n' + (myHeadersArray.length>7? myHeadersArray[7] : '');
								alert(messageAlert);
							});

						});

	};

	
	vm.getRazonesSocialesList = function() {

		vm.razonesSocialesList = null;

		$http.get('../resources/utils.properties')
				.then(
						function(response) {

							vm.u = response.data
									+ '/ConciliadorTarjetasVsGeopos/rest/conciliador/razonesSociales';
							$http({
								method : 'GET',
								url : vm.u
							})
							
							.success(function(respuesta) {
								vm.razonesSocialesList = respuesta;
							})

							.error(function(data, status, headers, config) {
								$scope.estadoProceso = "Error al consumir el servicio " + vm.u;
								var myHeaders = angular.toJson(headers());
								var myHeadersArray = myHeaders.split('"');
								var messageAlert = 'Error consultando las empresas facturadoras.' + '\n' + (myHeadersArray.length>7? myHeadersArray[7] : '');
								alert(messageAlert);
							});

						}
				);

	};

	vm.getTiendasList = function(nmrazonsocialSelected) {

		vm.tiendasList = null;
		vm.tiendaSelected = null;

		$http.get('../resources/utils.properties')
				.then(
						function(response) {

							vm.u = response.data
									+ '/ConciliadorTarjetasVsGeopos/rest/conciliador/tiendas';
							$http({
								method : 'GET',
								url : vm.u,
								params : ({
									'nmrazonsocialConciliadorGP' : nmrazonsocialSelected
								})
							})

							.success(function(respuesta) {
								vm.tiendasList = respuesta;
							})

							.error(function(data, status, headers, config) {
								$scope.estadoProceso = "Error al consumir el servicio " + vm.u;
								var myHeaders = angular.toJson(headers());
								var myHeadersArray = myHeaders.split('"');
								var messageAlert = 'Error consultando las tiendas.' + '\n' + (myHeadersArray.length>7? myHeadersArray[7] : '');
								alert(messageAlert);
							});

						});

	};

}]);

