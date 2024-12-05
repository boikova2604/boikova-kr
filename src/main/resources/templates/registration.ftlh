<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card p-4" style="width: 500px;">
            <h2 class="card-title text-center">Новый пользователь</h2>


            <#if message??>
                <div class="alert alert-danger" role="alert">
                    ${message}
                </div>
            </#if>

            <@l.login "/registration" true/>


            <div class="form-group text-center">
                <a href="/login">Уже есть аккаунт? Войти</a>
            </div>
        </div>
    </div>
</@c.page>
