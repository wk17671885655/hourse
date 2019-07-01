<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK 
rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <SCRIPT type="text/javascript" src="/admin/js/jquery-1.8.3.js"></SCRIPT>
  <script type="text/javascript">
$(function () {
    //根据区域自动加载
    showStreet($("#district").val());
    //给区域添加改变事件，去后台查询对应街道
     $('#district').change(function () {
              var val = $(this).val();
              showStreet(val);
     })
})
    // 展示街道数据
    function showStreet(districtId) {
        $("#street option").remove();   //清空选项
        //发送异步请求获取数据
        $.post("getStreetByDistrict",{"districtId": districtId},function (date) {
            for (var i = 0; i <date.length ; i++) {
                var street = "<OPTION value="+date[i].id+">"+date[i].name+"</OPTION>";
                $("#street").append($(street)); //追加节点
            }
           $("#street").val(${houseExt.streetId});
        },"json")
    }
  </script></HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post name=add.action 
action="updateHouse" enctype="multipart/form-data">
  <%--multipart/form-data 大文件传输表单设置--%>
<DIV class=infos>
<TABLE class=field>
  <TBODY>
  <%--<TR>--%>
    <%--<TD class=field>用户id</TD>--%>
    <%--<TD><INPUT id=userID class=text type=text name=id value="${user.id}"> </TD></TR>--%>
  <%--<TR>--%>
  <TR>
    <TD class=field>标　　题：</TD>
    <input type="text" hidden="hidden" name="id" value="${houseExt.id}">
    <TD><INPUT id=add_action_title class=text type=text name=title value="${houseExt.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD><SELECT class=text name=typeId>
      <c:forEach items="${types}" var="t">
        <OPTION   <c:if test="${houseExt.typeId==t.id}">selected="selected"</c:if>  value=${t.id}>${t.name}</OPTION>
      </c:forEach>
    </SELECT></TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text  value="${houseExt.floorage}"
name=floorage></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price  value="${houseExt.price}" > </TD></TR>
  <TR>
    <TD class=field>房产证日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<fmt:formatDate value="${houseExt.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate>" ></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=district_id id="district">
      <c:forEach items="${districts}" var="d">
        <OPTION <c:if test="${houseExt.districtId==d.id}">selected="selected"</c:if>  value=${d.id}>${d.name}</OPTION>
      </c:forEach>

    </SELECT> 街：<SELECT class=text
        name=streetId  id="street"><OPTION selected value=1001>请选择</OPTION></SELECT> </TD></TR>
  <TR>
    <TD class=field>联系方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${houseExt.contact}"> </TD></TR>
  <TR>
      <td><img src="http://localhost:80/${houseExt.path}" width="120" ></Td>
    <TD class=field>图片上传：</TD>
    <TD><INPUT id=add1_action_contact class=text type=file name=pfile> </TD></TR>
  <input name="oldPic" value="${houseExt.path}" hidden="hidden">
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${houseExt.description}</TEXTAREA></TD></TR></TBODY></TABLE>
<DIV class=buttons><INPUT   value=保存 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
