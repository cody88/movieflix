(function() {
    'use strict';

    angular
        .module('movieflix')
        .service('titleService', titleService);

    titleService.$inject = ['$http', '$q'];

    function titleService($http, $q) {
        var self = this;

        self.getCatalog = getCatalog;
        self.getCatalogTopIMDB = getCatalogTopIMDB;
        self.getCatalogFiltered = getCatalogFiltered;
        self.getCatalogSorted = getCatalogSorted;
        self.getTitleDetails = getTitleDetails;
        self.createTitle = createTitle;
        self.rateTitle = rateTitle;
        self.reviewTitle = reviewTitle;
        self.titles = null;

        function getCatalog(fromCount) {
            return $http.get('http://localhost:8080/moduleapi/api/catalog/' + fromCount)
                .then(successFn, errorFn);
        }

        function getCatalogTopIMDB(type, fromCount) {
            return $http.get('http://localhost:8080/moduleapi/api/catalog/topimdb/' + type + '/' + fromCount)
                .then(successFn, errorFn);
        }

        function getCatalogFiltered(field, value, fromCount) {
            return $http.get('http://localhost:8080/moduleapi/api/catalog/filter/' + field + '/' + value + '/' + fromCount)
                .then(successFn, errorFn);
        }

        function getCatalogSorted(order, field, fromCount) {
            return $http.get('http://localhost:8080/moduleapi/api/catalog/sort/' + order + '/' + field + '/' + fromCount)
                .then(successFn, errorFn);
        }

        function getTitleDetails(id) {
            return $http.get('http://localhost:8080/moduleapi/api/title/' + id + '/details')
                .then(successFn, errorFn);
        }

        function createTitle(title) {
            return $http.post('http://localhost:8080/moduleapi/api/title/add', title)
                .then(successFn, errorFn);
        }

        function rateTitle(starRating) {
            return $http.post('http://localhost:8080/moduleapi/api/title/' + id + '/rate', starRating)
                .then(successFn, errorFn);
        }

        function reviewTitle(review) {
            return $http.post('http://localhost:8080/moduleapi/api/title/' + id + '/review', review)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            self.titles = response.data;
            //console.log(self.titles.toSource());
            return response.data; //RESOLVE
        }

        function errorFn(error) {
            return $q.reject(error); //REJECT
        }
    }
})();