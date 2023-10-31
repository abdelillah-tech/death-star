<template>
    <FileUpload @result-fetched="handleResultFetched" />
    <OddsDisplay :msg="result" />
</template>

<script>
import OddsDisplay from './components/Odds.vue'
import FileUpload from './components/FileUpload.vue'
import { ref } from "vue";


export default {
    name: 'App',
    components: {
        OddsDisplay,
        FileUpload
    },
    setup() {
        const result = ref(null);
        const handleResultFetched = (value) => result.value = value;

        return {
            result,
            handleResultFetched,
        }
    }
}
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
