<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  CustomUser: Anton
  Date: 05.01.2017
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Успех </title>

    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/fonts/font-awesome.css" var="FontAwesomeCss" />
    <spring:url value="/resources/js/jquery.js" var="jqueryJs" />
    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJs" />
    <spring:url value="/resources/js/scroll.js" var="scrollJs" />
    <spring:url value="/resources/js/salvattore.min.js" var="salvattoreJs" />
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${FontAwesomeCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${scrollJs}"></script>
    <script src="${salvattoreJs}"></script>

    <script type="text/javascript">
        function isEmpty(name) {
            return name===" " || name==="";
        }
        function valid (form) {
            var fail = false;
            var name = form.name.value;
            var price = form.price.value;
            var country = form.country.value;
            var length = form.lineLength.value;
            var width = form.width.value;
            var brStrength = form.brStrength.value;
            if (isEmpty(name)){
                fail = "Input the name to continue.";
            } else if (isEmpty(price)){
                fail = "Input the price to continue (example: 124.99).";
            } else if (isEmpty(country)){
                fail = "Input the country to continue.";
            } else if (isEmpty(length)){
                fail = "Input length to continue";
            } else if (isEmpty(width)){
                fail = "Input width to continue";
            } else if (isEmpty(brStrength)){
                fail = "Input breaking strength to continue";
            }
            if (fail) alert(fail);
            else form.submit();
        }
    </script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.js"></script>
    <![endif]-->

</head>

<body>
<div class="navbar navbar-inverse navbar-static-top">
    <div class="container-fluid">
        <div class="col-md-6">
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-home"></i> Mainpage</a></li>
                <li><a href="#contacts" data-toggle="modal"><i class="fa fa-phone"></i> Contacts</a></li>
                <li><a href="/myBasket"><i class="fa fa-shopping-basket"></i> My Basket</a></li>
            </ol>
        </div>
        <div class="col-md-6">
            <c:if test="${name=='guest'}">
                <a href="/login" class="btn btn-primary"><i class="fa fa-sign-in"></i> Log in</a>
            </c:if>
            <a href="/register" class="btn btn-primary">Register</a>
            <c:if test="${name!='guest'}">
                <c:url value="/logout" var="logoutUrl" />
                <a href="${logoutUrl}" class="btn btn-primary">Log out</a>
            </c:if>
            <b class="pull-right" style="color: white; font-size: x-large;">Hello, ${name}</b>
        </div>
    </div>
    <div class="container">
        <div class="navbar-header">
            <button type="button"  class="navbar-toggle" data-toggle ="collapse" data-target="#something">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="col-md-10">
            <div class="collapse navbar-collapse" id="something">
                <ul class="nav navbar-nav" >
                    <li><a href="/spinnings">Spinnings</a></li>
                    <li><a href="/reels">Reels</a></li>
                    <li><a href="/lines">Fishing Lines</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">Gear<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="floats">Floats</a></li>
                            <li><a href="hooks">Hooks</a></li>
                            <li><a href="hooklinks">Hooklinks</a></li>
                            <li><a href="weights">Weights, Styls</a></li>
                            <li><a href="feeders">Feeders</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">Lure<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="wobblers">Wobblers</a></li>
                            <li><a href="spoons">Spoons</a></li>
                            <li><a href="twisters">Twisters</a></li>
                            <li><a href="jigheads">Jig Heads</a></li>
                        </ul>
                    </li>
                    <li><a href="baits">Bait</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">Other<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="holdalls">Holdalls</a></li>
                            <li><a href="bells">Bells</a></li>
                            <li><a href="stands">Stands</a></li>
                            <li><a href="daggers">Daggers</a></li>
                            <li><a href="keepnets">Keepnets</a></li>
                            <li><a href="clonks">Clonks</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="col-md-2">
    </div>
    <div class="col-md-8">
        <c:if test="${lines.size() == 0}">
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">
                    <i class="fa fa-times fa-fw"></i>
                </button>
                <p>Unfortunately, there are no goods of such kind!</p>
                <p><button type="button" class="btn btn-success" data-dismiss="alert">OK</button></p>
            </div>
        </c:if>
        <c:if test="${OkMessage!=null}">
            <c:if test="${OkMessage!='PROBLEM'}">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="fa fa-times fa-fw"></i>
                    </button>
                    <p><strong>${OkMessage}</strong> was successfully added to <a href="myBasket" class="alert-link">your basket</a>!</p>
                    <p><button type="button" class="btn btn-success" data-dismiss="alert">OK</button></p>
                </div>
            </c:if>
            <c:if test="${OkMessage=='PROBLEM'}">
                <div class="alert alert-warning alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert">
                        <i class="fa fa-times fa-fw"></i>
                    </button>
                    <p>An error has occurred! Try again</p>
                    <p><button type="button" class="btn btn-warning" data-dismiss="alert">OK</button></p>
                </div>
            </c:if>
        </c:if>
        <div  class="row">
            <c:forEach items="${lines}" var="line">
                <div class="col-md-4">
                    <div class="thumbnail">
                        <div class="caption">
                            <b style="font-size: 20px; min-height: 56px;" class="pull-left">${line.name}</b>
                            <a href="/line_${line.id}"><img src=${line.photoLink} width="200px" height="200px" alt=""></a>
                            <b style="font-size: 16px;" class="pull-right">${line.country}     ${line.lineLength}m     ${line.brStrength}kg     ${line.price}₴</b>
                            <br>
                            <br>
                            <a href="/line_${line.id}" class="btn btn-success pull-left">More details <i class="fa fa-arrow-right"></i></a>
                            <c:if test="${role=='ADMIN'}">
                                <form action="deleteLine" method="post">
                                    <button class="btn btn-danger pull-right" name="id" value="${line.id}"><i class="fa fa-window-close"></i></button>
                                </form>
                            </c:if>
                            <c:if test="${role=='USER'}">
                                <form action="addlineToBasket" method="post">
                                    <button class="btn btn-success pull-right" name="id" value="${line.id}"><i class="fa fa-shopping-basket" aria-hidden="true"></i></button>
                                </form>
                            </c:if>
                            <br>
                            <br>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${role=='ADMIN'}">
            <div class="row" >
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#newLine">Add Line</button>
            </div>
        </c:if>
    </div>
    <c:if test="${lines.size()!=0}">
        <div class="col-md-2">
            <h3>Sort by:</h3>
            <div id="accordion" class="panel-group">
                <br>
                <form action="linesFromCountry">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#collapse-country" data-parent="#accordion" data-toggle="collapse" class="spoiler"><i class="fa fa-chevron-right" aria-hidden="true"></i><b> Country</b></a>
                            </h4>
                        </div>
                        <div id="collapse-country" class="panel-collapse collapse">
                            <div class="panel-body">
                                <c:forEach items="${countries}" var="country">
                                    <li style="list-style: none">
                                        <input type="radio" value=${country} name="country"/>${country}
                                    </li>
                                </c:forEach>
                            </div>
                            <input type="submit" value="SORT" class="btn-default">
                        </div>
                    </div>
                </form>
                <br>
                <form action="linesByPrice">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#collapse-price" data-parent="#accordion" data-toggle="collapse" class="spoiler"><i class="fa fa-chevron-right" aria-hidden="true"></i><b> Price</b></a>
                            </h4>
                        </div>
                        <div id="collapse-price" class="panel-collapse collapse">
                            <div class="panel-body">
                                From: <input type="number" step="0.1" value="${min}" name="min" pattern="\d+(\.\d{2})?" class="pull-right" /><br>
                                To: <input type="number" step="0.1" value="${max}" name="max" pattern="\d+(\.\d{2})?" class="pull-right" />
                            </div>
                            <input type="submit" value="SORT" class="btn-default">
                        </div>
                    </div>
                </form>
                <br>
                <form action="linesByLength">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#collapse-length" data-parent="#accordion" data-toggle="collapse" class="spoiler"><i class="fa fa-chevron-right"></i><b> Length</b></a>
                            </h4>
                        </div>
                        <div id="collapse-length" class="panel-collapse collapse">
                            <div class="panel-body">
                                From: <input type="number" value="${minLength}" pattern="^[ 0-9]+$" name="min" class="pull-right" /><br>
                                To: <input type="number" value="${maxLength}" pattern="^[ 0-9]+$" name="max" class="pull-right" />
                            </div>
                            <input type="submit" value="SORT" class="btn-default">
                        </div>
                    </div>
                </form>
                <br>
                <form action="linesByWidth">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#collapse-width" data-parent="#accordion" data-toggle="collapse" class="spoiler"><i class="fa fa-chevron-right"></i><b> Width</b></a>
                            </h4>
                        </div>
                        <div id="collapse-width" class="panel-collapse collapse">
                            <div class="panel-body">
                                From: <input type="number" step="0.01" value="${minWidth}" pattern="\d+(\.\d{2})?" name="min" class="pull-right" /><br>
                                To: <input type="number" step="0.01" value="${maxWidth}" pattern="\d+(\.\d{2})?" name="max" class="pull-right" />
                            </div>
                            <input type="submit" value="SORT" class="btn-default">
                        </div>
                    </div>
                </form>
                <br>
                <form action="linesByStrength">
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#collapse-strength" data-parent="#accordion" data-toggle="collapse" class="spoiler"><i class="fa fa-chevron-right"></i><b> Strength</b></a>
                            </h4>
                        </div>
                        <div id="collapse-strength" class="panel-collapse collapse">
                            <div class="panel-body">
                                От: <input type="number" step="0.1" value="${minStrength}" pattern="\d+(\.\d{2})?" name="min" class="pull-right" /><br>
                                До: <input type="number" step="0.1" value="${maxStrength}" pattern="\d+(\.\d{2})?" name="max" class="pull-right" />
                            </div>
                            <input type="submit" value="SORT" class="btn-default">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </c:if>
</div>

<c:if test="${numberOfPages>1}">
    <form action="${pageContext.request.contextPath}" method="post">
        <div class="btn-toolbar">
            <div class="btn-group-lg" style="margin-top: 16px;">
                <button class="btn btn-primary" <c:if test="${pointer<=2}">disabled</c:if> name="pointer" value="1"><i class="fa fa-chevron-left"></i><i class="fa fa-chevron-left"></i></button>
                <button class="btn btn-primary" <c:if test="${pointer<=2}">disabled</c:if> name="pointer" value="${pointer-1}"><i class="fa fa-chevron-left"></i></button>
                <c:forEach items="${pages}" var="page">
                    <button class="btn btn-primary <c:if test="${pointer == page}">active</c:if>" name="pointer" value="${page}">${page}</button>
                </c:forEach>
                <button class="btn btn-primary" <c:if test="${pointer>=numberOfPages-1}">disabled</c:if> name="pointer" value="${pointer+1}"><i class="fa fa-chevron-right"></i></button>
                <button class="btn btn-primary" <c:if test="${pointer>=numberOfPages-1}">disabled</c:if> name="pointer" value="${numberOfPages}"><i class="fa fa-chevron-right"></i><i class="fa fa-chevron-right"></i></button>
            </div>
        </div>
    </form>
</c:if>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="container" style="background-color: white; opacity: 0.8; width: 100%; padding: 20px; bottom: 0px;<c:if test="${lines.size()<4}">position: absolute;</c:if>">
    <div class="row" style="margin-top: 10px">
        <div class="col-xs-8">
            <ul class="list-unstyled list-inline" style="font-weight: bold; font-size: large; font-family: Cambria; padding-left: 20px;">
                <li><a href="/">Mainpage</a></li>
                <li><a href="#contacts" data-toggle="modal">Contacts</a></li>
                <li><a href="/myBasket">My Basket</a></li>
            </ul>
        </div>
        <div class="col-xs-4">
            <p class="text-muted pull-right" style="color: black;">© 2017 Company. All rights reserved</p>
        </div>
    </div>
</div>
<!--Contacts window-->
<div class="modal fade" id="contacts">
    <div class="modal-dialog modal-lg" style="border-radius: 15px; width: 30%">
        <div class="modal-content-contacts">
            <div class="modal-header">
                <p class="modal-title" align="center" style="color: black; font-family: Cambria; font-size: x-large">CONTACTS</p>
            </div>
            <div class="modal-body">
                <p style="font-family: 'Times New Roman'; font-size: large">Email: blabla@bla.com</p>
                <p style="font-family: 'Times New Roman'; font-size: large">Phone1: 1234567890</p>
                <p style="font-family: 'Times New Roman'; font-size: large">Phone2: 1234567890</p>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-dismiss="modal">BACK</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="newLine">
    <div class="modal-dialog modal-lg">
        <div class="modal-content-contacts">
            <div class="modal-header">
                <p class="modal-title" align="center"><font face="joan" size="6" color="darkslategray">Add new Line</font></p>
            </div>
            <div class="modal-body">
                <form action="newLine" onsubmit="valid(document.getElementById('form')); return false" name="test" id="form">
                    <label for="name">Name:</label>
                    <input name="name" placeholder="Name" id="name" /><br /><br />
                    <label for="price">Price:</label>
                    <input type="number" step="0.01" name="price" placeholder="Example: 124.99" pattern="\d+(\.\d{2})?" id="price" /><br /><br />
                    <label for="country">Country:</label>
                    <input name="country" placeholder="Country" id="country" /><br /><br />
                    <label for="lineLength">Length:</label>
                    <input type="number" name="lineLength" pattern="^[ 0-9]+$" placeholder="Example: 150" id="lineLength" /><br /><br />
                    <label for="width">Width:</label>
                    <input type="number" step="0.01" name="width" pattern="\d+(\.\d{2})?" placeholder="Example: 0.16" id="width" /><br /><br />
                    <label for="brStrength">Breaking Strength:</label>
                    <input type="number" step="0.01" name="brStrength" pattern="\d+(\.\d{2})?" placeholder="Example: 5.81" id="brStrength" /><br /><br />
                    <label for="photoLink">Photo Link: </label>
                    <input name="photoLink" placeholder="Photo Link" id="photoLink" /><br /><br />
                    <input type="submit" class="btn-success" name="submit" value="Submit" />
                    <br />
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-dismiss="modal">Назад</button>
            </div>
        </div>
    </div>
</div>

</body>

</html>