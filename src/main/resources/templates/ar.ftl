<html>
<script src="https://aframe.io/releases/0.6.1/aframe.min.js"></script>
<script src="https://jeromeetienne.github.io/AR.js/aframe/build/aframe-ar.js"></script>
<body style='margin : 0px; overflow: hidden;'>
<a-scene embedded arjs='sourceType: webcam; debugUIEnabled: false'>
    <#list models as model>
        <a-marker type="pattern" url="https://raw.githubusercontent.com/jeromeetienne/AR.js/master/three.js/examples/marker-training/examples/pattern-files/pattern-letter${model.letter}.patt"  >
            <#if model.type == "OBJ">
                <a-entity scale="${model.scale}"
                    obj-model='obj: /model/${model.getSet_id().getId()}/${model.getId()}/${model.originalModelFileName};
                    <#if model.hasMtlFile()>
                    mtl: /model/${model.getSet_id().getId()}/${model.getId()}/${model.originalMtlFileName}
                    </#if>'>
                </a-entity>
            </#if>
            <#if model.type == "DAE">
                <a-entity scale='${model.scale}'
                          collada-model='url(/model/${model.getSet_id().getId()}/${model.getId()}/${model.originalModelFileName})'>
                </a-entity>
            </#if>
            <#if model.type == "glTF">
                <a-entity scale='${model.scale}'
                          gltf-model='url(/model/${model.getSet_id().getId()}/${model.getId()}/${model.originalModelFileName})'>
                </a-entity>
            </#if>
            <#if model.type == "glTF 2.0">
                <a-entity scale='${model.scale}'
                          gltf-model-next='src: url(/model/${model.getSet_id().getId()}/${model.getId()}/${model.originalModelFileName});'>
                </a-entity>
            </#if>
            <#if model.type == "PLY">
                <a-entity scale='${model.scale}'
                          ply-model='src: url(/model/${model.getSet_id().getId()}/${model.getId()}/${model.originalModelFileName});'>
                </a-entity>
            </#if>
            <#if model.type == "JSON">
                <a-entity scale='${model.scale}'
                          json-model='src: url(/model/${model.getSet_id().getId()}/${model.getId()}/${model.originalModelFileName});'>
                </a-entity>
            </#if>
            <#if model.type == "FBX">
                <a-entity scale='${model.scale}'
                          fbx-model='src: url(/model/${model.getSet_id().getId()}/${model.getId()}/${model.originalModelFileName});'>
                </a-entity>
            </#if>
        </a-marker>
    </#list>
    <a-entity camera></a-entity>
</a-scene>
</body>
</html>