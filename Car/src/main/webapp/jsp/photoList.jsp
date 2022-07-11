<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>查看图片页面 </span>
    </div>
    <div class="providerAdd">
        <form id="indentModifyForm" name="indentModifyForm" method="post" action="#">
            <input type="hidden" name="method" value="modifyexe">
            <input type="hidden" name="uid" value=""/>
            <img id="image" alt="图片" title="图片" width="800px" height="500px">
        </form>
    </div>
</div>
</section>
<%@include file="/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/photo.js"></script>
