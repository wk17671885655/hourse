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
    <script type="text/javascript">
         $(function () {
             //展示数据
             $('#dg').datagrid({
             url:'getAllStreet',
             fitColumns: true,
             pagination: true,
             pageList: [5, 10, 15, 20],
             toolbar:"#ToolBar",
             pageSize:5,
             columns:[[
                 {field:'ck',checkbox:true},
                 {field:'id',title:'编号',width:100},
                 {field:'name',title:'名称',width:100},
                 {field:'district',title:'区域', width:50, align: "center",
                     formatter: function(value,row,index){
                         return value.name;
                     }
                 }
             ]]
         });
         })

        //保存数据
        function Save() {
            $('#ModiyDialogForm').form('submit', {
                url:"add",
                onSubmit: function(){
                    // alert("正在发送");
                    // do some check
                    // return false to prevent submit;
                },
                success:function (date) {
                    var obj = $.parseJSON(date);
                    if(obj.result>0){
                        $("#AddDialog").dialog("close");  //关闭
                        //alert("添加成功");
                        $('#dg').datagrid("reload");
                        $.messager.alert('提示框','恭喜添加成功!');

                    }else {
                        $.messager.alert('提示框','添加失败');
                    }
                }
            });
        }
         //    添加数据
         function add() {
             $("#AddDialog").dialog("open").dialog("title","添加区域");
             $.post("deleteSingle",{"id":id},
                 function(data){
                     var obj = $.parseJSON(date);
                     alert(obj);
                 }, "json");
         }
         //  关闭对话框
        function Close(obj) {
            $("#"+obj).dialog("close");
        }
         function ModifyBySelect() {
             var SelectRows = $("#dg").datagrid('getSelections');
             if(SelectRows.length!=1){
                 $.messager.alert('提示框','您没有选中数据或选择多行数据');
                 return;
             }
             var selectRow = SelectRows[0];
             $("#UpdateDialog").dialog('open').dialog('setTitle','修改窗口');
             $("#ModiyDialogForm1").form('load',selectRow);
         }

    </script>
    <title>区域</title>
</head>
<body>
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a>
        <a
                href="javascript:ModifyBySelect()" class="easyui-linkbutton"
                iconCls="icon-edit" plain="true">修改</a>
        <a
                href="javascript:deleteSelect()" class="easyui-linkbutton"
                iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>
<table id="dg"></table>
<%--添加数据弹窗--%>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
                <td>街道</td>
                <td><input type="text"
                           name="name" id="jname" /></td>
            </tr>
            <tr>
              <td>区域</td>
              <td><select>
                  </select></td>
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
            <tr>
                <td>街道</td>
                <td><input type="text"
                           name="name" id="bname3" /></td>
            </tr>
        </table>
    </form>
</div>
<!--给添加对话框添加按钮-->
<div id="AddDialogButtons">
    <a href="javascript:Save()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:Close('AddDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
