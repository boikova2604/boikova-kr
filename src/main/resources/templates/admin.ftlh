<#import "parts/common.ftlh" as c>
<@c.page>
    <div>
        <button onclick="location.href='/hall/admin'">Manage Halls</button>
        <button onclick="location.href='/session/admin'">Manage Sessions</button>
    </div>
    <div>
        <h1>Admin Panel</h1>


        <div>
            <h2>Add a New Show</h2>
            <form action="/admin/addShow" method="post" enctype="multipart/form-data">
                <input type="text" name="title" placeholder="Enter title" required />
                <input type="text" name="duration" placeholder="Enter duration" required />
                <input type="number" name="ageRating" placeholder="Enter age rating" required />
                <textarea name="description" placeholder="Описание"></textarea>
                <input type="file" name="file">
                <select name="ganreId">
                    <#list ganres as ganre>
                        <option value="${ganre.id?c}">${ganre.name}</option>
                    </#list>
                </select>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit">Add Show</button>
            </form>
        </div>
        <div>Список спектаклей:</div>
        <#list shows as show>
            <div>
                <b>${show.id}</b> - <b>${show.title}</b> - <b>${show.ganre}</b> - <b>${show.duration}</b> -
                <#if show.description??>
                <b>${show.description}</b>
                </#if>
                <form action="/admin/deleteShow" method="post" style="display:inline;">
                    <input type="hidden" name="showId" value="${show.id?c}" />
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button type="submit">Delete</button>
                </form>
            </div>
        <#else>
            <div>No shows available</div>
        </#list>

        <div>
            <h2>Add a New Genre</h2>
            <form action="/admin/addGanre" method="post">
                <input type="text" name="ganreName" placeholder="Enter genre name" required />
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit">Add Genre</button>
            </form>
        </div>

        <div>Список жанров:</div>
        <#list ganres as ganre>
            <div>
                <b>${ganre.id?c}</b>
                <b>${ganre.name}</b>
            </div>
        <#else>
            <div>No genres available</div>
        </#list>

        <div>
            <h2>Manage Users</h2>
            <a href="/user">Go to User Management</a>
        </div>
    </div>
</@c.page>
