<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户发布页面</span>
        </div>
<%--    action="${pageContext.request.contextPath}/user/addUser"--%>
        <div class="providerAdd">
            <form id="userIssueAdd" name="userIssueAdd" method="post"  action="${pageContext.request.contextPath}/carIssue/userIssueAdd" enctype="multipart/form-data">
<%--				<input type="hidden" name="method" value="add">--%>
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="carBrand">车辆品牌名称：</label>
                    <input type="text" name="carBrand" id="carBrand" >
					<font color="red"></font>
                </div><div>
                    <label for="carSeries">车的系列：</label>
                    <input type="text" name="carSeries" id="carSeries" >
					<font color="red"></font>
                </div>
                <div>
                    <label for="carAgeLimit">年限：</label>
                    <input type="text" Class="Wdate" id="carAgeLimit" name="carAgeLimit"
					readonly="readonly" onclick="WdatePicker();">
					<font color="red"></font>
                </div>
                <div>
                    <label for="carPrice">价格：</label>
                    <input type="text" name="carPrice" id="carPrice" >单位/万元
                    <font color="red"></font>
                </div>
                <div>
                    <label>上传图片：</label>
                    <input type="file" name="file">
                    <font color="red"></font>
                </div>
                <div class="providerAddBtn">
                    <input type="submit" name="add" id="add"  value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
        </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userIssueAdd.js"></script>
