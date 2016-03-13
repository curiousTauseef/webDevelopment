/**
 * Created by GuoJunjun on 06/03/16. <junjunguo.com>
 *
 */
var textfil = './doc/mylikesphotos.txt';

var reader = new XMLHttpRequest() || new ActiveXObject('MSXML2.XMLHTTP');
var count  = 0; // slider count
var imgList;

/**
 * load saved txt file
 * @param textFile
 */
function loadJSONfile(textFile) {
    reader.open('get', textFile, true);
    reader.onreadystatechange = getJSONobject;
    reader.send(null);
}
/**
 * cast to jsonString variable if succeed
 */
function getJSONobject() {
    if (reader.readyState == 4) {
        imgList = shuffleList(JSON.parse(reader.responseText).photos);
        loadimages();
        console.log("list size: " + imgList.length);
        $('#my-btn-more-photos').on('click', function () {
            loadimages();
        });
    } else {
        // error occurred
    }
}

/**
 * Load images to htmllist
 */
function loadimages() {
    var slideHtml = '';
    for (i = 0; i < 6; i++) {
        var j = (count * 6 + i) % (imgList.length);
        if (i == 0) {
            slideHtml += '<div class="item active">';
        } else {
            slideHtml += '<div class="item">';

        }

        slideHtml +=
            '   <img src="http://static.panoramio.com/photos/medium/' + imgList[j].id + '.jpg" alt="junjunguo.com">' +
            '   <div class="carousel-caption">' +
            '       <p>' + imgList[j].title + '</p>' +
            '   </div>' +
            '</div>';
    }
    //$("#my-carousel").carousel("pause").removeData();
    document.getElementById("my-slides").innerHTML = slideHtml;
    count++;
}

/**
 * Shuffle a list
 *
 * @param list
 * @returns {Array}
 */
function shuffleList(list) {
    var copy = [], index, n = list.length;
    while (n) {
        index = Math.floor(Math.random() * (n--));
        copy.push(list[index]);
        list.splice(index, 1);
    }
    return copy;
}
/**
 * get a random file: path: ./doc/   ;  total files 7
 * @returns {string}
 */
function getRandomFile() {
    return './doc/myphotos' + Math.floor(Math.random() * (7)) + '.txt';
}

loadJSONfile(textfil);

/*
 original
 large
 medium (default value)
 small
 thumbnail
 square
 mini_square
 */