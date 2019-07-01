<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
       Object user = session.getAttribute("user");
       if (user==null){
           out.print("<script>alert('你还没有登录，可以滚啦');location.href='login.jsp'</script>");
       }
%>
<!-- saved from url=(0050)http://localhost:8080/HouseRent/manage!list.action -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>青鸟租房 - 用户管理</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR ></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV>
<DIV class=search>【欢迎：${user.name}】<LABEL class="ui-green searchs"><a href="selectHouseExtByUserId" title="">管理房屋</a></LABEL><LABEL class="ui-green searchs"><a href="goFaBu" title="">发布房屋信息</a></LABEL>
<LABEL class=ui-green><INPUT onclick='document.location="login.jsp"' value="退       出" type=button name=search></LABEL>
</DIV></DIV>
<DIV class="main wrap">
<DIV id=houseArea>
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="fo">
  <TR>
    <TD class=house-thumb><SPAN><A href="details.htm" target="_blank"><img src="http://localhost:80/${fo.path}" width="100" height="75" alt=""></A></SPAN></TD>
    <TD>
      <DL>
          <DT><A href="details.htm" target="_blank">${fo.title}</A><c:if test="${fo.ispass!=0}"><div style="color: red">(审核中)</div></c:if></DT>
        <DD>${fo.dname}&nbsp;&nbsp;${fo.sname},面积:${fo.floorage}m&su p2;,${fo.tname}<BR>联系方式：${fo.contact} </DD></DL></TD>
    <TD class=house-type><LABEL class=ui-green><INPUT onclick=window.location.href="getUserHouse?id=${fo.id}" value="修    改" type=button name=search></LABEL></TD>
    <TD class=house-price><LABEL class=ui-green><INPUT  onclick=window.location.href="isdel?id=${fo.id}"    value="删    除" type=button name=search></LABEL></TD></TR>
  </c:forEach>
</TBODY></TABLE></DIV>
<DIV class=pager>
<UL>
    <c:forEach begin="1" end="${pageInfo.pages}" step="1" var="page">
        <LI class=current><A id=widget_338868862
                             href="selectHouseExtByUserId?page=${page}"
                             parseContent="true" showError="true" targets="houseArea"
                             ajaxAfterValidation="false" validate="false"
                             dojoType="struts:BindAnchor">${page}</A>
        </LI>
    </c:forEach>
 </UL><SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
