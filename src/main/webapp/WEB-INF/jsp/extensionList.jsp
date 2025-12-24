<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>파일 확장자 차단</title>

    <link rel="stylesheet" href="/css/extensionList.css" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
</head>

<body>
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<div class="page">
    <div class="title">파일 확장자 차단</div>
    <p class="desc">파일확장자에 따라 특정 형식의 파일을 첨부하거나 전송하지 못하도록 제한</p>

    <div class="section">
        <div class="row">
            <div class="label">고정 확장자</div>
            <div class="inline" id="fixedArea">
                <c:forEach var="fixExt" items="${fixList}">
                    <label class="chk">
                        <input type="checkbox" name="fixExt" value="${fixExt.extension}"
                            ${fixExt.enable == 'Y' ? 'checked' : ''}>
                            ${fixExt.extension}
                    </label>
                </c:forEach>
            </div>
        </div>

        <div class="row">
            <div class="label">커스텀 확장자</div>

            <div class="customGrid">
                <input id="extInput" type="text" placeholder="확장자 입력" maxlength="20" />
                <button class="btn" id="addBtn" type="button">+추가</button>
            </div>
        </div>

        <div class="row row-top">
            <div class="label"></div>

            <div class="customGrid">
                <div class="chipBox">
                    <div class="counterInBox" id="counter">
                        <span id="currentCount">${fn:length(customList)}</span>/200
                    </div>
                    <div class="chips" id="chipList">
                        <c:forEach var="customExt" items="${customList}">
                            <div class="chip" data-ext="${customExt.extension}">
                                <span>${customExt.extension}</span>
                                <button type="button" class="delete-btn">X</button>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

</body>

</html>