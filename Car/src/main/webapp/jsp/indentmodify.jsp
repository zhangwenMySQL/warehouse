<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>订单信息页面 >> 修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="indentModifyForm" name="indentModifyForm" method="post" action="#">
            <input type="hidden" name="method" value="modifyexe">
            <input type="hidden" name="uid" value=""/>

            <div>
                <label for="isDeal">交易状态：</label>
                交易成功 <input type="radio" name="isDeal" id="isDeal"  value="0">
                交易失败 <input type="radio" name="isDeal" id="isDeal"  value="-1">
            </div>

            <div class="providerAddBtn">
                <input type="submit" name="save" id="save" value="保存"/>
                <input type="button" id="back" name="back" value="返回"/>
            </div>
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/indentmodify.js"></script>
