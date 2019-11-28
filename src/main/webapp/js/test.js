$("input[type='radio'][name='inlineRadio']").click(function () {
    that.businessScene = $("input[type='radio'][name='inlineRadio'][checked='checked']").val();
    test(businessScene);
});

let selectNameArr = [];
const Lottery = function(nameArr){
    let _name = ''
    function random (){
        _name = nameArr[(Math.random().toFixed(4) * 10000) % nameArr.length]
        selectNameArr.push(_name)
        if(selectNameArr.length !== [...new Set(selectNameArr)].length){
            selectNameArr.splice(selectNameArr.length-1,1)
            random()
        }
    }
    if(selectNameArr.length === 0){
        random()
    } else if(selectNameArr.length === 1){
        if(Math.random().toFixed(1) < 0.1){
            _name = selectNameArr[0]
            selectNameArr.splice(0,1)
        }else {
            random()
        }
    } else if(selectNameArr.length === 2){
        if(Math.random().toFixed(1) < 0.1){
            _name = selectNameArr[0]
            selectNameArr.splice(0,1)
        }else if(Math.random().toFixed(1) < 0.2){
            _name = selectNameArr[1]
            selectNameArr.splice(1,1)
        }else {
            random()
        }
    } else if(selectNameArr.length === 3){
        _name = selectNameArr[0]
        selectNameArr.splice(0,1)
    }

    return _name
}
// [1,2,3]
const nameArrays = ['111','222','333','444','555','666']
const aa = Lottery(nameArrays)
