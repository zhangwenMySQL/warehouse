<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>用户管理页面 >> 用户审核页面</span>
    </div>
    <div class="providerAdd">
        <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath}/user/checkResult">
            <div>
                <label for="userAuditStatus">审核状态：</label>
                    审核通过 <input type="radio" name="userAuditStatus" id="userAuditStatus"  value="0">
                    审核不通过 <input type="radio" name="userAuditStatus" id="userAuditStatus"  value="-1">
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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/usermodify.js"></script>
