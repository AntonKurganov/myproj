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
<html lang="eng">
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
    <link href="${mainCss}" rel="stylesheet" />
    <link href="${FontAwesomeCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <script src="${bootstrapJs}"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.js"></script>
    <![endif]-->

    <style>
        strong{
            color: white;
            font-family: "Times New Roman";
            font-size: 19px;
        }
    </style>

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

    <div class="container" style="width: fit-content; background-color: black; border-radius: 20px; opacity: 0.5;">
        <div align="center" style="padding: 20px 150px;">
            <form action="newUser" method="POST">
                <strong>NAME:</strong></br><input type="text" name="name"></br>
                <strong>PASSWORD:</strong></br><input type="password" name="password"></br>
                <strong>E-MAIL:</strong></br><input type="text" name="email"></br>
                <strong>PHONE:</strong></br><input type="text" name="phone"></br></br>
                <input type="submit" value="SUBMIT" href="/login" class="btn btn-primary"/>
                <br>
                <c:if test="${exists ne null}">
                    <strong style="font-size: 20px">User already exists!</strong>
                </c:if>
            </form>
        </div>
    </div>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="container" style="background-color: white; opacity: 0.8; width: 100%; padding: 20px; bottom: 0px; position: absolute">
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
</body>
</html>
