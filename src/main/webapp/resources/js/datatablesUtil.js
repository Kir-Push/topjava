function makeEditable() {
    $('#add').click(function () {
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.edit').click(function () {
        $('#id').val($(this).attr("id"));
        $("#email").val($(this).attr("email"));
        $("#name").val($(this).attr("petja"));
        $('#editRow').modal();
    });

    $('.update').click(function () {
        $('#id').val($(this).attr("id"));
        $("#description").val($(this).attr("description"));
        $("#calories").val($(this).attr("calories"));
        $("#dateTime").val($(this).attr("dateTime"));
        $('#editRow').modal();
    });

    $('.delete').click(function () {
        deleteRow($(this).parents('tr').attr("id"));
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $('#filterRows').submit(function () {
        filter();
        return false;
    })

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            filter();
            successNoty('Deleted');
        }
    });
}

function updateActive(state,id) {
  //  var enabled =  state.is(":checked");
    var enabled = state;
    $.ajax({
        url: ajaxUrl + "active",
        type: 'POST',
        data: {active: enabled, id: id},
        success: function () {
            updateTable();
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.clear();
        $.each(data, function (key, item) {
            datatableApi.row.add(item);
        });
        datatableApi.draw();
    });
}

function save() {
    var form = $('#detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            filter();
            successNoty('Saved');
        }
    });
}

function filter() {
    var form = $('#filterRows');
    debugger;
    $.ajax({
        type: "GET",
        url: ajaxUrl+"filter",
        data: form.serialize(),
        success: function (data) {
            datatableApi.clear();
            $.each(data, function (key, item) {
                datatableApi.row.add(item);
            });
            datatableApi.draw();
        }
    });

}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
