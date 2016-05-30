'use strict';

var Company = angular.module('company', ['ngRoute', 'ngResource', 'ui.bootstrap', 'ui.grid', 'ui.grid.selection'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/home', {
                name: "Employees List",
                templateUrl: 'project/app/views/employee_list.html',
                controller: "EmployeeListController"
            })
            .otherwise({
                redirectTo: 'home'
            });
    }
);