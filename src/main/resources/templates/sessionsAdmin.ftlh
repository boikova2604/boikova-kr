<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container my-4">

        <div class="card mb-4">
            <div class="card-header">
                <h5>Добавить новый сеанс</h5>
            </div>
            <div class="card-body">
                <form action="/session/addSession" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />

                    <div class="mb-3">
                        <label for="showId" class="form-label">Выберите шоу</label>
                        <select class="form-select" id="showId" name="showId" required>
                            <option value="" selected disabled>-- Выберите шоу --</option>
                            <#list shows as show>
                                <option value="${show.id?c}">${show.title}</option>
                            </#list>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="hallId" class="form-label">Выберите зал</label>
                        <select class="form-select" id="hallId" name="hallId" required>
                            <option value="" selected disabled>-- Выберите зал --</option>
                            <#list halls as hall>
                                <option value="${hall.id?c}">${hall.name} </option>
                            </#list>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="date" class="form-label">Дата и время сеанса</label>
                        <input type="datetime-local" class="form-control" id="date" name="date" required>
                    </div>

                    <div class="mb-3">
                        <label for="price" class="form-label">Цена билета</label>
                        <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Добавить сеанс</button>
                </form>
            </div>
        </div>



        <h5>Список сеансов</h5>
        <#if sessions?has_content>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Шоу</th>
                    <th>Зал</th>
                    <th>Дата и время</th>
                    <th>Цена</th>
                    <th>Действия</th>
                </tr>
                </thead>
                <tbody>
                <#list sessions as session>
                    <tr>
                        <td>${session.id}</td>
                        <td>${session.show.title}</td>
                        <td>Зал ${session.hall.name} </td>
                        <td>${session.date?string("dd.MM.yyyy HH:mm")}</td>
                        <td>${session.price} руб.</td>
                        <td>
                            <a href="/session/edit?sessionId=${session.id?c}" class="btn btn-warning btn-sm me-2">Редактировать</a>
                            <form action="/session/delete" method="post" style="display:inline;">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input type="hidden" name="sessionId" value="${session.id?c}">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Вы уверены, что хотите удалить этот сеанс?');">Удалить</button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        <#else>
            <p>Сеансы не найдены.</p>
        </#if>
    </div>
</@c.page>
