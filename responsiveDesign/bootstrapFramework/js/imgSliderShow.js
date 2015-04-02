/**
 * This file is part of cssa
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on March 30, 2015.
 */


/**shuffle image name list and return a list with objects ready for slides
 *
 * @param list : an image name list
 * @returns {Array}: an image address list
 */
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

/**
 * add image to slider
 * @param imgPath
 */
var elemImg = document.createElement("img");
function addImage(imgPath) {
    var aboutImg = imgPath.split("slider/")[1].slice(0, -4);

    elemImg.setAttribute("class", "img-responsive");
    elemImg.setAttribute("alt", aboutImg);
    elemImg.src = imgPath;
    if (document.getElementById("home-image-show-slider").contains(elemImg)) {
        document.getElementById("home-image-show-slider").removeChild(elemImg);
    }
    document.getElementById("home-image-show-slider").appendChild(elemImg);
    document.getElementById("about-img").innerHTML = aboutImg;
}

/** * image name list
 *
 * @type {string[]}
 */
var ImgNameList = [
    "Norwegian Electric Locomotive No. 3 Ohma Electra i NTNU.jpg",
    "NTNU Gløshaugen.jpg",
    "Trondheim downtown.jpg"
];
function slideShow() {
    var counter = 0; // to keep track of current slide
    var slides = shuffleList(ImgNameList); // a collection of all of the slides path

    var showImage = function () {
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

    startLoop();

// set timeout
    var timeOut = setTimeout(startLoop, 5000);

    function startLoop() {
        nextImage();
        timeOut = setTimeout(startLoop, 5000); // repeat myself
    }

    function stopLoop() { // to be called when you want to stop the timer
        clearTimeout(timeOut);
    }
}

//run the script:

//run slide show
slideShow();

//startLoop();