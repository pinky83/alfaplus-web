/**
 * Created by Дмитрий on 04.02.2017.
 */
var ajaxUrl = 'rest/patients/byName/яковенко';
var imagesAjaxUrl = 'rest/images/byPatient/2048';
var indexMap = [];
var counter = 0;
var ageCheck = false;
var nameCheck = false;
var datatableApi2 =
    $('#datatable2').DataTable({
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
                "data": "id"
            },
            {
                "data":"name"
            },
            {
                "data":"birthDate",
                "render": function (date, type) {
                    if (type == 'display') {
                        return '<span>' + date.substring(0, 4) + '</span>';
                    }
                    return date;
                }
            },
            {
                "data":"comments"
            }
        ]
    });

var datatableApi3 =
    $('#datatable3').DataTable({
        "ajax": {
            "url": imagesAjaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "scrollY": "300px",
        "scrollCollapse": false,
        "searching": false,
        "columns": [
            {
                "data": "id"
            },
            {
                "data": "imageDate"
            },
            {
                "data": "description"
            }
        ]
    });

function updateImageTableByData(data) {
    datatableApi3.clear().rows.add(data).draw();
}

$('#datatable2').on( 'click', 'tr', function () {
    var id = this.innerText.substring(0,5);
    imagesAjaxUrl = 'rest/images/byPatient/' + id;
    // alert(imagesAjaxUrl);
    $.get(imagesAjaxUrl, updateImageTableByData);
} );

function updatePatientTableByData(data) {
    datatableApi2.clear().rows.add(data).draw();
}

$('#ageCheck').change(function () {
    ageCheck = !ageCheck;
    }
);

function searchByName () {
    var text = $('#searchPatientName').val();
    if (ageCheck) {
        var age = "&&age=" + $('#searchPatientAge').val() + "-01-01";
        ajaxUrl = 'rest/patients/getByNameWithYear/' + "?name=" + text + age;
    }
    else ajaxUrl = 'rest/patients/byName/' + text;
    $.get(ajaxUrl, updatePatientTableByData);
}