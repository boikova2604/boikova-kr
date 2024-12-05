<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container mt-5">
        <h2>Профиль пользователя</h2>


        <div class="card mt-4">
            <div class="card-body">
                <h5 class="card-title">Привет, ${user.username}!</h5>
                <p><strong>Аккаунт активен:</strong> ${user.active?then("Да", "Нет")}</p>
                <p><strong>Роли:</strong>
                    <#list user.roles as role>${role}<#if role_has_next>, </#if></#list>
                </p>
            </div>
        </div>

        <div class="card mt-4">
            <div class="card-body">
                <h5 class="card-title">Ваши заказы</h5>
                <#if orders??>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Сеанс</th>
                            <th>Стоимость</th>
                            <th>Действия</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orders as order>
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.session.show.title}</td>
                                <td>${order.cost} ₽</td>
                                <td>
                                    <a href="/profile/orders/${order.id?c}" class="btn btn-info btn-sm">Детали</a>
                                    <form action="/profile/orders/${order.id?c}/cancel" method="post" style="display:inline;">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                        <button type="submit" class="btn btn-danger btn-sm">Отменить</button>
                                    </form>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                <#else>
                    <p>У вас пока нет заказов.</p>
                </#if>
            </div>
        </div>
    </div>
</@c.page>
