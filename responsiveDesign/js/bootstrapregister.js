/**
 * Created by GuoJunjun on 04/04/15. <junjunguo.com>
 *
 */


/**
 * Tell the server to register the given user
 * @param firstname
 * @param lastname
 * @param email
 * @param password
 * @returns {boolean}
 */
function registerAuser(firstname, lastname, email, password) {
    // register succeed:
    return true;
}


/**
 * check server if this email is unregistered
 * @param email
 * @returns {boolean}
 */
function unregisteredEmail(email) {
    //assume a@a.com is registered
    if (email == "a@a.com") {
        return false;
    }
    return true;
}

// -------------------------------------- //

$('#nav-register').click(function () {
    $('#registerModal').modal('show');
    $('#btn-t-c').click(function () {
        $('#t_and_c_m').modal('show');
        $('.hide-t-c').click(function () {
            $('#t_and_c_m').modal('hide');
        });
    });
    registerValidate();
});

/**
 * validate register information
 */
function registerValidate() {
    var form = document.getElementById('registerForm');
    validateFirstName();
    validateLastName();
    validateEmail();
    validatePassword();
    validateConfirmPassword();
    form.addEventListener("submit", function (event) {
        event.preventDefault(); // keep modal window open
        // if form is valid
        if (form.checkValidity()) {
            var firstname = $("input#inputFirstName").val();
            var lastname = $("input#inputLastName").val();
            var email = $("input#inputEmail").val();
            var password = $("input#inputPassword").val();

            if (registerAuser(firstname, lastname, email, password)) {
                // register succeed:
                $('#register-succeed').modal('show');
                document.getElementById("congratulations-user").innerHTML =
                    firstname;
                setTimeout(
                    function () {
                        $('#registerModal').modal('hide');
                        $('#register-succeed').modal('hide');
                    }, 4000);
            } else {
                // register not succeed
            }
        }
    }, false);
}
/**
 * register
 * validate: first name
 */
function validateFirstName() {
    var firstname = document.getElementById('inputFirstName');
    firstname.addEventListener("keyup", function (event) {
        if (firstname.validity.valid) {
            document.getElementById('form-group-first-name').className =
                "form-group has-success has-feedback";
            document.getElementById('form-span-first-name').className =
                "glyphicon glyphicon-ok form-control-feedback";
        } else {
            document.getElementById('form-group-first-name').className =
                "form-group has-error has-feedback";
            document.getElementById('form-span-first-name').className =
                "glyphicon glyphicon-remove form-control-feedback";
        }
    }, false);
}

/**
 * register
 * validate: last name
 */
function validateLastName() {
    var lastname = document.getElementById('inputLastName');
    lastname.addEventListener("keyup", function (event) {
        if (lastname.validity.valid) {
            document.getElementById('form-group-last-name').className =
                "form-group has-success has-feedback";
            document.getElementById('form-span-last-name').className =
                "glyphicon glyphicon-ok form-control-feedback";
        } else {
            document.getElementById('form-group-last-name').className =
                "form-group has-error has-feedback";
            document.getElementById('form-span-last-name').className =
                "glyphicon glyphicon-remove form-control-feedback";
        }
    }, false);
}

/**
 * register
 * validate: email
 */
function validateEmail() {
    var email = document.getElementById('inputEmail');
    email.addEventListener("keyup", function (event) {
        document.getElementById('inputEmail').title = "Email address";
        document.getElementById('inputEmail').setCustomValidity('');
        if (email.validity.valid) {
            var inputemail = $("input#inputEmail").val();
            if (unregisteredEmail(inputemail)) {
                document.getElementById('form-group-email').className =
                    "form-group has-success has-feedback";
                document.getElementById('form-span-email').className =
                    "glyphicon glyphicon-ok form-control-feedback";
            } else {
                document.getElementById('form-group-email').className =
                    "form-group has-warning has-feedback";
                document.getElementById('form-span-email').className =
                    "glyphicon glyphicon-warning-sign form-control-feedback";
                document.getElementById('inputEmail').title =
                    inputemail + " is already registered!";
                document.getElementById('inputEmail').setCustomValidity(inputemail +
                " is already registered !");
            }
        } else {
            document.getElementById('form-group-email').className =
                "form-group has-error has-feedback";
            document.getElementById('form-span-email').className =
                "glyphicon glyphicon-remove form-control-feedback";
        }
    }, false);
}

/**
 * register
 * validate: password
 */
function validatePassword() {
    var inputpassword = document.getElementById('inputPassword');
    inputpassword.addEventListener("keyup", function (event) {
        var password = $("input#inputPassword").val();
        var passwordcf = $("input#inputPasswordConfirm").val();
        document.getElementById('inputPasswordConfirm').setCustomValidity('');
        if (inputpassword.validity.valid) {
            document.getElementById('form-group-password').className =
                "form-group has-success has-feedback";
            document.getElementById('form-span-password').className =
                "glyphicon glyphicon-ok form-control-feedback";
        } else {
            document.getElementById('form-group-password').className =
                "form-group has-error has-feedback";
            document.getElementById('form-span-password').className =
                "glyphicon glyphicon-remove form-control-feedback";
        }
        // check confirm password in case password is modified after confirm
        // password
        validateConfirmPasswordIn();
    }, false);
}

/**
 * register
 * validate: password confirmation listener
 */
function validateConfirmPassword() {
    var passwordconfirm = document.getElementById('inputPasswordConfirm');
    passwordconfirm.addEventListener("keyup", function (event) {
        validateConfirmPasswordIn();
    }, false);
}
/**
 * only do the confirm password validate
 */
function validateConfirmPasswordIn() {
    var password = $("input#inputPassword").val();
    var passwordcf = $("input#inputPasswordConfirm").val();
    document.getElementById('inputPasswordConfirm').setCustomValidity('');
    if (passwordcf == password) {
        document.getElementById('form-group-password-confirm').className =
            "form-group has-success has-feedback";
        document.getElementById('form-span-password-confirm').className =
            "glyphicon glyphicon-ok form-control-feedback";
    } else {
        document.getElementById('form-group-password-confirm').className =
            "form-group has-error has-feedback";
        document.getElementById('form-span-password-confirm').className =
            "glyphicon glyphicon-remove form-control-feedback";
        document.getElementById('inputPasswordConfirm').setCustomValidity("The password you typed in are not same!");
    }
}
