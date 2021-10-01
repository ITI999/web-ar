<#import "parts/common.ftl" as c>

<@c.page>
<table class="table table-striped" >
    <thead class="thead-dark">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Edit models</th>
            <th>Set in AR</th>
            <th>Delete set</th>
        </tr>
    </thead>

    <#list sets as set>
        <tr>
            <td>
                ${set.title}
            </td>
            <td>
                ${set.description}
            </td>
            <td>
                <a href="/set/${set.id}">Edit</a>
            </td>
            <td>
                <a href="/ar/${set.id}">Go to AR</a>
            </td>
            <td>
                <a href="/models/delete/${set.id}">Delete</a>
            </td>
        </tr>
    </#list>
</table>
<div>
    Add model
</div>
<form method="post">
    <div><label> title : <input type="text" name="title"/> </label></div>
    <div><label> description : <input type="text" name="description"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="ADD"/></div>
</form>
</@c.page>