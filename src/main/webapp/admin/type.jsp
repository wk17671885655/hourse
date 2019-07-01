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
            $('#dg').datagrid({
                //一个用以从远程站点请求数据的超链接地址。
                url:'getAllByPage',
                //设置为true将自动使列适应表格宽度以防止出现水平滚动。
                fitColumns: true,
                //设置true将在数据表格底部显示分页工具栏。
                pagination: true,
                //当设置分页属性时，初始化每页记录数列表。
                pageList: [5, 10, 15, 20],
                //数据表格顶部面板的工具栏。
                toolbar:"#ToolBar",
                pageSize:20,
                columns: [[
                    {field:'ck',checkbox:true},  //复选框列
                    { field: 'id', title: '编号', width: 50, align: "center" },
                    { field: 'name', title: '类型', width: 50, align: "center" },
                    {field:'opt',title:'操作', width:80, align: "center",
                        formatter: function(value,row,index){
                           return "<input type='button' value='删除' onclick='deltetById("+row.id+")'>";
                        }
                    }

                ]]
            });
        })
        function Add() {
            // alert("打开窗口");
            $("#AddDialog").dialog("open").dialog('setTitle',"添加类型");
        }

        //关闭添加窗口
        function CloseDialog(obj){
            $("#"+obj).dialog("close");
        }
        //保存
        function SaveDialog() {
            $('#ModiyDialogForm').form('submit',{
                url:"addtype",
                success:function (date) {
                    var obj = $.parseJSON(date);
                    if(obj.result>0){
                        $("#AddDialog").dialog("close");  //关闭
                        //alert("添加成功");
                        $('#dg').datagrid('reload');
                        $.messager.alert('提示框','恭喜添加成功!');

                    }else {
                        $.messager.alert('提示框','添加失败');
                    }
                }
            });
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
        function Up() {
            alert("123");
            $('#ModiyDialogForm1').form('submit',{
                url:"updateType",
                success:function (date) {
                    var obj = $.parseJSON(date);
                    if(obj.result>0){
                        $('#dg').datagrid('reload');
                        $("#UpdateDialog").dialog("close");  //关闭
                        $.messager.alert('提示框','恭喜修改成功!');

                    }else {
                        $("#UpdateDialog").dialog("close");
                        $.messager.alert('提示框','修改失败');
                    }
                }
            });
        }

        function deltetById(id) {
            $.messager.confirm('提示框', '确定要把我送到火星吗？', function(result){
                if (result){
                    $.post("deltetById",{"id":id},
                        function(data){
                            $('#dg').datagrid('reload');
                        }, "json");
                    // exit action;
                }
            });
        }
        function Delete1() {
            //获取选中行
            var SelectRows = $("#dg").datagrid('getSelections');
            if(SelectRows.length==0){
                $.messager.alert("提示标题","亲，你还没用选中行");
                return
            }
            var delValue="";
            for (var i = 0; i <SelectRows.length ; i++) {
                delValue=delValue+SelectRows[i].id+",";
            }
            delValue= delValue.substring(0,delValue.length-1);
            $.messager.confirm("提示","确定删除"+SelectRows.length+"行",function (r) {
               if(r){
                   $.post("delete",{"ids":delValue},function (data) {
                       if(data.result>0){
                           $("#dg").datagrid('reload'); //刷新
                           //alert("添加成功");
                           $.messager.alert('提示框','恭喜你成功删除'+data.result+'行!');
                       }
                       else
                       {
                           $.messager.alert('提示框','系统正在维护!');
                       }
                   },"json")
               }
            })
        }

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
