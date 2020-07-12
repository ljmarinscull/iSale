<template>
    <div class="h-screen text-center bg-gray-200 sm:mx-6 xl:mx-24 flex flex-col justify-center items-center mb-8">
        <h1 class="text-6xl font-bold text-black">404 Error</h1>
        <p class="text-2xl text-gray-600">The resource could not be found.</p>
        <router-link class=".underline text-blue-400" to="/home" exact>
            Go to Home page
        </router-link>
    </div>
</template>

<script>
export default {
    name:'error'
}
</script>