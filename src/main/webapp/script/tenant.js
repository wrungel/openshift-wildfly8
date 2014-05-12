var app = angular.module('tenants', ['ngGrid', 'ui.bootstrap']);
// Create a controller with name tenantsList to bind to the html page.
app.controller('tenantsList', function ($scope, $http) {
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
        sortInfo: $scope.sortInfo
    };
});