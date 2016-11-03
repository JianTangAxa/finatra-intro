angular.module('todoApp',[]).
config([ '$httpProvider', function($httpProvider) {
  delete $httpProvider.defaults.headers.post['Content-Type']
}]);