<#macro login path isRegisterForm>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card p-4" style="width: 400px;">

            <#if errorMessage??>
                <div class="alert alert-danger" role="alert">
                    ${errorMessage}
                </div>
            </#if>

            <#if successMessage??>
                <div class="alert alert-success" role="alert">
                    ${successMessage}
                </div>
            </#if>

            <form action="${path}" method="post">
                <div class="form-group row">
                    <label for="username" class="col-sm-3 col-form-label">Логин:</label>
                    <div class="col-sm-9">
                        <input type="text" id="username" name="username" class="form-control" placeholder="Логин" required/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-3 col-form-label">Пароль:</label>
                    <div class="col-sm-9">
                        <input type="password" id="password" name="password" class="form-control" placeholder="Введите пароль" required/>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <#if !isRegisterForm>
                        <a href="/registration">Новый пользователь</a>
                    </#if>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary">Отправить</button>
                </div>
            </form>
        </div>
    </div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-outline-dark border-0" ><span class="material-symbols-outlined">
logout
</span></button>
    </form>
</#macro>