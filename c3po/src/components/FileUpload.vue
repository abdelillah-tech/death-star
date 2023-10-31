<template>
    <label class="file-upload">
        <span v-if="!selectedFile">Choose a file</span>
        <span v-else>{{ selectedFile.name }}</span>
        <input ref="file" v-on:change="handleFileUpload" class="input-file" type="file">
    </label>
</template>
<script>

import { ref } from "vue"

export default{
    name:'FileUpload',
    setup(props, {emit}) {
        const selectedFile = ref(null);

        const handleFileUpload = async (event) => {
            selectedFile.value = event.target.files[0];

            const formData = new FormData();
            if(selectedFile.value) {
                formData.append('file', selectedFile.value, "empire");
                await fetchData(formData);
            } else emit('result-fetched', null);
        }

        const fetchData = (formData) => fetch('http://localhost:8080/give-me-the-odds', {
            method: 'POST',
            body: formData,
        })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    return response.text().then(err => Promise.reject(err));
                }
            })
            .then(data => {
                emit('result-fetched', `${data}%`);
            })
            .catch(error => {
                emit('result-fetched', error);
            });

        return {
            handleFileUpload,
            selectedFile,
            odds: null
        }
    }
}
</script>

<style scoped>
.file-upload {
    background-color: #91f7c0;
    color: black;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

.input-file {
    display: none;
}

.file-upload:hover {
    background-color: #163a30;
    color: white;
}
</style>
