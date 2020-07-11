$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/user",
         type: 'POST',
         contentType: 'application/json',
             data: {
                 "firstname": "1",
                 "lastname": "2",
                 "email": "3",

             },
    }).then(function(data) {
        $('.firstname').append(data.firstname);
        $('.lastname').append(data.lastname);
        $('.email').append(data.email);
    });
});