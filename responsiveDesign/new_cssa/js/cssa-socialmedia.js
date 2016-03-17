/**
 * Facebook JS-SDK
 */
$(document).ready(function () {
    var p = {
        url: "http://cssa.no", /*获取URL，可加上来自分享到QQ标识，方便统计*/
        desc: "", /*分享理由(风格应模拟用户对话),支持多分享语随机展现（使用|分隔）*/
        title: $("meta[name='title']").attr("content"), /*分享标题(可选)*/
        summary: $("meta[name='summary']").attr("content"), /*分享摘要(可选)*/
        images: $("meta[name='pics']").attr("content"), /*分享图片(可选)*/
        flash: $("meta[name='flash']").attr("content"), /*视频地址(可选)*/
        site: 'http://cssa.no/' /*分享来源(可选) 如：QQ分享*/
    };
    var s = [];
    for (var i in p) {
        s.push(i + '=' + encodeURIComponent(p[i] || ''));
    }
    $("a#socialicon-fb").attr("href", ['http://www.facebook.com/sharer.php?', s.join('&')].join(''));
});

/**
 * QQ JS-SDK
 */
$(document).ready(function () {
    var p = {
        url: location.href, /*获取URL，可加上来自分享到QQ标识，方便统计*/
        desc: "", /*分享理由(风格应模拟用户对话),支持多分享语随机展现（使用|分隔）*/
        title: $("meta[name='title']").attr("content"), /*分享标题(可选)*/
        summary: $("meta[name='summary']").attr("content"), /*分享摘要(可选)*/
        pics: $("meta[name='pics']").attr("content"), /*分享图片(可选)*/
        flash: $("meta[name='flash']").attr("content"), /*视频地址(可选)*/
        site: 'http://cssa.no/' /*分享来源(可选) 如：QQ分享*/
    };
    var s = [];
    for (var i in p) {
        s.push(i + '=' + encodeURIComponent(p[i] || ''));
    }
    $("a#socialicon-qq").attr("href", ['http://connect.qq.com/widget/shareqq/index.html?', s.join('&')].join(''));
//        document.write(['<a class="icon fa-qq" href="http://connect.qq.com/widget/shareqq/index.html?',s.join('&'),'" target="_blank">分享到QQ</a>'].join(''));
});