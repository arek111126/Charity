$(function () {
    function Errors(xhr, status, err) {
        console.log(xhr);
        console.log(status);
        console.log(err);
    }

    $("#summaryButton").on("click", function () {
        // Fundation name and number of bags
        var institution = $("input[name='institution']:checked").next().next().children(":nth-child(1)");
        var fundationNameField = $("ul li:nth-child(2) span[class='summary--text']");
        var numbOfBagsField = $("ul li:nth-child(1) span[class='summary--text']");
        fundationNameField.text("Fundacja " + institution.text());
        var bags = $("input[name='quantity']").val();
        numbOfBagsField.text(bags + " worki z rzeczami  w dobrym stanie dla dzieci");

        //receive address
        var street = $("input[name='street']").val();
        var city = $("input[name='city']").val();
        var zipCode = $("input[name='zipCode']").val();
        var phone = $("input[name='phone']").val();

        var receiveData = $("#summaryInfo").children(":nth-child(1)").find("ul");
        receiveData.children(":nth-child(1)").text(street);
        receiveData.children(":nth-child(2)").text(city);
        receiveData.children(":nth-child(3)").text(zipCode);
        receiveData.children(":nth-child(4)").text(phone);

        //date of receipt
        var pickUpDate = $("input[name='pickUpDate']").val();
        var pickUpTime = $("input[name='pickUpTime']").val();
        var pickUpComment = $("textarea[name='pickUpComment']").val();

        var recipeData = $("#summaryInfo").children(":nth-child(2)").find("ul");
        recipeData.children(":nth-child(1)").text(pickUpDate);
        recipeData.children(":nth-child(2)").text(pickUpTime);
        if (pickUpComment.length > 0) {
            recipeData.children(":nth-child(3)").text(pickUpComment);
        } else {
            recipeData.children(":nth-child(3)").text("Brak uwag");
        }

    });
    //After choose category find containg category institution
    $("#next1").on('click', function () {
        var checkedChecboxes = [];
        $("input[name='categories']:checked").each(function () {
            checkedChecboxes.push($(this).val());
        });
        var requestUrl = "/app/category/" + checkedChecboxes + "/institution"
        $.ajax({
            url: requestUrl,
            data: {},
            type: "GET",
            dataType: "json"
        }).done(function (result) {
            $(result).each(function () {
                var newDiv = $("<div class=\"form-group form-group--checkbox institutions\">\n" +
                    "                    <label>\n" +
                    "                        <input type=\"radio\" name=\"institution\" value=" + this.id + ">\n" +
                    "                        <span class=\"checkbox radio\"></span>\n" +
                    "                        <span class=\"description\">\n" +
                    "                  <div class=\"title\">" + this.name + "</div>\n" +
                    "                  <div class=\"subtitle\">" + this.description + "</div>\n" +
                    "                </span>\n" +
                    "                    </label>\n" +
                    "                </div>")
                $("div[id='institutionList']").find("h3").after(newDiv);



            })


        }).fail(function (xhr, status, err) {
            Errors(xhr, status, err);
        })


    });
    //if click comback button delete current institution
$("#combackTocategory").on('click',function () {
    $(".institutions").remove();
});

});