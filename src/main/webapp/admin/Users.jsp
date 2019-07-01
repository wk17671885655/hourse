<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2019/6/18
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>租房网系统</title>
    <link href="Css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/users.js">
    </script>
    <title>区域</title>
</head>
<body>
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a
                href="javascript:ModifyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>
        <a
                href="javascript:Delete1()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>
<%--搜索条件--%>
<div>
    电话：<input type="text" id="tel">
    年龄：<input type="text" id="ageFrom">岁
    —— &nbsp;<input type="text" id="ageTo">岁
    <a id="btn" href="javascript:searchUser();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
</div>
<table id="dg"></table>

<!--添加窗口-->
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
            <td>名称</td>
            <td><input type="text"
                       name="name" id="bname" /></td>
        </tr>
        </table>
    </form>
</div>
<%--修改窗口--%>
<div id="UpdateDialog" class="easyui-dialog" buttons="#UpdateDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="ModiyDialogForm1" method="post">
        <table>
            <tr>
                <td hidden="hidden">编号</td>
                <td><input type="text"
                           name="id" id="bname2" hidden="hidden" /></td>
            </tr>
            <tr>
                <td>名称</td>
                <td><input type="text"
                           name="name" id="bname1" /></td>
            </tr>
        </table>
    </form>
</div>
<!--给添加对话框添加按钮-->
<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('AddDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<!--给修改对话框添加按钮-->
<div id="UpdateDialogButtons">
    <a href="javascript:Up()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('UpdateDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
