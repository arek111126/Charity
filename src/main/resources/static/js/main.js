$(function () {
    function Errors(xhr, status, err) {
        console.log(xhr);
        console.log(status);
        console.log(err);
    }

    function mod(i) {
        return i % 2;
    }

    $.ajax({
        url: "/institution/all",
        data: {},
        type: "GET"
    }).done(function (result) {
        console.log("items");
        console.log(result);

        $.each(result, function (i, item) {
            var div = $("<div class=\"col\">\n" +
                "                    <div class=\"title\">" + item.name + "</div>\n" +
                "                    <div class=\"subtitle\">" + item.description + "</div>\n" +
                "                </div>");
            if (mod(i) == 0) {
                $(".help--slides .help--slides-items").append($("<li></li>"));
            }
            $(".help--slides .help--slides-items li:last-child").append(div);


        })


    }).fail(function (xhr, status, err) {

        Errors(xhr, status, err);
    });

    $.ajax({
        url: "/donation/statistic",
        data: {},
        type: "GET",
        dataType: "json"
    }).done(function (result) {
        $(".stats .container div:first-child em").text(result.sumQuantity);
        $(".stats .container div:last-child em").text(result.sumCategory);

    }).fail(function (xhr, status, err) {
        Errors(xhr, status, err);
    });

});