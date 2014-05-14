var app = angular.module('rentApp', ['ngRoute', 'ngGrid', 'ui.bootstrap']);

app.controller('newTenantController',
function newTenantController($scope, $http) {


    // Form submit handler.
    $scope.submit = function(form) {
        // Trigger validation flag.
        $scope.submitted = true;
        // If form is invalid, return and let AngularJS show validation errors.
        if (form.$invalid) {
            return;
        }
        $http({
                    url: 'resources/new_tenant',
                    method: 'POST',
                    params: {
                        firstName: $scope.firstName,
                        sortFields: $scope.lastName
                    }
                }).success(function (data) {

                })}
       });




app.controller('tenantsController',
function tenantsController($scope, $http) {
    // Makes the REST request to get the data to populate the grid.
    $scope.refreshGrid = function (page) {
        $http({
            url: 'resources/tenants',
            method: 'GET',
            params: {
                page: page,
                sortFields: $scope.sortInfo.fields[0],
                sortDirections: $scope.sortInfo.directions[0]
            }
        }).success(function (data) {
            $scope.tenants = data;
        });
    };

    // Do something when the grid is sorted.
    // The grid throws the ngGridEventSorted that gets picked up here and assigns the sortInfo to the scope.
    // This will allow to watch the sortInfo in the scope for changed and refresh the grid.
    $scope.$on('ngGridEventSorted', function (event, sortInfo) {
        $scope.sortInfo = sortInfo;
    });

    // Watch the sortInfo variable. If changes are detected than we need to refresh the grid.
    // This also works for the first page access, since we assign the initial sorting in the initialize section.
    $scope.$watch('sortInfo', function () {
        $scope.refreshGrid($scope.tenants.currentPage);
    }, true);

    // Initialize required information: sorting, the first page to show and the grid options.
    $scope.sortInfo = {fields: ['lastName'], directions: ['asc']};
    $scope.tenants = {currentPage : 1};
    $scope.gridOptions = {
        data: 'tenants.list',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,
        columnDefs: [
            {field:'lastName', displayName:'Last name'},
            {field:'firstName', displayName:'First name'}
        ]

    };
});

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/tenants', {
        templateUrl: 'partials/tenant-list.html',
        controller: 'tenantsController'
      }).
       when('/new-tenant', {
              templateUrl: 'partials/new-tenant.html',
              controller: 'newTenantController'
            }).
      otherwise({
        redirectTo: '/tenants'
      });
  }]);


function HeaderController($scope, $location)
{
      $scope.isActive = function (viewLocation) {
          return viewLocation === $location.path();
      };
}

