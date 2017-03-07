<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title><spring:message code="project.name"/></title>
	<link rel="stylesheet" href="${resourceRoot}/css/style.css" type="text/css" charset="utf-8" />
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h1><img src="${resourceRoot}/images/logo.jpg" width="172" height="45" alt="Charity Trust"/></h1>
			<div id="nav">
				<a href="<spring:url value="/"/>"><img src="${resourceRoot}/images/m1.jpg" width="66" height="41" alt="M1"/></a>
				<a href="#"><img src="${resourceRoot}/images/m2.jpg" width="87" height="41" alt="M2"/></a>
				<a href="#"><img src="${resourceRoot}/images/m3.jpg" width="83" height="41" alt="M3"/></a>
				<a href="#"><img src="${resourceRoot}/images/m4.jpg" width="72" height="41" alt="M4"/></a>
				<a href="#"><img src="${resourceRoot}/images/m5.jpg" width="84" height="41" alt="M5"/></a>
				<a href="#"><img src="${resourceRoot}/images/m6.jpg" width="64" height="41" alt="M6"/></a>
				<a href="#"><img src="${resourceRoot}/images/m7.jpg" width="89" height="41" alt="M7"/></a>
				<a href="#"><img src="${resourceRoot}/images/m8.jpg" width="89" height="41" alt="M8"/></a>
			</div>
		</div>
		<div id="headline">
			<div id="photo"><div>
				<img src="${resourceRoot}/images/pic_1.jpg" width="618" height="254" alt="Pic 1"/>
			</div></div>
			<div id="search-news">
				<h2>Search this site</h2>
				<form action="">
					<div><input type="text" name="q" value="" id="q" /></div>
					<div class="more"><input type="image" name="search" value="" src="${resourceRoot}/images/btn_search.gif"/></div>
				</form>
				
				<h2 class="downcast">News &amp; Events</h2>
				<div id="news-box">
					<h3><a href="#">05.12.06</a></h3>
					<p><a href="#">Lorem ipsum dolor</a> sit amet, consectetuer adipiscing elit.</p>
					<p class="more"><a href="#">more</a></p>
				  <h3><a href="#">01.12.06</a></h3>
				  <p><a href="#">Lorem ipsum dolor</a> sit amet, consectetuer adipiscing elit.</p>
				  <p class="more"><a href="#">more</a></p>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div id="body">
			<div id="body-left">
				<h2><img src="${resourceRoot}/images/h_welcome_to_our_site.gif" width="203" height="20" alt="H Welcome To Our Site" /></h2>
			<p>Don't forgot to check <a href="http://www.freewebsitetemplates.com">free website templates</a> every day, because we add at least one free website template daily.</p>
				
				<p>This is a template designed by free website templates for you for free you can replace all the text by your own 
				text. This is just a place holder so you can see how the site would look like.</p> 
				
				<p>You can remove any link to our websites from this template you're  free to use the template without linking 
				back to us. Don't want your boss to know you used a free website template ;) .</p>
				
				<p>If you're having problems editing the template please don't hesitate to ask for help on the <a href="http://www.freewebsitetemplates.com/forum/">forum</a>.</p>
				
				<p>If you're looking for beautiful and professionally made templates you can find them at <a href="http://www.templatebeauty.com">Template Beauty</a>.</p>
				<p>Even more websites all about website templates on <a href="http://www.justwebtemplates.com">Just Web Templates</a>.</p>
				<p class="more"><a href="#">more</a></p>
				<h2><img src="${resourceRoot}/images/h_needs.gif" width="150" height="20" alt="Urgent Needs"></h2>
				<img src="${resourceRoot}/images/pic_3.jpg" width="139" height="118" alt="Pic 3" class="left" />
				<p><a href="#">Lorem ipsum dolor</a> sit amet, consectetuer adipiscing elit. Mauris ut elit. Sed eu pede. Morbi quis ante a pede feugiat egestas. Etiam feugiat mi in pede. Aenean massa sem.</p>
				<ul class="plussbullets">
					<li><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing</a></li>
					<li><a href="#">Elit. Mauris ut elit. Sed eu pede. Morbi quis ante a</a></li>
					<li><a href="#">Pede feugiat egestas. Etiam feugiat mi in pede. Aenean massa sem.</a></li>
				</ul>
			</div>
			<div id="body-right">
				<h2><img src="${resourceRoot}/images/h_donations_for_us.gif" width="151" height="20" alt="Donations For Us"></h2>
				<p><img src="${resourceRoot}/images/pic_2.jpg" width="321" height="93" alt="Pic 2"></p>
				<p>If you're looking for beautiful and professionally made templates you can find them at <a href="http://www.templatebeauty.com">Template Beauty</a>.</p>
				<p>Even more websites all about website templates on <a href="http://www.justwebtemplates.com">Just Web Templates</a>.</p>
				<p class="more"><a href="#"><img src="${resourceRoot}/images/btn_click_here.gif" width="51" height="19" alt="Btn Click Here"></a></p>
				<h2><img src="${resourceRoot}/images/h_gallery.gif" width="65" height="22" alt="Gallery"></h2>
				<div id="gallery">
					<a href="#"><img src="${resourceRoot}/images/gal_1.jpg" width="101" height="96" alt="Gal 1"></a>
					<a href="#"><img src="${resourceRoot}/images/gal_2.jpg" width="114" height="96" alt="Gal 2"></a>
					<a href="#"><img src="${resourceRoot}/images/gal_3.jpg" width="112" height="96" alt="Gal 3"></a>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div id="footer">
		<p>&copy; 2006 all rights reserved.</p>
	</div>
</body>
</html>
