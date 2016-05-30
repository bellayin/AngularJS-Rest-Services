'use strict';

Company.controller('EmployeeListController', function ($rootScope, $timeout, $route, $scope, EmployeeListService) {

    $scope.name = "List of Employees";

    $('#alert-success').hide();
    $('#alert-warning').hide();

    /* GridOption params*/
    $scope.gridOptions = {
        modifierKeysToMultiSelect: false,
        data: EmployeeListService.query(),
        columnDefs: [
            {name: 'id'},
            {name: 'name'},
            {name: 'lastName'},
            {name: 'age'},
            {name: 'address'},
            {name: 'position'},
            {
                name: 'Delete',
                cellTemplate: '<button class="btn primary" ng-click="grid.appScope.deleteRow(row)">Delete</button>'
            }],
        multiSelect: false,
        selectedItems: [],


        onRegisterApi: function (gridApi) {
            $scope.gridApi = gridApi;
            var selection;
            gridApi.selection.on.rowSelectionChanged($scope, function (rows) {
                if ((gridApi.selection.getSelectedRows()).length > 0) {
                    selection = gridApi.selection.getSelectedRows();
                    $scope.editId = selection[0].id;
                    $scope.editData = [{
                        id: selection[0].id,
                        name: selection[0].name,
                        lastName: selection[0].lastName,
                        age: selection[0].age,
                        address: selection[0].address,
                        position: selection[0].position
                    }]
                }
                else   $scope.editId = null;
            });
        }
    };


    // Calls the rest method to save a employee.
    $scope.createEmployee = function () {
        EmployeeListService.save($scope.employee).$promise.then(
            function () {
                $route.reload();
                $timeout(function () {
                    $('#alert-success').show();
                    $scope.employee = {};
                }, 10);


            },
            function () {
                $('#alert-warning').show();

            });
    };
    // Calls the rest method to save a employee.
    $scope.updateEmployee = function () {
        EmployeeListService.save($scope.edit).$promise.then(
            function () {
                $route.reload();
                $timeout(function () {
                    $('#alert-success').show();
                    $scope.employee = {};
                }, 10);

            })
    };


    $scope.deleteRow = function (row) {

        EmployeeListService.delete({id: row.entity.id}).$promise.then(
            function () {
                var index = $scope.gridOptions.data.indexOf(row.entity);
                $scope.gridOptions.data.splice(index, 1);
                $('#alert-success').show();
            },
            function () {
                // Broadcast the event for a server error.
                $rootScope.$broadcast('error');
            });

    };

    $scope.clearForm = function () {
        $scope.employee = [];
    };
})
;