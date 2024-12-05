<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container my-4">


        <div class="card mb-4">
            <div class="card-header">
                <h5>Добавить новое шоу</h5>
            </div>
            <div class="card-body">
                <form action="/show/addShow" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="mb-3">
                        <label for="title" class="form-label">Название</label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>
                    <div class="mb-3">
                        <label for="duration" class="form-label">Продолжительность (мин)</label>
                        <input type="number" class="form-control" id="duration" name="duration" required>
                    </div>
                    <div class="mb-3">
                        <label for="ageRating" class="form-label">Возрастное ограничение (+)</label>
                        <input type="number" class="form-control" id="ageRating" name="ageRating" required>
                    </div>
                    <div class="mb-3">
                        <label for="ganreId" class="form-label">Жанр</label>
                        <select class="form-select" id="ganreId" name="ganreId" required>
                            <#list ganres as ganre>
                                <option value="${ganre.id?c}">${ganre.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Описание</label>
                        <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="file" class="form-label">Файл</label>
                        <input class="form-control" type="file" id="file" name="file">
                    </div>
                    <button type="submit" class="btn btn-primary">Добавить шоу</button>
                </form>
            </div>
        </div>


        <#list shows as show>
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">Название: ${show.title}</h5>
                    <p><strong>Id:</strong> ${show.id} </p>
                    <p><strong>Жанр:</strong> ${show.ganre} </p>
                    <p><strong>Продолжительность:</strong> ${show.duration} мин</p>
                    <p><strong>Ограничение: </strong> ${show.ageRating}+ </p>
                    <#if show.filename??>
                        <p><strong>Файл:</strong> ${show.filename} </p>
                    </#if>
                    <#if show.description??>
                        <p><strong>Описание:</strong> ${show.description} </p>
                    </#if>
                    <div class="mt-3">
                        <a href="/show/editShow?showId=${show.id?c}" class="btn btn-warning me-2">Редактировать</a>
                        <form action="/show/deleteShow" method="post" style="display:inline;">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="hidden" name="showId" value="${show.id?c}">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Вы уверены, что хотите удалить это шоу?');">Удалить</button>
                        </form>
                    </div>
                </div>
            </div>
        </#list>

    </div>
</@c.page>
