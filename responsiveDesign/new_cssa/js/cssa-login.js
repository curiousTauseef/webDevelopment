/**
 * Check if the login email and its password er correct
 * @param email
 * @param password
 * @returns {boolean}
 */
function login(email, password) {
    $.ajax({
        type: "POST",
        url: "/user/login.action",
        data: {
            email: email,
            password: password
        },
        beforeSend: function () {
            document.getElementById('loginSubmit').className = "btn btn-lg btn-block btn-warning";
            document.getElementById('login-submit-btn-span').className = "glyphicon glyphicon-refresh glyphicon-refresh-animate";
        },
        success: function (data) {
            if (data == "success") {
                document.getElementById('form-group-password-login').className =
                    "form-group has-success has-feedback";
                document.getElementById('form-span-password-login').className =
                    "glyphicon glyphicon-ok form-control-feedback";

                document.getElementById('loginSubmit').className = "btn btn-lg btn-block btn-primary";
                document.getElementById('login-submit-btn-span').className = "";
                loginSucceed();
            }
            else {
                document.getElementById('message-from-server').innerText = "Incorrect email or password!";
                document.getElementById('form-group-password-login').className =
                    "form-group has-warning has-feedback";
                document.getElementById('form-span-password-login').className =
                    "glyphicon glyphicon-warning-sign form-control-feedback";

                document.getElementById('form-group-email-login').className =
                    "form-group has-warning has-feedback";
                document.getElementById('form-span-email-login').className =
                    "glyphicon glyphicon-warning-sign form-control-feedback";

                document.getElementById('loginSubmit').className = "btn btn-lg btn-block btn-primary";
                document.getElementById('login-submit-btn-span').className = "";
            }
        }
    });
}

/**
 * Check cookies whether the member is login or not
 * @returns {boolean}
 */

/**
 * Tell the server to register the given user
 * @param firstname
 * @param lastname
 * @param email
 * @param password
 * @returns {boolean}
 */
function register(firstname, lastname, email, password) {
    $.ajax({
        type: "POST",
        url: "/user/register.action",
        data: {
            email: email,
            password: password,
            firstName: firstname,
            lastName: lastname
        },
        beforeSend: function () {
            document.getElementById('registerSubmit').className = "btn btn-lg btn-block btn-warning";
            document.getElementById('register-submit-btn-span').className = "glyphicon glyphicon-refresh glyphicon-refresh-animate";
        },
        success: function (data) {
            if (data == "success") {
                document.getElementById('registerSubmit').className = "btn btn-lg btn-block btn-primary";
                document.getElementById('register-submit-btn-span').className = "";
                registerSucceed();
            }
            else {
                // error here
                document.getElementById('registerSubmit').className = "btn btn-lg btn-block btn-primary";
                document.getElementById('register-submit-btn-span').className = "";
            }
        }
    });
}

/**
 * Check server whether this email exits
 * @param email
 * @returns {boolean}
 */
function checkEmail(email) {
    $.ajax({
        type: "POST",
        url: "/user/checkEmail.action",
        data: {
            email: email
        },
        beforeSend: function () {
            document.getElementById('form-span-email').className =
                "glyphicon glyphicon-refresh glyphicon-refresh-animate form-control-feedback";
        },
        success: function (data) {
            if (data == "success") {
                // email not registered
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
                    email + " is already registered!";
                document.getElementById('inputEmail').setCustomValidity(email +
                " is already registered !");
            }
        }
    });
}

/**
 * Login succeed actions
 */
function loginSucceed() {
    //login succeed:
    $('#welcome-back').modal('show');
    //                welcome info
    document.getElementById("welcome-back-user").innerHTML = $("input#inputEmailLogin").val();
    setTimeout(
        function () {
            $('#welcome-back').modal('hide');
            $('#cssaLoginModal').modal('hide');
            location.href ="/index"
        }, 2000);


}

/**
 * Validate register information
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
        validateEmail(); //valid email again
        // if form is valid
        if (form.checkValidity()) {
            var firstname = $("input#inputFirstName").val();
            var lastname = $("input#inputLastName").val();
            var email = $("input#inputEmail").val();
            var password = $("input#inputPassword").val();
            register(firstname, lastname, email, password);
        }
    }, false);
}

/**
 * Register succeed actions
 * Feedback messages: close window
 */
