
<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/user" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!""}" placeholder="Search by username">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <#list users as user>
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">Имя: ${user.username}</h5>
                <p><strong>Id:</strong> ${user.id} </p>
                <p><strong>Роли:</strong> <#list user.roles as role>${role}<#sep>, </#list> </p>
                <p><strong>Активные билеты:</strong><#list user.orders as orders>
                <ul>
                ${orders.session.show.title}
                ${orders.session.date}
                <#list orders.seats as seat>
                        Ряд ${seat.row}, Место ${seat.place}<#if seat_has_next>, </#if>
                    </#list>
                    <form action="/user/order/${orders.id?c}/cancel" method="post" style="display:inline;">
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit" class="btn btn-danger btn-sm"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                            </svg></button>
                    </form>
                </ul>
                    </#list>
                </p>
                <div class="mt-3">
                    <a href="/user/${user.id?c}" class="btn btn-warning me-2">Редактировать</a>
                    <form action="/user/deleteUser" method="post" style="display:inline;">
                        <input type="hidden" name="userId" value="${user.id?c}" />
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit" class="btn btn-danger" onclick="return confirm('Вы уверены, что хотите удалить этого пользователя? Все приобретенные билеты будут аннулированы.');">Удалить</button>
                    </form>
                </div>
            </div>
        </div>
    </#list>



</@c.page>