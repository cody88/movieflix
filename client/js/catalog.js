
    var nrows, ncols;
    for(nrows=0; nrows<titleVm.titles.length%4; nrows++) {
        var newrow = document.createElement("tr");
        for(ncols=0; ncols<4 && ncols<titleVm.titles.length; ncols++) {
            var cell = document.createElement("td");
            var lin = document.createElement("a");
            lin.href = '"#"';
            cell.appendChild(lin);
            var imag = document.createElement("img");
            img.className = 'catalogimage';
            img.ngSrc = '{{titleVm.titles[4*nrows + ncols].posterLink}}';
            var titl = document.createElement("div");
            titl.id = "cattitlename";
            titl.innerText = "{{titleVm.titles[4*nrows + ncols].titleName}}";
            lin.appendChild(imag); lin.appendChild(titl);
            var editButton = document.createElement("button");
            editButton.id = "edittitlebutton"; editButton.innerText = "Edit";
            var deleteButton = document.createElement("button");
            deleteButton.id = "deletetitlebutton"; deleteButton.innerText = "Delete";
            cell.appendChild(editButton); cell.appendChild(deleteButton);

            newrow.appendChild(cell);
            console.log(cell);
        }
        document.getElementById('catalogtable').appendChild(newrow);
    }

