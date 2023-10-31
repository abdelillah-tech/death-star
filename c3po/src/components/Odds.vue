<template>
    <div class="odds">
        <h1>{{ !!msg ? msg : "No result"}}</h1>
    </div>
</template>

<script>
export default {
    name: 'OddsDisplay',
    props: {
        msg: String
    }
}
</script>

<style scoped>
h1 {
    font-size: 150px;
    color: #163a30;
}
</style>
