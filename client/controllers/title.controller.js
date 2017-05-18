(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('TitleController', TitleController);

    TitleController.$inject = ['titleService', '$routeParams', '$q'];
    //TitleController.hasOwnProperty(titles);

    function TitleController(titleService, $routeParams, $q) {
        var titleVm = this;

        titleVm.titles = {};
        titleVm.loadData = loadData;
        titleVm.populateGrid = populateGrid;
        //init();

        function loadData() {
            console.log('in TitleController.loadData()');

            var defer = $q.defer();

            /*userListVm.sorter = {
                sortBy: 'firstName',
                sortOrder: false
            };*/


            titleService.getCatalog(0) //$routeParams.id)
                .then(function(data) {
                    titleVm.titles = data;
                    defer.resolve();
                })
                .catch(function(data) {
                    console.log(data);
                    defer.reject();
                });

            return defer.promise;
        }

        function populateGrid() {
            //console.log(titleVm.toSource());
            titleVm.loadData().then(function(response){
                var nrows, ncols;
                for(nrows=0; nrows<=titleVm.titles.length%4; nrows++) {
                    var newrow = document.createElement("tr");
                    for (ncols = 0; ncols < 4 && (4*nrows+ncols) < titleVm.titles.length; ncols++) {
                        var cell = document.createElement("td");
                        var lin = document.createElement("a");
                        lin.href = 'details.html?id='+(4*nrows+ncols);
                        cell.appendChild(lin);
                        var imag = document.createElement("img");
                        imag.className = 'catalogimage';
                        imag.src = titleVm.titles[4*nrows + ncols].posterLink;
                        var titl = document.createElement("div");
                        titl.id = "cattitlename";
                        titl.innerText = titleVm.titles[4*nrows + ncols].titleName;
                        lin.appendChild(imag);
                        lin.appendChild(titl);
                        var editButton = document.createElement("button");
                        editButton.id = "edittitlebutton";
                        editButton.innerText = "Edit";
                        var deleteButton = document.createElement("button");
                        deleteButton.id = "deletetitlebutton";
                        deleteButton.innerText = "Delete";
                        cell.appendChild(editButton);
                        cell.appendChild(deleteButton);

                        newrow.appendChild(cell);
                    }
                    document.getElementById('catalogtable').appendChild(newrow);
                }
            });
        }
    }
})();