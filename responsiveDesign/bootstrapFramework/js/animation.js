/**
 * This file is part of cssa
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on March 29, 2015.
 */

/**
 * Flying title
 */
$("#logo-left").animate({"left": "+=300px", opacity: 1}, 800);
$("#logo-right").animate({"right": "+=800px", opacity: 1}, 800);


/**
 * Due to how HTML5 defines its semantics, the autofocus HTML attribute has no effect in Bootstrap modals. To achieve the same effect, use some custom JavaScript:
 */
$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').focus()
})

/**
 *jquery load to html file
 */
$('#login-modal').load('loginmodal.html #cssaLoginModal');
