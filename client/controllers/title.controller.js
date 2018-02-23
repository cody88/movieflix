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
        titleVm.titleIndex = -1;

        function loadData() {
            console.log('in TitleController.loadData()');

            var defer = $q.defer();

            /*userListVm.sorter = {
                sortBy: 'firstName',
                sortOrder: false
            };*/

            if(titleService.titles == null) {
                titleService.getCatalog(0) //$routeParams.id)
                .then(function(data) {
                    titleVm.titles = data;
                    defer.resolve();
                })
                .catch(function(data) {
                    console.log(data);
                    defer.reject();
                });
            } else {
                titleVm.titles = titleService.titles;
            }
            console.log(titleVm.titles.toSource());

            return defer.promise;
        }

        function populateGrid(toPopulate) {
            //history.pushState("", document.title, window.location.pathname + window.location.search);
            titleVm.loadData().then(function(response){
                $scope.titles = titleVm.titles;
                if(toPopulate == true)
                    populateGridCore();
                else {
                    var search = decodeURIComponent(window.location.href.slice(window.location.href.indexOf('?')+1));
                    var params = search.split('&');
                    for(var i=0; i<params.length; i++) {
                        var keyVal = params[i].split('=');
                        console.log(keyVal.toSource());
                        if(keyVal[0] == 'id') {
                            titleVm.titleIndex = keyVal[1];
                            break;
                        }
                    }
                    // Housekeeping for the details (and edittitle) page
                    var rDate = new Date(titleVm.titles[titleVm.titleIndex].releaseDate);
                    titleVm.titles[titleVm.titleIndex].releaseDate = rDate.getDate()+"/"+(rDate.getMonth()+1)+"/"+rDate.getFullYear();
                    titleVm.titles[titleVm.titleIndex].releaseDateET = rDate.getFullYear()+"-"
                                                                        +(rDate.getMonth()<9 ? '0'+(rDate.getMonth()+1) : (rDate.getMonth()+1))+"-"
                                                                        +(rDate.getDate()<10 ? '0'+rDate.getDate() : rDate.getDate());
                    console.log(titleVm.titles[titleVm.titleIndex].toSource());
                    // Housekeeping for the details page
                    var nHours = Math.floor(titleVm.titles[titleVm.titleIndex].runtimeInMinutes / 60);
                    var nMins = titleVm.titles[titleVm.titleIndex].runtimeInMinutes % 60;
                    if(nHours == 0)
                        titleVm.titles[titleVm.titleIndex].runtime = titleVm.titles[titleVm.titleIndex].runtimeInMinutes+" mins";
                    else {
                        titleVm.titles[titleVm.titleIndex].runtime = nHours;
                        if(nHours == 1)
                            titleVm.titles[titleVm.titleIndex].runtime += " hour ";
                        else
                            titleVm.titles[titleVm.titleIndex].runtime += " hours ";
                        titleVm.titles[titleVm.titleIndex].runtime += (nMins+" mins");
                    }
                    // Housekeeping for the details page
                    titleVm.titles[titleVm.titleIndex].genre = titleVm.titles[titleVm.titleIndex].genre.join(',');
                    titleVm.titles[titleVm.titleIndex].directors = titleVm.titles[titleVm.titleIndex].directors.join(',');
                    titleVm.titles[titleVm.titleIndex].writers = titleVm.titles[titleVm.titleIndex].writers.join(',');
                    titleVm.titles[titleVm.titleIndex].actors = titleVm.titles[titleVm.titleIndex].actors.join(',');
                    titleVm.titles[titleVm.titleIndex].languages = titleVm.titles[titleVm.titleIndex].languages.join(',');
                    // Housekeeping for the details page
                    titleVm.titles[titleVm.titleIndex].awardsLine = "";
                    if(titleVm.titles[titleVm.titleIndex].primaryAwardWon == true)
                        titleVm.titles[titleVm.titleIndex].awardsLine += "Won ";
                    else
                        titleVm.titles[titleVm.titleIndex].awardsLine += "Nominated for ";
                    titleVm.titles[titleVm.titleIndex].awardsLine += (
                        titleVm.titles[titleVm.titleIndex].primaryAwardCount + " " +
                        titleVm.titles[titleVm.titleIndex].primaryAward + "s. Another " +
                        titleVm.titles[titleVm.titleIndex].otherWins + " wins & " +
                        titleVm.titles[titleVm.titleIndex].otherNominations + " nominations."
                    );

                    // Housekeeping for the details page
                    var link = document.getElementById("EditTitleLink");
                    if(link != null)
                        link.href = "edittitle.html?id="+titleVm.titleIndex;
                }
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
                    titl.innerText = $scope.titles[4*nrows+ncols].titleName;
                    lin.appendChild(imag);
                    lin.appendChild(titl);
                    var editButton = document.createElement("button");
                    editButton.id = "edittitlebutton";
                    editButton.innerText = "Edit";
                    editButton.addEventListener("click", function() {
                        dsds
                    }, false);
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
                if(toInlclude && document.getElementById('namefilteroption').checked) {
                    filtered = true;
                    var name =  document.getElementById('namefilteroptiontext').value;
                    if(titleVm.titles[i].titleName.toUpperCase().indexOf(name.trim().toUpperCase()) == -1)
                        toInlclude = false;
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