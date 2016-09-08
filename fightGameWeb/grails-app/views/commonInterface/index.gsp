<%--
  Created by IntelliJ IDEA.
  User: asus-pc
  Date: 2016-7-21
  Time: 22:12
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <asset:javascript src="Fight.js"/>
    <asset:javascript src="jquery-2.0.3.js"/>
</head>

<body>

<span id="playerNameText" style="display:none">
    请输入用户名:<input id="playerNameInput" name="playerName" value="1"/><br/>
    <input type="submit" name="submit" value="确认" onclick="generatePlayer()">
</span>

<span id="errorMsg" style="color: red">
</span>

<span id="menu" style="display: none">
    <button onclick="showUserInfo()">显示英雄信息</button>
    <button onclick="fight()">匹配战斗</button>
    <button onclick="showStatistics()">显示战斗统计</button>
</span>

<span id="userInfo"></span>

<span id="modifyHero"></span>

<span id="showStatistics"></span>

<script>
//    showErrorMsg("test");
    var db = FT.DBConnector.getInstance()
    db.openDB(function () {
        db.getToken(
                function (tokenItem) {
                    console.log("get it")
                    console.log("token:")
                    console.log(tokenItem)
                    if (tokenItem == null) {
                        addInputText();
                    } else {
                        getUserInfo(tokenItem.token, function (result) {
                            if (result.success === false) {
                                addInputText()
                            } else {
                                getHeroesInfo(result);
                            }
                        });
                    }
                }, function (e) {
                    console.log("get failed")
                    db.addToken("test1", function () {
                        console.log("insert success")
                    }, function (e) {
                        console.log("insert failed:")
                        console.log(e)
                    })
                }
        )
    }, function (e) {
        console.log("open db error:")
        console.log(e)
    });

    function addInputText() {
        $("#playerNameText").css('display', "")
    }

    function showErrorMsg(msg) {
        $("#errorMsg").html("test")
//        document.getElementById("errorMsg").innerText = msg
    }

    function hideInputText() {
        $("#playerNameText").css('display', "none")
    }

    function clearErrorMsg() {
        $("#errorMsg").html("")
    }

    function generatePlayer() {
        var playerName = $("#playerNameInput").val()
        if (playerName != null) {
            console.log(playerName)
            $.ajax({
                url: 'f/generateToken?playerName=' + playerName,
                success: function (msg) {
                    console.log(msg);
                    if (msg.success === false){
                        showErrorMsg(msg.msg)
                    }else {
                        db.addToken(msg.securityKey)
                        hideInputText();
                        clearErrorMsg();
                        domain(msg.player, msg.heroes);
                    }
                }
            })
        }
    }

    function getUserInfo(token, onsuccess) {
        $.ajax({
            url: "f/getPlayerInfoBySecurityKey?key=" + token,
            success: function (result) {
                console.log(result);
                onsuccess && onsuccess(result)
            }
        })
    }

    function getHeroesInfo(playerInfo) {
        $.ajax({
            url:"f/getHeroesInfosByPlayer?playerId="+playerInfo.id,
            success:function(result){
                domain(playerInfo,result)
            }
        })
    }

    function domain(playerInfo, heroesInfo) {
        console.log(playerInfo);
        console.log(heroesInfo);
        _playerInfo = playerInfo;
        _heroesInfo = heroesInfo;
        showMenu();
    }

    var _playerInfo = null;
    var _heroesInfo = null;

    function showMenu(){
        $("#menu").css("display","")
    }

    function hideMenu(){
        $("#menu").css("display","none")
    }

    function showUserInfo(){
        $("userInfo").html(
            "<div><div>"
        )
    }

    function hideUserInfo(){
        $("#userInfo").html("")
    }

    function fight(){}
    function showStatics(){}

</script>

</body>
</html>