<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>车辆信息页面 >> 修改页面</span>
    </div>
    <div class="providerAdd">
        <form id="carForm" name="carForm" method="post" action="#">
            <input type="hidden" name="method" value="modifyexe">
            <input type="hidden" name="uid" value=""/>
            <div>
                <label for="carBrand">品牌：</label>
                <input type="text" name="carBrand" id="carBrand" value="${carCheck.carBrand }">
                <font color="red"></font>
            </div>
            <div>
                <label for="carSeries">车系：</label>
                <input type="text" name="carSeries" id="carSeries" value="${carCheck.carSeries }">
                <font color="red"></font>
            </div>
            <div>
                <label for="carAgeLimit">年限：</label>
                <input type="text" name="carAgeLimit" id="carAgeLimit" value="${carCheck.carAgeLimit }">
                <font color="red"></font>
            </div>
            <div>
                <label for="carPrice">价格：</label>
                <input type="text" name="carPrice" id="carPrice" value="${carCheck.carPrice }">单位/万元
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/carmodify.js"></script>
