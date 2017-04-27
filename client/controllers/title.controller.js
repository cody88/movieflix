(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('TitleController', TitleController);

    TitleController.$inject = ['titleService'];

    function TitleController(titleService) {
        var titleVm = this;

        init();

        function init() {
            console.log('in TitleController');

            /*userListVm.sorter = {
                sortBy: 'firstName',
                sortOrder: false
            };*/

            titleService.getCatalog(0)
                .then(function(data) {
                    titleVm.users = data;
                })
                .catch(function(error) {
                    console.log(error);
                });
        }
    }
})();