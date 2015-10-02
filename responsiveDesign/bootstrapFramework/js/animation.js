/**
 * This file is part of cssa
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on March 29, 2015.
 */


/**
 * login variables are here:
 */

function login() {
    var inputEmail = $('#inputEmail').val();
    var inputPassword = $("#inputPassword").val();

    console.log("Email: " + inputEmail + "\nPassword: " + inputPassword);
}
/**
 * register variables are here:
 */
function register() {
    var firstName = $("#first_name").val();
    var lastName = $("#last_name").val();
    //var displayName = $("#display_name").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var pwConfirm = $("#password_confirmation").val();

    if (password != pwConfirm) {
        console.log("check our input password");
        throw new Error();
    } else {
        console.log("First name: " + firstName + "\nLast name: " + lastName + "\nEmail: " + email);
    }
}


// --------------------------- //

/**
 * Due to how HTML5 defines its semantics, the autofocus HTML attribute has no effect in Bootstrap modals.
 * To achieve the same effect, use some custom JavaScript:
 */
$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').focus();
});

/**
 *jquery load to html file
 */
$('#login-modal').load('loginmodal.html #cssaRegisterModal, #cssaLoginModal');

/**
 * Flying title
 */
$("#logo-left").animate({"left": "+=300px", opacity: 1}, 800);
$("#logo-right").animate({"right": "+=800px", opacity: 1}, 800);

/**
 * custom register modal:
 */
$('#nav-register').click(function () {
    $('#cssaRegisterModal').modal('show');
    $('#btn-t-c').click(function () {
        console.log("show....");
        $('#t_and_c_m').modal('show');
        $('.hide-t-c').click(function () {
            $('#t_and_c_m').modal('hide');
        });
    });
    $('#registerSubmit').click(function () {
        register();
    });
});

/**
 * custom login modal:
 */
$("#nav-login").click(function () {
    $('#cssaLoginModal').modal('show');
    $('#loginSubmit').click(function () {
        login();
    });

    //$('#loginSubmit').validator().on('submit', function (e) {
    //    $('form').validator().on('submit', function (e) {
    //    if (e.isDefaultPrevented()) {
    //        console.log("valid inputs !");
    //        // everything looks good!
    //        login();
    //    } else {
    //        console.log("invalid inputs ......")
    //    }
    //});
});