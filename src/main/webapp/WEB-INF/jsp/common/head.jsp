<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/jstlcore.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta property="og:url" content="https://geekily-archive.com">
    <meta property="og:title" content="Geekily Archive">
    <meta property="og:type" content="website">
    <meta property="og:image" content="https://geekily-archive.com/storage/image/common/meta_logo.png">
    <meta property="og:description" content="The simplest blog.">
    
	<link rel="icon" href="<c:url value="/image/favicon.ico"/>">
    <link rel="apple-touch-icon" href="<c:url value="/image/favicon.ico"/>">
	
	<title>${sessionScope.userMap.archiveLogo}</title>
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/jqueryui/jquery-ui.min.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap/bootstrap.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrapIcon/font/bootstrap-icons.min.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/jstree/style.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/pixelarity/pixelarity.css"/>"/>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR&family=Single+Day&display=swap">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>"/>
	
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jquery/jquery-3.7.1.min.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jqueryui/jquery-ui.min.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/bootstrap/bootstrap.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jstree/jstree.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/tinymce/tinymce.min.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/pixelarity/pixelarity-faceless.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/chartjs/chart.umd.min.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/tool.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/option.js"/>"></script>
	<script type="text/javascript" charset="utf-8" src="<c:url value="/js/responsiveWebController.js"/>"></script>
</head>