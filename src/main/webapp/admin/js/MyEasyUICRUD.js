var url;
function ResetValues(){
	$("#bname").val('');
	$("#author").val('');
	$("#buytime").val('');
	$("#sname").val('');
}
 
function SaveDialog(){
	//表单异步提交添加
	$("#ModiyDialogForm").form('submit',{
		url:url,
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(result){
			if( result.errorMsg ){
				$.messager.alert("系统提示",result.errorMsg);
				return;
			}
			else{
				$.messager.alert("系统提示","保存成功");
				ResetValues();
				$("#AddDialog").dialog('close'); //关闭
				$("#data").datagrid('reload'); //刷新
			}
		}
	});
	
	ResetValues();
}
 
function CloseDialog(){
	ResetValues();
	$("#AddDialog").dialog('close');
}
 

//设置添加url
function Add(){
	$("#AddDialog").dialog('open').dialog('setTitle',"添加数据");
	url = "add.do";
}
 
//修改的url
function ModifyBySelect(){
	//获取选中的行
	var SelectRows = $("#data").datagrid('getSelections');
	if( 1 != SelectRows.length ){
		$.messager.alert("系统提示", "请选择一行要编辑的数据");
		return;
	}
	var SelectRow = SelectRows[0];
	//打开编辑对话框
	$("#AddDialog").dialog('open').dialog('setTitle',"编辑数据");
	//获得行对象的数据加载到表单中显示
	$("#ModiyDialogForm").form('load',SelectRow);
	url = "up.do";  //设置修改的地址
}
 
//删除的代码
function DeleteByFruitName(){
	//获取选择行
	var SelectRows = $("#data").datagrid('getSelections');
	alert("ddd"+SelectRows.length);
	if( 0 == SelectRows.length ){
		$.messager.alert("系统提示", "请选择要删除的数据");
		return;
	}
	var SelectIndexArr = [];
	for( var i = 0 ; i < SelectRows.length; i++ ){
		SelectIndexArr.push(SelectRows[i].xh);
	}
	var SelectIndexToString = SelectIndexArr.join(",");
	$.messager.confirm("系统提示", "你确定要删除<font color=red> " + SelectRows.length + " </font>条数据吗?", function(xo){
		if( xo ){
			$("#data").datagrid('reload');
			//支持多项删除
			//alert(SelectIndexToString);
			$.post("delMore.do",{xhs:SelectIndexToString},function(result){
				if( result.success ){
					$.messager.alert("系统提示", "你已成功删除 <font color=green> " + result.DeleteCounts + " </font>条数据!~");
					$("#data").datagrid('reload');
				}
				else{
					$.messager.alert("系统提示", "<font color=red>删除失败</font>");
				}
			},"json");
		}
	});
}
 
//打开菜单
function OpenTab(Text,URL){
	if( $("#MenusTabs").tabs('exists', Text)){
		$("#MenusTabs").tabs('select', Text);
	}
	else{
		var Content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src=" + URL + "></iframe>";
		$("#MenusTabs").tabs('add',{
			title:Text,
			closable:true,
			content:Content
		});	
	}
}
 

//制作树型导航菜单
$(function(){
	 //定义树型菜单内容
	var TreeMenusDatas=[{
		text:"图书馆图书系统",
		children:[{
			text:"小说图书信息",
			attributes:{
				url:"data.jsp"
			}
		}]
	}];
	
	//加载树型菜单内容
	$("#TreeMenus").tree({
		data:TreeMenusDatas,
		lines:true,
		onClick:function(node){
			if(node.attributes ){
				OpenTab( node.text, node.attributes.url);
			}
		}
	});

});