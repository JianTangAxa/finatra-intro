angular.module('todoApp').
controller("todoController", ['$scope', '$http', function($scope, $http) {
   $scope.todoList = [];
   $scope.newTodo = {};

   function refreshTodoList() {
       $http.get('http://localhost:8888/api/todos').then(function success(response){
           $scope.todoList=response.data
       });
   }

   refreshTodoList();

   $scope.createNewTodo = function() {
       console.log($scope.newTodo)
       $http.post('http://localhost:8888/api/todos', $scope.newTodo, {headers: {
                                                                        'Content-Type': "application/x-www-form-urlencoded"
                                                                      }}).then(function success(response){
           refreshTodoList();
       });
   }
}]);