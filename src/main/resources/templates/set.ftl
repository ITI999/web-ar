<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

<table class="table table-striped">
    <thead class="thead-dark">
        <tr>
            <th scope="col">.ojb</th>
            <th scope="col">.mtl</th>
            <th scope="col">Letter</th>
            <th scope="col">Scale</th>
            <th scope="col">Type</th>
            <th scope="col">Edit model</th>
            <th scope="col">Delete model</th>
        </tr>
    </thead>

    <#list models as model>
        <tr>
            <td>
                ${model.originalModelFileName}
            </td>
            <td>
                <#if model.hasMtlFile()>
                    ${model.originalMtlFileName}
                </#if>
            </td>
            <td>
                ${model.letter}
            </td>
            <td>
                ${model.scale}
            </td>
            <td>
                ${model.type}
            </td>
            <td>
                <a href="/set/edit/${model.id}">Edit</a>
            </td>
            <td>
                <a href="/set/delete/${model.id}">Delete</a>
            </td>
        </tr>
    </#list>
</table>


<form method="post" enctype="multipart/form-data">
    <p>
        Marker:
        <select name="letter">
            <option>A</option>
            <option>B</option>
            <option>C</option>
            <option>D</option>
            <option>F</option>
        </select>
    </p>
    <p>
        Scale (example: 0.01 0.01 0.01):
        <input type="text" name="scale" >
    </p>
    <p>
        Type of model:
        <select name="type">
            <option>OBJ</option>
            <option>DAE</option>
            <option>glTF</option>
            <option>glTF 2.0</option>
            <option>PLY</option>
            <option>JSON</option>
            <option>FBX</option>
        </select>
    </p>
    <p>
        Model:
        <input type="file" name="modelFile" >
    </p>
    <p>
        .mtl File(for .obj):
        <input type="file" name="mtlFile">
    </p>
    <p>
        Textures and other:
        <input id="fileInput" type="file" name="uploadingFiles" multiple>
    </p>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Добавить</button>
</form>
</@c.page>