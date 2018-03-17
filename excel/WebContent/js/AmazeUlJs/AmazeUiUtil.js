/**
 * 
 */

//获取界面height需减的height
function getWindowSub(){
	return ($(window).height()-90) + "px";
}
/**
 * 多选框全选/取消全选
 * f_cb 父多选框
 * z_cb 子多选框
 * .prop('checked'); //16+:true/false
 */
function checkBoxSelectAll(f_cb,z_cb){
	var fcb = $("input[name="+f_cb+"]");
	if(fcb.prop('checked')){
		$("input[name="+z_cb+"]").prop("checked", true);
	}else{
		$("input[name="+z_cb+"]").prop("checked",false);
	}
}
/**
 * 多选框反选
 * @param cb
 */
function checkBoxInvert(cb){
	$("[name = "+cb+"]:checkbox").each(function () {
		$(this).attr("checked", !$(this).prop("checked"));
	});
}
/**
 * 多选框获取选中数据
 * @param 多选框name
 * @returns {Array}
 */
function getCheckBoxSelected(cb){
	var val = new Array();
	$("input[name="+cb+"]").each(function(){
		 //由于复选框一般选中的是多个,所以可以循环输出 
		if($(this).prop("checked")){
			val.push($(this).val());
		}
	});
	return val;
}