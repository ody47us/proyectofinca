const domain = document.domain == 'localhost' ? 'http://localhost:8080' : document.domain;

var inputs = [
    $('#inputs div #idInput'),
    $('#inputs div #nameInput'),
    $('#inputs div #addressInput'),
    $('#inputs div #exensionInput'),
    $('#inputs div #descriptionInput')
];

$(document).ready(() => {
    var used = false;

    $.ajax({
        type: 'GET',
        url: domain + '/api/Category',
        success: (res) => {
            for (let i = 0; i < res.length; i++) {
                let catRow;
                if(i == 0){
                    catRow = `<option value="${res[i].id}" selected>${res[i].name}</option>`;
                }else{
                    catRow = `<option value="${res[i].id}">${res[i].name}</option>`;
                }
                $('#catSelect').append(catRow);
            }
        }
    });

    $('#sumbitFarm').click(add);

    $('#removeFram').click(() => {
        if (used) return;
        used = true;
        $.ajax({
            type: 'DELETE',
            url: domain + '/api/Farm',
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
                $('#farmsTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })
    })

    $('#uploadFram').click(() => {
        if (used) return;

        used = true;
        $.ajax({
            type: 'PUT',
            url: domain + '/api/Farm',
            contentType: 'application/json',
            data: JSON.stringify({
                'id': parseInt(inputs[0].val()),
                'name': inputs[1].val(),
                'address': inputs[2].val(),
                'extension': parseInt(inputs[3].val()),
                'description': inputs[4].val(),
                'category':{
                    'id':parseInt($('#catSelect').val())
                }
            })
        })
            .fail(() => {
                alert('An unspected error');
            })
            .always(() => {
                used = false;
                build();
                $('#farmsTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })
    });
    $('#addFarm').click(() => {
        if (used) return;
        used = true;
        $.ajax({
            type: 'POST',
            url: domain + '/api/Farm',
            contentType: 'application/json',
            data: JSON.stringify({
                'name': inputs[1].val(),
                'address': inputs[2].val(),
                'extension': parseInt(inputs[3].val()),
                'description': inputs[4].val(),
                'category':{
                    'id':parseInt($('#catSelect').val())
                }
            })
        })
            .fail(() => {
                alert('An unspected error');
            })
            .always(() => {
                used = false;
                build();
                $('#farmsTable').css('display', 'block');
                $('#inputContainer').css('display', 'none');
            })
    });
    build();
})
function build() {
    $('#farmsRows').empty();

    $.ajax({
        type: 'GET',
        url: domain + '/api/Farm',
        success: (res) => {
            res.forEach(i => {
                const row = '<tr class="tableRow">' +
                    `<th scope="row" class="id">${i.id}</th>` +
                    `<th scope="row" class="name">${i.name}</th>` +
                    `<th scope="row" class="address">${i.address}</th>` +
                    `<th scope="row" class="extension">${i.extension}</th>` +
                    `<th scope="row" class="description">${i.description}</th>` +
                    `<th scope="row" class="category">${i.category.name}</th>` +
                    '<td class="edit"><a class="btn btn-secondary btn-sm btn-edit">edit</a></td></tr>';
                $('#farmsRows').append(row);
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

    $('#addFarm').css('display', 'block');
    $('#removeFram').css('display', 'none');
    $('#uploadFram').css('display', 'none');

    $('#farmsTable').css('display', 'none');
    $('#inputContainer').css('display', 'block');
}
function edit() {
    var parent = $(this).parent().parent();
    var options = [
        parent.find('th.id'),
        parent.find('th.name'),
        parent.find('th.address'),
        parent.find('th.extension'),
        parent.find('th.description')
    ];
    for (let i = 0; i < options.length; i++) {
        inputs[i].val(options[i].text());
    };

    inputs[0].css('display', 'flex');

    $('#addFarm').css('display', 'none');
    $('#removeFram').css('display', 'block');
    $('#uploadFram').css('display', 'block');

    $('#farmsTable').css('display', 'none');
    $('#inputContainer').css('display', 'block');
}