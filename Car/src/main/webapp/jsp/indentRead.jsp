<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>订单详情页面</span>
        </div>
<%--    action="${pageContext.request.contextPath}/user/addUser"--%>
        <div class="providerAdd">
            <form id="userIssueAdd" name="userIssueAdd" method="post"  action="#">
<%--				<input type="hidden" name="method" value="add">--%>
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="carBrand">车辆品牌名称：</label>
                    <input type="text" name="carBrand" id="carBrand" value="${readCar.carBrand}" readonly>
					<font color="red"></font>
                </div><div>
                    <label for="carSeries">车的系列：</label>
                    <input type="text" name="carSeries" id="carSeries" value="${readCar.carSeries}" readonly>
					<font color="red"></font>
                </div>
                <div>
                    <label for="carAgeLimit">年限：</label>
                    <input type="text" Class="Wdate" id="carAgeLimit" name="carAgeLimit"
                           onclick="WdatePicker();" value="${readCar.carAgeLimit}" readonly>
					<font color="red"></font>
                </div>
                <div>
                    <label for="carPrice">价格：</label>
                    <input type="text" name="carPrice" id="carPrice" value="${readCar.carPrice}" readonly>
                    <font color="red"></font>
                </div>
                <div class="providerAddBtn">
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
        </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userIssueAdd.js"></script>
