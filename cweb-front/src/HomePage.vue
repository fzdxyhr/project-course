<template>
	<div class="outer">
		<navigation :active-index='index'></navigation>
		<layout></layout>
		<page-footer></page-footer>
	</div>
</template>

<script>
	import Layout from './components/homepage/Layout.vue'
	import Nav from './components/public/Nav.vue'
	import Footer from './components/public/Footer.vue'
	export default {
		components: {
			'layout': Layout,
			'page-footer': Footer,
			'navigation': Nav,
		},
		data: () => {
			return {
				index: "1",
			}
		}
	}
</script>

<style>
	.outer {
		min-width: 960px;
	}
</style>
