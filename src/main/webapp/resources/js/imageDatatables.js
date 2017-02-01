/**
 * Created by Дмитрий on 29.01.2017.
 */
var ajaxUrl = 'rest/images/last/';
var indexMap = [];
var counter = 0;
var counter2 = 0;
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
                "render": function (date, type, row) {
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
                "render": function (sex, type, row) {
                    if (type == 'display') {
                        return '<span>' + (sex == 0 ? 'М' : 'Ж')  + '</span>';
                    }
                    return sex;
                }
            },
            {
                "data": "patient.birthDate",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        return '<span>' + date.substring(0, 4) + '</span>';
                    }
                    return date;
                }
            }
        ]
        // "order": [
        //     [
        //         0,
        //         "desc"
        //     ]
        // ]
    });

$('#datatable').on( 'click', 'tr', function () {
     var id = this.innerText.substring(0,5);
    $('#image_thumb').attr("src", "thumb/000"+id);
} );

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

$('#prev').on('click', function () {
    var arrayCopy = [];
    if (indexMap.length>100) arrayCopy = indexMap.slice(-100);
    else arrayCopy = indexMap;
    var fileIndex;
    if (arrayCopy.length>100) fileIndex =  arrayCopy[arrayCopy.length-22];
    else fileIndex =  arrayCopy[arrayCopy.length-20];
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
