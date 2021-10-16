const domain = document.domain == 'localhost' ? 'http://localhost:8080' : document.domain;

var inputs = [
    $('#inputs div #idInput'),
    $('#inputs div #NameInput'),
    $('#inputs div #PasswordInput'),
    $('#inputs div #EmailInput'),
    $('#inputs div #AgeInput')
];

$(document).ready(() => {
    var used = false;

  $('#SubmitClient').click(add);

    $('#removeClient').click(() => {
        if (used) return;
        used = true;
        $.ajax({
            type: 'DELETE',
            url: domain + '/api/Client',
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
                $('#ClientTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })
    })

    $('#uploadClient').click(() => {
        if (used) return;

        used = true;
        $.ajax({
            type: 'PUT',
            url: domain + '/api/Client',
            contentType: 'application/json',
            data: JSON.stringify({
                'id': parseInt(inputs[0].val()),
                'name': inputs[1].val(),
                'password': inputs[2].val(),
                'email':inputs[3].val(),
                'age': parseInt(inputs[4].val())
            })
        })
            .fail(() => {
                alert('An unspected error');
            })
            .always(() => {
                used = false;
                build();
                $('#ClientTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })
    });
    $('#addClient').click(() => {
        if (used) return;
        used = true;
        $.ajax({
            type: 'POST',
            url: domain + '/api/Client',
            contentType: 'application/json',
            data: JSON.stringify({
                'name': inputs[1].val(),
                'password': inputs[2].val(),
                'email':inputs[3].val(),
                'age': parseInt(inputs[4].val())
                }
            )
        })
            .fail(() => {
                alert('An unspected error');
            })
            .always(() => {
                used = false;
                build();
                $('#ClientTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })
    });


    build();
})
function build() {
    $('#ClientsRows').empty();

    $.ajax({
        type: 'GET',
        url: domain + '/api/Client',
        success: (res) => {
            console.log(res)
            res.forEach(i => {
                const row = '<tr class="tableRow">' +
                    `<th scope="row" class="id">${i.id}</th>` +
                    `<th scope="row" class="name">${i.name}</th>` +
                    `<th scope="row" class="password">${i.password}</th>` +
                    `<th scope="row" class="email">${i.email}</th>` +
                    `<th scope="row" class="age">${i.age}</th>` +
                    '<td class="edit"><a id="edit"class="btn btn-secondary btn-sm btn-edit">edit</a></td></tr>';
                $('#ClientsRows').append(row);
            });
            $('#edit').click(edit);
        }
    });
}
function add() {
    $('#idInputG').css('display', 'none');

    inputs.forEach(i => {
        i.val('');
    });

    $('#addClient').css('display', 'block');
    $('#removeClient').css('display', 'none');
    $('#uploadClient').css('display', 'none');

    $('#ClientTable').css('display', 'none');
    $('#inputContainer').css('display', 'block');
}

function edit() {
    var parent = $(this).parent().parent();
    var options = [
        parent.find('th.id'),
        parent.find('th.name'),
        parent.find('th.password'),
        parent.find('th.email'),
        parent.find('th.age')
    ];
    for (let i = 0; i < options.length; i++) {
        inputs[i].val(options[i].text());
    };

    inputs[0].css('display', 'flex');

    $('#addClient').css('display', 'none');
    $('#removeClient').css('display', 'block');
    $('#uploadCLient').css('display', 'block');

    $('#ClientTable').css('display', 'none');
    $('#inputContainer').css('display', 'block');
}