function registerSucceed() {
    // register succeed:
    document.getElementById('registerSubmit').style.visibility = 'hidden';
    $('#register-succeed').modal('show');
    document.getElementById("congratulations-user").innerHTML = $("input#inputFirstName").val();
    setTimeout(
        function () {
            $('#cssaRegisterModal').modal('hide');
            $('#register-succeed').modal('hide');
        }, 4000);
}

/**
 * Register
 * Validate: first name
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
 * Register
 * Validate: last name
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
 * Register
 * Validate: email
 */
function validateEmail() {
    var email = document.getElementById('inputEmail');
    email.addEventListener("keyup", function (event) {
        document.getElementById('registerSubmit').style.visibility = 'visible';
        document.getElementById('inputEmail').title = "Email address";
        document.getElementById('inputEmail').setCustomValidity('');
        if (email.validity.valid) {
            var inputemail = $("input#inputEmail").val();
            checkEmail(inputemail)
        } else {
            document.getElementById('form-group-email').className =
                "form-group has-error has-feedback";
            document.getElementById('form-span-email').className =
                "glyphicon glyphicon-remove form-control-feedback";
        }
    }, false);
}

/**
 * Register
 * Validate: password
 */
function validatePassword() {
    var inputpassword = document.getElementById('inputPassword');
    inputpassword.addEventListener("keyup", function (event) {
        var password = $("input#inputPassword").val();
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
 * Register
 * Validate: password confirmation listener
 */
function validateConfirmPassword() {
    var passwordconfirm = document.getElementById('inputPasswordConfirm');
    passwordconfirm.addEventListener("keyup", function (event) {
        validateConfirmPasswordIn();
    }, false);
}

/**
 * Only do the confirm password validate
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
$(document).ready(function () {
    /**
     *Load from html file
     */
    $('#login-modal').load('/html/login.html #cssaRegisterModal, #cssaLoginModal, #welcome-back');

    /**
     * Flying title
     */
    $("#logo-left").animate({"left": "+=300px", opacity: 1}, 800);
    $("#logo-right").animate({"right": "+=800px", opacity: 1}, 800);

    /**
     * Custom login modal:
     */
    $("#nav-login").click(function () {
        $('#cssaLoginModal').modal('show');
        var form = document.getElementById('loginForm');
        var email = document.getElementById('inputEmailLogin');
        var password = document.getElementById('inputPasswordLogin');

        email.addEventListener("keyup", function (event) {
            document.getElementById('message-from-server').innerText = "";
            if (email.validity.valid) {
                //check server if the email exist
                document.getElementById('form-group-email-login').className =
                    "form-group has-success has-feedback";
                document.getElementById('form-span-email-login').className =
                    "glyphicon glyphicon-ok form-control-feedback";
            } else {
                document.getElementById('form-group-email-login').className =
                    "form-group has-error has-feedback";
                document.getElementById('form-span-email-login').className =
                    "glyphicon glyphicon-remove form-control-feedback";

            }
        }, false);
        password.addEventListener("keyup", function (event) {
            document.getElementById('form-group-password-login').className = "";
            document.getElementById('form-span-password-login').className = "";
            document.getElementById('message-from-server').innerText = "";

        }, false);
        form.addEventListener("submit", function (event) {
            document.getElementById('message-from-server').innerText = "";
            event.preventDefault(); // keep modal window open
            var inputPassword = $("input#inputPasswordLogin").val();
            var inputEmail = $("input#inputEmailLogin").val();
            login(inputEmail, inputPassword);
        }, false);
    });

    /**
     * Custom register modal:
     */
    $('#nav-register').click(function () {
        $('#cssaRegisterModal').modal('show');
        $('#btn-t-c').click(function () {
            $('#t_and_c_m').modal('show');
            $('.hide-t-c').click(function () {
                $('#t_and_c_m').modal('hide');
            });
        });
        registerValidate();
    });
});


