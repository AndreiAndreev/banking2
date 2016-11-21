;var $table = $('#table'),
    $remove = $('#remove'),
    $add = $('#add'),
    $save = $('#save'),
    $addList = $("#addList"),
    selections = [];

var names = {
    'name': 'First Name',
    'surname': 'Last Name',
    'mail': 'Email Address',
    'job': 'Title',
    'companyName': 'Company',
    'companyLink': 'Web Site',
    'relevance': 'Confidence',
    'linkedInUrl': 'LinkedIn URL',
    'list': 'List Name'
};

function initTable() {
    $table.bootstrapTable({
        columns: [{
            field: 'state',
            checkbox: true,
            align: 'center',
            cardVisible: false
        }, {
            field: 'name',
            title: 'First Name',
            sortable: true,
            editable: {
                type: 'text',
                title: 'First Name',
                url: window.location.pathname + '/update'
            },
            align: 'center'
        }, {
            field: 'surname',
            title: 'Last Name',
            sortable: true,
            editable: {
                type: 'text',
                title: 'Last Name',
                url: window.location.pathname + '/update'
            },
            align: 'center'
        }, {
            field: 'mail',
            title: 'Email Address',
            sortable: true,
            editable: {
                type: 'text',
                title: 'Email Address',
                url: window.location.pathname + '/update'
            },
            align: 'center'
        }, {
            field: 'relevance',
            title: 'Confidence',
            sortable: true,
            editable: {
                type: 'text',
                title: 'Confidence',
                url: window.location.pathname + '/update'
            },
            align: 'center',
            visible: false,
            formatter: relevanceFormatter
        }, {
            field: 'job',
            title: 'Title',
            sortable: true,
            editable: {
                type: 'text',
                title: 'Title',
                url: window.location.pathname + '/update'
            },
            align: 'center'
        }, {
            field: 'linkedInUrl',
            title: 'LinkedIn URL',
            sortable: true,
            editable: {
                type: 'text',
                title: 'LinkedIn URL',
                url: window.location.pathname + '/update'
            },
            visible: false,
            align: 'center'
        }, {
            field: 'companyName',
            title: 'Company',
            sortable: true,
            editable: {
                type: 'text',
                title: 'Company',
                url: window.location.pathname + '/update'
            },
            align: 'center'
        }, {
            field: 'companyLink',
            title: 'Web Site',
            sortable: false,
            editable: {
                type: 'text',
                title: 'Web Site',
                url: window.location.pathname + '/update'
            },
            align: 'center',
            visible: false
        }, {
            field: 'list',
            title: 'List Name',
            sortable: true,
            editable: {
                type: 'text',
                title: 'List Name',
                url: window.location.pathname + '/update'
            },
            align: 'center',
            visible: false
        }
        ]
    });

    // sometimes footer render error.
    setTimeout(function () {
        $table.bootstrapTable('resetView');
    }, 200);

    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);

        // save your data, here just save the current page
        selections = getIdSelections();
        // push or splice the selections if you want to save all data selections
    });

    $table.on('all.bs.table', function (e, name, args) {
        console.log(name, args);
    });

    $remove.click(function () {
        var ids = getIdSelections();
        var removeMail = function (id) {
            $.ajax({
                url: window.location.pathname + '/remove',
                type: 'POST',
                timeout: 10000,
                crossDomain: true,
                dataType: "json",
                data: JSON.stringify(ids),
                contentType: "application/json; charset=UTF-8",
                beforeSend: function () {
                    // TODO: should be some on before listener
                },
                error: function (request) {
                    console.log(request);
                },
                success: function (request) {
                    var ids_array = [];
                    $.each(ids, function(index, value) {
                        ids_array.push(value.id)
                    });

                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: ids_array
                    });

                    $remove.prop('disabled', true);
                }
            });
        };

        removeMail(ids);
    });
    $add.click(function () {
        $('#modal').modal('show');
    });

    $addList.click(function () {
        var name = $('#newName').val();
        if (name !='') {
            $.ajax({
                url : window.location.pathname + '/addNewList?name=' + name,
                type : 'POST',
                dataType : 'json',
                contentType: "application/json; charset=UTF-8",
            });
            swal("New list added!", '', "success");
            $('#newName').val('');
        } else {
            swal("Please, enter name", "", "error");
        }

    });

    $('#myLists').change(function () {
        console.log($("#myLists option:selected").text());
        $('#table').bootstrapTable('refresh', {url: window.location.pathname + '/get_mails?name=' + $("#myLists option:selected").text()});
    });



    $save.click(function () {
        var mail = {};
        mail.name = $('#name').val();
        mail.surname = $('#surname').val();
        mail.mail = $('#e-mail').val();
        mail.relevance = $('#relevance').val();
        mail.job = $('#job').val();
        mail.companyName = $('#company-name').val();
        mail.companyLink = $('#company-link').val();
        mail.linkedInUrl = $('#linkedin-url').val();
        mail.list = $("#newSelList option:selected").text()
        console.log("list name = " + mail.list);

        var addMail = function (mail) {
            $.ajax({
                url: window.location.pathname + '/add',
                type: 'POST',
                timeout: 10000,
                crossDomain: true,
                dataType: "json",
                data: JSON.stringify(mail),
                contentType: "application/json; charset=UTF-8",
                beforeSend: function () {
                    // TODO: should be some on before listener
                },
                error: function (request) {
                    console.log(request);
                },
                success: function (request) {
                    $table.bootstrapTable('insertRow', {
                        index: $table.bootstrapTable('getData').length + 1,
                        row: {
                            id: request.id,
                            name: mail.name,
                            surname: mail.surname,
                            mail: mail.mail,
                            relevance: mail.relevance,
                            job: mail.job,
                            companyName: mail.companyName,
                            companyLink: mail.companyLink,
                            linkedInUrl: mail.linkedInUrl,
                            list: mail.list
                        }
                    });
                }
            });
        };

        addMail(mail);
    });

    $(window).resize(function () {
        $table.bootstrapTable('resetView');
    });
}

function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return {"id" : row.id}
    });
}

function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        if (names[key]) {
            switch (names[key]) {
                case 'LinkedIn URL' :
                    html.push('<p><b>' + names[key] + ':</b> <a href="' + value + '">' + value + '</a></p>');
                    break;

                case 'Confidence':
                    html.push('<p><b>' + names[key] + ':</b> ' + value + '%</p>');
                    break;

                default:
                    html.push('<p><b>' + names[key] + ':</b> ' + value + '</p>');

            }
        }
    });
    return html.join('');
}

window.operateEvents = {
    'click .like': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        $table.bootstrapTable('remove', {
            field: 'id',
            values: [row.id]
        });
    }
};

function relevanceFormatter(value) {
    return value + '%'
}

$(document).ready(function () {
    initTable();
});