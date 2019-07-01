<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2019/6/18
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                url:'getAllBypage',
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
                    { field: 'name', title: '区域', width: 50, align: "center" },
                    {field:'opt',title:'操作', width:30, align: "center",
                        formatter: function(value,row,index){
                            var name = "'"+row.name+"'";
                           return "<input type='button' value='删除' onclick='delSingle("+row.id+")'> | " +
                               "<input type='button' value='查看区域' onclick='getStreet("+row.id+",&quot;"+row.name+"&quot;)'>" ;
                        }
                    }

                ]]
            });
        })
        //添加弹窗
        function Add() {
            // alert("打开窗口");
            $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
        }

        //关闭添加窗口
        function CloseDialog(obj){
            $("#"+obj).dialog("close");
        }
        //添加保存
        function SaveDialog() {
            $('#ModiyDialogForm').form('submit',{
                url:"addDistrict",
                success:function (date) {
                    var obj = $.parseJSON(date);
                    if(obj.result>0){
                        $("#AddDialog").dialog("close");  //关闭
                        //alert("添加成功");
                        $("#dg").datagrid('reload');
                        $.messager.alert('提示框','恭喜添加成功!');

                    }else {
                        $.messager.alert('提示框','添加失败');
                    }
                }
            });
        }
          //修改弹窗
        function ModifyBySelect() {
        //    判断有没选中记录
            var selectRows = $("#dg").datagrid('getSelections');
            if(selectRows.length!=1){
                $.messager.alert('提示框','您还没用选中或选中了多行');
                return
            }
          // 选中一行还原数据
            var selectRow = selectRows[0];
            $('#UpdateDialog').dialog('open').dialog('setTitle',"修改区域");
            $('#ModiyDialogForm1').form('load',selectRow);
        }
          // 修改保存
        function UpdateDialog() {
            $('#ModiyDialogForm1').form('submit',{
                url:"updateDistrict",
                success:function (date) {
                    alert(date);
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
        //删除单行
        function delSingle(id) {
            $.messager.confirm('提示框', '确定要把我送到火星吗？', function(result){
                if (result){
                    $.post("deleteSingle",{"id":id},
                        function(data){
                             $.messager.alert("提示",data.result>0?"删除成功":"删除失败");
                            $('#dg').datagrid('reload');//刷新页面
                        }, "json");
                    // exit action;
                }
            });
        }
        //删除多行
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
                           $.messager.alert('提示框','恭喜你成功删除'+SelectRows.length+'行!');
                       }
                       else
                       {
                           $.messager.alert('提示框','系统正在维护!');
                       }
                   },"json")
               }
            })
        }
        function getStreet(id,name) {
            var  districtId=id;
            $("#openStreetDialog").dialog("open").dialog('setTitle','街道信息');
            //展示数据
            $('#dgStreet').datagrid({
                url:'getStreetByDistrict',
                queryParams: {"districtId": id},  //发送参数
                title:name+"街道信息",
                fitColumns: true,
                pagination: true,
                pageList: [5, 10, 15, 20],
                // toolbar:"#ToolBar",
                pageSize:5,
                columns:[[
                    {field:'ck',checkbox:true},
                    {field:'id',title:'编号',width:100},
                    {field:'name',title:'街道',width:100},
                    {
                        field: 'opt', title: '操作', width: 50, align: "center",
                        formatter: function (value, row, index) {
                            //同步跳转 "<a href='delDistrict?id="+row.id+"'>删除</a>"
                            return "<a href='javascript:delStrBy("+row.id+")'>删除</a> | <a href='javascript:UpStreet("+row.id+")'>修改街道</a>";
                        }
                    }
                ]]
            });
            $("#districtId").val(id);
        }

        function Save() {
            $('#ModiyDialogForm3').form('submit', {
                url: "add",
                // onSubmit: function(){
                //     // alert("正在发送");
                //     // do some check
                //     // return false to prevent submit;
                // },
                success: function (date) {
                    var obj = $.parseJSON(date);
                    if (obj.result > 0) {
                        //alert("添加成功");
                        $('#dgStreet').datagrid("reload");
                        $.messager.alert('提示框', '恭喜添加成功!');
                    } else {
                        $.messager.alert('提示框', '添加失败');
                    }
                }
            })
        }
        //删除街道区域
        function delStrBy(id) {
            $.messager.confirm('提示框', '确定要把我送到火星吗？', function(result){
                if (result){
                    $.post("deleteStreetById",{"id":id},
                        function(data){
                            if(data.result>0){
                                $('#dgStreet').datagrid("reload");
                                $.messager.alert("提示","删除成功");
                            }else {
                                $.messager.alert("提示","系统维护升级");
                            }
                        }, "json");
                    // exit action;
                }
            });
        }

        function UpStreet(id) {
              $.post("getStreetById",{"id":id},function (selectRow) {
                  $('#UpStreetDialog').dialog('open').dialog('setTitle',"修改街道");
                $("#upid").val(selectRow.id);
                  $("#upname").val(selectRow.name);
                  $("#updistrictId").val(selectRow.districtId);
              },"json")
        }
        
        function UpdateStreetDialog() {
                $('#UpStreetDialogForm4').form('submit', {
                        url:"update",
                success:function(date){
                    alert(date);
                            var obj = $.parseJSON(date);
                            if (obj.result > 0) {
                                $('#dgStreet').datagrid('reload');
                                $("#UpStreetDialog").dialog("close");  //关闭
                                $.messager.alert('提示框', '恭喜修改成功!');

                            } else {
                                $("#UpStreetDialog").dialog("close");
                                $.messager.alert('提示框', '修改失败');
                            }
                }
            });
        }
        //     $('#UpStreetDialogForm4').form('submit', {
        //             url:"update",
        //     success:function(data){
        //         alert(data)
        //     }
        // });
            // $('#UpStreetDialogForm').form('submit', {
            //     url: "update",
            //     success: function (date) {
            //         alert(date);
            //         // var obj = $.parseJSON(date);
            //         // if (obj.result > 0) {
            //         //     $('#dgStreet').datagrid('reload');
            //         //     $("#UpStreetDialog").dialog("close");  //关闭
            //         //     $.messager.alert('提示框', '恭喜修改成功!');
            //         //
            //         // } else {
            //         //     $("#UpStreetDialog").dialog("close");
            //         //     $.messager.alert('提示框', '修改失败');
            //         // }
            //     }

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
                <td ><input type="text" hidden="hidden"     name="id" id="bname1" /></td>
            </tr>
            <tr>
                <td>名称</td>
                <td><input type="text"
                           name="name" id="bname2" /></td>
            </tr>
        </table>
    </form>
