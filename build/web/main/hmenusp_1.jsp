
<script language="JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
<body onLoad="MM_preloadImages('<%=approot%>/imagessp/home2.gif','<%=approot%>/imagessp/logout2.gif')">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr> 
    <td width="526"> 
      <table border="0" cellspacing="0" cellpadding="0" width="526">
        <tr> 
          <td rowspan="2"><img src="<%=approot%>/imagessp/logo-finance1.jpg" width="216" height="144" /></td>
          <td><img src="<%=approot%>/imagessp/logotxt-finance.gif" width="343" height="94" /></td>
        </tr>
        <tr> 
          <td style="background:url(<%=approot%>/imagessp/head-line.gif) repeat-x"><img src="<%=approot%>/imagessp/head-corner.gif" width="119" height="50" /></td>
        </tr>
      </table>
    </td>
    <td width="100%" valign="top"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td valign="top" style="background:url(<%=approot%>/imagessp/head-bg.gif) repeat-x">
            <table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="31" align="right" valign="top" >&nbsp;</td>
                <td rowspan="2"><img src="<%=approot%>/imagessp/logo-kopegtel.jpg" width="73" height="74" /></td>
                <td rowspan="2"><img src="<%=approot%>/imagessp/logo-telkom.gif" width="43" height="74" /></td>
              </tr>
              <tr> 
                <td align="right" valign="top" ><img src="<%=approot%>/imagessp/kopegtel-txt.gif" width="167" height="22" /></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr> 
          <td style="background:url(<%=approot%>/imagessp/head-icon-bg.gif) repeat-x">		   
            <table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>                 
                <td>&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
