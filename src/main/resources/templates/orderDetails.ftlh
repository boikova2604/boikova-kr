<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container mt-5">
        <h2>Детали заказа</h2>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Сеанс: ${order.session.show.title}</h5>
                <p><strong>Стоимость:</strong> ${order.cost} ₽</p>
                <p><strong>Места:</strong>
                    <#list order.seats as seat>
                        Ряд ${seat.row}, Место ${seat.place}<#if seat_has_next>, </#if>
                    </#list>
                </p>
                <p><strong>Статус покупки:</strong> ${order.purchase?string("Подтверждено", "В ожидании")}</p>
                <a href="/profile" class="btn btn-secondary">Назад</a>
            </div>
        </div>
    </div>
</@c.page>
