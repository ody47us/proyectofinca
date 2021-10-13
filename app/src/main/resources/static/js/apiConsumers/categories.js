const domain = document.domain == 'localhost' ? 'http://localhost:8080' : document.domain;

var inputs = [
    $('#inputs div #idInput'),
    $('#inputs div #nameInput'),
    $('#inputs div #descriptionInput')
];

$(document).ready(() => {
    var used = false;

    $('#sumbitCategory').click(add);

    $('#removeCategory').click(() => {
        if (used) return;
        used = true;
        $.ajax({
            type: 'DELETE',
            url: domain + '/api/Category',
            contentType: 'application/json',
            data: JSON.stringify({
                'id': parseInt(inputs[0].val()),
            })
        })
            .fail(() => {
                alert('An unspected error');
            })
            .always(() => {
                used = false;
                build();
                $('#categoriesTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })
    })

    $('#uploadCategory').click(() => {
        if (used) return;
        used = true;
        $.ajax({
            type: 'PUT',
            url: domain + '/api/Category',
            contentType: 'application/json',
            data: JSON.stringify({
                'id': parseInt(inputs[0].val()),
                'name': inputs[1].val(),
                'description': inputs[2].val()
            })
        })
            .fail(() => {
                alert('An unspected error');
            })
            .always(() => {
                used = false;
                build();
                $('#categoriesTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })

    });
    $('#addCategory').click(() => {
        if (used) return;
        used = true;
        $.ajax({
            type: 'POST',
            url: domain + '/api/Category',
            contentType: 'application/json',
            data: JSON.stringify({
                'name': inputs[1].val(),
                'description': inputs[2].val()
            })
        })
            .fail(() => {
                alert('An unspected error');
            })
            .always(() => {
                used = false;
                build();
                $('#categoriesTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })
    });
    build();
})
function build() {
    $('#categoriesRows').empty();

    $.ajax({
        type: 'GET',
        url: domain + '/api/Category',
        success: (res) => {
            res.forEach(i => {
                const row = '<tr class="tableRow">' +
                    `<th scope="row" class="id">${i.id}</th>` +
                    `<th scope="row" class="name">${i.name}</th>` +
                    `<th scope="row" class="description">${i.description}</th>` +
                    '<td class="edit"><a class="btn btn-secondary btn-sm btn-edit">edit</a></td></tr>';
                $('#categoriesRows').append(row)
            });
            $('td a.btn-edit').click(edit);
        }
    });
}
function add() {
    $('#idInputG').css('display', 'none');

    inputs.forEach(i => {
        i.val('');
    });

    $('#addCategory').css('display', 'block');
    $('#removeCategory').css('display', 'none');
    $('#uploadCategory').css('display', 'none');

    $('#categoriesTable').css('display', 'none');
    $('#inputContainer').css('display', 'block');
}
function edit() {
    var parent = $(this).parent().parent();
    var options = [
        parent.find('th.id'),
        parent.find('th.name'),
        parent.find('th.description')
    ];


    for (let i = 0; i < options.length; i++) {
        inputs[i].val(options[i].text());
    };

    inputs[0].css('display', 'flex');

    $('#addCategory').css('display', 'none');
    $('#removeCategory').css('display', 'block');
    $('#uploadCategory').css('display', 'block');

    $('#categoriesTable').css('display', 'none');
    $('#inputContainer').css('display', 'block');
}