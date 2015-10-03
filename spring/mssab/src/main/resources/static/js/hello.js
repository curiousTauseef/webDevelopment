/**
 * This file is part of mssab
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on October 03, 2015.
 */

/**
 * The name of the application is "hello" and it has an empty (and redundant) "config" and an empty
 * "controller" called "home". The "home" controller will be called when we load the "index.html"
 * because we have decorated the content <div> with ng-controller="home".
 *
 * Notice that we injected a magic $scope into the controller function (Angular does dependency
 * injection by naming convention, and recognises the names of your function parameters). The
 * $scope is then used inside the function to set up content and behaviour for the UI elements that
 * this controller is responsible for.
 */
//angular.module('hello', [])
//    .controller('home', function ($scope) {
//        $scope.greeting = {id: 'xxx', content: 'Hello World!'}
//    })
/**
 * app should now be secure and functional, and it will say "Hello World!". The greeting is
 * rendered by Angular in the HTML using the handlebar placeholders, {{greeting.id}} and
 * {{greeting.content}}.
 */

//
/**
 * We injected an $http service, which is provided by Angular as a core feature, and used it to GET
 * our resource. Angular passes us the JSON from the response body back to a callback function on
 * success.
 */
angular.module('hello', [])
    .controller('home', function ($scope, $http) {
        $http.get('/resource/').success(function (data) {
            $scope.greeting = data;
        })
    });