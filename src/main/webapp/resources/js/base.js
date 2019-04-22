$(document).ready(function () {


    $("#add-question").click(function () {

        let questnumb = $("div.survey-question-info").length + 1;
        let val = questnumb - 1;

        $("#survey-question-info").append(" <div class=\"survey-question-info mb-4\" >\n" +
            "\n" +
            "                        \n" +
            "                        <p><span class=\"number\">" + questnumb + "</span>Question</p>\n" +
            "\n" +
            "                        <div>\n" +
            "                            <label for=\"survey-question-body" + questnumb + "\">Type a question\n" +
            "                                <textarea id=\"survey-question-body" + questnumb + "\" name=\"questions[" + val + "].description\" class=\"mb-3\" maxlength=\"512\" name=\"question.description\" required=\"true\" rows=\"4\">\n" +
            "</textarea>\n" +
            "                            </label>\n" +
            "                        </div>\n" +
            "\n" +
            "\n" +
            "                        <p>Choose a type of the question:</p>\n" +
            "\n" +
            "                        <div class=\"row justify-content-around mt-3 mb-4\">\n" +
            "                            <div>\n" +
            "                                <label for=\"question-single" + questnumb + "\">\n" +
            "                                    <input id=\"question-single" + questnumb + "\" name=\"questions[" + val + "].type\" type=\"radio\" value=\"single\"/>\n" +
            "                                    One answer question</label>\n" +
            "                            </div>\n" +
            "                            <div>\n" +
            "                                <label for=\"question-multiple" + questnumb + "\">\n" +
            "                                    <input id=\"question-multiple" + questnumb + "\" name=\"questions[" + val + "].type\" type=\"radio\" value=\"multi\"/>\n" +
            "                                    Multiple choice question</label>\n" +
            "                            </div>\n" +
            "                        </div>\n" +

            "                            <ol>\n" +
            "                                \n" +
            "                                <div class=\"mb-4\">\n" +
            "                                    <li>\n" +
            "                                        <label for=\"survey-answer-body" + questnumb + "\">Answer\n" +
            "                                            <textarea id=\"survey-answer-body" + questnumb + "\" name=\"questions[" + val + "].questionAnswers[0].description\" class=\"mb-1\" maxlength=\"256\" required=\"true\" rows=\"2\">\n" +
            "</textarea>\n" +
            "                                        </label>\n" +
            "\n" +
            "                                        <label for=\"survey-answer-is-right" + questnumb + "\">\n" +
            "                                            <input id=\"survey-answer-is-right" + questnumb + "\" name=\"questions[" + val + "].questionAnswers[0].right\" type=\"checkbox\" value=\"true\"/><input type=\"hidden\" name=\"_questions[" + val + "].questionAnswers[0].right\" value=\"on\"/>\n" +
            "                                            Is it a right answer?\n" +
            "                                        </label>\n" +
            "                                    </li>\n" +
            "                                </div>\n" +
            "                                <div class=\"mb-4\">\n" +
            "                                    <li>\n" +
            "                                        \n" +
            "                                        <label for=\"survey-answer-body-2-" + questnumb + "\">Answer\n" +
            "                                            <textarea id=\"survey-answer-body-2-" + questnumb + "\" name=\"questions[" + val + "].questionAnswers[1].description\" class=\"mb-1\" maxlength=\"256\" name=\"answer.description\" required=\"true\" rows=\"2\">\n" +
            "</textarea>\n" +
            "                                        </label>\n" +
            "                                        <label for=\"survey-answer-is-right-2-" + questnumb + "\">\n" +
            "                                            <input id=\"survey-answer-is-right-2-" + questnumb + "\" name=\"questions[" + val + "].questionAnswers[1].right\" type=\"checkbox\" value=\"true\"/><input type=\"hidden\" name=\"_questions[" + val + "].questionAnswers[1].right\" value=\"on\"/>\n" +
            "                                            Is it a right answer?\n" +
            "                                        </label>\n" +
            "                                    </li>\n " +
            "                                </div>\n" +
            "\n" +
            "                          <button type=\"button\" class=\"btn col-3 add-answer\" id=\"add-answer-" + questnumb + "\">Add answer</button>\n" +
            "                            </ol>\n" +
            "                    </div>\n" +
            "<hr>");
    });

    $(document).on("click", "button.add-answer", function () {

        let questNumb = $(this).attr('id').substring(11);
        let valQ = questNumb - 1;
        let ansNumb = $(this).parent().find($("li")).length + 1;
        let valA = ansNumb - 1;
        $(this).before(
            "                                <div class=\"mb-4\">\n" +
            "                                   <li>\n" +
            "                                        \n" +
            "                                        <label for=\"survey-answer-body-" + ansNumb + "-" + questNumb + "\">Answer\n" +
            "                                            <textarea id=\"survey-answer-body-" + ansNumb + "-" + questNumb + "\" name=\"questions[" + valQ + "].questionAnswers[" + valA + "].description\" class=\"mb-1\" maxlength=\"256\" name=\"answer.description\" required=\"true\" rows=\"2\">\n" +
            "</textarea>\n" +
            "                                        </label>\n" +
            "                                        <label for=\"survey-answer-is-right-" + ansNumb + "-" + questNumb + "\">\n" +
            "                                            <input id=\"survey-answer-is-right-" + ansNumb + "-" + questNumb + "\" name=\"questions[" + valQ + "].questionAnswers[" + valA + "].right\" type=\"checkbox\" value=\"true\"/><input type=\"hidden\" name=\"_questions[" + valQ + "].questionAnswers[" + valA + "].right\" value=\"on\"/>\n" +
            "                                            Is it a right answer?\n" +
            "                                        </label>\n" +
            "                                    </li>" +
            "                                </div>\n"
        );

    });



});






