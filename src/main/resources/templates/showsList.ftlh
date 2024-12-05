<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container my-4">
        <h2 class="mb-4">Список Шоу</h2>
        <div class="form-row mb-4">
            <div class="form-group col-md-6">
                <form method="get" action="/show" class="form-inline mb-4">
                    <input type="text" name="filter" class="form-control mr-2" value="${filter!""}" placeholder="Поиск по названию">

                    <select name="ganreId" class="form-control mr-2">
                        <option value="">Все жанры</option>
                        <#list ganres as ganre>
                            <option value="${ganre.id?c}">${ganre.name}</option>
                        </#list>
                    </select>


                    <button type="submit" class="btn btn-primary">Поиск</button>
                </form>
            </div>
        </div>
        <#list shows as show>
            <div class="card mb-4 shadow-sm">
                <div class="row g-0">
                    <!-- Изображение шоу с закругленными углами -->
                    <div class="col-md-4">
                        <#if show.filename?? && show.filename?trim != "">
                            <img src="/img/${show.filename}" class="img-fluid rounded-start p-2" style="height: 100%; object-fit: cover;" alt="${show.title}">
                        <#else>
                            <img src="/img/placeholder.jpg" class="img-fluid rounded-start p-2" style="height: 100%; object-fit: cover;" alt="Изображение отсутствует">
                        </#if>
                    </div>
                    <!-- Содержимое карточки -->
                    <div class="col-md-8">
                        <div class="card-body d-flex flex-column h-100">
                            <a href="/shows/${show.id?c}" class="text-decoration-none text-dark">
                                <h5 class="card-title">${show.title}</h5>
                            </a>

                            <p class="card-text"><strong>Жанр:</strong> ${show.ganre}</p>
                            <p class="card-text"><strong>Продолжительность:</strong> ${show.duration} мин</p>
                            <p class="card-text"><strong>Возрастное ограничение:</strong> ${show.ageRating}+</p>
                            <#if show.description?? && show.description?trim != "">
                                <p class="card-text"><strong>Описание:</strong> ${show.description}</p>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <#if shows?size == 0>
            <div class="alert alert-info" role="alert">
                Нет доступных шоу.
            </div>
        </#if>
    </div>
</@c.page>
