/**
 * This file is part of cssa
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on March 16, 2016.
 */

var freader = new XMLHttpRequest() || new ActiveXObject('MSXML2.XMLHTTP');
var c2015; // slider count
var c2016; // slider count
var url2015;
var url2016;
/**
 * load saved txt file
 * @param textFile
 * @param nr file number
 */
function loadMyFile(textFile, nr) {
    freader.open('get', textFile, true);
    freader.onreadystatechange = toJSONobject(nr);
    freader.send(null);
}
/**
 * cast to jsonString variable if succeed
 */
function toJSONobject(nr) {
    if (freader.readyState == 4) {
        var image = new Image();
        var urlList = shuffleFList(JSON.parse(freader.responseText).photos);
        image.src = urlList[0].url;
        image.onload = function () {
            //console.info("Image loaded !");
            if (nr == 2015) {
                url2015 = urlList;
                c2015 = 0;
            } else if (nr == 2016) {
                url2016 = urlList;
                c2016 = 0;
            }
            loadFimgs(nr);
        };
        image.onerror = function () {
            //console.error("Cannot load image");
        }
    } else {
        // error occurred
    }
}

/**
 * Load images to htmllist
 */
function loadFimgs(nr) {
    var urlList;
    var count;
    if (nr == 2015) {
        $("#carousel-flickr-2015").carousel(0);
        count = c2015;
        urlList = url2015;
    } else if (nr == 2016) {
        $("#carousel-flickr-2016").carousel(0);
        count = c2016;
        urlList = url2016;
    }
    var fhtml = '';
    for (var i = 0; i < 6; i++) {
        var j = (count * 6 + i) % (urlList.length);
        if (i == 0) {
            fhtml +=
                '<div class="item active animated ' + animate() + '">';
        } else {
            fhtml +=
                '<div class="item">';
        }
        fhtml +=
            '   <img src="' + urlList[j].url +
            '" alt="photo: ' + urlList[j].title + '">' +
            '   <div class="carousel-caption">';

        if (i == 5) {
            fhtml +=
                '<div>' +
                '   <h4 class="animated fadeInDownBig"><a href="https://www.flickr.com/photos/guojunjun" class="icon fa-flickr">' +
                '       View all photos at Flickr</a></h4>' +
                '   <h2 class="animated flip"><a id="flickr-next" class="icon fa-forward"> Next slider</a></h2>' +
                '</div>';
        }

        fhtml +=
            '       <p class="animated ' + animate() + '">' + urlList[j].title + '</p>' +
            '   </div>' +
            '</div>';
    }
    document.getElementById("flickr-slides-2016").innerHTML = fhtml;
    count++;
    document.getElementById("flickr-next").addEventListener("click", loadFimgs);
    if (nr == 2015) {
        c2015 = count;
    } else if (nr == 2016) {
        c2016 = count;
    }
}

/**
 * Shuffle a list
 *
 * @param list
 * @returns {Array}
 */
function shuffleFList(list) {
    var copy = [], index, n = list.length;
    while (n) {
        index = Math.floor(Math.random() * (n--));
        copy.push(list[index]);
        list.splice(index, 1);
    }
    return copy;
}
// --------- use handled json  ---------
var sf2015 = '../images/springFestival2015.txt'; // 0
var sf2016 = '../images/springFestival2016.txt'; // 1

loadMyFile(sf2016, 2016);
loadMyFile(sf2015, 2015);
