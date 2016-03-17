/*
/!**shuffle image name list and return a list with objects ready for slides
 *
 * @param list : an image name list
 * @returns {Array}: an image address list
 *!/
function shuffleList(list) {
    var copy = [], n = list.length, index;
    imgaddress = 'images/slider/';
    while (n) {
        index = Math.floor(Math.random() * (n--));
        copy.push(imgaddress + list[index]);
        list.splice(index, 1);
    }
    return copy;
}

/!**
 * add image to slider
 * @param imgPath
 *!/
var elemImg = document.createElement("img");
function addImage(imgPath) {
    var aboutImg = imgPath.split("slider/")[1].slice(0, -4);

    elemImg.setAttribute("class", "img-responsive");
    elemImg.setAttribute("alt", aboutImg);
    elemImg.src = imgPath;
    //if (document.getElementById("home-image-show-slider").contains(elemImg)) {
    //    document.getElementById("home-image-show-slider").removeChild(elemImg);
    //}
    document.getElementById("home-image-show-slider").appendChild(elemImg);
    document.getElementById("about-img").innerHTML = aboutImg;
}

/!** * image name list
 *
 * @type {string[]}
 *!/
var ImgNameList = [
    "Norwegian electric locomotive 3 Ohma Electra.jpg",
    "NTNU Gloshaugen.jpg",
    "Trondheim Downtown.jpg"
];
function slideShow() {
    var counter = 0; // to keep track of current slide
    var slides = shuffleList(ImgNameList); // a collection of all of the slides path

    function showImage() {
        // calculates the actual index of the element to show
        var theImage = Math.abs(counter % slides.length);
        addImage(slides[theImage]);
    };

    // add click events to prev; next buttons
    $('#nav-slider-left').on('click', function () {
        nextImage();
        //stopLoop();
        //startLoop();
    });
    $('#nav-slider-right').on('click', function () {
        counter--;
        showImage();
    });

    function nextImage() {
        counter++;
        showImage();
    }

    showImage();

    // set timeout
    var timeOut = setTimeout(startLoop, 5000);

    function startLoop() {
        nextImage();
        timeOut = setTimeout(startLoop, 5000); // repeat myself
    }

    function stopLoop() {
        // to be called when you want to stop the timer
        clearTimeout(timeOut);
    }
}

//run the script:

//run slide show
$(document).ready(function () {
    slideShow();
});

//startLoop();*/


$(function() {

    var Page = (function() {

        var $navArrows = $( '#nav-arrows' ),
            $nav = $( '#nav-dots > span' ),
            slitslider = $( '#slider' ).slitslider( {
                onBeforeChange : function( slide, pos ) {

                    $nav.removeClass( 'nav-dot-current' );
                    $nav.eq( pos ).addClass( 'nav-dot-current' );

                }
            } ),

            init = function() {

                initEvents();

            },
            initEvents = function() {

                // add navigation events
                $navArrows.children( ':last' ).on( 'click', function() {

                    slitslider.next();
                    return false;

                } );

                $navArrows.children( ':first' ).on( 'click', function() {

                    slitslider.previous();
                    return false;

                } );

                $nav.each( function( i ) {

                    $( this ).on( 'click', function( event ) {

                        var $dot = $( this );

                        if( !slitslider.isActive() ) {

                            $nav.removeClass( 'nav-dot-current' );
                            $dot.addClass( 'nav-dot-current' );

                        }

                        slitslider.jump( i + 1 );
                        return false;

                    } );

                } );

            };

        return { init : init };

    })();

    Page.init();

});