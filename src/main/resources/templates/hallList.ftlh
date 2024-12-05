<#import "parts/common.ftlh" as c>
<@c.page>
    <div>Список залов:</div>
    <div class="card">
        <#list halls as hall>
            <div class="card-body d-flex align-items-center justify-content-between">
                <div>
                    <h5 class="card-title">${hall.name} зал</h5>
                    <p><strong>Вместительность:</strong> ${hall.seatCapacity} мест</p>
                </div>
                <a href="/session?filter=&hallId=${hall.id?c}" class="btn btn-primary">Сеансы в зале</a>
            </div>
        </#list>
    </div>
</@c.page>
