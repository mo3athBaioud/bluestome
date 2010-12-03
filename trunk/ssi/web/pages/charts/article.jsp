<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script language="javascript" src="${ctx}/js/validator.js"></script>
<script type="text/javascript" src="${ctx}/js/datetimeSelect.js"></script>
<script type="text/javascript" src="${ctx}/js/validator.js"></script>
<script type="text/javascript" src="${ctx}/amline/swfobject.js"></script>
<%@ include file="/commons/style.jsp"%>

<HTML>
	<HEAD>
		<script type="text/javascript">

	//表单验证
	function formSubmit(obj) {
		if( !validate() ) {
			return false;
		}
		return true;
	}
	//验证方法
	function validate() {
		var v = new Validator();
		v.setNotEmpty("startdate");
		return v.validate();
	}
	
    //amChart图标初始化方法
	function amChartInited(chart_id){
	  // get the flash object into "flashMovie" variable   
	  flashMovie = document.getElementById(chart_id);
	}
	
	//导出报表为图片
    function export2Image() {
         flashMovie.exportImage('${ctx}/export2Image.cgi'); 
    }
    
    //导出报表为Excel
    function export2Excel(){
      alter('导出数据到Excel');
    }

	//返回到数据查询主页
	function back2index(){
		location.href="${ctx}/index.jsp";
	}
	
	function  fresh(){ 
		window.location= "${ctx}/article.cgi"; 
	}
	
	function timer(){ 
		setTimeout(fresh,60000); 
	} 
	
  </script>
	</HEAD>
	<BODY ><!-- onload="timer()" -->
		<CENTER>
			<FORM name=form1 action="${ctx}/article.cgi" method="post" onSubmit="return formSubmit(this);">
				<TABLE class="form" cellspacing="1" cellpadding="0" id="tbData"
					width="825">
					<TR>
						<TD rowspan=4></TD>
						<TD></TD>
						<TD rowspan=4></TD>
					</TR>
					<TR>
						<TD id="startdate_tip">
							<FONT face="">起止时间：</FONT>
							<input type="text" name="startdate" styleClass="input"
								onclick="setYearPeriod(1950,2050);setAutoExpandYearPeriod(true);setDay(this);" />
							<FONT face="">结束时间: </FONT>
							<input type="text" name="enddate" styleClass="input"
								onclick="setYearPeriod(1950,2050);setAutoExpandYearPeriod(true);setDay(this);" />
							<input id=submit type=submit value="搜索" class="button">
							<br>
						</TD>
					</TR>
					<TR>
						<TD CLASS=line_across>
							数据状态:${isOk}<br/>
							数据:${data}
						</TD>
					</TR>
					<TR>
						<TD align="center">
							<c:choose>
								<c:when test="${isOk == 'ok'}">
									<div id="flashcontent">
										<strong>请更新您的FLASH版本!</strong>
									</div>
									<script type="text/javascript">
										// <![CDATA[	
										var so = new SWFObject("${pageContext.request.contextPath}/amline/amline.swf", "amline", "520", "400", "8", "#FFFFFF");
									    so.addVariable("path", "${pageContext.request.contextPath}/amline/");
										so.addVariable("chart_id", "amline"); // if you have more then one chart in one page, set different chart_id for each chart	
										so.addVariable("chart_data",encodeURIComponent("${data}"));
								        so.addVariable("chart_settings", encodeURIComponent("<settings><background><color>#FFFFFF,#FF9E01</color><alpha>100</alpha></background><labels><label><y>24</y><width>520</width><align>center</align><text><![CDATA[<b>SSI文章统计</b>]]></text></label></labels></settings>"));
									    so.write("flashcontent");
										// ]]>
									</script>
									<p align="center">
										<input type="button" value="导出图片" onclick="export2Image();"
											class="button">
										<input type="button" value="导出到Excel"
											onclick="export2Excel();" class="button">
										<input type="button" value="返回" onclick="back2index();"
											class="button">
									</p>
								</c:when>
								<c:otherwise>
									<p align="center">
										<center>
											<font COLOR="red">暂无数据</font>
										</center>
										<input type="button" value="返回" onclick="back2index();"
											class="button">
									</p>
								</c:otherwise>
							</c:choose>
						</TD>
					</TR>
				</TABLE>
			</FORM>
		</CENTER>
	</BODY>
</HTML>
