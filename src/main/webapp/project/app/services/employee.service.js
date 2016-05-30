'use strict';

Company.factory('EmployeeListService', function($resource){
    return $resource('company/employees/:id');
});