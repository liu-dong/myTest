$("input[type='radio'][name='inlineRadio']").click(function () {
    that.businessScene = $("input[type='radio'][name='inlineRadio'][checked='checked']").val();
    test(businessScene);
});