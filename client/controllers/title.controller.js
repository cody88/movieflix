(function() {
    'use strict';

    angular
        .module('movieflix')
        .controller('TitleController', TitleController);

    TitleController.$inject = ['titleService', '$scope', '$routeParams', '$q',  'orderByFilter'];
    //TitleController.hasOwnProperty(titles);

    function TitleController(titleService, $scope, $routeParams, $q, orderBy) {
        var titleVm = this;

        titleVm.titles = {};
        titleVm.sortoption = null;
        titleVm.loadData = loadData;
        titleVm.populateGrid = populateGrid;
        titleVm.toggleDisabled = toggleDisabled;
        titleVm.mySort = mySort;
        titleVm.myFilterF = myFilterF;

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
            titleVm.loadData().then(function(response){
                $scope.titles = titleVm.titles;
                populateGridCore();
            });
        }

        function populateGridCore() {
            var nrows, ncols;
            for(nrows=0; nrows<=$scope.titles.length%4; nrows++) {
                var newrow = document.createElement("tr");
                for (ncols = 0; ncols < 4 && (4*nrows+ncols) < $scope.titles.length; ncols++) {
                    var cell = document.createElement("td");
                    var lin = document.createElement("a");
                    lin.href = 'details.html?id='+(4*nrows+ncols);
                    cell.appendChild(lin);
                    var imag = document.createElement("img");
                    imag.className = 'catalogimage';
                    imag.src = $scope.titles[4*nrows + ncols].posterLink;
                    var titl = document.createElement("div");
                    titl.id = "cattitlename";
                    titl.innerText = $scope.titles[4*nrows + ncols].titleName;
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
                for(; ncols<4; ncols++) {
                    var cell = document.createElement("td");
                    newrow.appendChild(cell);
                }
                document.getElementById('catalogtable').appendChild(newrow);
            }
        }

        function toggleDisabled(field) {
            var state = document.getElementById(field).disabled;
            document.getElementById(field).disabled = !state;
            if(state == false) {
                $scope.titles = orderBy(titleVm.titles, 'titleId');
                document.getElementById('catalogtable').innerHTML = "";
                populateGridCore();
            }
        }

        function mySort() {
            $scope.titles = {};
            switch(titleVm.sortoption) {
                case 'sortmoviesimdb':
                    for(var i=0; i<titleVm.titles.length; i++) {
                        var title = titleVm.titles[i];
                        if(!title.type.match(/tv/i)) {
                            if($scope.titles.length > 0)
                                $scope.titles.splice.apply($scope.titles, [0, 0].concat(title));
                            else
                                $scope.titles = [title];
                        }
                    }
                    $scope.titles = orderBy($scope.titles, 'imdbRating', true);
                    break;
                case 'sorttvimdb':
                        for(var i=0; i<titleVm.titles.length; i++) {
                        var title = titleVm.titles[i];
                        if(title.type.match(/tv/i)) {
                            if($scope.titles.length > 0)
                                $scope.titles.splice.apply($scope.titles, [0, 0].concat(title));
                            else
                                $scope.titles = [title];
                        }
                    }
                    $scope.titles = orderBy($scope.titles, 'imdbRating', true);
                    break;
                case 'sortbothimdb': 
                    $scope.titles = orderBy(titleVm.titles, 'imdbRating', true);
                    break;
                case 'sortbothyearrelease': 
                    $scope.titles = orderBy(titleVm.titles, 'releaseDate', true);
                    break;
                case 'sortbothvotes': 
                    $scope.titles = orderBy(titleVm.titles, 'imdbVotes', true);
                    break;
                default: 
                    $scope.titles = orderBy(titleVm.titles, 'titleId', true);
            }
            document.getElementById('catalogtable').innerHTML = "";
            populateGridCore();
        }

        function myFilterF() {
            $scope.titles = {};
            var filtered = false;
            for(var i=0; i<titleVm.titles.length; i++) {
                var toInlclude = true;
                if(document.getElementById('yearfilteroption').checked) {
                    filtered = true;
                    var year =  document.getElementById('yearfilteroptiontext').value;
                    if(titleVm.titles[i].year != year)
                        toInlclude = false;
                }
                if(toInlclude && document.getElementById('genrefilteroption').checked) {
                    filtered = true;
                    var gens = document.getElementById('genrefilteroptiontext').value.split(",");
                    var genres = titleVm.titles[i].genre.join(",").toUpperCase();
                    for(var j=0; j<gens.length; j++) {
                        if(genres.indexOf(gens[j].trim().toUpperCase()) != -1)
                            break;
                    }
                    toInlclude = (j == gens.length)? false : true;
                }
                
                if(toInlclude) {
                    if($scope.titles.length > 0)
                        $scope.titles.splice.apply($scope.titles, [0, 0].concat(titleVm.titles[i]));
                    else
                        $scope.titles = [titleVm.titles[i]];
                }
            }
            if(!filtered)
                $scope.titles = titleVm.titles;
            document.getElementById('catalogtable').innerHTML = "";
            populateGridCore();
        }
    }
})();