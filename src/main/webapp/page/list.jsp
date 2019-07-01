<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <SCRIPT type="text/javascript" src="/admin/js/jquery-1.8.3.js"></SCRIPT>
  <script type="text/javascript">
      $(function () {
          $.post("getType",null,function (date) {
              for (var i = 0; i <date.length ; i++) {
                  var type = "<OPTION value="+date[i].id+">"+date[i].name+"</OPTION>";
                  $("#type").append($(type)); //追加节点
              }
              if(${condition.tid!=null}){
                  alert(${condition.tid});
                  $("#type").val(${condition.tid});
              }
          },"json")

          $.post("getdistrictAll",null,function (date) {
              for (var i = 0; i <date.length ; i++) {
                  var type = "<OPTION value="+date[i].id+">"+date[i].name+"</OPTION>";
                  $("#district").append($(type)); //追加节点
              }
              if(${condition.did!=null}){
                  alert(${condition.did});
                  $("#district").val(${condition.did});
              }
          },"json")


          $("li select").change(function () {
              alert("提交");
               $("#sform").submit();
          })
      })


  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action=goHouse>
  <DT>
  <UL>
    <LI class=bold>房屋信息</LI>
    <LI>标题：<INPUT class=text type=text name=title value="${condition.title}" > <LABEL class=ui-blue><INPUT onclick=doSearch() value=搜索房屋 type=submit name=search></LABEL>
    </LI></UL></DT>
  <DD>
  <UL>
    <INPUT type="text" hidden="hidden" value="${condition.page}" name="page">
    <LI class=first>价格 </LI>
    <LI><SELECT name=price> <OPTION selected value="">不限</OPTION> <OPTION 
      value=0-100>100元以下</OPTION> <OPTION value=100-200>100元—200元</OPTION> 
      <OPTION value=200-1000000>200元以上</OPTION></SELECT> </LI></UL></DD>
  <DD>
  <UL>
    <LI class=first>房屋位置</LI>
    <LI><SELECT id=district name=did> <OPTION selected
      value="">不限</OPTION> </SELECT> </LI></UL></DD>
  <DD>
  <UL>
    <LI class=first>房型</LI>
    <LI><SELECT name=tid id="type"> <OPTION selected value="">不限</OPTION> </SELECT>
  </LI></UL></DD>
  <DD>
  <UL>
    <LI class=first>面积 </LI>
    <LI><SELECT name=floorage> <OPTION selected value="">不限</OPTION> <OPTION 
      value=0-40>40以下</OPTION> <OPTION value=40-500>40-500</OPTION> <OPTION 
      value=500-1000000>500以上</OPTION></SELECT> </LI></UL></DD></FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="h">
  <TR>
    <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.jsp" target="_blank">${h.title}</A></DT>
        <DD>${h.dname}&nbsp;${h.sname},${h.floorage}平米<BR>联系方式：${h.contact} </DD></DL></TD>
    <TD class=house-type>${h.tname}</TD>
    <TD class=house-price><SPAN>${h.price}</SPAN>元/月</TD></TR>
  </c:forEach>
  </TBODY></TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="goHouse?page=1">首页</A></LI>
  <LI><A href="goHouse?page=${pageInfo.prePage==0?1:pageInfo.prePage}">上一页</A></LI>
  <LI><A href="goHouse?page=${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage}">下一页</A></LI>
  <LI><A href="goHouse?page=${pageInfo.pages}">末页</A></LI></UL><SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
