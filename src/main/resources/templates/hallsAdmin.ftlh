<#import "parts/common.ftlh" as c>
<@c.page>
    <h1>Manage Halls</h1>
    <div>
        <h2>Add a New Hall</h2>
        <form action="/hall/addHall" method="post">
            <input type="text" name="hallName" placeholder="Enter Hall Name" required />
            <input type="number" name="rows" placeholder="Enter rows Capacity" required />
            <input type="number" name="seatsPerRow" placeholder="Enter seats per rows Capacity" required />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add Hall</button>
        </form>
    </div>
    <div>
        <h2>Halls List</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Seat Capacity</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list halls as hall>
                <tr>
                    <td>${hall.id}</td>
                    <td>${hall.name}</td>
                    <td>${hall.seatCapacity}</td>
                    <td>
                        <form action="/hall/delete" method="post" style="display:inline;">
                            <input type="hidden" name="hallId" value="${hall.id?c}" />
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