</div>
<%--查看街道窗口--%>
<div id="openStreetDialog" class="easyui-dialog"
     style="width: 850px; height: 550px; padding: 10px 20px;" closed="true">
        <table id="dgStreet">  </table>
            <form id="ModiyDialogForm3" method="post" >
        <table >
            <tr>
                <td >区域编号</td>
                <td><input type="text"
                          name="districtId" id="districtId" /></td>
            </tr>
            <tr>
                <td>街道</td>
                <td><input type="text"
                           name="name" id="bname3" /></td>
            </tr>
            <tr>
                <td><input type="button"
                          onclick="Save()" value="添加" /></td>
            </tr>
        </table>
            </form>
                <%--修改街道弹窗窗口--%>
                <div id="UpStreetDialog" class="easyui-dialog" buttons="#UpDialogButtons"
                     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">  <table>
                    <form id="UpStreetDialogForm4" method="post">
                            <tr>
                                <td>编号</td>
                                <td><input type="text"
                                    value=""  id="upid"     name="id" /></td>
                            </tr>
                            <tr>
                                <td>街道</td>
                                <td><input type="text"
                                       value=""    id="upname"        name="name" /></td>
                            </tr>
                            <tr>
                                <td >区域编号</td>
                                <td><input type="text"
                                     value=""      id="updistrictId"     name="districtId" /></td>
                            </tr>
                    </form>
                </table>
                </div>
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
    <a href="javascript:UpdateDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('UpdateDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<div id="UpDialogButtons">
    <a href="javascript:UpdateStreetDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:CloseDialog('UpStreetDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>
