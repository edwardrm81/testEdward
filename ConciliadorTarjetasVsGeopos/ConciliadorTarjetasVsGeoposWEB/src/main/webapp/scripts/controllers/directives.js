var app = angular.module('app.directives', [ 'ui.bootstrap' ]);

app.directive('caDatepicker', [function(dateFormat) {
	  return {
	    restrict: 'A',
	    require : 'ngModel',
	    link: function($scope, element, attributes, ngModelCtrl) {
	    
	      element.datepicker({
	        dateFormat: attributes.caDatepicker,
	        onSelect: function(date) {
	        	$scope.$apply(function () {
                    ngModelCtrl.$setViewValue(date);
                });
	        }
	      });
	    }
	  };
	}]
);