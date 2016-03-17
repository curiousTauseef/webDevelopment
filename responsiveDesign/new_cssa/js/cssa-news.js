$(document).ready(function () {
    $("[id^=updateNewsContent]").click(function () {
        var id = this.id;
        var newsContentId = this.id.substring(17, this.id.length);
        if($("#newsContentPicDescription" + newsContentId).val() == null){
            var newsContentPicDescription= "";
        } else{
            newsContentPicDescription= $("#newsContentPicDescription" + newsContentId).val();
        }
        $.ajax({
            type: "POST",
            url: "/committee/news/updateNewsContent",
            data: {
                newsContentId: newsContentId,
                newsContentContent: $("#newsContentContent" + newsContentId).val(),
                newsContentPicDescription: newsContentPicDescription,
                newsContentPriority: $("#newsContentPriority" + newsContentId).val()
            },
            beforeSend: function () {
                alert("updating");
            },
            success: function (data) {
                if (data == "success") {
                    alert("success");
                } else {
                    alert("error");
                }
            }
        })
    });

    $("[id^=deleteNewsContent]").click(function () {
        var confirm = window.confirm("Are you sure to delete?");
        if (confirm == false) {
            throw new Error();
        }
        var id = this.id;
        var newsContentId = this.id.substring(17, this.id.length);
        $.ajax({
            type: "POST",
            url: "/committee/news/deleteNewsContent",
            data: {
                newsContentId: newsContentId
            },
            beforeSend: function () {
                alert("deleting");
            },
            success: function (data) {
                if (data == "success") {
                    $("#newsContent" + newsContentId).remove();
                } else {
                    alert("error");
                }
            }
        })
    });
});