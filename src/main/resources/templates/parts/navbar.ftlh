<#include "security.ftlh">
<#import "login.ftlh" as l>

<style>
    nav.navbar .dropdown:hover > .dropdown-menu {
        display: block;
    }
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/main"><span class="material-symbols-outlined">
theater_comedy
</span></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/show">Представления</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/hall">Залы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/session">Сеансы</a>
            </li>
            <#if isAdmin>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Admin
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/user">Users</a>
                        <a class="dropdown-item" href="/session/admin">Sessions</a>
                        <a class="dropdown-item" href="/show/showAdminList">Shows</a>
                    </div>
                </li>
            </#if>

        </ul>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/profile">${name}</a>
            </li>
            <li class="nav-item">
                <@l.logout />
            </li>
        </ul>
    </div>
</nav>

