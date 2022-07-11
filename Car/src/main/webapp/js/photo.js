var userObj;
var backBtn = null;
// $("#rollAction").submit(function () {
//     $.post("/ManagementSystem/user/roll", $(this).serialize(), function (data) {
//         if (data) {
//             load(data, 8, null, null);
//         }
//         return false;
//     })
//     return false;
// })





$(function () {

    $.post("/Car/carIssue/seePhoto", $(this).serialize(), function (data) {
        if (data) {
            $("#image").attr("src",data);
        }
        return false;
    })
    return false;
});

