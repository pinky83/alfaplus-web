/**
 * Created by Дмитрий on 29.01.2017.
 */
var ajaxUrl = 'rest/images/last/';
var indexMap = [];
var counter = 0;
var datatableApi =
     $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
         "scrollY": "600px",
         "scrollCollapse": false,
         "searching": false,
        "columns": [
            {
                "data": "id",
                "render" : function (id) {
                    indexMap[counter] = id;
                    counter++;
                    return id;
                }
            },
            {
                "data": "imageDate",
                "render": function (date, type) {
                    if (type == 'display') {
                        return '<span>' + date.split('T').join(' ') + '</span>';
                    }
                    return date;
                }
            },
            {
                "data": "patient.name"
            },
            {
                "data": "patient.sex",
                "render": function (sex, type) {
                    if (type == 'display') {
                        return '<span>' + (sex == 0 ? 'М' : 'Ж')  + '</span>';
                    }
                    return sex;
                }
            },
            {
                "data": "patient.birthDate",
                "render": function (date, type) {
                    if (type == 'display') {
                        return '<span>' + date.substring(0, 4) + '</span>';
                    }
                    return date;
                }
            },
            {
                "data": "description",
                "render": function (description, type) {
                    if (type == 'display') {
                        return '<span style="display: none" class="description">' + description + '</span>';
                    }
                    return description;
                }

            }
        ]
    });

$('#datatable').on( 'click', 'tr', function () {
     var id = this.innerText.substring(0,5);
    $('#image_thumb').attr("src", "thumb/000"+id);
    $('#image_description').attr("placeholder", $(this).find('span.description').text())
} );

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

$('#prev').on('click', function () {
    var tempArray = [];
    if (indexMap.length>100) tempArray = indexMap.slice(-100);
    else tempArray = indexMap;
    var fileIndex;
    if (tempArray.length>100) fileIndex =  tempArray[tempArray.length-22];
    else fileIndex =  tempArray[tempArray.length-20];
    if (indexMap.length>400) indexMap = indexMap.slice(-250);
    ajaxUrl = 'rest/images/previous/' +fileIndex;
    $.get(ajaxUrl, updateTableByData);
    }
);

$('#next').on('click', function () {
    ajaxUrl = 'rest/images/next/' + indexMap[indexMap.length-1];
    $.get(ajaxUrl, updateTableByData);
    }
);

function searchByDate () {
    var attribute = $('#search').attr('type');
    if (attribute == 'number')  $('#search').attr('type', 'date');
    else {
        ajaxUrl = 'rest/images/byDate/' + $('#search').val();
        $.get(ajaxUrl, updateTableByData);
    }
}

function searchById () {
    var attribute = $('#search').attr('type');
    if (attribute == 'date')  $('#search').attr('type', 'number');
    else {
        ajaxUrl = 'rest/images/find/' + $('#search').val();
        $.get(ajaxUrl, updateTableByData);
    }
}

