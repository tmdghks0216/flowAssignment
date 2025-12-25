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
            <div class="list-wrapper"> <div class="chipBox" id="chipListContainer">
                <div class="chips" id="chipList">
                    <c:forEach var="customExt" items="${customList}">
                        <div class="chip" data-ext="${customExt.extension}">
                            <span>${customExt.extension}</span>
                            <button type="button" class="delete-btn">X</button>
                        </div>
                    </c:forEach>
                </div>
            </div>
                <div class="counterBottom">
                    <span id="currentCount">${fn:length(customList)}</span>/200
                </div>
            </div>
        </div>

    </div>
</div>

</body>
<script type="text/javascript">
    $(document).ready(function() {
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": true,
            "positionClass": "toast-top-center",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "100",
            "hideDuration": "1000",
            "timeOut": "1500",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }

        // 고정 확장자 체크박스 클릭 이벤트
        $('input[name="fixExt"]').on('change', function() {

            const extension = $(this).val();
            const isChecked = $(this).is(':checked');

            const extensionData = {
                extension : extension,
                enable : isChecked ? 'Y' : 'N',
                type: 'FIX'
            }

            $.ajax({
                url: '/update/fixExtension',
                contentType: 'application/json',
                data: JSON.stringify(extensionData),
                type : "post",
                success: function(res) {
                    if (res.code > 0) {
                        console.log("업데이트 되었습니다.");
                        toastr.success('업데이트 되었습니다.');
                    } else {
                        alert("업데이트에 실패했습니다.");
                        location.reload();
                    }
                },
                error: function(xhr) {
                    alert("서버 통신 중 오류가 발생했습니다.");
                    location.reload();
                }
            });
        });

        //커스텀확장자 등록
        $("#addBtn").click(function (){
            const customExt = $("#extInput").val().toLowerCase().trim();
            const isFixed = $('input[name="fixExt"]').get().some(el => el.value.toLowerCase() === customExt);

            //공백 체크
            if (!customExt) {
                toastr.warning("확장자를 입력해주세요.");
                return;
            }

            const regex = /^[a-z0-9]+$/;

            if (!regex.test(customExt)) {
                toastr.warning("확장자는 영문과 숫자만 입력할 수 있습니다.");
                $('#extInput').val('').focus();
                return;
            }

            //고정확장자 데이터 중복체크
            if(isFixed){
                toastr.warning("고정 확장자는 등록할 수 없습니다.");
                return;
            }

            const extensionData = {
                extension : customExt,
                enable : 'Y',
                type: 'CUSTOM'
            }


            $.ajax({
                url: '/insert/customExtension',
                contentType: 'application/json',
                data: JSON.stringify(extensionData),
                type : "post",
                success: function(res) {
                    if (res.code === 10) {
                        toastr.warning(res.msg);
                    }else if(res.code === 11){
                        toastr.warning(res.msg);
                    }else {
                        addChip(customExt);
                        $('#extInput').val('').focus();

                        const $count = $('#currentCount');
                        $count.text(parseInt($count.text()) + 1);

                        toastr.info(res.msg);
                    }
                },
                error: function(xhr) {
                    alert("서버 통신 중 오류가 발생했습니다.");
                    location.reload();
                }
            });
        });

        //커스텀확장자 삭제
        $('#chipList').on('click', '.delete-btn', function() {
            const $chip = $(this).closest('.chip');
            const customExt = $chip.data('ext');

            console.log(customExt);

            const extensionData = {
                extension : customExt,
                type: 'CUSTOM'
            }


            $.ajax({
                url: '/delete/customExtension',
                contentType: 'application/json',
                data: JSON.stringify(extensionData),
                type : "delete",
                success: function(res) {
                    $chip.remove();

                    const $count = $('#currentCount');
                    const newCount = parseInt($count.text()) - 1;
                    $count.text(newCount >= 0 ? newCount : 0);

                    toastr.info("삭제되었습니다.");
                },
                error: function(xhr) {
                    alert("서버 통신 중 오류가 발생했습니다.");
                    location.reload();
                }
            });
        });
    });

    function addChip(customExt) {
        const chipHtml =
            '<div class="chip" data-ext="' + customExt + '">' +
            '<span>' + customExt + '</span>' +
            '<button type="button" class="delete-btn"">X</button>' +
            '</div>';

        $('#chipList').prepend(chipHtml);
        $('#chipListContainer').scrollTop(0);
    }
</script>
</html>