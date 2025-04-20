<%@ page contentType="text/html ; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript">
<script charset="Shift_JIS">
</script>
<style type="text/css">
<!--
  table.top2 {
    border-width:2px 1px 1px 2px;
    border-style:solid;
    border-color:#606060 #ffffff #ffffff #606060;
    color:#ff0000;
    font-size:12pt;
    font-weight:bold;
  }

  table.header{
    left     : 6px;
    position : relative;
    z-index  : 2;
  }
  table.item{
    top      : -8px;
    height   : 70px;
    border   : 1px solid black;
    position : relative;
    z-index  : 1;
  }

  td.data {
    border-width:2px 1px 1px 2px;
    border-style:solid;
    border-color:#606060 #ffffff #ffffff #606060;
  }

  div.left {
    width:865px; height:400px; overflow-y:scroll;
  }

input.gray {background-color:#dcdcdc;}
table.top1 {border-width:2px 1px 1px 2px; border-style:solid; border-color:#606060 #ffffff #ffffff #606060;}
select.gray {background-color:#dcdcdc;}


-->
</style>

<title>エラー画面</title>
</head>
<body>

<form name="form1" method="post">
  <table>
    <tr>
      <td>エラーメッセージ</td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td><span class="label label-danger"><font color="red">${message}</font></span></td>
      <td><span class="label label-danger"><font color="red">${message2}</font></span></td>
      <td></td>
    </tr>
  </table>
<br>
<br>
<br>
</form>
</body>
</html>