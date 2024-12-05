<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container mt-4">
        <div class="row align-items-start">

            <div class="col-md-8">
                <h1>${show.title}</h1>
                <p><b>Жанр:</b> ${show.ganre}</p>
                <p><b>Длительность:</b> ${show.duration} мин</p>
                <p><b>Возрастное ограничение:</b> ${show.ageRating}+</p>
                <a href="/session?filter=${show.title}&hallId=&sortSession=asc" class="btn btn-primary">Выбрать сеанс</a>
            </div>


            <div class="col-md-4">
                <#if show.filename??>
                    <img
                            src="/img/${show.filename}"
                            class="img-fluid rounded"
                            alt="${show.title}"
                            style="max-width: 100%; max-height: 300px; object-fit: cover;"
                    >
                </#if>
            </div>
        </div>


        <div class="mt-4">
            <#if show.description??>
                <p>${show.description!''}</p>
            </#if>
        </div>
    </div>
</@c.page>
