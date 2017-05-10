(function() {
    'use strict';

// Declare app level module which depends on views, and components
    angular.module('movieflix', ['ngMessages', 'ngRoute']).
    config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.otherwise({redirectTo: '/index.html'});
    }]).config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.common = {
            'ah': 'uni',
            'Accept': 'application/json'
        };
    }]);

    moduleConfig.$inject = ['$routeProvider'];

    function moduleConfig($routeProvider) {
        $routeProvider
            .when('/catalog/:fromCount', {
                templateUrl: 'user-list.tmpl.html',
                controller: 'TitleController',
                controllerAs: 'titleVm'
            })
            .when('/catalog/topimdb/:type/:fromCount', {
                templateUrl: 'user-detail.tmpl.html',
                controller: 'TitleController',
                controllerAs: 'titleVm'
            })
            .when('/catalog/filter/:field/:value/:fromCount', {
                templateUrl: 'user-detail.tmpl.html',
                controller: 'TitleController',
                controllerAs: 'titleVm'
            })
            .when('/catalog/sort/:order/:field/:fromCount', {
                templateUrl: 'user-detail.tmpl.html',
                controller: 'TitleController',
                controllerAs: 'titleVm'
            })
            .when('/title/:id/details', {
                templateUrl: 'user-detail.tmpl.html',
                controller: 'TitleController',
                controllerAs: 'titleVm'
            })
            .when('/title/add', {
                templateUrl: 'add-user.tmpl.html',
                controller: 'TitleController',
                controllerAs: 'titleVm'
            })
            .when('/title/:id/rate', {
                templateUrl: 'user-detail.tmpl.html',
                controller: 'TitleController',
                controllerAs: 'titleVm'
            })
            .when('/title/:id/review', {
                templateUrl: 'user-detail.tmpl.html',
                controller: 'TitleController',
                controllerAs: 'titleVm'
            })
            .otherwise({
                redirectTo: '/home.html'
            });
    }
})();