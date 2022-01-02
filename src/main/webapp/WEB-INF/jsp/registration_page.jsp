<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.volodko.controller.constant.CommandName" %>
<%@ page import="com.epam.volodko.controller.constant.ParameterName" %>
<%@ page import="com.epam.volodko.controller.constant.Message" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="localization.locale" var="loc"/>
    <fmt:message bundle="${loc}"  key="default.title" var="title"/>
    <fmt:message bundle="${loc}"  key="registration.login.note" var="login_note"/>
    <fmt:message bundle="${loc}"  key="registration.login.placeholder" var="login_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.password.note" var="pass_note"/>
    <fmt:message bundle="${loc}"  key="registration.password.placeholder" var="pass_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.pass_restrict.note" var="pass_restrict_note"/>
    <fmt:message bundle="${loc}"  key="registration.repeat_password.note" var="repeat_pass_note"/>
    <fmt:message bundle="${loc}"  key="registration.repeat_password.placeholder" var="repeat_pass_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.name.note" var="name_note"/>
    <fmt:message bundle="${loc}"  key="registration.name.placeholder" var="name_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.phone.note" var="phone_note"/>
    <fmt:message bundle="${loc}"  key="registration.phone.placeholder" var="phone_placeholder"/>
    <fmt:message bundle="${loc}"  key="registration.register.button" var="register_button"/>
    <fmt:message bundle="${loc}"  key="registration.to_initial_page.button" var="to_initial_page_button"/>
    <fmt:message bundle="${loc}"  key="registration.pass_restrict.message" var="pass_restrict_mess"/>
    <fmt:message bundle="${loc}"  key="registration.failed.message" var="registration_failed_mess"/>
    <fmt:message bundle="${loc}"  key="registration.exception.message" var="registration_exception_mess"/>

    <title>${title}</title>
</head>
<body>
<br/><br/>

<div class="container">
    <div class="text-center">
        <form action="Controller" method="post">
            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.REGISTRATION}">
            <p>${login_note}
            <input type="text" name="${ParameterName.USER_LOGIN}" placeholder="${login_placeholder}">
            </p>
            <p>${pass_note}
            <input type="password" name="${ParameterName.USER_PASSWORD}" placeholder="${pass_placeholder}">
            </p>
            <p class="help-footnote"> <c:out value="${pass_restrict_note}"/> </p>
            <br/><br/>
            <p>${repeat_pass_note}
            <input type="password" name="${ParameterName.USER_REPEAT_PASSWORD}" placeholder="${repeat_pass_placeholder}">
            </p>
            <p>${name_note}
            <input type="text" name="${ParameterName.USER_NAME}" placeholder="${name_placeholder}">
            </p>
            <p>${phone_note}
            <input type="tel" name="${ParameterName.USER_PHONE}" placeholder="${phone_placeholder}">
            </p>

            Role<br/>
            <select name="user_role" size="1" >
                <option disabled>Choose your role</option>
                <option value="admin">admin</option>
                <option value="client">client</option>
                <option value="driver">driver</option>
            </select>
            <br/><br/>

            <input type="submit" class="btn btn-success" value="${register_button}">
        </form>

        <p class="text-danger">
            <c:set var="errorMessage" scope="page" value="${requestScope.get(ParameterName.ERROR_MESSAGE)}"/>
            <c:if test="${not empty errorMessage}">
                <c:if test="${errorMessage eq Message.PASSWORD_RESTRICTION_WARN}">
                    <c:out value="${pass_restrict_mess}"/>
                </c:if>
                <c:if test="${errorMessage eq Message.REGISTRATION_FAILED}">
                    <c:out value="${registration_failed_mess}"/>
                </c:if>
                <c:if test="${errorMessage eq Message.REGISTRATION_EXCEPTION}">
                    <c:out value="${registration_exception_mess}"/>
                </c:if>
            </c:if>
        </p>


        <form action="Controller" method="get">
            <input type="hidden" name="${CommandName.COMMAND}" value="${CommandName.GO_TO_INITIAL_PAGE}">
            <input type="submit" class="btn btn-info" value="${to_initial_page_button}">
        </form>
    </div>
</div>
</body>
</html>
