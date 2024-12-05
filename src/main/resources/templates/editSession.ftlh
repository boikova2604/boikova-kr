<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container my-4">
        <div class="card">
            <div class="card-header">
                <h5>Редактировать сеанс: ${session.id}</h5>
            </div>
            <div class="card-body">
                <#if error??>
                    <div class="alert alert-danger">
                        ${error}
                    </div>
                </#if>
                <form action="/session/edit" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="hidden" name="sessionId" value="${session.id?c}">


                    <div class="mb-3">
                        <label for="price" class="form-label">Цена билета</label>
                        <input type="number" step="0.01" class="form-control" id="price" name="price" value="${session.price}" required>
                    </div>


                    <div class="mb-3">
                        <label for="date" class="form-label">Дата и время сеанса</label>
                        <input type="datetime-local" class="form-control" id="date" name="date" value="${session.date?string("yyyy-MM-dd'T'HH:mm")}" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                    <a href="/session/admin" class="btn btn-secondary ms-2">Отмена</a>
                </form>
            </div>
        </div>
    </div>
</@c.page>
