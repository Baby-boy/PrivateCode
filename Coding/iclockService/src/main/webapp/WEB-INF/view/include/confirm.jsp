<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglibs.jspf" %>

<style>
    .pop-mask {
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        z-index: 999;
        background: url(${ctx}/images/mask-bg.png) repeat;
    }

    .popup {
        width: 540px;
        height: 295px;
        position: fixed;
        left: 28%;
        margin-right: 320px;
        top: 23%;
        z-index: 1000;
        background-color: #fff;
    }

    .popup img {
        float: right;
        margin: 10px 10px 0 0;
        cursor: pointer;
    }

    .popup p {
        font-family: "Microsoft YaHei";
        font-size: 18px;
        text-align: center;
        margin-top: 100px;
    }

    .popup > div {
        margin: 60px 0 0 180px;
    }

    .popup > div a {
        width: 115px;
        line-height: 35px;
        font-size: 12px;
        color: #fff;
        text-align: center;
        letter-spacing: 1px;
        float: left;
        background-color: #d14f98;
        border-radius: 3px;
        margin-left: 24px;
    }
</style>
<div class="pop-mask" style=" display: none;">
    <img src="${ctx}/images/zd.gif" style="display: block;position: absolute;top: 50%;left: 50%;">
</div>
<div class="popup" style="display: none;">
    <img src="${ctx}/images/close.png">

    <p>您已成功加入书单！</p>

    <div>
        <a href="javascript:void(0);">确定</a>
        <br class="clear">
    </div>
</div>

<script>
    require(['jquery'], function () {
        $(" .popup img,.popup >div a").click(function () {
            $(".pop-mask").hide();
            $(".popup").hide();
        });
    });
</script>