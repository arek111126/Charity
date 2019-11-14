$(function () {


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

    })


});