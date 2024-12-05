<#import "parts/common.ftlh" as c>
<@c.page>


        <div class="form-row mb-4">
            <div class="form-group col-md-6">
                <form method="get" action="/session" class="form-inline mb-4">
                    <input type="text" name="filter" class="form-control mr-2 rounded-5" value="${filter!""}" placeholder="Поиск по названию представления">
                    <select name="hallId" class="form-control mr-2">
                        <option value="">Все залы</option>
                        <#list halls as hall>
                            <option value="${hall.id?c}">${hall.name}</option>
                        </#list>
                    </select>
                    <select name="sortSession" class="form-control mr-2">
                        <option value="asc" <#if sortSession == "asc">selected</#if>>По дате</option>
                        <option value="mny" <#if sortSession == "mny">selected</#if>>По цене</option>
                    </select>
                    <button type="submit" class="btn btn-primary rounded-circle" style="width: 40px; height: 40px; padding: 0; display: flex; align-items: center; justify-content: center;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                        </svg></button>
                </form>
            </div>
        </div>


        <#list sessions as session>
            <div class="card mb-4 shadow-sm">
                <div class="row g-0">

                    <div class="col-md-4">
                        <#if session.show.filename??>
                            <img src="/img/${session.show.filename}" class="img-fluid rounded-start-5 p-2" style="height: 100%; object-fit: cover;" alt="${session.show.title}">
                        <#else>
                            <img src="/img/placeholder.jpg" class="img-fluid rounded-start-5 p-2" style="height: 100%; object-fit: cover;" alt="${session.show.title}">
                        </#if>
                    </div>

                    <div class="col-md-8">
                        <div class="card-body d-flex flex-column h-100">
                            <a href="/shows/${session.show.id?c}" class="text-decoration-none text-dark">
                                <h5 class="card-title">${session.show.title}</h5>
                            </a>
                            <p class="card-text"><strong>Зал:</strong> ${session.hall.name}</p>
                            <p class="card-text">
                                <strong>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-alarm me-2" viewBox="0 0 16 16">
                                        <path d="M8.5 5.5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5z"/>
                                        <path d="M6.5 0a.5.5 0 0 0 0 1H7v1.07a7.001 7.001 0 0 0-3.273 12.474l-.602.602a.5.5 0 0 0 .707.708l.746-.746A6.97 6.97 0 0 0 8 16a6.97 6.97 0 0 0 3.422-.892l.746.746a.5.5 0 0 0 .707-.708l-.601-.602A7.001 7.001 0 0 0 9 2.07V1h.5a.5.5 0 0 0 0-1h-3zm1.038 3.018a6.093 6.093 0 0 1 .924 0 6 6 0 1 1-.924 0zM0 3.5c0 .753.333 1.429.86 1.887A8.035 8.035 0 0 1 4.387 1.86 2.5 2.5 0 0 0 0 3.5zM13.5 1c-.753 0-1.429.333-1.887.86a8.035 8.035 0 0 1 3.527 3.527A2.5 2.5 0 0 0 13.5 1z"/>
                                    </svg>
                                </strong> ${session.date}
                            </p>
                            <p class="card-text">
                                <strong>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-wallet2 me-2" viewBox="0 0 16 16">
                                        <path d="M12.136.326A1.5 1.5 0 0 1 14 1.78V3h.5A1.5 1.5 0 0 1 16 4.5v9a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 13.5v-9a1.5 1.5 0 0 1 1.432-1.499L12.136.326zM5.562 3H13V1.78a.5.5 0 0 0-.621-.484L5.562 3zM1.5 4a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-13z"/>
                                    </svg>
                                </strong> ${session.price} ₽
                            </p>
                            <div class="mt-auto">
                                <a href="/order/${session.id?c}" class="btn btn-success w-100">Выбрать место</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    <#if sessions?size == 0>
        <div class="alert alert-info" role="alert">
            Нет доступных шоу.
        </div>
    </#if>
    </div>
</@c.page>
