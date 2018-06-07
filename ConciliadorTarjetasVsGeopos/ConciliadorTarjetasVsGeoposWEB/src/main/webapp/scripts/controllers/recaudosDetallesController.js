var app = angular.module('app').controller('RecaudosDetallesCtrl', function ($scope, $rootScope, $http, $modal, serveData) {

  var vm = this;
  $scope.consolidadosDiffDTO = serveData.get();
  $scope.items = ['item1', 'item2', 'item3'];
  $scope.animationsEnabled = true;
  
  $scope.numberOfPagesR = function() {
    return ($rootScope.detallesLista!=null && $rootScope.pageSizeR!=null) ? Math.ceil($rootScope.detallesLista.length / $rootScope.pageSizeR) : 0;
  };
  
  $scope.getRecaudosDetallesList = function(consolidadosDiffDTO) {

    $rootScope.detallesLista = null;
    $rootScope.curPageR = 0;
    $rootScope.pageSizeR = 10;

		$http.get('../resources/utils.properties')
				.then(
						function(response) {

							if( consolidadosDiffDTO.fecha == null || consolidadosDiffDTO.fecha == '' || consolidadosDiffDTO.fecha.trim()  == '') {
								alert('Error consultando los detalles.' + '\n' + 'Debe ingresar la fecha');
								return null;
							}

							if( consolidadosDiffDTO.cdtienda == null || consolidadosDiffDTO.cdtienda == '' || consolidadosDiffDTO.cdtienda.trim()  == '') {
								alert('Error consultando los detalles.' + '\n' + 'Debe ingresar una tienda');
								return null;
							}

							if( consolidadosDiffDTO.nmcaja == null || consolidadosDiffDTO.nmcaja == '' || consolidadosDiffDTO.nmcaja.trim()  == '') {
								alert('Error consultando los detalles.' + '\n' + 'Debe ingresar la caja');
								return null;
							}

							vm.u = response.data
									+ '/ConciliadorTarjetasVsGeopos/rest/conciliador/recaudosDetalles';
							$http({
								method : 'GET',
								url : vm.u,
								params : ({
									'cdtienda' : consolidadosDiffDTO.cdtienda,
									'fecha' : consolidadosDiffDTO.fecha,
									'nmcaja' : consolidadosDiffDTO.nmcaja
								})
							})

							.success(function(respuesta) {
								$rootScope.detallesLista = respuesta;
							})

							.error(function(data, status, headers, config) {
								$scope.estadoProceso = "Error al consumir el servicio " + vm.u;
								var myHeaders = angular.toJson(headers());
								var myHeadersArray = myHeaders.split('"');
								var messageAlert = 'Error consultando los detalles.' + '\n' + (myHeadersArray.length>7? myHeadersArray[7] : '');
								alert(messageAlert);
							});

						});

	}

  $scope.openRecaudosDetalles = function (size) {

    var modalInstance = $modal.open({
      animation: $scope.animationsEnabled,
      templateUrl: './templates/recaudosDetalles.html', //'myTeplateRecaudosDetalles.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {
        items: function () {
          return $scope.items;
        }
      }
    });

    modalInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    }, function () {
      
    });
  };

  $scope.toggleAnimation = function () {
    $scope.animationsEnabled = !$scope.animationsEnabled;
  };

});

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

angular.module('app').controller('ModalInstanceCtrl', function ($scope, $modalInstance, items) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  /*
  $scope.ok = function () {
    $modalInstance.close($scope.selected.item);
  };
  */

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});