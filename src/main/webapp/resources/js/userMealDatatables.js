/**
 * Created by buhalo on 02.08.16.
 */
var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
    return false;
}
jQuery('#startDate').datetimepicker({
    timepicker: false,
    format: 'Y-m-d'});
jQuery('#endDate').datetimepicker({
    timepicker: false,
    format: 'Y-m-d'});
jQuery('#startTime').datetimepicker({
    datepicker: false,
    format: 'H:i'});
jQuery('#endTime').datetimepicker({
    datepicker: false,
    format: 'H:i'});
$(function () {
    datatableApi = $('#datatable').DataTable(
        {
            "ajax": {
                "url": ajaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false,
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (data.exceed) {
                    $(row).addClass("exceeded");
                }
                else
                {
                    $(row).addClass("normal");
                }

            },
        });

    $('#filter').submit(function () {
        updateTable();
        return false;
    });
    makeEditable();
});
