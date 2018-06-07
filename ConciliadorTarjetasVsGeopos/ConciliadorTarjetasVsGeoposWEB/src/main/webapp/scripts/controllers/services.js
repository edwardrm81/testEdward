var app = angular.module('app.services', []);

app.service('serveData', [function () {
		
		var savedData = {}
		function set(data) {
			savedData = data;
		}
	 	
		function get() {
			return savedData;
		}

		return {
			set: set,
			get: get
		}
		
	}]
);