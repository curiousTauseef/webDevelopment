/**
 * Created by GuoJunjun on 07/03/16. <junjunguo.com>
 *
 */

var flickrURL = "https://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=54f8af3fd039bb7376cf5f26eef23522&photoset_id=72157646082323705&user_id=125412181%40N03&format=json&nojsoncallback=1&auth_token=72157665498906476-54d9a084cb5ad53e&api_sig=ad4abc6185cd1771d61e4d399a24c625";

var textfil = './doc/flickrFlowers.txt';

var reader = new XMLHttpRequest() || new ActiveXObject('MSXML2.XMLHTTP');
var photoList;

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
        photoList = JSON.parse(reader.responseText).photoset.photo;
        //console.log(photoList);
        loadJSON();
    } else {
        // error occurred
    }
}

/**
 * Load images to htmllist
 */
function loadJSON() {
    //var photos = "";// string
    var jsonString = '{ "photos" : ['; // json string
    //for (var j in photoList) {
    for (i = 0; i < photoList.length; i++) {
        //console.log(photoList[i]);
        var j = photoList[i];
        //https://farm1.staticflickr.com/2/1418878_1e92283336_m.jpg
        //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
        var url = "https://farm" + j.farm + ".staticflickr.com/" + j.server + "/" + j.id + "_" + j.secret + "_b.jpg";
        jsonString += '{ "url":"' + url + '", "title":"' + j.title + '" },';

    }
    jsonString = jsonString.slice(0, -1);
    jsonString += ']}';
    console.log("\n" + jsonString);
}

loadJSONfile(flickrURL);