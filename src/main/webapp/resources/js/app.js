function setSurveysCode() {
    document.getElementById("surveys-code").innerHTML = surveyId;
}


function updateChart() {
    $.get('/survey/' + surveyId + '/question/' + questionId + '/results-data', function (result) {
        var newSurveyData = result.values;

        if (result.votes == 0) {
            document.getElementById("chart").innerHTML = "<div class='mb-5 mt-5'><h2 class='text-center '>No answers yet!</h2></div>"
        } else {
        var same = true;


        if (surveyData.length !== newSurveyData.length) {
            same = false;
        } else {
            for (let i = 0; i < surveyData.length; i++) {
                if (surveyData[i].label !== newSurveyData[i].label ||
                    surveyData[i].value !== newSurveyData[i].value) {
                    same = false;

                    break;
                }
            }
        }

        if (!same) {
            surveyData = newSurveyData;
            pie.updateProp("data.content", surveyData);
        }
    }});
}
function startPolling() {
    var timerId = setInterval(function () {
        updateChart();
    }, 2000);
}
