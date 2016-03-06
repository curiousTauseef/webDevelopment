/**
 * Created by GuoJunjun on 05/03/16. <junjunguo.com>
 *
 */

/**request options
 *
 * @type {{user: string, tag: string, order: *}}
 */
var myRequest = {'user': '2481456', 'tag': 'like', 'order': panoramio.PhotoOrder.DATE_DESC};

/**display options
 *
 * @type {{width: number, height: number, columns: number, rows: number, croppedPhotos: boolean}}
 */
var myOptions = {
    'width':         800,
    'height':        400,
    'columns':       20,
    'rows':          2,
    'croppedPhotos': true
};
/**where to display
 *
 * @type {Element}
 */
var wapiblock = document.getElementById('wapiblock');
var widget    = new panoramio.PhotoListWidget(wapiblock, myRequest, myOptions);
widget.setPosition(0);
/**
 * get photos as a array list
 */
var array = widget.getPhotos();

//var photos = "";// string
var jsonString = '{ "photos" : ['; // json string
/**
 * click when photos are loaded
 *
 * photos will be read and cached to variables
 */
$('#my-btn').on('click', function () {
    for (i = 0; i < array.length; i++) {
        if (array[i] != null) {
            jsonString += '{ "id":"' + array[i].getPhotoId() + '", "title":"' + array[i].getPhotoTitle() + '" },';
        }
    }
    jsonString = jsonString.slice(0, -1);
    jsonString += ']}';
    //console.log(jsonString);
});
/**
 * make file ready
 */
$('#my-btn-done').on('click', function () {
    download(jsonString, 'myphotos.txt', 'text/plain');
});
/** download the file: convert data to text file
 *
 * @param text
 * @param name
 * @param type
 */
function download(text, name, type) {
    var a      = document.getElementById("download");
    var file   = new Blob([text], {type: type});
    a.href     = URL.createObjectURL(file);
    a.download = name;
}


// load image

var textfil = './doc/myphotos.txt';

var reader = new XMLHttpRequest() || new ActiveXObject('MSXML2.XMLHTTP');
var jsonString;
var array;
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
        jsonString = reader.responseText;
        array      = JSON.parse(jsonString).photos;
        console.log(array);
        loadimages(array);
    } else {
        // error occurred
    }
}

/**
 * Load images to htmllist
 */
function loadimages(imglist) {
    for (i = 0; i < imglist.length; i++) {
        addImage(imglist[i].title, 'http://static.panoramio.com/photos/small/' + imglist[i].id + '.jpg')
        //        var fileType = str[1];
        //htmllist.push(
        //    "<img class='img-responsive' src='http://static.panoramio.com/photos/small/" + imglist[i].id + "'" +
        //    "alt='junjunguo.com'>" + "\n" + "<h3>" +
        //    imglist[i].title + "</h3>");
    }
}

function addImage(title, imgsrc) {
    var liTag      = document.createElement("li");
    var imgTag     = document.createElement("img");
    imgTag.className += " img-responsive wow bounceIn";
    var h4Tag      = document.createElement("h4");
    h4Tag.className += " wow rotateIn";
    var imageTitle = document.createTextNode(title);
    imgTag.src     = imgsrc;
    h4Tag.appendChild(imageTitle);
    liTag.appendChild(imgTag);
    liTag.appendChild(h4Tag);
    document.getElementById("show-image").appendChild(liTag);
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