<#import "parts/common.ftlh" as c>
<@c.page>
    <div class="container my-4">
        <div class="card">
            <div class="card-header">
                <h5>Редактировать шоу: ${show.title}</h5>
            </div>
            <div class="card-body">
                <form action="/show/editShow" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="hidden" name="showId" value="${show.id?c}">

                    <div class="mb-3">
                        <label for="title" class="form-label">Название</label>
                        <input type="text" class="form-control" id="title" name="title" value="${show.title}" required>
                    </div>

                    <div class="mb-3">
                        <label for="duration" class="form-label">Продолжительность (мин)</label>
                        <input type="number" class="form-control" id="duration" name="duration" value="${show.duration}" required>
                    </div>

                    <div class="mb-3">
                        <label for="ageRating" class="form-label">Возрастное ограничение</label>
                        <input type="number" class="form-control" id="ageRating" name="ageRating" value="${show.ageRating}" required>
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
                        <textarea class="form-control" id="description" name="description" rows="3" required>${show.description}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="file" class="form-label">Загрузить новый файл</label>
                        <input class="form-control" type="file" id="file" name="file">
                        <#if show.filename?? && show.filename != "">
                            <p class="mt-2">Текущий файл: <a href="/uploads/${show.filename}" target="_blank">${show.filename}</a></p>
                        </#if>
                    </div>


                    <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                    <a href="/show/showAdminList" class="btn btn-secondary ms-2">Отмена</a>
                </form>
            </div>
        </div>
    </div>
</@c.page>
