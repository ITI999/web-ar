<#import "parts/common.ftl" as c>

<@c.page>
<h5>Choose set of models</h5>
<table class="table table-striped">
    <thead class="thead-dark">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>AR</th>
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
                <a href="/ar/${set.id}">Go to AR</a>
            </td>
        </tr>
    </#list>
</table>
</@c.page>