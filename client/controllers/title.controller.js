(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('TitleController', TitleController);

    TitleController.$inject = ['titleService', '$routeParams'];

    function TitleController(titleService, $routeParams) {
        var titleVm = this;

        titleVm.loadData = loadData;
        //init();

        function loadData() {
            console.log('in TitleController.loadData()');

            /*userListVm.sorter = {
                sortBy: 'firstName',
                sortOrder: false
            };*/


            titleService.getCatalog(0)//$routeParams.id)
                .then(function(data) {
                    titleVm.titles = data;
                })
                .catch(function(data) {
                    console.log(data);
                });
        }
    }
})();