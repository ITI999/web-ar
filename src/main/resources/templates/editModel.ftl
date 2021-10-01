<#import "parts/common.ftl" as c>

<@c.page>
<form method="post" >
    <p>
        Marker:
        <select name="letter" >
            <option <#if model.letter=='A'>selected</#if> >A</option>
            <option <#if model.letter=='B'>selected</#if> >B</option>
            <option <#if model.letter=='C'>selected</#if> >C</option>
            <option <#if model.letter=='D'>selected</#if> >D</option>
            <option <#if model.letter=='F'>selected</#if> >F</option>
        </select>
    </p>
    <p>
        Scale (example: 0.01 0.01 0.01):
        <input type="text" name="scale" value="${model.scale}">
    </p>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Save</button>
</form>
</@c.page>