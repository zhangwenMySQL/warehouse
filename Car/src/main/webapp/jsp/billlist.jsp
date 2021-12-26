<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/head.jsp"%>

<div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>订单管理页面</span>
       </div>
       <div class="search">
       <form method="get" action="#" id="billQuery">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>商品名称：</span>
			<input name="queryProductName" type="text" value="${queryProductName }">
			 
			<span>供应商：</span>
			<select name="queryProviderId" id="queryProviderId">
<%--				<c:if test="${providerList != null }">--%>
<%--				   <option value="0">--请选择--</option>--%>
<%--				   <c:forEach var="provider" items="${providerList}">--%>
<%--				   		<option <c:if test="${provider.id == queryProviderId }">selected="selected"</c:if>--%>
<%--				   		value="${provider.id}">${provider.proName}</option>--%>
<%--				   </c:forEach>--%>
<%--				</c:if>--%>
       		</select>
			 
			<span>是否付款：</span>
			<select name="queryIsPayment" >
				<option value="">--请选择--</option>
				<option value="1" >未付款</option>
				<option value="2" >已付款</option>
       		</select>
			
			 <input	value="查 询" type="submit" id="searchbutton">
			 <a href="${pageContext.request.contextPath }/jsp/billadd.jsp">添加订单</a>
		</form>
       </div>
       <!--账单表格 样式和供应商公用-->
      <table class="providerTable" cellpadding="0" cellspacing="0" id="billTable">

      </table>
    <div id="billDiv">

    </div>
  </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<%@include file="rollpage.jsp"%>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billlist.js"></script>